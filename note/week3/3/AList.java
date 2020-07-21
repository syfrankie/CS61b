public class AList<TypeofItem> implements List61b<TypeofItem>{

	private TypeofItem[] items;
	private int size;

	public AList() {
		items = (TypeofItem[]) new Object[100];
		size = 0;
	}

	@Override
	public void insert(TypeofItem x, int pos) {
		TypeofItem[] newItems = (TypeofItem[]) new Object[items.length+1];
		System.arraycopy(items, 0, newItems, 0, pos);
		newItems[pos] = x;
		System.arraycopy(items, pos, newItems, pos+1, items.length-pos);
		items = newItems;
	}

	public void resize(int sizeNew) {
		TypeofItem[] temp = (TypeofItem[]) new Object[sizeNew];
		System.arraycopy(items, 0, temp, 0, size);
		items = temp;
	}

	@Override
	public void addLast(TypeofItem x) {
		if (size == items.length) {
			resize(size*2);
		}
		items[size] = x;
		size += 1;
	}

	@Override
	 public void addFirst(TypeofItem x) {
		insert(x, 0);
	 }

	 @Override
	public TypeofItem getLast() {
		return items[size-1];
	 }

	@Override
	public TypeofItem getFirst() {
		return items[0];
	}

	public TypeofItem get(int i) {
		return items[i];
	}

	@Override
	public int size() {
		return size;
	}

	public TypeofItem removeLast() {
		TypeofItem temp = getLast();
		items[size-1] = null;
		size -= 1;
		return temp;
	}

}