package b_oop.d_packages.packageA;

import java.util.ArrayList;
import java.util.List;

public class ClassA {
    final int id;                     // default
    public final String name;
    protected String path;
    private final List<String> values = new ArrayList<>();

    public ClassA(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addValues(String val) {
        values.add(val);
    }

    public void showInfo() {
        System.out.println("Id: " + id + ", Name: " + name);
        if(path != null) {
            showValues();
        }
    }

    private void showValues() {
        System.out.println("Path: " + path);
        for (String s : values) {
            System.out.println(s);
        }
    }

    protected void updatePath(String newPath) {
        if (path == null || path.isBlank()) {
            System.out.println("path not set");
        } else {
            path = path + ": " + newPath;
        }
    }
}

