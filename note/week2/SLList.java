public class SLList<TypeofItem> {

	private class Node {
		public TypeofItem item;
		public Node next;

		public Node(TypeofItem i, Node n){
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

	public void addFirst(TypeofItem x){
		sentinel.next = new Node(x, sentinel.next);
		size += 1;
	}
	public TypeofItem getFirst(){
		return sentinel.next.item;
	}
	
	public void addLast(TypeofItem x){
		size += 1;
		Node p = sentinel;
		while (p.next != null) {
			p = p.next;
		}
		p.next = new Node(x, null);
	}
	public int getSize(){
		return size;
	}

	public static void main(String[] args){
		SLList<Integer> L = new SLList<>(11);
		System.out.println(L.getFirst());
		L.addFirst(0);
		L.addLast(19);
		System.out.println(L.getSize());
	}

}