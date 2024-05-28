package b_oop.b_inheritance.relationship_ex.makers;

public class Artist extends Maker {
    private String firstAlbum;

    public Artist(String name, String firstAlbum) {
        super(name);
        this.firstAlbum = firstAlbum;
    }

    public String getFirstAlbum() {
        return firstAlbum;
    }

    @Override
    public String toString() {
        return "Artist: " + super.getName() +
                ", FirstAlbum: " + firstAlbum;
    }
}
