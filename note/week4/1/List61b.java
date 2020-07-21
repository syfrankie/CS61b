public interface List61b<TypeofItem> {
    public void addFirst(TypeofItem x);
    public void addLast(TypeofItem x);
    public void insert(TypeofItem x, int pos);

    public TypeofItem getLast();
    public TypeofItem getFirst();
    public TypeofItem get(int i);

    public int size();
    public TypeofItem removeLast();

    default public void print() {
        for (int i=0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println("");
    }
}
