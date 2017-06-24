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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Email;

@Entity
@Table
public class User extends BasePO {


  private static final long serialVersionUID = 6295814719063548261L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Length(min = 3, max = 20)
  @Column(name = "name", nullable = false, length = 25, unique = true)
  private String name;

  @Column(nullable = false)
  @Length(min = 6, max = 255)
  private String pwd;

  @Column(length = 100)
  @Email
  private String email;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(name = "userroles", joinColumns = {@JoinColumn(name = "userroles_user")},
      inverseJoinColumns = {@JoinColumn(name = "userroles_role")})
  private Set<Role> roles;

  /**
   * 不被持久化的
   */
  @Transient
  private List<Long> roleIdList;
  @Length(min = 6, max = 20)
  @Transient
  private String retryPwd;
  @Transient
  private boolean resetPwd;



  public User() {
    super();
  }


  public User(String name, String pwd, String email) {
    super();
    this.name = name;
    this.pwd = pwd;
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



  public String getPwd() {
    return pwd;
  }


  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public Set<Role> getRoles() {
    return roles;
  }


  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }


  public String getRetryPwd() {
    return retryPwd;
  }


  public void setRetryPwd(String retryPwd) {
    this.retryPwd = retryPwd;
  }


  public List<Long> getRoleIdList() {
    return roleIdList;
  }


  public void setRoleIdList(List<Long> roleIdList) {
    this.roleIdList = roleIdList;
  }



  public boolean getResetPwd() {
    return resetPwd;
  }


  public void setResetPwd(boolean resetPwd) {
    this.resetPwd = resetPwd;
  }


  @Override
  public String toString() {
    return "User [name=" + name + ", pwd=" + pwd + ", email=" + email + "]";
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
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
    User other = (User) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (pwd == null) {
      if (other.pwd != null)
        return false;
    } else if (!pwd.equals(other.pwd))
      return false;
    return true;
  }



}
