public interface Deque<T> {

    public void printDeque();
    public void addFirst(T x);
    public void addLast(T x);

    public boolean isEmpty();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    public int size();

}
