public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node head;
    private int size;
    public LinkedListDeque() {
        head = new Node(null, null, null);
        size = 0;
    }

    public LinkedListDeque(T x) {
        head = new Node(null, null, null);
        head.next = new Node(x, null, null);
        head.next.prev = head.next;
        size = 1;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void printDeque() {
        Node p = head.next;
        while (p.next != null) {
            System.out.print(p.item + " ");
            p = p.next;
            if (p == head.next) {
                break;
            }
        }
        System.out.println("");
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            head.next = new Node(x, null, null);
            head.next.prev = head.next;
        } else {
            Node nodeNew = new Node(x, head.next, head.next.prev);
            head.next.prev = nodeNew;
            nodeNew.prev.next = nodeNew;
            head.next = nodeNew;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            head.next = new Node(x, null, null);
            head.next.prev = head.next;
        } else {
            Node nodeNew = new Node(x, head.next, head.next.prev);
            nodeNew.prev.next = nodeNew;
            head.next.prev = nodeNew;
        }
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node temp = head.next;
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            head.next = temp.next;
            size -= 1;
            return temp.item;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node temp = head.next.prev;
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            head.next.prev = temp.prev;
            size -= 1;
            return temp.item;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node p = head.next;
            for (int i=0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    @Override
    public int size() {
        return size;
    }
}
