package com.leador.gcloud.monitor.restful;

import org.springframework.web.client.RestTemplate;

public class RestFulUtil {
  private static RestTemplate restTemplate = new RestTemplate();

  public static Object getObject(String url, Class targetClass) {
    return restTemplate.getForObject(url, targetClass);
  }
}
