package com.leador.gcloud;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class HashTest {
  public static void main(String[] args) {
    String str = "admin";
    String sha1 = new Md5Hash(str).toString();
    
    DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
    String sha2=defaultPasswordService.encryptPassword("admin");
    
    System.out.println(sha2);
    System.out.println(sha1);
  }
}
