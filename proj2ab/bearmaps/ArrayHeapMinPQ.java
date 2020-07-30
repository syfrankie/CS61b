package bearmaps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private class Node {
        private T item;
        private double priority;

        private Node(T i, double p) {
            item = i;
            priority = p;
        }

        public T getItem() {
            return item;
        }
        public double getPriority() {
            return priority;
        }
    }

    private List<Node> minHeap;
    private int count;

    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>(3);
        minHeap.add(null);
        count = 0;
    }

    private int compare(int idx1, int idx2) {
        Node n1 = minHeap.get(idx1);
        Node n2 = minHeap.get(idx2);
        if (n1 == null) return idx2;
        else if (n2 == null) return idx1;
        else if (n1.getPriority() < n2.getPriority()) return idx1;
        else return idx2;
    }

    private void switchItems(int idx1, int idx2) {
        Node n1 = minHeap.get(idx1);
        Node n2 = minHeap.get(idx2);
        minHeap.set(idx1, n2);
        minHeap.set(idx2, n1);
    }

    private int parentIdx(int idx) {return idx / 2;}

    private int leftIdx(int idx) {return 2 * idx;}

    private int rightIdx(int idx) {return 2 * idx + 1;}

    private void swim(int idx) {
        if (idx <= 0 || idx > count) {
            throw new IllegalArgumentException("Invalid Index");
        }
        int parent = parentIdx(idx);
        if (parent == 0) return;
        if (compare(parent, idx) == idx) {
            switchItems(parent, idx);
            swim(parent);
        }
    }

    private void sink(int idx) {
        if (idx <= 0 || idx > count) {
            throw new IllegalArgumentException("Invalid Index");
        }
        int left = leftIdx(idx);
        if (left > count) return;
        int cmp1 = compare(idx, left);
        int right = rightIdx(idx);
        if (right > count) {
            if (cmp1 == left) switchItems(idx, left);
            return;
        }
        int cmp2 = compare(idx, right);
        if (cmp1 == idx && cmp2 == idx) return;
        int cmp = compare(left, right);
        if (cmp == left) {
            switchItems(left, idx);
            sink(left);
        } else {
            switchItems(right, idx);
            sink(right);
        }

    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException("Item Existed");
        minHeap.add(new Node(item, priority));
        count += 1;
        swim(count);
    }

    @Override
    public boolean contains(T item) {
        if (count == 0) return false;
        for (int i = 1; i <= count; i++) {
            T curr = minHeap.get(i).getItem();
            if (curr.equals(item)) return true;
        }
        return false;
    }

    @Override
    public T getSmallest() {
        if (count == 0) throw new NoSuchElementException("Empty Heap");
        return minHeap.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        if (count == 1) {
            count -= 1;
            return minHeap.remove(1).getItem();
        }
        Node temp = minHeap.get(count);
        T out = minHeap.set(1, temp).getItem();
        minHeap.remove(count);
        count -= 1;
        sink(1);
        return out;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void changePriority(T item, double priority) {
        for (int i = 0; i <= count; i++) {
            Node curr = minHeap.get(i);
            if (curr.getItem().equals(item)) {
                double temp = curr.getPriority();
                Node replace = new Node(item, priority);
                minHeap.set(i, replace);
                if (priority < temp) swim(i);
                else if (priority > temp) sink(i);
                return;
            }
        }
    }

    @Test
    public void testIndexing() {
        assertEquals(6, leftIdx(3));
        assertEquals(10, leftIdx(5));
        assertEquals(7, rightIdx(3));
        assertEquals(11, rightIdx(5));

        assertEquals(3, parentIdx(6));
        assertEquals(5, parentIdx(10));
        assertEquals(3, parentIdx(7));
        assertEquals(5, parentIdx(11));
    }

    @Test
    public void testSwim() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.count = 7;
        for (int i = 1; i <= 7; i += 1) {
            pq.minHeap.add(new ArrayHeapMinPQ<String>.Node("x" + i, i));
        }
        // Change item x6's priority to a low value.

        pq.minHeap.get(6).priority = 0;
        //System.out.println("PQ before swimming:");
        //System.out.println(pq);

        // Swim x6 upwards. It should reach the root.

        pq.swim(6);
        //System.out.println("PQ after swimming:");
        //System.out.println(pq);
        assertEquals("x6", pq.minHeap.get(1).item);
        assertEquals("x2", pq.minHeap.get(2).item);
        assertEquals("x1", pq.minHeap.get(3).item);
        assertEquals("x4", pq.minHeap.get(4).item);
        assertEquals("x5", pq.minHeap.get(5).item);
        assertEquals("x3", pq.minHeap.get(6).item);
        assertEquals("x7", pq.minHeap.get(7).item);
    }

    @Test
    public void testSink() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.count = 7;
        for (int i = 1; i <= 7; i += 1) {
            pq.minHeap.add(new ArrayHeapMinPQ<String>.Node("x" + i, i));
        }
        // Change root's priority to a large value.
        pq.minHeap.get(1).priority = 10;
        //System.out.println("PQ before sinking:");
        //System.out.println(pq);

        // Sink the root.
        pq.sink(1);
        //System.out.println("PQ after sinking:");
        //System.out.println(pq);
        assertEquals("x2", pq.minHeap.get(1).item);
        assertEquals("x4", pq.minHeap.get(2).item);
        assertEquals("x3", pq.minHeap.get(3).item);
        assertEquals("x1", pq.minHeap.get(4).item);
        assertEquals("x5", pq.minHeap.get(5).item);
        assertEquals("x6", pq.minHeap.get(6).item);
        assertEquals("x7", pq.minHeap.get(7).item);
    }
}
