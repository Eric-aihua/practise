package com.leador.gcloud.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("dashboard")
public class DashboardController extends BaseController {


  private static final long serialVersionUID = -7112946665922486099L;
  public static final String DASH_BOARD = "dashboard";

  public DashboardController() {
    super();
  }

  @RequestMapping(method = RequestMethod.GET)
  public String initDashBoard() {
    return DASH_BOARD;
  }

}
