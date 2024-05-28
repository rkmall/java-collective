package b_oop.b_inheritance.relationship_ex.items;

import b_oop.b_inheritance.relationship_ex.makers.Maker;

public abstract class MediaItem {
    private final int itemId;
    private final String itemType;
    private final double runningTime;
    private boolean isAvailable;
    private double price;

    public MediaItem(int itemId,
                     String itemType,
                     double runningTime,
                     boolean isAvailable,
                     double price) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.runningTime = runningTime;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public abstract Maker getMaker();   // abstract method

    public int getItemId() { return itemId; }

    public String getItemType() { return itemType; }

    public double getRunningTime() { return runningTime; }

    public boolean isAvailable() { return isAvailable; }

    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Item: " + "Id: " + itemId +
                ", Type: " + itemType +
                ", Maker: " + getMaker() +  // calls appropriate method for getMaker()
                ", Running time: " + runningTime +
                ", Availability: " + isAvailable;
    }
}
