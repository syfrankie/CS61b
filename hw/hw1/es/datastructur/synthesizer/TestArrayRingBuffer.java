package es.datastructur.synthesizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArrayRingBuffer {
    @Test
    public void testArrayRingBuffer() {
        BoundedQueue<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(9);
        arb.enqueue(8);
        arb.enqueue(7);
        arb.enqueue(6);
        arb.enqueue(5);
        arb.dequeue();
        arb.dequeue();
        int ans1 = arb.dequeue();
        assertEquals(7, ans1);
        arb.dequeue();
        arb.enqueue(1);
        arb.enqueue(2);
        int ans2 = arb.peek();
        assertEquals(5, ans2);
        int ans3 = arb.fillCount();
        assertEquals(3, ans3);
    }
}
