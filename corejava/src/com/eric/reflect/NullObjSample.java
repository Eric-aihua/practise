package com.eric.reflect;

import java.util.ArrayList;

/**

 * use NULLOBJ to replace null object

 *

 * archive $ProjectName: $

 * @author Admin

 * @version $Revision: $ $Name: $

 */
public class NullObjSample {
    public static final String PCE_VERSION_CONTROL = "@(#) $RCSfile: $, $Revision: $, $Date: $";

    public static void main(String[] args) {
        Staff staff = new Staff("President", "CTO", "Marketing Manager", "Product Manager", "Project Lead",
                "Software Engineer", "Software Engineer", "Software Engineer", "Software Engineer", "Test Engineer",
                "Technical Writer");
        staff.fillPosition("President", new PersonSample("Me", "Last", "The Top, Lonely At"));
        staff.fillPosition("Project Lead", new PersonSample("Janet", "Planner", "The Burbs"));
        if (staff.positionAvailable("Software Engineer"))
            staff.fillPosition("Software Engineer", new PersonSample("Bob", "Coder", "Bright Light City"));
        System.out.println(staff);
    }
}

interface Null {
}

class Staff extends ArrayList<Position> {
    public void add(String title, PersonSample person) {
        add(new Position(title, person));
    }

    public void add(String... titles) {
        for (String title : titles)
            add(new Position(title));
    }

    public Staff(String... titles) {
        add(titles);
    }

    public boolean positionAvailable(String title) {
        for (Position position : this)
            if (position.getTitle().equals(title) && position.getPerson() == PersonSample.NULL)
                return true;
        return false;
    }

    public void fillPosition(String title, PersonSample hire) {
        for (Position position : this)
            if (position.getTitle().equals(title) && position.getPerson() == PersonSample.NULL) {
                position.setPerson(hire);
                return;
            }
        throw new RuntimeException("Position " + title + " not available");
    }

}

class Position {
    private String title;
    private PersonSample person;

    public Position(String title, PersonSample person) {
        super();
        this.title = title;
        if (person == null) {
            this.person = PersonSample.NULL;
        } else {
            this.person = person;
        }
    }

    public Position(String title) {
        super();
        this.title = title;
        this.person = PersonSample.NULL;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the person
     */
    public PersonSample getPerson() {
        return person;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(PersonSample person) {
        if (person == null) {
            this.person = PersonSample.NULL;
            return;
        }
        this.person = person;
    }

    public String toString() {
        return "Position: " + title + " " + person + "\n";
    }

}

class PersonSample {
    public final String first;
    public final String last;
    public final String address;

    public PersonSample(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    public String toString() {
        return "Person: " + first + " " + last + " " + address;
    }

    public static NullPerson NULL = new NullPerson();

    public static class NullPerson extends PersonSample implements Null {

        public NullPerson() {
            super("null", "Null", "Null");
        }

        public String toString() {
            return "NULLPERSON";
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
