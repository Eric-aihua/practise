package com.leador.gcloud.monitor.json;

import java.io.Serializable;

public class RoleJson implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1299217933444056669L;
  private Long id;
  private String name;
  private String description;


  public RoleJson() {
    super();
  }

  public RoleJson(Long id, String name, String description) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}
