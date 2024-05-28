package h_io_nio.io;

import java.io.Serializable;

public class Company implements Serializable { // must implement Serializable interface
    private final String name;
    private final String location;

    public Company(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() { return name; }

    public String getLocation() { return location; }

    @Override
    public String toString() {
        return "Company: name = " + name + ", location = " + location;
    }
}
