package c_reflection;

import b_oop.a_oop.employee_ex.Employee;

import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public class ReflectionEx {

    public static void main(String[] args) {
        //obtainingClassInstance();

//        Employee e = new Employee();
//        getClassMethodUsage(e);

//        Object obj;
//        try {
//             obj = usingNewInstance(Employee.class);
//        } catch (IllegalAccessException | InstantiationException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(obj);

        //obtainClassDetailInfo(Employee.class);

        Employee e = new Employee("Jim", 1000);
        invokeMethod(e);
    }

    public static void obtainingClassInstance() {
        // Using object reference
        String s = "hello";
        Class<? extends String> clazz = s.getClass();
        System.out.println("String: " + clazz.getName());

        // Using type
        Class<Integer> num = int.class;
        System.out.println("Integer: " + num.getName());

        Class<double[]> a1 = double[].class;
        System.out.println("double[]: " + a1.getName());

        // Using class name
        Class<?> emp1 = null;
        try {
            emp1 = Class.forName(Employee.class.getName());
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        assert emp1 != null;
        System.out.println("Employee: " + emp1.getSimpleName());
    }

    public static void getClassMethodUsage(Object o) {
        if (o.getClass() == Employee.class) {
            System.out.println("OK");
        } else {
            System.out.println("Not Employee class");
        }
    }

    public static Object usingNewInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        try {
            // return from Caller (AccessController.doPrivileged) to this method
            return AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                @Override
                public Object run() throws Exception {
                    try {
                        // return from Callback to Caller (Access Controller)
                        return clazz.getDeclaredConstructor().newInstance();
                    } catch (InvocationTargetException | NoSuchMethodException e) {
                        throw (InstantiationException) (new InstantiationException()).initCause(e);
                    }
                }
            });
        } catch (PrivilegedActionException pae) {
            Exception e = pae.getException();
            if (e instanceof InstantiationException) throw (InstantiationException)e;
            throw (IllegalAccessException)e;
        }
    }

    public static void obtainClassDetailInfo(Class<?> clazz) {
        System.out.println( "Simple name: " + clazz.getSimpleName() + "\n" +
                "Full name: " + clazz.getName() + "\n" +
                "Package name: " + clazz.getPackageName() + "\n" +
                "Type name: " + clazz.getTypeName()
        );

        printFields(clazz);
        printConstructors(clazz);
        printMethods(clazz);
    }

    private static void printFields(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields(); // get fields array
        // Iterate through the fields array
        for (Field f : declaredFields) {
            Class<?> fieldType = f.getType();   // get field type
            String fieldName = f.getName();     // get field name
            String modifiers = Modifier.toString(f.getModifiers()); // get field modifiers
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " ");  // print modifier if present
            }
            System.out.println(fieldType.getName() + " " + fieldName + ";" );
        }
    }

    private static void printConstructors(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();// get ctor array
        // Iterate through the ctor array
        for (Constructor<?> c : constructors) {
            String name = c.getName();                                  // get ctor name
            String modifiers = Modifier.toString(c.getModifiers());     // get ctor modifiers
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " "); // print modifier if present
            }

            System.out.print(name + "(" );
            Class<?>[] paramTypes = c.getParameterTypes(); // get ctor params
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName()); // print params names
            }
            System.out.println(name + ");" );
        }
    }

    private static void printMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();  // get method array
        // Iterate through the method array
        for (Method method : methods) {
            String name = method.getName();                              // get method name
            Class<?> returnType = method.getReturnType();                // get method return type
            String modifiers = Modifier.toString(method.getModifiers()); // get method modifiers
            if (!modifiers.isEmpty()) {
                System.out.print(modifiers + " ");  // print modifier if present
            }

            System.out.print(returnType.getName() + " " + name + "(");  // print return type
            Class<?>[] paramTypes = method.getParameterTypes();         // get method params
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());              // print params name
            }
            System.out.println(");");
        }
    }

    public static void invokeMethod(Object o) {
        Class<?> clazz = o.getClass();
        // Invoking Accessor
        Method method;
        String res;
        try {
            method = clazz.getMethod("getName", null); // get method
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
        try {
            res = (String) method.invoke(o);    // invoke method
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(res);

        // Invoking Mutator
        Method method1;
        Double result;
        try {
            method1 = clazz.getMethod("raiseSalary", double.class);     // get method
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
        try {
            result = (Double) method1.invoke(o, 1.0);   // invoke method
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(result);
        System.out.println(o);
    }
}
