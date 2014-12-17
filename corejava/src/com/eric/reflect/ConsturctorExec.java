package com.eric.reflect;

import java.lang.reflect.Constructor;

public class ConsturctorExec {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    public static void main(String[] args) {
        try {
            Constructor<Student> con = Student.class.getConstructor(String.class, String.class);
            Student student = con.newInstance("Eric", "30");
            System.out.println(student);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private String name;
    private String age;

    public Student(String name, String age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Student() {
        super();
    }

    public String toString() {
        return "name:" + name + " age" + age;
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
