package com.leador.gcloud.monitor.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leador.gcloud.monitor.dao.RightDao;
import com.leador.gcloud.monitor.po.GCRight;

@Repository
public class RightDaoImpl extends BaseDaoImpl<GCRight> implements RightDao {
  public RightDaoImpl() {
    super(GCRight.class);
  }

  @Override
  public List<GCRight> findRightsByPaging(int start, int size) {
    return query("from " + getEntityClass().getName(), null, start, size);
  }

  @Override
  public List<GCRight> findRights() {
    return query("from " + getEntityClass().getName(), null);
  }

}
