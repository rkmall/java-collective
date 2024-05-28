package d_generics.generic_class;

public class KeyValPair<K, V> { // type parameter specified at the end of Class header
    private final K key;        // variable of type T
    private final V value;      // variable of type U

    public KeyValPair(K key, V second) {
        this.key = key;
        this.value = second;
    }

    public K getKey() {   // returns type T
        return key;
    }
    public V getValue() {  // returns type U
        return value;
    }
}
