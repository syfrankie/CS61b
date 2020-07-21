import java.lang.reflect.Type;

public class SLList<TypeofItem> implements List61b<TypeofItem> {

	private class Node {
		public TypeofItem item;
		public Node next;

		public Node(TypeofItem i, Node n) {
			item = i;
			next = n;
		}
	}

	private Node sentinel;
	private int size;

	public SLList() {
		sentinel = new Node(null, null);
		size = 0;
	}

	public SLList(TypeofItem x) {
		sentinel = new Node(null, null);
		sentinel.next = new Node(x, null);
		size = 1;
	}

	@Override
	public void addFirst(TypeofItem x) {
		sentinel.next = new Node(x, sentinel.next);
		size += 1;
	}

	@Override
	public TypeofItem getFirst() {
		return sentinel.next.item;
	}

	@Override
	public void addLast(TypeofItem x) {
		size += 1;
		Node p = sentinel;
		while (p.next != null) {
			p = p.next;
		}
		p.next = new Node(x, null);
	}

	@Override
	public void insert(TypeofItem x, int pos) {
		size += 1;
		Node p = sentinel;
		while (pos > 1 && p.next != null) {
			p = p.next;
			pos -= 1;
		}
		Node temp = new Node(x, p.next);
		p.next = temp;
	}

	public Node getLastNode() {
		Node p = sentinel;
		while (p.next != null) {
			p = p.next;
		}
		return p;
	}

	@Override
	public TypeofItem getLast() {
		Node temp = getLastNode();
		return temp.item;
	}

	@Override
	public TypeofItem get(int i) {
		Node p = sentinel;
		while (i+1 >= 1 && p.next != null){
			p = p.next;
			i -= 1;
		}
		return p.item;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public TypeofItem removeLast() {
		Node temp = getLastNode();
		if (temp == sentinel) {
			return null;
		}
		Node p = sentinel;
		while (p.next != temp) {
			p = p.next;
		}
		p.next = null;
		return temp.item;
	}
}