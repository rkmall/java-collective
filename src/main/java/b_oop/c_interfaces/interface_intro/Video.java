package b_oop.c_interfaces.interface_intro;

public class Video implements Playable {
    private String name;
    public Video(String name) { this.name = name; }

    @Override
    public void play(String filename) {
        System.out.println("Now playing video: " + filename);
    }
}
