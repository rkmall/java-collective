package b_oop.b_inheritance.relationship_ex.items;

import b_oop.b_inheritance.relationship_ex.makers.Director;
import b_oop.b_inheritance.relationship_ex.makers.Maker;

public class Movie extends MediaItem {
    private String movieName;
    private Director director;

    public Movie(int itemId,
                 String itemType,
                 double runningTime,
                 boolean isAvailable,
                 double price,
                 Director director,
                 String movieName) {
        super(itemId, itemType,runningTime, isAvailable, price);
        this.director = director;
        this.movieName = movieName;
    }

    @Override
    public Maker getMaker() {   // abstract method overridden, returns director
        return director;
    }
}
