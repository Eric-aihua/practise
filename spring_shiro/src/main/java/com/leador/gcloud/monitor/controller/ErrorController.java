package com.leador.gcloud.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

  @RequestMapping("/403")
  public String error403() {
    return "403";
  }

  @RequestMapping("/404")
  public String error404() {
    return "404";
  }

  @RequestMapping("/500")
  public String error500() {
    return "500";
  }

}
