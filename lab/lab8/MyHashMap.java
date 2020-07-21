import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V>  implements Map61B<K, V>{

    private int count;
    private int size;
    private double factor;

    private class Dict{
        private K key;
        private V val;

        private Dict(K k, V v) {
            key = k;
            val = v;
        }
    }

    private ArrayList<ArrayList<Dict>> buckets;
    private HashSet<K> keySet;

    public MyHashMap() {
        this(16);
    }
    public MyHashMap(int initialSize) {
        this(initialSize,.75);
    }
    public MyHashMap(int initialSize, double loadFactor) {
        size = initialSize;
        factor = loadFactor;
        clear();
    }

    private int hashing(K key, int num) {
        return (key.hashCode() & 0x7fffffff) % num;
    }

    @Override
    public void clear() {
        buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<Dict>());
        }
        keySet = new HashSet<>();
        count = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("Invalid Argument");
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Invalid Argument");
        int idx = hashing(key, size);
        ArrayList<Dict> temp = buckets.get(idx);
        for (Dict d: temp) {
            if (d.key.equals(key)) {
                return d.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void put(K key, V val) {
        if (key == null || val == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }

        // update
        if (containsKey(key)) {
            int idx = hashing(key, size);
            ArrayList<Dict> temp = buckets.get(idx);
            for (Dict d: temp) {
                if (d.key.equals(key)) {
                    d.val = val;
                    return;
                }
            }
        }

        // resize
        if (count >= size*factor) {
            ArrayList<ArrayList<Dict>> bucketsNew = new ArrayList<>();
            for (int i = 0; i < size*2; i++) {
                bucketsNew.add(new ArrayList<Dict>());
            }
            for (K k: keySet) {
                int idx = hashing(key, size*2);
                bucketsNew.get(idx).add(new Dict(k, get(k)));
            }
            size *= 2;
            buckets = bucketsNew;
        }
        // insert
        int idx = hashing(key, size);
        buckets.get(idx).add(new Dict(key, val));
        keySet.add(key);
        count += 1;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Method Unavailable");
    }

    @Override
    public V remove(K key, V val) {
        throw new UnsupportedOperationException("Method Unavailable");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
