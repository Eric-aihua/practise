package com.leador.gcloud.monitor.dao;

import java.util.List;

import com.leador.gcloud.monitor.po.BasePO;

public interface BaseDao<E extends BasePO> {
  /**
   * find entity by id
   * 
   * @param id
   * @return
   */
  public E findById(long id);

  /**
   * @return All relevant entity object list
   */
  public List<E> findAll();

  /**
   * add po
   * 
   * @param e
   */
  public void save(E e);

  /**
   * delete po
   * 
   * @param e
   */
  public void delete(E e);

  /**
   * update po
   * 
   * @param e
   */
  public void update(E e);

  /**
   * query po list no paging
   * 
   * @param hql
   * @param args
   * @return
   */
  public List<E> query(String hql, Object[] args);

  /**
   * query po list by paging
   * 
   * @param hql
   * @param args
   * @param page
   * @return
   */
  public List<E> query(String hql, Object[] args, int start, int size);

  /**
   * just return one record
   * 
   * @param hql
   * @param args
   * @return
   */
  public E querySingleResult(String hql, Object[] args);

}
