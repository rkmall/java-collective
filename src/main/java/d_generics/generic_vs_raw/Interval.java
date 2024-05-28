package d_generics.generic_vs_raw;

import d_generics.generic_class.KeyValPair;

import java.io.Serializable;

// Generic type: Representation of Interval class
public class Interval<T extends Comparable & Serializable> implements Serializable {
    private T lower;
    private T upper;

    public Interval(T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
    }
}

// Raw type: Representation of Interval class (above) in VM
class RawTypeInterval implements Serializable {
    private Comparable lower;
    private Comparable upper;

    public RawTypeInterval(Comparable lower, Comparable upper) {
        this.lower = lower;
        this.upper = upper;
    }
}
