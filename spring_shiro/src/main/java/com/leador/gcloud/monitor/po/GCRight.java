package com.leador.gcloud.monitor.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class GCRight extends BasePO {

  /**
   * 
   */
  private static final long serialVersionUID = -2066243818694977871L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long id;
  @Column(name = "name", length = 50, unique = true, nullable = false)
  private String name;
  @Column(length = 100, unique = true, nullable = false)
  private String label;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "rights")
  private Set<Role> role;


  public GCRight() {
    super();
  }


  public GCRight(String name, String label) {
    super();
    this.name = name;
    this.label = label;
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

  public String getLabel() {
    return label;
  }


  public void setLabel(String label) {
    this.label = label;
  }


  public Set<Role> getRole() {
    return role;
  }

  public void setRole(Set<Role> role) {
    this.role = role;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((label == null) ? 0 : label.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GCRight other = (GCRight) obj;
    if (label == null) {
      if (other.label != null)
        return false;
    } else if (!label.equals(other.label))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }


}
