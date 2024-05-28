package z_memory_and_gc;

public class MemoryAllocEx {

    public static void main(String[] args) {

        localStaticVariable();
    }

    public static void localVariable() {
        // Local variable "s" references MClass object which is accessible from
        // the application whenever needed so, it has a reference chain from GC root
        MClass s = new MClass("local var");

        // Variable "s" is set to null that makes MClass object not referenced by any variable,
        // this breaks the reference chain from GC and object will be garbage collected
        s = null;
    }


    public static void localStaticVariable() {
        MClassStatic m = new MClassStatic("property");
        m.INSTANCE = new MClassStatic("parameter");

        // Even though "m" is set to null, the static variable
        // INSTANCE will still have reference chain from GC through
        // its Class (referenced by ClassLoader) and won't be garbage collected.
        m = null;
    }
}


class MClassStatic {
    /*
     * Static variables are associated with Class, and only gets cleaned-up
     * whe the ClassLoader responsible for loading this Class gets cleaned-up.
     *
     *     INSTANCE <-- this class <-- this class's ClassLoader
     *
     * This also applies to Constants (public static final).
     */
    public static MClassStatic INSTANCE;

    public MClassStatic(String name) {}
}


class MClass {
    public MClass(String name) {}
}






