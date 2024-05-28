package b_oop.c_interfaces.interface_intro;

public class Audio implements Playable {
    private String name;
    public Audio(String name) { this.name = name; }

    @Override
    public void play(String filename) {
        System.out.println("Now playing audio: " + filename);
    }
}
