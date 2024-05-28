package b_oop.c_interfaces.lambda_ex.functional_interfaces_ex;

import java.util.function.*;

public class FunctionalInterfaceDriverEx {

    public static void main(String[] args) {

        functionalInterfacesEx();
    }

    public static void functionalInterfacesEx() {
        // Param: String
        // Return: Integer
        Function<String, Integer> fun = s -> s.length();

        // Params: String, Integer
        // Returns: String
        BiFunction<String, Integer, String> biFun = (s, i) -> {
            return s + ":" + (s.length() + i);
        };

        // Param: String
        // Return: String
        UnaryOperator<String> unaryFun = s -> "prefix" + s;

        // Param: String, String
        // Return: String
        BinaryOperator<String> binFun = (s1, s2) -> s1 + s2;

        // Param: String
        // Return: void
        Consumer<String> consumer = s -> s.toLowerCase();

        // Param: String, Integer
        // Return: void
        BiConsumer<String, Integer> biConsumer = (s, i) -> System.out.println(s.length() + i);

        // Param: none
        // Return: String
        Supplier<String> supplier = () -> "code: 123";

        // Param: String
        // Return: boolean
        Predicate<String> predicate = s -> s.length() > 10;

        // Params: String, Integer
        // Returns: boolean
        BiPredicate<String, Integer> biPredicate = (s, i) -> s.length() == i;

        // Param: none
        // Return: void
        Runnable runnable = () -> System.out.println("Running");
    }
}
