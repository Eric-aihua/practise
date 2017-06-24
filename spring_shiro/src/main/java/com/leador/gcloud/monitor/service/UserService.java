package com.leador.gcloud.monitor.service;

import java.util.List;

import com.leador.gcloud.monitor.po.User;

public interface UserService {
  public void saveUser(User user);

  public void removeUser(User user);

  public User findUserByName(String userName);

  public List<User> findUsers();

  public List<User> findUsersByPaging(String param, int start, int size);

  public boolean userLogin(String name, String pwd);

  public void updateUser(User user);

  public User findUserById(Long id);

  public List<User> findUsersByName(String param);
}
