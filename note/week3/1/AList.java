public class AList<TypeofItem> {

	private TypeofItem[] items;
	private int size;

	public AList() {
		items = (TypeofItem[]) new Object[100];
		size = 0;
	}

	public void resize(int sizeNew) {
		TypeofItem[] temp = (TypeofItem[]) new Object[sizeNew];
		System.arraycopy(items, 0, temp, 0, size);
		items = temp;
	}

	public void addLast(TypeofItem x) {
		if (size == items.length) {
			resize(size*2);
		}
		items[size] = x;
		size += 1;
	}

	public TypeofItem getLast() {
		return items[size-1];
	}

	public TypeofItem get(int i) {
		return items[i];
	}

	public int getSize() {
		return size;
	}

	public TypeofItem removeLast() {
		TypeofItem temp = getLast();
		items[size-1] = null;
		size -= 1;
		return temp;
	}

	public static void main(String[] args) {
		AList<Integer> L = new AList<>();
		L.addLast(11);
		L.resize(1);
		L.addLast(19);
		System.out.println(L.getSize());
		L.addLast(321);
		System.out.println(L.removeLast());
		System.out.println(L.getLast());
	}
}