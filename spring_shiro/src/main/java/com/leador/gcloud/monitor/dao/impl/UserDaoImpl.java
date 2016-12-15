package com.leador.gcloud.monitor.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.leador.gcloud.monitor.dao.UserDao;
import com.leador.gcloud.monitor.po.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }

  @Override
  public List<User> findUsers() {
    return query("from " + getEntityClass().getName(), null);
  }

  @Override
  public List<User> findUsersByPaging(String userName, int start, int size) {
    StringBuilder sb = new StringBuilder("from " + getEntityClass().getName());
    if (StringUtils.isNotBlank(userName)) {
      sb.append(" where name like '" + userName + "%'");
    }
    return query(sb.toString(), null, start, size);
  }

  @Override
  public User findUserByName(String userName) {

    return querySingleResult("from " + getEntityClass().getName() + " where name=?",
        new Object[] {userName});
  }

  @Override
  public List<User> findUsers(String param) {
    StringBuilder sb = new StringBuilder("from " + getEntityClass().getName());
    if (StringUtils.isNotBlank(param)) {
      sb.append(" where name like '" + param + "%'");
    }
    return query(sb.toString(), null);
  }
}
