package b_oop.b_inheritance.objectclass_ex;

import b_oop.a_oop.employee_ex.Employee;

import java.util.Arrays;

public class ObjectEx {

    public static void main(String[] args) {

        System.out.println(stringHashCodeEx("OK"));    // 2524
        System.out.println(stringHashCodeEx("Hello")); // 69609650

        defaultToStringEx();

    }

    public static void ObjectTypeEx() {
        Object obj = new Employee();
        if (obj instanceof Employee) {
            Employee e = (Employee) obj;    // object
        }

        Employee[] staffs = new Employee[3];
        obj = staffs;       // object array
        obj = new int[3];   // primitive array
    }

    public static int stringHashCodeEx(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);       // charAt(i) returns ASCII value of that char
        }
        return h;
    }

    public static void defaultToStringEx() {
        System.out.println(System.in);          // prints in the format "classname@hashcode"

        // Use static Arrays.toString() to print arrays elements
        int[] A = new int[] { 1, 2, 3, 4, 5 };
        System.out.println(A);                  // prints "[I@...." where [I denotes array
        System.out.println(Arrays.toString(A)); // prints the array elements

        // Use static Arrays.deepToString() to print element of multidimensional array
        int[][] M = new int[][] {
                {1,2,3},
                {11, 22, 33}
        };
        System.out.println(Arrays.deepToString(M)); // prints the array elements
    }
}
