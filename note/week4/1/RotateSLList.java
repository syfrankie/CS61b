public class RotateSLList<TypeofItem> extends SLList<TypeofItem> {
    public void rotateRight(){
        TypeofItem temp = removeLast();
        addFirst(temp);
    }

    public static void main(String[] args) {
        RotateSLList<Integer> mylist = new RotateSLList<>();
        mylist.addLast(19);
        mylist.addFirst(11);
        mylist.addLast(0);
        mylist.addFirst(7);
        mylist.print();
        mylist.rotateRight();
        mylist.print();
    }
}
