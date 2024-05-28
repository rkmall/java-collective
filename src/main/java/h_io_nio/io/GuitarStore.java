package h_io_nio.io;

import java.util.HashMap;
import java.util.List;

public class GuitarStore {
    private final HashMap<String, Guitar> gtrs = new HashMap<>();

    public void addGuitar(Guitar gtr) {
        if (gtr != null) {
            String name = gtr.getName();
            gtrs.put(name, gtr);
            System.out.println(name + " added to store.");
        }
    }

    public void addAllGuitar(List<Guitar> list) {
        if (!list.isEmpty()) {
            for (Guitar gtr : list) {
                addGuitar(gtr);
            }
        }
    }

    public HashMap<String, Guitar> getAllGuitars() {
        return this.gtrs;
    }
}
