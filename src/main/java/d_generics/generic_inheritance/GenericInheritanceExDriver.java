package d_generics.generic_inheritance;

import b_oop.a_oop.employee_ex.Employee;
import b_oop.a_oop.employee_ex.Manager;

import java.util.ArrayList;
import java.util.List;

public class GenericInheritanceExDriver {

    public static void main(String[] args) {

        //typeParamInheritanceNotInGenerics();
        //inheritanceInGenerics();

        upperBoundLowerBoundTest();

        //wildCardConcept();
        //unboundedWildCard();
        //wildcardCaptureEx();
        //typeParamVsWildcards();
    }

    public static void typeParamInheritanceNotInGenerics() {
        Manager ceo = new Manager("Jim", 2000, "CEO", 200);
        Manager cto = new Manager("Kim", 2000, "CTO", 150);

        // Generic type
        Pair<Manager> managerPair = new Pair<>(ceo, cto); // OK
        //Pair<Employee> employeePair = managerPair;  // Compile error: Pair<Manager> is not Pair<Employee>
                                                      // even though Manager is subclass of Employee

        // Raw type
        Pair rawPair = managerPair; // OK
        rawPair.setFirst(new Employee("Jim", 1000));  // Only compile-time warning
        System.out.println("First: " + rawPair.getFirst());        // No type checking: Unsafe
                                                                   // and can cause type error
        // Array type
        Manager[] managers = { ceo, cto };
        Employee[] employees = managers;    // OK
        //employees[0] = new Employee("Jim", 1000);   // Runtime error: ArrayStoreException
    }

    public static void inheritanceInGenerics() {
        Manager ceo = new Manager("Jim", 2000, "CEO", 200);
        Manager cto = new Manager("Kim", 2000, "CTO", 150);

        ArrayList<Manager> managers = new ArrayList<>(List.of(ceo, cto));
        List<Manager> boss = managers; // OK, ArrayList<Manager> is subtype of List<Manager>
    }

    // ------------------------------Wildcard--------------------------------------------

    private static void upperBoundLowerBoundTest() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        upperBound(ints);   // OK: passing List<Integer>, subtype of List<? extends Number>

        List<Number> numbers = new ArrayList<>();
        lowerBound(numbers);// OK: passing List<Number>, supertype of List<? super Integer>
    }

    private static void upperBound(List<? extends Number> numbers) {
        // extends ensures only method that returns Number can be called
        Number result =  numbers.get(1);
        //numbers.add(10);
    }

    private static void lowerBound(List<? super Integer> integers) {
        // super ensures that only method that uses Integer as parameter can be called
        integers.add(10);    // OK, supertype of ? is Integer
        //int res = numbers.get(0);  // can't use return value
    }

    public static void wildCardConcept() {
        Manager ceo = new Manager("Jim", 2000, "CEO", 200);
        Manager cto = new Manager("Kim", 2000, "CTO", 150);
        Pair<Manager> managerPair = new Pair<>(ceo, cto);

        Employee e1 = new Employee("Gina", 1000);
        Employee e2 = new Employee("Liam", 1000);
        Pair<Employee> employeePair = new Pair<>(e1, e2);

        printPair(managerPair);    // OK since printPair<? extends Employee>
        printPair(employeePair);   // OK
    }

    private static void printPair(Pair<? extends Employee> pair) {
        Employee first = pair.getFirst();       // read
        Employee second = pair.getSecond();     // read
        System.out.println("First: " + first + "Second: " + second);
    }

    private static void setPair(Pair<? extends Employee> pair) {
        Employee first = new Employee("Gina", 1000);
        Employee second = new Employee("Liam", 1000);
        //pair.setFirst(first);   // write, Compile time error
        //pair.setSecond(second); // write, Compile time error
    }

    // Supertype bound example
    public static void minMaxBonus(Manager[] a, Pair<? super Manager> pair) {
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (min.getBonus() > a[i].getBonus()) {
                min = a[i];
            }

            if (max.getBonus() < a[i].getBonus()) {
                max = a[i];
            }
        }
        pair.setFirst(min);   // OK, write
        pair.setSecond(max);  // OK, write
        //Manager boss = result.getFirst();   // Compile error: read, can't return value
        System.out.println("First: " + pair.getFirst() + "Second: " + pair.getSecond());

        swapHelper(pair);   // pair is capture of <? super Manager>
        System.out.println("First: " + pair.getFirst() + "Second: " + pair.getSecond());
    }

    // Unbounded wildcard example ----------------------------------------------------------
    public static void unboundedWildCard() {
        Manager ceo = new Manager("Jim", 2000, "CEO", 200);
        Manager cto = new Manager("Kim", 2000, "CTO", 150);
        Pair<Manager> managerPair = new Pair<>(ceo, cto);

        printEmployeesUnboundedWildcard(managerPair);   // OK: expects Pair<?>
        //printEmployeesObjects(managerPair);             // Compile error: expects Pair<Object>
    }

    // Using generic unbounded type
    private static void printEmployeesUnboundedWildcard(Pair<?> pair) {
        Object first = pair.getFirst();
        Object second = pair.getSecond();
        System.out.println("First: " + first + "Second: " + second);

        //pair.setFirst(pair.getSecond());    // Compile error
        //pair.setSecond(pair.getFirst());    // Compile error
    }

    // Using Raw type
    private static void printEmployeesObjects(Pair<Object> pair) {
        Object first = pair.getFirst();
        Object second = pair.getSecond();
        System.out.println("First: " + first + "Second: " + second);

        pair.setFirst(pair.getSecond());    // OK
        pair.setSecond(pair.getFirst());    // OK
    }

    // Wildcard capture -----------------------------------------------------------------------
    public static void wildcardCapture1(Pair<?> p) {
        //? t = p.getFirst();           // Syntax error
        //p.setSecond(p.getFirst());    // Syntax error
    }

//    public static <? extends Employee> void wildcardCapture3() {    // Syntax error
//    }

//    public static <T> Pair<T> wildcardCapture2() {      // OK, type parameter
//        ...
//    }

    private static void wildcardCaptureEx() {
        Manager ceo = new Manager("Jim", 2000, "CEO", 200);
        Manager cto = new Manager("Kim", 2000, "CTO", 150);
        Manager[] managers = new Manager[] { ceo, cto };
        Pair<Manager> managerPair = new Pair<>(ceo, cto);
        minMaxBonus(managers, managerPair );
    }

    private static <T> void swapHelper(Pair<T> pair) {
        T t = pair.getFirst(); // the captured <? super Manager> assigned to generic type T
        pair.setFirst(pair.getSecond());
        pair.setSecond(t);
    }

    //-----------------------------Type Parameter Vs Wildcards---------------------------------
    public static void typeParamVsWildcards() {
        List<String> src = List.of("apple", "ball", "cat");
        List<String> dest = new ArrayList<>();
        copyUsingTypeParam(dest, src);
        dest.forEach(System.out::println);

        Manager ceo = new Manager("Jim", 2000, "CEO", 200);
        Manager cto = new Manager("Kim", 2000, "CTO", 150);
        List<Manager> from = List.of(ceo, cto);
        List<Employee> to = new ArrayList<>();
        //copyUsingTypeParam(employees, managers);  // Compile error: no generic type inheritance

        copyUsingWildcards(to, from);    // OK, wildcard enables generic type inheritance
        to.forEach(System.out::println);
    }

    /**
     * This method uses Type variable, and no wildcard
     * It is more restrictive because it requires both src and dest
     * lists with the same exact type
     */
    private static <T> void copyUsingTypeParam(List<T> dest, List<T> src) {
        for (T item : src) {
            dest.add(item);
        }
    }

    /**
     * This method uses Type variable with wildcard
     * It is more flexible and handles subtype and supertype
     * For src list: ? extends T because we need to read from the src
     * For dest list: ? super T because we need to write to the dest
     */
    private static <T> void copyUsingWildcards(List<? super T> dest, List<? extends T> src) {
        for (T item : src) {
            dest.add(item);
        }
    }
}
