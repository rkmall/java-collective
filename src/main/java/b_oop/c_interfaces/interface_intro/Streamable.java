package b_oop.c_interfaces.interface_intro;

public interface Streamable {
    double MAX_THREADS = 8;         // constant: public static final

    void play(String filename);

    void stream(String filename);

    static void displayMaxThread() {    // static method
        System.out.println("Max Thread = " + MAX_THREADS);
    }
}
