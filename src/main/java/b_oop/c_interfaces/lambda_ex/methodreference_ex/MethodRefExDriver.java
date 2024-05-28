package b_oop.c_interfaces.lambda_ex.methodreference_ex;

import b_oop.a_oop.employee_ex.Employee;

import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.Flow;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodRefExDriver {

    public static void main(String[] args) {

        //methodReferenceIntro();
        //methodReferenceTypes();
        //usingMethodReference();
        //constructorReference();
        //createListOfObjects();


        BiFunction<String, Locale, String> stringLocaleStringBiFunction = String::toUpperCase;
    }

    public static void methodReferenceIntro() {
        // Using lambda expression:
        //  - Assigning interface variable with the lambda expr.
        //  - Notice lambda syntax that contains params and expressions.
        Displayable displayLambda = s -> System.out.println(s);

        // Using method reference:
        //  - Assigning interface variable with method reference.
        //  - Notice method reference doesn't require to specify params.
        //  - The params are inferred from the method referenced by
        //    the method reference.
        Displayable displayMethRef = System.out::println;
    }

    public static void methodReferenceTypes() {
        // Reference to static method of a class
        //  - Class::staticMethod
        Displayable displayFun = Example::displayContent;

        // Reference to instance method of a class (object)
        //  - object::instanceMethod
        Example example = new Example("example");
        Listable listFun = example::listContent;

        // Reference to instance method of arbitrary object
        //  - Class::instanceMethod
        //  - instance method of arbitrary object because the first param becomes
        //    the implicit param/receiver/target of the method.
        Comparator<String> comparator =  String::compareTo;
        // Same as above
        Comparator<String> comparator1 = (x, y) -> x.compareTo(y);

        Function<String, String> fun = String::toLowerCase;
    }

    public static void usingMethodReference() {
        // Class::staticMethod
        String[] strings = new String[] { "dog", "apple", "ball" };
        printUpper(strings, Example::displayContent);

        // object::instanceMethod
        Example example = new Example("example");
        printContent(strings, example::listContent);

        // Class::instanceMethod
        Arrays.sort(strings, String::compareToIgnoreCase);
        // Same as above
        Arrays.sort(strings, (s1, s2) -> s1.compareToIgnoreCase(s2));
        printContent(strings, example::listContent);
    }

    // Caller
    private static void printUpper(String[] content, Displayable displayable) {
        for (String s : content) {
            displayable.execute(s.toUpperCase());
        }
    }

    // Caller
    private static void printContent(String[] content, Listable listable) {
        int len = listable.listContent(content);
        System.out.println("Array length: " + len);
    }

    public static void constructorReference() {
        Example ex =  createObject("example", Example::new);
        System.out.println(ex);
    }

    // Caller
    private static Example createObject(String name, Creator creator ) {
        return creator.create(name);
    }

    private static void createListOfObjects() {
        ArrayList<String> names = new ArrayList<>(List.of("Jim", "Kim", "Kale"));
        Stream<Example> stream = names.stream().map(Example::new);      // constructor called for each element
        List<Example> examples = stream.collect(Collectors.toList());
        examples.forEach(System.out::println);
    }
}
