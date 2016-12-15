package com.leador.gcloud.monitor.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leador.gcloud.monitor.dao.RightDao;
import com.leador.gcloud.monitor.po.GCRight;
import com.leador.gcloud.monitor.service.RightService;

@Repository
@Transactional
public class RightServiceImpl implements RightService {

  @Autowired
  private RightDao rightDao;

  private static final Logger logger = LoggerFactory.getLogger(RightServiceImpl.class);

  public void saveRight(GCRight right) {
    logger.info("Add Right" + right);
    rightDao.save(right);
  }

  public void updateRight(GCRight right) {
    rightDao.update(right);
  }


  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<GCRight> findRights() {
    return rightDao.findAll();

  }

  @Override
  public void removeRight(GCRight right) {
    rightDao.delete(right);
  }


  @Override
  public List<GCRight> findRightsByPaging(int start, int size) {
    return rightDao.findRightsByPaging(start, size);
  }

  @Override
  public GCRight findRightById(Long rightId) {
    return rightDao.findById(rightId);
  }


  public RightDao getRightDao() {
    return rightDao;
  }

  public void setRightDao(RightDao rightDao) {
    this.rightDao = rightDao;
  }

}
