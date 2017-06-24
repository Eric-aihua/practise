package com.leador.gcloud.monitor.json;

import java.io.Serializable;

public class UserJson implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7963776308762187315L;
  private Long id;
  private String name;
  private String email;

  public UserJson(Long id, String name, String email) {
    super();
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


}
