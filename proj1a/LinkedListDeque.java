public class LinkedListDeque<T> {

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

	public boolean isEmpty() {
		return (size == 0);
	}

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

	public int size() {
		return size;
	}
	
	/*
	public static void main(String[] args) {
		LinkedListDeque<Integer> L = new LinkedListDeque<>();
		L.addFirst(11);
		L.addFirst(0);
		L.addLast(19);
		L.printDeque();
		System.out.println(L.get(1));
		System.out.println(L.removeFirst());
		System.out.println(L.removeLast());
		System.out.println(L.size());
	}
	*/

}