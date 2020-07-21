public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void resize(int sizeNew) {
        T[] temp = (T[]) new Object[sizeNew];
        System.arraycopy(items, 0, temp, 0, size);
        items = temp;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println("");
    }

    @Override
    public void addFirst(T item) {
        if (size == 0) {
            items[0] = item;
        } else {
            T[] temp = (T[]) new Object[items.length+1];
            System.arraycopy(items, 0, temp, 1, size);
            temp[0] = item;
            items = temp;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size*2);
        }
        items[size] = item;
        size += 1;
    }

    @Override
    public T removeFirst() {
        T out = items[0];
        if (size != 0) {
            T[] temp = (T[]) new Object[items.length];
            System.arraycopy(items, 1, temp, 0, size);
            items = temp;
            size -= 1;
        }
        return out;
    }

    @Override
    public T removeLast() {
        T out = items[size-1];
        items[size-1] = null;
        size -= 1;
        return out;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public int size() {
        return size;
    }
}
