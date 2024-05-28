package b_oop.b_inheritance.relationship_ex.items;

import b_oop.b_inheritance.relationship_ex.makers.Artist;
import b_oop.b_inheritance.relationship_ex.makers.Maker;

public class Album extends MediaItem {
    private String albumName;
    private int noOfSongs;
    private Artist artist;

    public Album(int itemId,
                 String itemType,
                 double runningTime,
                 boolean isAvailable,
                 double price,
                 Artist artist,
                 String albumName,
                 int noOfSongs) {
        super(itemId, itemType, runningTime, isAvailable, price);
        this.artist = artist;
        this.noOfSongs = noOfSongs;

    }

    @Override
    public Maker getMaker() { // abstract method overridden, returns artist
        return artist;
    }
}
