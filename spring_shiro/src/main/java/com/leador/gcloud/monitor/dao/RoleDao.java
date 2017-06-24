package com.leador.gcloud.monitor.dao;

import java.util.List;

import com.leador.gcloud.monitor.po.Role;

public interface RoleDao extends BaseDao<Role> {
  public List<Role> findRolesByPaging(int start, int size);

  public List<Role> findRoles();

  public Role findRoleByName(String roleName);

  public List<Role> findRolesByName(String roleName);

  public List<Role> findRolesByPaging(String roleName, int start, int size);
}
