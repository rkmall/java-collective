package b_oop.b_inheritance.relationship_ex;

public class OrderTracker {
    private final int orderId;
    private final String shippingAddress;
    private boolean isShipped;

    public OrderTracker(int orderId, String shippingAddress) {
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public boolean isShipped() {
        return isShipped;
    }
}
