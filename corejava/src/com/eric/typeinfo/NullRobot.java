package com.eric.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author
 * 
 */

interface Robot {
	String name();
	
	String model();
	
	List<Operations> operations();
	
	class Test {
		public static void test(Robot r) {
			if (r instanceof Null)
				System.out.println("[Null Robot]");
			System.out.println("Robot name: " + r.name());
			System.out.println("Robot model: " + r.model());
			for (Operations operation : r.operations()) {
				System.out.println(operation.description());
				operation.command();
			}
		}
	}
}

public class NullRobot {
	// return a null object
	public static Robot newNullRobot(Class<? extends Robot> type) {
		return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader(), new Class [] { Null.class, Robot.class}, new NullRobotHandler(type));
	}
	
	public static void main(String[] args) {
		
		Robot.Test.test(new SnowRemovalRobot("Snow remove robot"));
		System.out.println();
		Robot.Test.test(newNullRobot(new SnowRemovalRobot("Snow remove robot").getClass()));
	}
}

interface Null {
}

class NullRobotHandler implements InvocationHandler {
	private String	nullName;
	private Robot	robot	= new NRobot();
	
	public NullRobotHandler(Class<? extends Robot> robotClass) {
		this.nullName = robotClass.getSimpleName() + "  NULL OBJ";
	}
	
	public NullRobotHandler() {}
	
	class NRobot implements Robot, Null {
		
		public String name() {
			return nullName;
		}
		
		public String model() {
			return nullName;
		}
		
		public List<Operations> operations() {
			return Collections.emptyList();
		}
		
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(robot, args);
	}
}

class SnowRemovalRobot implements Robot {
	private String	name;
	
	public SnowRemovalRobot(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	public String model() {
		return "SnowBot Series 11";
	}
	
	public List<Operations> operations() {
		return Arrays.asList(new Operations() {
			public String description() {
				return name + " can shovel snow";
			}
			
			public void command() {
				System.out.println(name + " shoveling snow");
			}
		}, new Operations() {
			public String description() {
				return name + " can chip ice";
			}
			
			public void command() {
				System.out.println(name + " chipping ice");
			}
		}, new Operations() {
			public String description() {
				return name + " can clear the roof";
			}
			
			public void command() {
				System.out.println(name + " clearing roof");
			}
		});
	}
}

interface Operations {
	String description();
	
	void command();
}
