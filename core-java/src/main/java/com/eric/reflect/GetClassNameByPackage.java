package com.eric.reflect;


import com.google.common.reflect.ClassPath;

import java.io.IOException;

/**
 * 根据包名获取所有的类，更多方法参考：https://www.hellojava.com/a/80798.html
 */
public class GetClassNameByPackage {
    public static void main(String[] args) {
        String curPack=GetClassNameByPackage.class.getPackage().getName();
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        try {
            ClassPath classPath=ClassPath.from(classLoader);
            for(ClassPath.ClassInfo classInfo:classPath.getTopLevelClasses(curPack)){
                System.out.println(classInfo.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(curPack);

    }
}