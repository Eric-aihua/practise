package com.eric.reflect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InstanceSample {
    static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // PetCreator pc = new PetNameCreator();
        // System.out.println(pc.getPetList(50));
        // System.out.println(new PetCount(pc).petCount());
        PetCreator pc2 = new PetIiteralsCreator();
        // System.out.println(pc2.getPetList(50));
        System.out.println(new PetCountByInstance(pc2).petCount());

    }
}

class PetCountByInstance {
    private PetCreator pc;
    private PetInstanceCounter counter = new PetInstanceCounter();

    public PetCountByInstance(PetCreator pc) {
        this.pc = pc;
    }

    class PetInstanceCounter extends HashMap<Class<? extends Pet>, Integer> {
        public void count(Class<? extends Pet> type) {
            Integer count = get(type);
            if (count == null) {
                put(type, 1);

            } else {
                put(type, ++count);
            }
        }
    }

    // decide pet whether instance of extra Pet class, if need add a pet type just need to modify pets source
    public PetInstanceCounter petCount() throws InstantiationException, IllegalAccessException {
        for (Pet pet : pc.getPetList(1000)) {
            for (Class<? extends Pet> cla : pc.pets()) {
                if (cla.isInstance(pet))
                    counter.count(cla);
            }
        }
        return counter;
    }
}

class PetCount {
    private PetCreator pc;
    private PetCounter counter = new PetCounter();

    public PetCount(PetCreator pc) {
        this.pc = pc;
    }

    class PetCounter extends HashMap<String, Integer> {
        public void count(String type) {
            Integer count = get(type);
            if (count == null) {
                put(type, 1);

            } else {
                put(type, ++count);
            }
        }
    }

    // decide pet whether instance of extra Pet type, if add a pet type must add instance of choose
    public PetCounter petCount() throws InstantiationException, IllegalAccessException {
        for (Pet pet : pc.getPetList(1000)) {
            if (pet instanceof Pet)
                counter.count("Pet");
            if (pet instanceof Dog)
                counter.count("Dog");
            if (pet instanceof Mutt)
                counter.count("Mutt");
            if (pet instanceof Pug)
                counter.count("Pug");
            if (pet instanceof Cat)
                counter.count("Cat");
            if (pet instanceof Manx)
                counter.count("EgyptianMau");
            if (pet instanceof Manx)
                counter.count("Manx");
            if (pet instanceof Manx)
                counter.count("Cymric");
            if (pet instanceof Rodent)
                counter.count("Rodent");
            if (pet instanceof Rat)
                counter.count("Rat");
            if (pet instanceof Mouse)
                counter.count("Mouse");
            if (pet instanceof Hamster)
                counter.count("Hamster");
        }
        return counter;
    }

}

// use Class literals to get Classes
class PetIiteralsCreator extends PetCreator {

    @Override
    @SuppressWarnings("unchecked")
    public List<Class<? extends Pet>> pets() {
        return Arrays.asList(Pet.class, Dog.class, Cat.class, Rodent.class, Mutt.class, Pug.class, EgyptianMau.class,
                Manx.class, Cymric.class, Rat.class, Mouse.class, Hamster.class);
    }

}

// use Class name to get Class
class PetNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> pets = new ArrayList<Class<? extends Pet>>();
    private static String[] petNameArray = { "com.eric.reflect.Mutt", "com.eric.reflect.Pug",
            "com.eric.reflect.EgyptianMau", "com.eric.reflect.Manx", "com.eric.reflect.Cymric", "com.eric.reflect.Rat",
            "com.eric.reflect.Mouse", "com.eric.reflect.Hamster" };
    static {
        for (int i = 0; i < petNameArray.length; i++) {
            try {
                pets.add((Class<? extends Pet>) Class.forName(petNameArray[i]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Class<? extends Pet>> pets() {
        return pets;
    }

}

abstract class PetCreator {
    public abstract List<Class<? extends Pet>> pets();

    public Pet randomPet() throws InstantiationException, IllegalAccessException {
        return pets().get(new Random().nextInt(pets().size())).newInstance();
    }

    public List<? extends Pet> getPetList(int count) throws InstantiationException, IllegalAccessException {
        List<Pet> petList = new ArrayList<Pet>(count);
        for (int i = 0; i < count; i++) {
            petList.add(randomPet());
        }
        return petList;
    }
}

class Individual {
    private String name;

    public Individual() {
    };

    public Individual(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Person extends Individual {
    Person(String name) {
        super(name);
    }
} // /:~

// : typeinfo/pets/Pet.java

class Pet extends Individual {
    Pet(String name) {
        super(name);
    }

    Pet() {
        super();
    }
} // /:~

// : typeinfo/pets/Dog.java

class Dog extends Pet {
    Dog(String name) {
        super(name);
    }

    Dog() {
        super();
    }
} // /:~

// : typeinfo/pets/Mutt.java

class Mutt extends Dog {
    Mutt(String name) {
        super(name);
    }

    Mutt() {
        super();
    }
} // /:~

// : typeinfo/pets/Pug.java

class Pug extends Dog {
    Pug(String name) {
        super(name);
    }

    Pug() {
        super();
    }
} // /:~

// : typeinfo/pets/Cat.java

class Cat extends Pet {
    Cat(String name) {
        super(name);
    }

    Cat() {
        super();
    }
} // /:~

class EgyptianMau extends Cat {
    EgyptianMau(String name) {
        super(name);
    }

    EgyptianMau() {
        super();
    }
} // /:~

// : typeinfo/pets/Manx.java

class Manx extends Cat {
    Manx(String name) {
        super(name);
    }

    Manx() {
        super();
    }
} // /:~

// : typeinfo/pets/Cymric.java

class Cymric extends Manx {
    Cymric(String name) {
        super(name);
    }

    Cymric() {
        super();
    }
} // /:~

// : typeinfo/pets/Rodent.java

class Rodent extends Pet {
    Rodent(String name) {
        super(name);
    }

    Rodent() {
        super();
    }
} // /:~

// : typeinfo/pets/Rat.java

class Rat extends Rodent {
    Rat(String name) {
        super(name);
    }

    Rat() {
        super();
    }
} // /:~

// : typeinfo/pets/Mouse.java

class Mouse extends Rodent {
    Mouse(String name) {
        super(name);
    }

    Mouse() {
        super();
    }
}

class Hamster extends Rodent {
    Hamster(String name) {
        super(name);
    }

    Hamster() {
        super();
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
