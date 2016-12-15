package com.leador.gcloud.monitor.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.leador.gcloud.monitor.json.UserJson;
import com.leador.gcloud.monitor.po.Role;
import com.leador.gcloud.monitor.po.User;
import com.leador.gcloud.monitor.service.RoleService;
import com.leador.gcloud.monitor.service.UserService;
import com.leador.gcloud.monitor.util.GCloudConstant;

@Controller
@RequestMapping("/user")
public class UserController extends PaginationController {

  private static final long serialVersionUID = -4390925804804751517L;
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private static final String REDIRECT_USERLIST_URL = "redirect:/user/userlist";
  private static final String USER_UPDATE_URL = "/user/update";
  private static final String USER_CREATE_URL = "user/create";
  private static final String SETTING_URL = "setting";
  private static final String GROUPLIST = "grouplist";

  private static final String USER = "user";

  // @InitBinder
  // protected void initBinder(WebDataBinder binder) {
  // binder.addValidators(new UserValidator());
  // }

  private List<User> userList;
  @Autowired
  private HttpServletRequest request;


  private UserService userService;
  @Autowired
  private RoleService roleService;

  public UserController() {
    super();
  }

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = {"/", "/login", "/loginprocess"}, method = RequestMethod.GET)
  public String login(Map<String, Object> model) {
    return "login";
  }


  @RequestMapping(value = "/loginprocess", method = RequestMethod.POST)
  public String processLogin(@RequestParam("username") String username,
      @RequestParam("password") String password, Map<String, Object> model) {

    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    token.setRememberMe(true);
    logger.debug("为了验证登录用户而封装的token为"
        + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
    // 获取当前的Subject
    Subject currentUser = SecurityUtils.getSubject();
    try {
      // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
      // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
      // 所以这一步在调用login(token)方法时,它会走到GCloudRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
      currentUser.login(token);
      logger.info("对用户[" + username + "]进行登录验证..验证通过");
      return DashboardController.DASH_BOARD;

    } catch (UnknownAccountException uae) {
      logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
      model.put("message_login", "未知账户");
    } catch (IncorrectCredentialsException ice) {
      logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
      model.put("message_login", "密码不正确");
    } catch (LockedAccountException lae) {
      logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
      model.put("message_login", "账户已锁定");
    } catch (ExcessiveAttemptsException eae) {
      logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
      model.put("message_login", "用户名或密码错误次数过多");
    } catch (AuthenticationException ae) {
      logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
      ae.printStackTrace();
      model.put("message_login", "用户名或密码不正确");
    }
    // 验证是否登录成功
    if (currentUser.isAuthenticated()) {
      logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
    } else {
      token.clear();
    }
    return "login";

  }

  /**
   * 用户登出
   */
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request) {
    SecurityUtils.getSubject().logout();
    return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/user/login";
  }



  @Override
  @RequestMapping("/userlist")
  protected String redirectIntoModuleHome(HttpServletRequest request, Model model) {
    return SETTING_URL;
  }

  @Override
  protected List<?> getResultList(HttpServletRequest request, Integer start, Integer pageSize) {
    Object param = request.getParameter("userName");
    String paramValue = param != null ? param.toString() : null;
    List<User> originalList = userService.findUsersByPaging(paramValue, start, pageSize);
    List<UserJson> jsonList = new ArrayList<UserJson>();
    for (User user : originalList) {
      jsonList.add(new UserJson(user.getId(), user.getName(), user.getEmail()));
    }

    return jsonList;
  }

  @Override
  protected Integer getTotalRecordCount(HttpServletRequest request) {
    Object param = request.getParameter("userName");
    String paramValue = param != null ? param.toString() : null;
    return userService.findUsersByName(paramValue).size();
  }

  @Override
  protected String[][] getListMetaInfoDefine(HttpServletRequest request) {
    String[][] meta_info =
        { {SEARCH_PARAMETER}, {"userName"}, {"id", "name", "email"},
            {ID_HEADER, resources.getString("user.name"), "Email"},
            {PK_COLUMN, LINK_COLUMN, TEXT_COLUMN}, {ID}, {getTotalRecordCount(request) + ""}};
    return meta_info;
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String prepareCreateUser(Map<String, Object> model) {
    User user = new User();
    prepareCreatePage(model, user);
    return USER_CREATE_URL;
  }

  private void prepareCreatePage(Map<String, Object> model, User user) {
    model.put(USER, user);
    model.put(TITLE, "user.field.add");
    model.put(ACTION, "/user/add");
    initRoleCheckBoxs(model, user);
  }

  private void initRoleCheckBoxs(Map<String, Object> model, User user) {
    List<Role> roleList = roleService.findRoles();
    if (user.getRoles() != null) {
      List<Long> roleIdList = new ArrayList<Long>();
      for (Role role : user.getRoles()) {
        roleIdList.add(role.getId());
      }
      user.setRoleIdList(roleIdList);
    }
    model.put(GROUPLIST, roleList);

  }

  @RequestMapping("/add")
  public String addUser(@Valid User user, BindingResult result, Map<String, Object> model,
      @RequestParam(value = "action") String action) {
    if (action.equalsIgnoreCase(resources.getString("button.cancel"))) {
      return REDIRECT_USERLIST_URL;
    }
    if (result.hasErrors()) {
      prepareCreatePage(model, user);
      return USER_CREATE_URL;
    }
    translateRoleIdListToRoleSet(user);
    String cryptedPwd = new Md5Hash(user.getPwd()).toString();
    user.setPwd(cryptedPwd);
    userService.saveUser(user);
    return "redirect:/user/" + user.getId();
  }

  /**
   * 将user中roleIdList属性的值转换为roles属性的值
   * 
   * @param user2
   */
  private void translateRoleIdListToRoleSet(User user2) {
    Set<Role> roleSet = new HashSet<Role>();
    if (user2.getRoleIdList() != null) {
      for (Long roleid : user2.getRoleIdList()) {
        roleSet.add(roleService.findRoleById(roleid));
      }
      user2.setRoles(roleSet);
    }
  }



  /**
   * @param user
   * @param result
   * @param action, used to process multi submit button
   * @return
   */
  @RequestMapping("/update")
  public String action(@Valid User user, BindingResult result,
      @RequestParam(value = "action") String action, Map<String, Object> model) {
    String resultURL = null;
    if (action.equalsIgnoreCase(resources.getString("button.delete"))) {
      userService.removeUser(user);
      resultURL = REDIRECT_USERLIST_URL;
      return resultURL;
    }
    if (action.equalsIgnoreCase(resources.getString("button.cancel"))) {
      resultURL = REDIRECT_USERLIST_URL;
      return resultURL;
    }
    if (result.hasErrors()) {
      prepareUpdate(model, user);
      return USER_CREATE_URL;
    }
    if (action.equalsIgnoreCase(resources.getString("button.submit"))) {
      translateRoleIdListToRoleSet(user);
      adjustPassword(user);
      userService.updateUser(user);
      resultURL = "redirect:/user/" + user.getId();
    }
    return resultURL;
  }

  /**
   * 如果点击更新时，勾选了“修改密码”，则用Md5来HASH新的密码，然后存储。否则将使用原始密码
   * 
   * @param user
   */
  private void adjustPassword(User user) {
    if (user.getResetPwd()) {
      user.setPwd(new Md5Hash(user.getPwd()).toString());
    } else {
      User dbUser = userService.findUserById(user.getId());
      user.setPwd(dbUser.getPwd());
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String viewDetail(@PathVariable Long id, Map<String, Object> model) {
    User user = userService.findUserById(id);
    model.put(USER, user);
    prepareUpdate(model, user);
    return USER_CREATE_URL;
  }

  private void prepareUpdate(Map<String, Object> model, User user) {
    model.put(TITLE, "user.field.update");
    model.put(ACTION, USER_UPDATE_URL);
    model.put("updateMode", true);
    model.put(
        "showResetPwd",
        SecurityUtils.getSubject().getSession().getAttribute(GCloudConstant.CURRENT_USER)
            .equals(user.getName()));
    initRoleCheckBoxs(model, user);
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

}
