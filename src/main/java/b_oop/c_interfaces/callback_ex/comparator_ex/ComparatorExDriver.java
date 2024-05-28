package b_oop.c_interfaces.callback_ex.comparator_ex;

import b_oop.a_oop.employee_ex.Employee;

import java.util.*;

public class ComparatorExDriver {

    public static void main(String[] args) {
        //stringLengthComparatorEx();
        employeeSalaryComparatorEx();

    }

    public static void stringLengthComparatorEx() {
        List<String> strings = new ArrayList<>(List.of("cat", "apple", "ball"));
        strings.forEach(System.out::println);

        // Callback
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };

        // collection.sort() is Caller that will call the Callback at some point.
        strings.sort(comparator);

        strings.forEach(System.out::println);
    }

    public static void employeeSalaryComparatorEx() {
        Employee emp1 = new Employee("Jim", 2000);
        Employee emp2 = new Employee("Kim", 100);
        Employee emp3 = new Employee("Gary", 4000);
        ArrayList<Employee> staffs = new ArrayList<>(List.of(emp1, emp2, emp3));
        staffs.forEach(System.out::println);

        // collection.sort is a Caller that will call the Callback (passed on call site in
        // this case) at some point.
        staffs.sort(Comparator.comparing(Employee::getName));
        staffs.forEach(System.out::println);
    }
}
