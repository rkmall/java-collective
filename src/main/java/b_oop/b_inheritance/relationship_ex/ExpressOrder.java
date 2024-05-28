package b_oop.b_inheritance.relationship_ex;

import b_oop.b_inheritance.relationship_ex.items.MediaItem;

import java.util.List;

public class ExpressOrder extends Order {
    private static int TIME_LIMIT = 2; // additional variable to keep track of order

    public ExpressOrder(int orderNumber, List<MediaItem> mediaItems) {
        super(orderNumber, mediaItems);
    }

    // Super class method overridden for specialized operation
    @Override
    public void addItems(MediaItem newMediaItem, Account account) {
        if (TIME_LIMIT > 0) {
            super.addItems(newMediaItem, account);
            TIME_LIMIT = 0;
        } else {
            System.out.println("Express order already delivered");
        }
    }
}
