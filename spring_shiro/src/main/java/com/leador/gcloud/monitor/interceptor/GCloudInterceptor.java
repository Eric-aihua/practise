package com.leador.gcloud.monitor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leador.gcloud.monitor.util.GCloudConstant;


public class GCloudInterceptor extends HandlerInterceptorAdapter {

  /**
   * 检查当前用户是否登陆，如果未登陆，跳转到登陆页面
   * */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestUrl = request.getServletPath().toString();
    if (requestUrl.startsWith(GCloudConstant.LOGIN)
        || requestUrl.startsWith(GCloudConstant.RESOURCES)) {
      return true;
    }
    if (SecurityUtils.getSubject().isAuthenticated()) {
      return true;
    }
    response.sendRedirect(request.getContextPath() + GCloudConstant.LOGIN);
    return false;

  }
}
