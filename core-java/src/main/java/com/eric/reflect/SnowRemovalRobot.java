package com.eric.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SnowRemovalRobot implements Robot {
    //generate a NULL robot object 
    static Robot generateNullRobotObj(Robot obj) {
        return (Robot) Proxy.newProxyInstance(Robot.class.getClassLoader(), new Class[] {Robot.class,Null.class},
                new NullRobotHandler(obj.getClass()));
    }

    public static void main(String[] args) {
        Robot.Test.test(new SnowRemovalRobot("sample "));
        System.out.println();
        Robot.Test.test(generateNullRobotObj(new SnowRemovalRobot("sample ")));
    }

    private String name;

    public SnowRemovalRobot(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String model() {
        return "SnowBot Series 11";
    }

    public List<Operation> operations() {
        return Arrays.asList(new Operation() {
            public String description() {
                return name + " can shovel snow";
            }

            public void command() {
                System.out.println(name + " shoveling snow");
            }
        }, new Operation() {
            public String description() {
                return name + " can chip ice";
            }

            public void command() {
                System.out.println(name + " chipping ice");
            }
        }, new Operation() {
            public String description() {
                return name + " can clear the roof";
            }

            public void command() {
                System.out.println(name + " clearing roof");
            }
        });
    }

}

// null object handler, that use dynamic proxy to generate sepcial class null object
class NullRobotHandler implements InvocationHandler {
    private String name;
    private Robot nullRobot = new NullRobot();

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(nullRobot, args);
    }

    public NullRobotHandler(Class<? extends Robot> type) {
        name = "NULL " + type.getSimpleName() + " obj";
    }

    class NullRobot implements Robot, Null {

        public String name() {
            return name;
        }

        public String model() {
            return name;
        }

        public List<Operation> operations() {
            return Collections.emptyList();
        }

    }

}

interface Robot {
    String name();

    String model();

    List<Operation> operations();

    class Test {
        static void test(Robot robot) {
            System.out.println("name" + robot.name());
            System.out.println("model" + robot.model());
            for (Operation ope : robot.operations()) {
                System.out.print("description:" + ope.description() + " command:");
                ope.command();
            }
        }
    }

}

interface Operation {
    String description();

    void command();
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
