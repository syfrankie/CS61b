import edu.princeton.cs.algs4.Queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> queue = new Queue<>();
        String[] temp = new String[]{"apple", "pig", "water", "milk", "dog", "clock", "cup"};
        for (int i = 0; i < temp.length; i++) {
            queue.enqueue(temp[i]);
        }
        assertTrue(isSorted(QuickSort.quickSort(queue)));
    }

    @Test
    public void testMergeSort() {
        Queue<String> queue = new Queue<>();
        String[] temp = new String[]{"apple", "pig", "water", "milk", "dog", "clock", "cup"};
        for (int i = 0; i < temp.length; i++) {
            queue.enqueue(temp[i]);
        }
        assertTrue(isSorted(MergeSort.mergeSort(queue)));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
