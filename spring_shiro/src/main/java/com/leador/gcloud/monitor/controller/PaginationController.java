package com.leador.gcloud.monitor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leador.gcloud.monitor.util.Page;
import com.leador.gcloud.monitor.util.PageTag;

/**
 * 该类为分页Controller的基类,主要提供分页的一些模板方法，子类只需要实现一些抽象方法就可以实现分页效果
 * 
 * @author Eric.sunah
 *
 */
public abstract class PaginationController extends BaseController {


  private static final long serialVersionUID = -1050633259767879320L;
  protected static final String LINK_COLUMN = "link";
  protected static final String ICON_COLUMN = "icon";
  protected static final String TEXT_COLUMN = "text_td";
  protected static final String BUTTON_COLUMN = "button";
  protected static final String CHECKBOX_COLUMN = "checkbox";
  protected static final String SEARCH_PARAMETER = "search";
  protected static final String MULTI_FILTER_PARAMETER = "multi";

  protected static final String PK_COLUMN = "id";
  protected static final String ID = "id";
  protected static final String ID_HEADER = "";
  protected static final String PARAMTER = "param";
  protected static final String PAGIN_URL = "paginresult";

  @RequestMapping(value = "metainfo", method = RequestMethod.GET,
      headers = {"Accept=text/xml,application/json"})
  public @ResponseBody String[][] getListMetaInfo(HttpServletRequest request) {
    return getListMetaInfoDefine(request);
  }


  /**
   * 该方法适用于系统中带有分页页面的模块。所有继承该类的Controller只需要实现getResultList方法，就可以通过以下三种方式来访问分页数据（XXX代表模块名称）
   * 
   * (1)下面的URL返回根据param指定的第一页记录列表,默认页大小为10 http://localhost:8080/gcloud/xxxx/paginresult.json?param=
   * (2)下面的URL返回 无param的第11-20条记录
   * http://localhost:8080/gcloud/xxxx/paginresult.json?pageNum=2&pageSize=10
   * (3)下面的URL返回带param的第11-20条记录
   * http://localhost:8080/gcloud/xxxx/paginresult.json?param=role11401329273499
   * &pageNum=2&pageSize=10
   * 
   * @param model
   * @param param
   * @param page
   * @return
   */
  @RequestMapping(value = PAGIN_URL, method = RequestMethod.GET,
      headers = {"Accept=text/xml,application/json"})
  public @ResponseBody List getPaginationResultList(Model model, HttpServletRequest request,
      Page page) {
    Map<String, Object> map = new HashMap<String, Object>();
    Integer totalCount = getTotalRecordCount(request);
    checkPagingParameter(map, page, totalCount);
    List<?> list = getResultList(request, generateStartIndex(map), (Integer) map.get("pageSize"));
    return list;
  }

  protected Integer generateStartIndex(Map<String, Object> map) {
    return PageTag.getStartIndex((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
  }

  /**
   * 返回查询列表页面的视图名称
   * 
   * @return
   */
  protected abstract String redirectIntoModuleHome(HttpServletRequest  request,Model model);

  /**
   * 获得查询列表
   * 
   * @param param
   * @param start
   * @param pageSize
   * @return
   */
  protected abstract List<?> getResultList(HttpServletRequest request, Integer start,
      Integer pageSize);

  /**
   * 返回记录总条数
   * 
   * @param param
   * 
   * @return
   */
  protected abstract Integer getTotalRecordCount(HttpServletRequest request);

  /**
   * 该方法定义表格展示的定义，所有继承该类的类必须实现该方法 格式如下： [ [页面参数对应的类型列表], [页面查询参数名称列表],[对象属性列表],[表格Column的显示名称列表],
   * [表格字段的展示类型],[查看记录明细的标识],[总记录数] ]
   * 
   * 例如： [["search"], ["search_box"]["name","description"],["角色名称","角色描述"],
   * ["link","icon","text_td","button"],["id"] [10]]
   * 表格字段的展示类型支持的类型有："link","icon","text_td","button","checkbox"
   * 
   * @return
   */
  protected abstract String[][] getListMetaInfoDefine(HttpServletRequest request);
}
