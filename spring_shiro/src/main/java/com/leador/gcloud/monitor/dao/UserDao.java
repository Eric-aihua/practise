package com.leador.gcloud.monitor.dao;

import java.util.List;

import com.leador.gcloud.monitor.po.User;



public interface UserDao extends BaseDao<User> {
  public List<User> findUsers();

  public List<User> findUsersByPaging(String param, int start, int size);

  public User findUserByName(String userName);

  public List<User> findUsers(String param);

}
