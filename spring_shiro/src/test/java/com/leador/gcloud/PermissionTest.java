package com.leador.gcloud;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.leador.gcloud.monitor.po.GCRight;
import com.leador.gcloud.monitor.po.Role;
import com.leador.gcloud.monitor.po.User;
import com.leador.gcloud.monitor.service.RightService;
import com.leador.gcloud.monitor.service.RoleService;
import com.leador.gcloud.monitor.service.UserService;

public class PermissionTest {

  private static ApplicationContext ctx = null;
  private static UserService userSerivce;
  private static RoleService roleService;
  private static RightService rightService;
  private static String identify = System.currentTimeMillis() + "";

  @BeforeClass
  public static void onlyOnce() {
    ctx =
        new FileSystemXmlApplicationContext(
            "D:/Files/GCloud/GCloudMonitor/01开发库/code/GCloud/src/main/webapp/WEB-INF/hibernate.xml");
    userSerivce = ctx.getBean("userServiceImpl", UserService.class);
    roleService = ctx.getBean("roleServiceImpl", RoleService.class);
    rightService = ctx.getBean("rightServiceImpl", RightService.class);
  }

  // @Test
  public void testSave() {

    User u1 = new User();
    u1.setName("admin");
    String encryptPwd = new Md5Hash("admin").toString();
    u1.setPwd(encryptPwd);
    userSerivce.saveUser(u1);
    // System.out.println(encryptPwd);
    // User u1 = userSerivce.findUserByName("admin");
    // u1.setPwd(encryptPwd);
    // userSerivce.updateUser(u1);
  }

  // @Test
  public void testUpdateRight() {
    int index = 0;
    List<GCRight> list = rightService.findRights();
    for (GCRight right : list) {
      right.setLabel("权限" + index);
      index++;
      rightService.updateRight(right);
    }
  }

  // @Test
  public void testLogin() {
    assertTrue(userSerivce.userLogin("admin", "admin"));
  }


  // @Test
  public void testFindAll() {
    System.out.println("#########" + userSerivce.findUsers());

  }
  
  
  
  @Test
  public void testInsertRightRoleUser() {
    System.out.println("ID:" + identify);
    Set<GCRight> rights = new HashSet<GCRight>();
    Set<GCRight> rights2 = new HashSet<GCRight>();
    Set<GCRight> rights3 = new HashSet<GCRight>(rights2);
    generateRightData(rights, rights2);
    Set<Role> roles = generateTestRoleData(rights, rights2, rights3);

    User user1 = new User("admin" + identify, "admin" + identify, "sunaihua@126");
    user1.setRoles(roles);
    userSerivce.saveUser(user1);
  }

  private Set<Role> generateTestRoleData(Set<GCRight> rights, Set<GCRight> rights2,
      Set<GCRight> rights3) {
    rights3.addAll(rights);
    Set<Role> roles = new HashSet<Role>();
    Role role1 = new Role("role1" + identify, rights);
    role1.setDescription("测试角色1" + System.currentTimeMillis());
    Role role2 = new Role("role2" + identify, rights2);
    role2.setDescription("测试角色2" + System.currentTimeMillis());
    Role role3 = new Role("role3" + identify, rights3);
    role3.setDescription("测试角色3" + System.currentTimeMillis());
    // 对Role数据进行持久化
    roleService.saveRole(role1);
    roleService.saveRole(role2);
    roleService.saveRole(role3);
    roles.add(role1);
    roles.add(role2);
    roles.add(role3);
    return roles;
  }

  private void generateRightData(Set<GCRight> rights, Set<GCRight> rights2) {
    for (int i = 0; i < 5; i++) {
      GCRight right = new GCRight("test1" + i + identify, "/login" + i + identify);
      // 对right数据进行持久化
      rightService.saveRight(right);
      rights.add(right);
    }

    for (int i = 5; i < 10; i++) {
      GCRight right2 = new GCRight("test2" + i + identify, "/login" + i + identify);
      rightService.saveRight(right2);
      rights2.add(right2);
    }
  }

  // @Test
  public void testDeleteUserDate() {
    // 删除User数据的时候，应该只删除，User，UserRoles表
    List<User> userList = userSerivce.findUsers();
    for (User user : userList) {
      if (user.getName().endsWith(identify)) {
        userSerivce.removeUser(user);
      }
    }
  }

  // @Test
  public void testDeleteRoleDate() {
    // 删除Role数据的时:
    // (1)要确保User没有引用,即UserRoles没有对应的数据
    // (2)应该只删除，Roles,RoleRights表
    List<Role> roleList = roleService.findRoles();
    for (Role role : roleList) {
      if (role.getName().endsWith(identify)) {
        roleService.removeRole(role);
      }
    }
  }

  // @Test
  public void testSerachUserByName() {
    System.out.println("#######" + userSerivce.findUserByName("admin"));
    assertTrue(userSerivce.findUserByName("admin") != null);
  }


}
