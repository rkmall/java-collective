package b_oop.c_interfaces.interface_intro;

public class StreamX implements Streamable {
    private String name;

    public StreamX(String name) {
        this.name = name;
    }

    @Override
    public void play(String filename) {     // implementing interface Playable method
        System.out.println("Now playing: " + filename);
    }

    @Override
    public void stream(String filename) {   // implementing interface Streamable method
        System.out.println("Now streaming: " + filename);
    }
}
