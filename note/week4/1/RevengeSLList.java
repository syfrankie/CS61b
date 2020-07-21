public class RevengeSLList<TypeofItem> extends SLList<TypeofItem>{
    SLList<TypeofItem> removedItems;
    public RevengeSLList() {
        super();
        removedItems = new SLList<>();
    }

    public RevengeSLList(TypeofItem x) {
        super(x);
        removedItems = new SLList<>();
    }

    @Override
    public TypeofItem removeLast() {
        TypeofItem temp = super.removeLast();
        removedItems.addLast(temp);
        return temp;
    }

    public void printLost() {
        removedItems.print();
    }

    public static void main(String[] args) {
        RevengeSLList<Integer> my_list = new RevengeSLList<>();
        my_list.addLast(0);
        my_list.addLast(3);
        my_list.addLast(7);
        my_list.addLast(11);
        my_list.addLast(19);
        my_list.removeLast();
        my_list.removeLast();
        my_list.print();
        my_list.printLost();
    }
}
