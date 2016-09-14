package com.eric.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Because of erasure, the use of generics with exceptions is extremely limited.
 * A catch clause cannot catch an exception of a generic type, because the exact
 * type of the exception must be known at both compile time and run time. Also,
 * a generic class can’t directly or indirectly inherit from Throwable (this
 * further prevents you from tryi ng to define generic exceptions that can’t be
 * caught). However, type parameters may be used in the throws clause of a
 * method declaration. This allows you to write generic code that varies with
 * the type of a checked exception
 * 
 * @author Eric
 * 
 */
public class ExceptionOfGeneric {
	public static void main(String args[]) {
		ProcessRunner<String, Process1Exception> pr1 = new ProcessRunner<String, Process1Exception>();
		pr1.add(new ProcessImp1());
		try {
			System.out.println(pr1.runnAlllProcess());
		} catch (Process1Exception e) {
			e.printStackTrace();
		}
		ProcessRunner<Integer, Process2Exception> pr2 = new ProcessRunner<Integer, Process2Exception>();
		pr2.add(new ProcessImp2());
		try {
			System.out.println(pr2.runnAlllProcess());
		} catch (Process2Exception e) {
			e.printStackTrace();
		}
	}
}

interface Process<T, E extends Exception> {
	public void process(List<T> t) throws E;
}

class Process1Exception extends Exception {}

class Process2Exception extends Exception {}

class ProcessRunner<T, E extends Exception> extends ArrayList<Process<T, E>> {
	List<T> runnAlllProcess() throws E {
		List<T> results = new ArrayList<T>();
		for (Process<T, E> t : this) {
			// execute process may be throw variable type of exception
			t.process(results);
		}
		return results;
	}
}

class ProcessImp1 implements Process<String, Process1Exception> {
	
	public void process(List<String> strs) throws Process1Exception {
		strs.add("HELLO");
	}
	
}

class ProcessImp2 implements Process<Integer, Process2Exception> {
	
	public void process(List<Integer> t) throws Process2Exception {
		t.add(10);
	}
	
}