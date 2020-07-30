package bearmaps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3);
        pq.add("i", 9);
        pq.add("g", 7);
        pq.add("d", 4);
        pq.add("a", 1);
        pq.add("h", 8);
        pq.add("e", 5);
        pq.add("b", 2);
        //System.out.println("pq after adding 10 items: ");
        //System.out.println(pq);
        assertEquals(8, pq.size());
    }

    @Test
    public void testInsertAndRemoveOnce() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3);
        pq.add("i", 9);
        pq.add("g", 7);
        pq.add("d", 4);
        pq.add("a", 1);
        pq.add("h", 8);
        pq.add("e", 5);
        pq.add("b", 2);
        String removed = pq.removeSmallest();
        assertEquals("a", removed);
        assertEquals(7, pq.size());
    }

    @Test
    public void testInsertAndRemoveAllButLast() {
        ExtrinsicMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3);
        pq.add("i", 9);
        pq.add("g", 7);
        pq.add("d", 4);
        pq.add("a", 1);
        pq.add("h", 8);
        pq.add("e", 5);
        pq.add("b", 2);

        int i = 0;
        String[] expected = {"a", "b", "c", "d", "e", "g", "h", "i"};
        while (pq.size() > 1) {
            assertEquals(expected[i], pq.removeSmallest());
            i += 1;
        }
    }

    @Test
    public void testContains() {
        ExtrinsicMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("a", 1);
        assertTrue(pq.contains("a"));
        pq.removeSmallest();
        assertTrue(!pq.contains("a"));
    }

    /*
    // private testing
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
     */
}
