import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private int size;
        private Node left, right;

        private Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    public BSTMap() {
        clear();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("Invalid Argument");
        return get(key) != null;
    }

    private V helper1(Node n, K key) {
        if (key == null) throw new IllegalArgumentException("Invalid Argument");
        if (n == null) return null;
        int temp = key.compareTo(n.key);
        if (temp < 0) {
            return helper1(n.left, key);
        } else if (temp > 0) {
            return helper1(n.right, key);
        } else {
            return n.val;
        }
    }

    @Override
    public V get(K key) {
        return helper1(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    private Node helper2(Node n, K key, V val) {
        if (n == null) {
            size += 1;
            return new Node(key, val);
        }
        int temp = key.compareTo(n.key);
        if (temp < 0) {
            n.left = helper2(n.left, key, val);
        } else if (temp > 0) {
            n.right = helper2(n.right, key, val);
        } else {
            n.val = val;
        }
        return n;
    }

    @Override
    public void put(K key, V val) {
        if (key == null || val == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        root = helper2(root, key, val);
    }

    /* The following methods are not supported here: */

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
