package com.leador.gcloud.monitor.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leador.gcloud.monitor.dao.UserDao;
import com.leador.gcloud.monitor.po.User;
import com.leador.gcloud.monitor.service.UserService;

@Repository
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  public UserServiceImpl() {
    super();
  }

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  public void saveUser(User user) {
    logger.info("Add User" + user);
    userDao.save(user);
  }



  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<User> findUsers() {
    return userDao.findUsers();

  }

  @Override
  public User findUserByName(String userName) {
    return userDao.findUserByName(userName);
  }

  @Override
  public void updateUser(User user) {
    userDao.update(user);
  }

  @Override
  public void removeUser(User user) {
    userDao.delete(user);
  }

  @Override
  public List<User> findUsersByPaging(String param, int start, int size) {
    return userDao.findUsersByPaging(param, start, size);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public boolean userLogin(String name, String pwd) {
    if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(pwd)) {
      List<User> userList = findUsers();
      for (User user : userList) {
        if (user.getName().equals(name) && user.getPwd().equals(pwd)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public User findUserById(Long id) {
    return userDao.findById(id);
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }



  @Override
  public List<User> findUsersByName(String param) {
    return userDao.findUsers(param);
  }



}
