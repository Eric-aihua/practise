package com.leador.gcloud.monitor.service;

import java.util.List;

import com.leador.gcloud.monitor.po.GCRight;
import com.leador.gcloud.monitor.po.Role;

public interface RoleService {
  public void saveRole(Role Role);

  public void removeRole(Role Role);

  public List<Role> findRoles();

  public List<Role> findRolesByPaging(int start, int size);

  public Role findRoleById(Long id);

  public Role findRoleByName(String roleName);

  public void updateRole(Role role);

  public List<Role> findRoles(String roleName);

  public List<Role> findRolesByPaging(String roleName, int startIndex, int integer);


}
