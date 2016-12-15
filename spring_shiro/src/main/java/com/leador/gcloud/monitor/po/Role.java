package com.leador.gcloud.monitor.po;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Role extends BasePO {


  private static final long serialVersionUID = 5825756978575727011L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long id;
  @Column(name = "name", length = 50, unique = true, nullable = false)
  private String name;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(name = "rolerights", joinColumns = {@JoinColumn(name = "rolerights_role")},
      inverseJoinColumns = {@JoinColumn(name = "rolerights_right")})
  private Set<GCRight> rights;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "roles")
  private Set<User> user;
  @Column(length = 200)
  private String description;

  @Transient
  private List<Long> rightIdList;

  public Role() {
    super();
  }

  public Role(String name, Set<GCRight> rights) {
    super();
    this.name = name;
    this.rights = rights;
  }

  public Role(String name, String description) {
    super();
    this.name = name;
    this.description = description;
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

  public Set<GCRight> getRights() {
    return rights;
  }

  public void setRights(Set<GCRight> rights) {
    this.rights = rights;
  }



  public List<Long> getRightIdList() {
    return rightIdList;
  }

  public void setRightIdList(List<Long> rightIdList) {
    this.rightIdList = rightIdList;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<User> getUser() {
    return user;
  }

  public void setUser(Set<User> user) {
    this.user = user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    Role other = (Role) obj;
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
