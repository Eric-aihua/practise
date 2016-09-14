package com.eric.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {
    public static void main(String[] args) {
        List<Integer> idList = new ArrayList<Integer>();
        Collections.addAll(idList, 47, 48, 49, 50);
        printAnnoInfo(idList, PasswordUtils.class);
        // due to the Arrays.asList() will generate a fixed length List, so if change List length will throw Exception
        // printAnnoInfo(Arrays.asList(47, 48, 49, 50), PasswordUtils.class);
    }

    public static void printAnnoInfo(List<Integer> idList, Class<?> c) {
        // if traversing idList,in lines 24 will throw Exception
        // for(int id:idList){
        for (Method method : c.getDeclaredMethods()) {
            UseCase useCase = method.getAnnotation(UseCase.class);
            if (useCase != null) {
                System.out.println("usecase id:" + useCase.id() + " description:" + useCase.description());
                idList.remove(Integer.valueOf(useCase.id()));
            }
        }
        // }
        for (int id : idList) {
            System.out.println("UseCase:" + id + " inexistence ");
        }
    }
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
