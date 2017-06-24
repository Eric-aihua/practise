package com.leador.gcloud.monitor.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leador.gcloud.monitor.po.GCRight;
import com.leador.gcloud.monitor.po.Role;
import com.leador.gcloud.monitor.po.User;
import com.leador.gcloud.monitor.service.UserService;
import com.leador.gcloud.monitor.util.GCloudConstant;

/**
 * 自定义的指定Shiro验证用户登录的类
 * 
 * @create Sep 29, 2013 3:15:31 PM
 * @author aihua.sun
 */
@Component
public class GCloudRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;
  private static final Logger logger = LoggerFactory.getLogger(GCloudRealm.class);


  /**
   * 为当前登录的Subject授予角色和权限
   * 
   * @see 该方法的在需授权资源被访问时被调用 ，调用频率可以通过AuthorizationCache来控制
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
    String currentUsername = (String) super.getAvailablePrincipal(principals);
    List<String> roleList = new ArrayList<String>();
    List<String> rightList = new ArrayList<String>();
    generateAuthorizeInfo(currentUsername, roleList, rightList);
    // 为当前用户设置角色和权限
    return generateAuthorizationObject(roleList, rightList);
  }


  private SimpleAuthorizationInfo generateAuthorizationObject(List<String> roleList,
      List<String> rightList) {
    SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
    simpleAuthorInfo.addRoles(roleList);
    simpleAuthorInfo.addStringPermissions(rightList);
    return simpleAuthorInfo;
  }


  private void generateAuthorizeInfo(String currentUsername, List<String> roleList,
      List<String> rightList) {
    // 从数据库中获取当前登录用户的详细信息
    User user = userService.findUserByName(currentUsername);
    if (null != user) {
      // 实体类User中包含有用户角色的实体类信息
      if (null != user.getRoles() && user.getRoles().size() > 0) {
        // 获取当前登录用户的角色
        for (Role role : user.getRoles()) {
          roleList.add(role.getName());
          // 实体类Role中包含有角色权限的实体类信息
          if (null != role.getRights() && role.getRights().size() > 0) {
            // 获取权限
            for (GCRight right : role.getRights()) {
              if (!StringUtils.isEmpty(right.getName())) {
                rightList.add(right.getName());
              }
            }
          }
        }
      }
    } else {
      throw new AuthorizationException();
    }
  }


  /**
   * 验证当前登录的Subject,该方法在LoginController.login()方法中执行Subject.login()时被调用
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
      throws AuthenticationException {
    // 获取基于用户名和密码的令牌,authcToken是从LoginController里面currentUser.login(token)传过来的
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    logger.debug("验证当前Subject时获取到token为"
        + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
    User user = userService.findUserByName(token.getUsername());
    if (null != user) {
      AuthenticationInfo authcInfo =
          new SimpleAuthenticationInfo(user.getName(), user.getPwd(), user.getName());
      this.setSession(GCloudConstant.CURRENT_USER, user.getName());
      return authcInfo;
    } else {
      return null;
    }
  }


  /**
   * 将一些数据放到ShiroSession中,以便于其它地方使用
   * 
   * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
   */
  private void setSession(Object key, Object value) {
    Subject currentUser = SecurityUtils.getSubject();
    if (null != currentUser) {
      Session session = currentUser.getSession();
      logger.debug("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
      if (null != session) {
        session.setAttribute(key, value);
      }
    }
  }
}
