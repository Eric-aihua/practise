package com.eric.generic;

/**
 * 
 * Occasionally you can program around these issues, but sometimes you must compensate for erasure by introducing a type
 * tag . This means you explicitly pass in the Class object for your type so that you can use it in type expressions.
 * For example, the attempt to use instanceof in the previous program fails because the type information has been
 * erased. If you introduce a type tag, a dynamic islnstance( ) can be used instead:
 * 
 * 
 * 
 * 
 * @author Admin
 * 
 * @version $Revision: $ $Name: $
 */
class Erased<T> {

    public static void f(Object arg) {
        // if (arg instanceof T) {
		// } // Error
//		 T var = new T(); // Error
//		 T[] array = new T[SIZE]; // Error
//		 T[] array = (T) new Object[SIZE]; // Unchecked warning
	}
}
public class CompensatErasure<T> {
    public static void main(String[] args) {
        CompensatErasure<Building> ctt1 = new CompensatErasure<Building>(Building.class);
        ctt1.f(new Building());
        ctt1.f(new House());
        CompensatErasure<House> ctt2 = new CompensatErasure<House>(House.class);
        ctt2.f(new Building());
        ctt2.f(new House());
    }

    private Class<T> type;

    public CompensatErasure(Class<T> type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public void f(Object obj) {
        // Compensate instance of
        if (type.isInstance(obj)) {
            System.out.println(obj + " is instance of " + type.getSimpleName());
        } else {
            System.out.println(obj + " doesn't  instance of " + type.getSimpleName());
        }
        try {
            // Compensate new T();
            System.out.println(type.newInstance());
            // compensate new T[]{};
            Class<T>[] types = new Class[1];
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

class Building {
	@Override
	public String toString(){
		return "Building";
	}
}

class House extends Building {
	@Override
	public String toString(){
		return "House";
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
