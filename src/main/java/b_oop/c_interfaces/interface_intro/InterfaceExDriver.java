package b_oop.c_interfaces.interface_intro;

import b_oop.a_oop.employee_ex.Employee;

import java.util.Arrays;

public class InterfaceExDriver {

    public static void main(String[] args) {

        //understandingInterface();
        //usingInterface();
        //staticAndDefaultMethod();

    }

    public static void understandingInterface() {
        Employee emp1 = new Employee("Jim", 3000);
        Employee emp2 = new Employee("Kim", 1000);
        Employee emp3 = new Employee("Gary", 2000);
        Employee[] staffs = new Employee[] { emp1, emp2, emp3 };

        // sort requires the array element to be of type Comparable
        // as it calls Comparable compareTo() somewhere in its implementation
        // such as staffs[i].compareTo(staffs[j]) > 0
        Arrays.sort(staffs);
        for (Employee e : staffs) {
            System.out.println(e);
        }
    }

    public static void usingInterface() {
        //Playable playable = new Playable(); // Error: compile error, can't construct interface object

        // Ok to declare interface type
        Playable playableA;

        // Declared type must be initialized to class implementing interface
        // Audio class implements Playable interface
        playableA = new Audio("file.mp3");

        // instanceOf is used to check if an object implements interface
        System.out.println("Is instance of: " + (playableA instanceof Playable));
    }

    public static void staticAndDefaultMethod() {
        Streamable.displayMaxThread();
    }

}

