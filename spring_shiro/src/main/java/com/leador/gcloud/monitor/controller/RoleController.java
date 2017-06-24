package com.leador.gcloud.monitor.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leador.gcloud.monitor.json.RoleJson;
import com.leador.gcloud.monitor.po.GCRight;
import com.leador.gcloud.monitor.po.Role;
import com.leador.gcloud.monitor.service.RightService;
import com.leador.gcloud.monitor.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends PaginationController {

  /**
   * 
   */
  private static final long serialVersionUID = 7669170318137221646L;
  private static final String ROLE_LIST_VIEW = "roles";
  private static final String ROLE_FIELD_VIEW = "role/field";
  private static final String ROLE = "role";
  private static final String RIGHTLIST = "rightlist";
  private static final String ROLE_UPDATE_URL = "/role/update";
  private static final String REDIRECT_ROLE_URL = "redirect:/role/rolelist";

  @Autowired
  private RoleService roleService;
  @Autowired
  private RightService rightService;
  private List<GCRight> rightList;

  @Override
  protected List<?> getResultList(HttpServletRequest request, Integer start, Integer pageSize) {
    Object param = request.getParameter("roleName");
    String paramValue = param != null ? param.toString() : null;
    List<Role> roleList = roleService.findRolesByPaging(paramValue, start, pageSize);
    List<RoleJson> jsonList = new ArrayList<RoleJson>();
    for (Role role : roleList) {
      jsonList.add(new RoleJson(role.getId(), role.getName(), role.getDescription()));
    }
    return jsonList;
  }

  @Override
  protected Integer getTotalRecordCount(HttpServletRequest request) {
    Object param = request.getParameter("roleName");
    String paramValue = param != null ? param.toString() : null;
    return roleService.findRoles(paramValue).size();
  }

  @Override
  public String[][] getListMetaInfoDefine(HttpServletRequest request) {
    String[][] meta_info =
        { {SEARCH_PARAMETER},{"roleName"}, {"id", "name", "description"},
            {ID_HEADER, resources.getString("role.name"), resources.getString("role.desc")},
            {PK_COLUMN, LINK_COLUMN, TEXT_COLUMN}, {ID}, {getTotalRecordCount(request) + ""}};
    return meta_info;
  }

  @Override
  @RequestMapping("/rolelist")
  protected String redirectIntoModuleHome(HttpServletRequest  request,Model model) {
    return ROLE_LIST_VIEW;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String viewDetail(@PathVariable Long id, Map<String, Object> model) {
    Role role = roleService.findRoleById(id);
    prepareUpdate(model, role);
    return ROLE_FIELD_VIEW;
  }

  private void prepareUpdate(Map<String, Object> model, Role role) {
    model.put(ROLE, role);
    model.put(TITLE, "role.field.update");
    model.put(ACTION, ROLE_UPDATE_URL);
    model.put("updateMode", true);
    initRightCheckBoxs(model, role);
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String prepareCreateRole(Map<String, Object> model) {
    Role role = new Role();
    prepareCreatePage(model, role);
    return ROLE_FIELD_VIEW;
  }

  private void prepareCreatePage(Map<String, Object> model, Role role) {
    model.put(ROLE, role);
    model.put(TITLE, "role.field.add");
    model.put(ACTION, "/role/add");
    initRightCheckBoxs(model, role);
  }

  @RequestMapping("/add")
  public String addRole(@Valid Role role, BindingResult result, Map<String, Object> model,
      @RequestParam(value = "action") String action) {
    if (action.equalsIgnoreCase(resources.getString("button.cancel"))) {
      return REDIRECT_ROLE_URL;
    }
    if (result.hasErrors()) {
      prepareCreatePage(model, role);
      return ROLE_FIELD_VIEW;
    }
    translateRoleIdListToRoleSet(role);
    roleService.saveRole(role);
    return "redirect:/role/" + role.getId();
  }



  private void initRightCheckBoxs(Map<String, Object> model, Role role) {
    List<GCRight> roleList = rightService.findRights();
    if (role.getRights() != null) {
      List<Long> rightIdList = new ArrayList<Long>();
      for (GCRight right : role.getRights()) {
        rightIdList.add(right.getId());
      }
      role.setRightIdList(rightIdList);
    }
    model.put(RIGHTLIST, roleList);

  }

  @RequestMapping("/update")
  public String action(@Valid Role role, BindingResult result,
      @RequestParam(value = "action") String action, Map<String, Object> model) {
    String resultURL = null;
    if (action.equalsIgnoreCase(resources.getString("button.delete"))) {
      try {
        roleService.removeRole(role);
      } catch (DataIntegrityViolationException ex) {
        ex.printStackTrace();
        prepareUpdate(model, role);
        model.put("ErrorMessage", "error.delete.refer");
        return ROLE_FIELD_VIEW;
      } catch (Exception ex2) {
        prepareUpdate(model, role);
        model.put("ErrorMessage", "error.delete.unknown");
        return ROLE_FIELD_VIEW;
      }
      resultURL = REDIRECT_ROLE_URL;
      return resultURL;
    }
    if (action.equalsIgnoreCase(resources.getString("button.cancel"))) {
      resultURL = REDIRECT_ROLE_URL;
      return resultURL;
    }
    if (result.hasErrors()) {
      prepareCreatePage(model, role);
      return ROLE_FIELD_VIEW;
    }
    if (action.equalsIgnoreCase(resources.getString("button.submit"))) {
      translateRoleIdListToRoleSet(role);
      roleService.updateRole(role);
      resultURL = "redirect:/role/" + role.getId();
    }
    return resultURL;
  }

  private void translateRoleIdListToRoleSet(Role role) {
    Set<GCRight> rightSet = new HashSet<GCRight>();
    if (role.getRightIdList() != null) {
      for (Long rightId : role.getRightIdList()) {
        rightSet.add(rightService.findRightById(rightId));
      }
      role.setRights(rightSet);
    }
  }


  public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  public RightService getRightService() {
    return rightService;
  }

  public void setRightService(RightService rightService) {
    this.rightService = rightService;
  }

  public List<GCRight> getRightList() {
    return rightList;
  }

  public void setRightList(List<GCRight> rightList) {
    this.rightList = rightList;
  }



}
