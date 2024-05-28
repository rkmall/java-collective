package b_oop.b_inheritance.enumclass_ex;

import java.util.EnumMap;
import java.util.EnumSet;

public class EnumEx {

    public static void main(String[] args) {
        Size small = Size.SMALL;
        Size medium = Size.MEDIUM;
        Size large = Size.LARGE;

        // EnumConstant.name() return Enum constant name in string
        String name = Size.SMALL.name();

        // Use == instead of equals()
        Size small1 = Size.SMALL;
        System.out.println("Equals: " + (small == small1)); // true because same enum constant

        // Enum.valueOf() sets Enum variable using Enum class and Enum Constant
        Size xl = Enum.valueOf(Size.class, "EXTRA_LARGE");
        System.out.println(xl);

        // EnumClass.values() returns the array of all values declared in the Enum class
        Size[] values = Size.values();
        for (Size size : values) {
            System.out.println(size);
        }

        // EnumConstant.ordinal() returns the position of an enum constant
        // in the enum declaration
        System.out.println("Enum const SMALL position: " + Size.SMALL.ordinal());
        System.out.println("Enum const LARGE position: " + Size.LARGE.ordinal());


        //----------------------------------------------------------------------------

        // EnumSet
//        EnumSet<Level> enumSet = EnumSet.of(Level.LOW, Level.MEDIUM, Level.HIGH);
//        enumSet.forEach(System.out::println);

        // EnumMap
//        EnumMap<Level, String> enumMap = new EnumMap<>(Level.class);
//        enumMap.put(Level.HIGH, "high");
//        enumMap.put(Level.MEDIUM, "medium");
//        enumMap.put(Level.LOW, "low");
//        enumMap.forEach((level, str) -> System.out.println(level  + ":" + str));
    }
}

enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    private final String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}


enum Level implements Display {

    HIGH(3) {
        @Override
        public String asLowerCase() {
            return this.name().toLowerCase();
        }
    },

    MEDIUM(2) {
        @Override
        public String asLowerCase() {
            return this.name().toLowerCase();
        }
    },

    LOW(1) {
        @Override
        public String asLowerCase() {
            return this.name().toLowerCase();
        }
    };

    private final int levelCode;

    Level(int levelCode) {
        this.levelCode = levelCode;
    }

    // Enum class can have abstract method
    // Each constant of the enum class must implement it
    public abstract String asLowerCase();

    // Override interface method
    @Override
    public void displayInfo() {
        System.out.println(this.name() + ":" + levelCode);
    }
}


interface Display {
    public void displayInfo();
}
