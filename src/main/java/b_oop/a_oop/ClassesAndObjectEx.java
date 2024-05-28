package b_oop.a_oop;

import java.util.Date;

public class ClassesAndObjectEx {

    public static void main(String[] args) {

        objectDeclarationAndInitialization();
        //variableAndObject();
    }

    public static void objectDeclarationAndInitialization() {
        //---------------Declaration of object variable-----------------------
        // It only defines object variable that can refer to Data object later.
        // It does not create object and does not refer to any object yet!
        Date start;

        //---------------Initialization of object variable--------------------
        // Initializing variable with newly constructed object.
        // The variable now refers to the newly constructed object allocate in the memory.
        Date today = new Date();

        // Initializing variable by setting the variable to refer to existing object.
        // The variable now refers to the existing object in the memory.
        start = today;

        // Both variables start and today point to the same object in memory.

        System.out.println("Start: " + Integer.toHexString(System.identityHashCode(start)) +
                        "\nToday: " + Integer.toHexString(System.identityHashCode(today)));

        System.out.println("Start: " + start + "\nToday: " + today);
    }

    public static void variableAndObject() {

        // Object variable "today":
        // - is a stack variable.
        // - contains reference/address to the Date object on heap.
        // - it is created when this method is called
        //   and destroyed when this method completes.

        // new Date():
        // - constructs a new Date object lives in heap memory.
        // - it is eligible for GC, if no variable references it
        Date today = new Date();
        System.out.println(today);
    }
}




