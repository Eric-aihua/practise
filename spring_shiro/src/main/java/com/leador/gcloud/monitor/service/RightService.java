package com.leador.gcloud.monitor.service;

import java.util.List;

import com.leador.gcloud.monitor.po.GCRight;

public interface RightService {
  public void saveRight(GCRight Right);

  public void removeRight(GCRight Right);

  public List<GCRight> findRights();

  public List<GCRight> findRightsByPaging(int start, int size);

  public GCRight findRightById(Long rightId);

  public void updateRight(GCRight right);

}
