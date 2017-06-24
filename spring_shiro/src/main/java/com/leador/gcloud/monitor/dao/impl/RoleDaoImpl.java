package com.leador.gcloud.monitor.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.leador.gcloud.monitor.dao.RoleDao;
import com.leador.gcloud.monitor.po.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

  public RoleDaoImpl() {
    super(Role.class);
  }

  @Override
  public List<Role> findRolesByPaging(int start, int size) {
    return query("from " + getEntityClass().getName(), null, start, size);
  }

  @Override
  public List<Role> findRoles() {
    return query("from " + getEntityClass().getName(), null);
  }

  @Override
  public List<Role> findRolesByName(String roleName) {
    StringBuilder sb = new StringBuilder("from " + getEntityClass().getName());
    if (StringUtils.isNotBlank(roleName)) {
      sb.append(" where name like '"+roleName+"%'");
    }
    return query(sb.toString(), null);
  }

  @Override
  public List<Role> findRolesByPaging(String roleName, int start, int size) {
    StringBuilder sb = new StringBuilder("from " + getEntityClass().getName());
    if (StringUtils.isNotBlank(roleName)) {
      sb.append(" where name like '"+roleName+"%'");
    }
    return query(sb.toString(), null, start, size);
  }

  @Override
  public Role findRoleByName(String roleName) {
    return querySingleResult("from " + getEntityClass().getName() + " where name=?",
        new Object[] {roleName});
  }



}
