package b_oop.c_interfaces.lambda_ex.closure_ex;

import java.util.Arrays;
import java.util.Comparator;

public class ClosureExDriver {

    public static void main(String[] args) {

        outerScopeState("Java");

        ClosureExDriver exDriver = new ClosureExDriver();
        exDriver.thisRefersToTheClassThis();

        //stateInAnonClass();
        //stateInsideLambda();
        //stateOutsideLambda();
        //stateOutsideLambdaModifySolution1();
        //stateOutsideLambdaModifySolution2();

        String key = "Python";
        //System.out.println(filterKeyAnonEx(key));
        //System.out.println(filterKeyLambdaProblem(key));
        //System.out.println(filterKeyLambdaSolution(key));
    }

    public static void outerScopeState(String code) {
        String state = "ON";    // outer scope state
        displayContent((String s) -> {
            System.out.println("Method param: " + code  // outer scope
                    + "\nState param: " + state // outer scope
                    + "\nLambda param: " + s);  // lambda scope
        });
        System.out.println("main completed");
    }

    // Caller
    private static void displayContent(MyConsumer<String> consumer) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            consumer.consume("lambda arg");
        }).start();     // run on a new thread
    }

    public static void lambdaVariableShadowing() {
        //String first = "Jim";
        Comparator<String> comparator = (first, second) -> first.length() - second.length();
        // Error: first is already declared in the outer scope
    }

    public void thisRefersToTheClassThis() {
        MyConsumer<String> consumer = s -> System.out.println(this.toString());
        consumer.consume(":");
    }

    /**
     * Anonymous classes are not closure in Java:
     * Below, the state from the outer scope i.e.
     * outside the overridden function can be modified
     * inside the overridden function.
     */
    public static void stateInAnonClass() {
        MyConsumer<String> consumer = new MyConsumer<String>() {
            int id = 0;     // state, outside function body
            @Override
            public void consume(String item) {
                System.out.println(item + ", id: " + ++id); // OK to modify
            }
        };
        consumer.consume("Hello");
    }

    /**
     * Lambdas are closure:
     * Below, the state from the outer scope i.e.
     * outside the lambda scope can't be modified
     * inside the lambda expression.
     */
    public static void stateOutsideLambda() {
        int id = 0;         // state, outside lambda
        MyConsumer<String> consumer = item -> {
            //System.out.println(item + ", id: " + ++id); // Error: captured state is final,
        };                                               // can't modify state
        consumer.consume("Hello");
    }

    /**
     * Lambda expression and local variable:
     * Below, the local variable id is inside the lambda expression
     * and can be modified.
     */
    public static void stateInsideLambda() {
        MyConsumer<String> consumer = item -> {
            int id = 0;     // local state, inside lambda scope
            System.out.println(item + ", id: " + ++id); // OK to modify
        };
        consumer.consume("Hello");
    }

    /**
     * Lambda expression and outer scope variable solution:
     * Below, the outer scope variable id can't be modified inside lambda expression.
     * To modify outer scope variable, introduce local variable inside lambda expression
     * and assigned it with the outer scope variable.
     */
    public static void stateOutsideLambdaModifySolution1() {
        int id = 0;         // state, outside lambda
        MyConsumer<String> consumer = item -> {
            int temp = id;              // introduce local var assigned with the state
            System.out.println(item + ", id: " + ++temp); // OK: modifying local var
        };
        consumer.consume("Hello");
    }

    public static void stateOutsideLambdaModifySolution2() {
        int[] ids = new int[1];         // array is object type
        MyConsumer<String> consumer = item -> {
            System.out.println(item + ", id: " + ++ids[0]); // OK: object reference can be changes
        };                                                  // but the content can be modified
        consumer.consume("Hello");
    }

    public static String filterKeyAnonEx(String code) {
        StringFilter fun = new StringFilter() {
            String[] codes = new String[] { "C", "C++", "Java", "Kotlin", "Python" };
            @Override
            public String filter(String key) {
                codes = new String[] { "Go", "Scala" }; // OK: reassigned
                for (String s : codes) {
                    if (s.equals(key)) {
                        return key;
                    }
                }
                return null;
            }
        };
        return fun.filter(code);
    }

    public static String filterKeyLambdaProblem(String code) {
        String [] codes = new String[] { "C", "C++", "Java", "Kotlin", "Python" };  // code is always final
        //codes = new String[] { "C", "C++", "Java", "Kotlin", "Python", "Go" };    // Error: inside lambda scope
        StringFilter fun = key -> {                                                 // codes is final
            //codes = new String[] { "C", "C++", "Java", "Kotlin", "Python", "Go" };// Error: codes is final
            for (String s : codes) {
                if (s.equals(key)) {
                    return key;
                }
            }
            return null;
        };
        return fun.filter(code);
    }

    public static String filterKeyLambdaSolution(String code) {
        String [] codes = new String[] { "C", "C++", "Java", "Kotlin", "Python" };
        StringFilter fun = key -> {
            String[] temp = Arrays.copyOf(codes, codes.length + 1); // OK: local var, copy of original
            temp[temp.length - 1] = "Go";
            for (String s : temp) {
                if (s.equals(key)) {
                    return key;
                }
            }
            return null;
        };
        return fun.filter(code);
    }
}
