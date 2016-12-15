package com.leador.gcloud.monitor.util;

import java.io.Serializable;

/**
 * 分页对象
 * 
 * @author Eric.sunah
 *
 */
public class Page implements Serializable {

  private static final long serialVersionUID = -1164839171176133875L;

  public static final int DEFAULT_PAGE_SIZE = 10;

  public static final int DEFAULT_CURRENT_PAGE = 1;

  private int currentPage;// 当前页数，通常在Action层设置

  private int pageSize;// 每页记录数，通常在Action层设置

  private int totalCount;// 总记录数，在DAO层设置

  private String type;//

  private int pageNum;// 当前页数



  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public Page(int currentPage, int pageSize) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
  }

  public Page(int currentPage) {
    this.currentPage = currentPage;
    this.pageSize = DEFAULT_PAGE_SIZE;
  }

  public Page() {
    this.currentPage = DEFAULT_CURRENT_PAGE;
    this.pageSize = DEFAULT_PAGE_SIZE;
  }

  public int getFirstIndex() {
    return pageSize * (currentPage - 1);
  }

  public boolean hasPrevious() {
    return currentPage > 1;
  }

  public boolean hasNext() {
    return currentPage < getTotalPage();
  }



  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getTotalPage() {

    long remainder = totalCount % this.getPageSize();

    if (0 == remainder) {
      return totalCount / this.getPageSize();
    }

    return totalCount / this.getPageSize() + 1;
  }
}
