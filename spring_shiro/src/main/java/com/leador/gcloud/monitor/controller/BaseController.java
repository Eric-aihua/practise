package com.leador.gcloud.monitor.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.ui.Model;

import com.leador.gcloud.monitor.util.Page;
import com.leador.gcloud.monitor.util.PageTag;

public class BaseController implements Serializable {

  private static final long serialVersionUID = 3794487643020748844L;
  protected static final String ACTION = "action";
  protected static final String TITLE = "title";
  protected static final ResourceBundle resources = ResourceBundle.getBundle("message",
      Locale.getDefault());



  protected void checkPagingParameter(Map<String, Object> map, Page page, Integer totalRecordCount) {
    if (page.getPageSize() <= 0) {
      page.setPageNum(PageTag.DEFAULT_PAGE_SIZE);
    }
    if (page.getPageSize() > 50) {
      page.setPageNum(50);
    }
    Integer totalPageCount = (totalRecordCount + page.getPageSize() - 1) / page.getPageSize();
    if (page.getPageNum() <= 0) {
      page.setPageNum(1);
    }

    if (page.getPageNum() > totalPageCount) {
      page.setPageNum(totalPageCount);
    }
    map.put("startIndex", PageTag.getStartIndex(page.getPageNum(), page.getPageSize()));
    map.put("pageNum", page.getPageNum());
    map.put("totalPage", totalPageCount);
    map.put("pageSize", page.getPageSize());
    map.put("totalCount", totalRecordCount);
  }


  /**
   * 如需分页，可以继承PaginationController
   * 
   * @param map
   * @param pageNum
   * @param pageSize
   * @param totalCount
   */
  @Deprecated
  protected void initPage(Map<String, Object> map, Integer pageNum, Integer pageSize,
      Integer totalCount) {
    if (null == pageSize || pageSize.equals("") || pageSize == 0) {
      pageSize = PageTag.DEFAULT_PAGE_SIZE;
    }
    if (pageSize > 50) {
      pageSize = 50;
    }
    Integer totalPage = (totalCount + pageSize - 1) / pageSize;
    if (null == pageNum) {
      pageNum = 1;
    }

    if (pageNum > totalPage) {
      pageNum = totalPage;
    }
    map.put("startIndex", PageTag.getStartIndex(pageNum, pageSize));
    map.put("pageNum", pageNum);
    map.put("totalPage", totalPage);
    map.put("pageSize", pageSize);
    map.put("totalCount", totalCount);

  }

  /**
   * 如需分页，可以继承PaginationController
   * 
   * @param model
   * @param list
   * @param map
   */
  @Deprecated
  protected void initResult(Model model, List<?> list, Map<String, Object> map) {
    model.addAttribute("list", list);
    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry m = (Map.Entry) it.next();
      model.addAttribute(m.getKey().toString(), m.getValue());
    }
  }

}
