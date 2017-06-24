package com.leador.gcloud.monitor.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leador.gcloud.monitor.dao.RoleDao;
import com.leador.gcloud.monitor.po.Role;
import com.leador.gcloud.monitor.service.RoleService;

@Repository
@Transactional
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDao roleDao;

  private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

  public void saveRole(Role role) {
    logger.info("Add Role" + role);
    roleDao.save(role);
  }

  @Override
  public void updateRole(Role role) {
    logger.info("update Role" + role);
    roleDao.update(role);
  }


  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<Role> findRoles() {
    return roleDao.findRoles();

  }

  @Override
  public List<Role> findRoles(String roleName) {
    return roleDao.findRolesByName(roleName);
  }

  @Override
  public List<Role> findRolesByPaging(String roleName, int start, int size) {
    return roleDao.findRolesByPaging(roleName, start, size);
  }

  @Override
  public void removeRole(Role role) {
    roleDao.delete(role);
  }

  @Override
  public List<Role> findRolesByPaging(int start, int size) {
    return roleDao.findRolesByPaging(start, size);
  }


  public RoleDao getRoleDao() {
    return roleDao;
  }

  public void setRoleDao(RoleDao roleDao) {
    this.roleDao = roleDao;
  }


  @Override
  public Role findRoleById(Long id) {
    return roleDao.findById(id);
  }


  @Override
  public Role findRoleByName(String roleName) {
    return roleDao.findRoleByName(roleName);
  }



}
