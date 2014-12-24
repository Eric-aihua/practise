package com.eric.reflect;

import java.util.ArrayList;
import java.util.Random;
/*
 * factory interface
 * */
interface Factory<T> {
	public T create();
}

public class RegisterFactory {

	public static void main(String[] args) {
		System.out.println(Part.randomPart());
	}
}

class Part {
	private static ArrayList<Factory<? extends Part>> factoryList = new ArrayList<Factory<? extends Part>>();
	/*
	 * registry all factory in factoryList when initialize class
	 * */
	static {
		factoryList.add(new AirFilter().new AirFilterFactory());
		factoryList.add(new CabinFilter().new CabinFilterFactory());
		factoryList.add(new CabinBelt().new CabinBeltFactory());
		factoryList.add(new AirBelt().new AirBeltFactory());
	}

	public static Part randomPart() {
		return factoryList.get(new Random().nextInt(factoryList.size()))
				.create();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}

class Filter extends Part {
}

class Belt extends Part {
}

class AirBelt extends Belt {
	class AirBeltFactory implements Factory<AirBelt> {
		public AirBelt create() {
			return new AirBelt();
		}
	}
}

class CabinBelt extends Belt {
	class CabinBeltFactory implements Factory<CabinBelt> {
		public CabinBelt create() {
			return new CabinBelt();
		}
	}
}

class AirFilter extends Filter {
	class AirFilterFactory implements Factory<AirFilter> {
		public AirFilter create() {
			return new AirFilter();
		}
	}
}

class CabinFilter extends Filter {
	class CabinFilterFactory implements Factory<CabinFilter> {
		public CabinFilter create() {
			return new CabinFilter();
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
