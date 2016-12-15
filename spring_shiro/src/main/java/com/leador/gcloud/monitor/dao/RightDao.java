package com.leador.gcloud.monitor.dao;

import java.util.List;

import com.leador.gcloud.monitor.po.GCRight;

public interface RightDao extends BaseDao<GCRight> {
  public List<GCRight> findRightsByPaging(int start, int size);

  public List<GCRight> findRights();
}
