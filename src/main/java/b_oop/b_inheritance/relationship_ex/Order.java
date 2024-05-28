package b_oop.b_inheritance.relationship_ex;

import b_oop.b_inheritance.relationship_ex.items.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int orderNumber;

    // Aggregation: Order has Items object as field but Order doesn't
    // create Item, it is passed from outside in the constructor.
    // So, if Order dies, Item can exist independently.
    // NOTE: Although new ArrayList is created here, but the items are created
    //       and passed from outside to be added to the array list.
    //       This allows the items to exist independently.
    private List<MediaItem> mediaItems = new ArrayList<>();

    // Composition: Order has OrderTracker object as field and creates
    // OrderTracker in finalizeOrder() method i.e. OrderTracker
    // can't exist independently. If Order dies, OrderTracker dies too.
    private OrderTracker orderTracker;
    private boolean isPaid;

    public Order(int orderNumber, List<MediaItem> mediaItems) {
        this.orderNumber = orderNumber;
        this.mediaItems = mediaItems;
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    // Dependence: This method uses Account object passed from outside.
    public void addItems(MediaItem newMediaItem, Account account) {
        double credit = account.getBalance();
        double itemPrice = newMediaItem.getPrice();
        if (credit > 0.0 && credit >= itemPrice) {
            mediaItems.add(newMediaItem);
            account.balanceAccount(itemPrice);
        } else {
            System.out.println("No sufficient credit");
        }
    }

    public void finalizeOrder(Account account) {
        if (orderTracker == null) {
            orderTracker = new OrderTracker(orderNumber, account.getAccountAddress());
        }
        isPaid = true;
    }

    public void showItems() {
        for (MediaItem mediaItem : mediaItems) {
            System.out.println(mediaItem);
        }
    }

    public OrderTracker getOrderTracker() {
        return orderTracker;
    }

    public List<MediaItem> getItems() {
        return mediaItems;
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        return "Order: " + "Order number: " + orderNumber +
                ", Items: " + mediaItems +
                ", Paid: " + isPaid;
    }
}
