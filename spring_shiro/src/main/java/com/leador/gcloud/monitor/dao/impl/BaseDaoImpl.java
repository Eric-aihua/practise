package com.leador.gcloud.monitor.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leador.gcloud.monitor.dao.BaseDao;
import com.leador.gcloud.monitor.po.BasePO;
import com.leador.gcloud.monitor.util.Page;

@Repository
public class BaseDaoImpl<E extends BasePO> implements BaseDao<E> {
  private Class<E> entityClass;
  private SessionFactory sessionFactory;

  public BaseDaoImpl() {}

  public BaseDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  protected BaseDaoImpl(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

  public Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public E findById(long id) {
    return (E) getCurrentSession().get(entityClass, id);
  }

  @Override
  public List<E> findAll() {
    return query("from " + getEntityClass().getName(), null);
  }

  @Override
  public void save(E e) {
    getCurrentSession().save(e);
  }

  @Override
  public void update(E e) {
    getCurrentSession().update(e);
  }

  @Override
  public void delete(E e) {
    getCurrentSession().delete(e);
  }

  @Override
  public List<E> query(String hql, Object[] args) {
    Query query = getCurrentSession().createQuery(hql);
    setQueryParams(query, args);
    return query.list();
  }

  @Override
  public E querySingleResult(String hql, Object[] args) {
    Query query = getCurrentSession().createQuery(hql);
    setQueryParams(query, args);
    return (E) query.uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public List<E> query(String hql, Object[] params, int start, int size) {
    // generatePageTotalCount(hql, params, page);
    Query query = sessionFactory.getCurrentSession().createQuery(hql);
    setQueryParams(query, params);
    query.setFirstResult(start);
    query.setMaxResults(size);
    return query.list();
  }

  private void setQueryParams(Query query, Object[] args) {
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        query.setParameter(i, args[i]);
      }
    }
  }

  /**
   * 该方法会改变参数page的totalCount字段
   * 
   * @param originHql 原始hql语句
   * @param params 原始参数
   * @param page 页面对象
   */
  private void generatePageTotalCount(String originHql, Object[] params, Page page) {
    String generatedCountHql = "select count(*) " + originHql;
    Query countQuery = sessionFactory.getCurrentSession().createQuery(generatedCountHql);
    setQueryParams(countQuery, params);
    int totalCount = ((Long) countQuery.uniqueResult()).intValue();
    page.setTotalCount(totalCount);
  }

  public Class<E> getEntityClass() {
    return entityClass;
  }

  public void setEntityClass(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }



}
