public class demo {
    public static void main(String[] args) {
        WeightedQuickUnion myset = new WeightedQuickUnion(10);

        myset.union(2, 0);
        myset.union(0, 3);
        myset.union(3, 7);

        myset.union(1, 5);
        myset.union(5, 6);
        myset.union(5, 8);
        myset.union(5, 9);

        System.out.println(myset.connected(2, 6));
        myset.union(3, 9);
        System.out.println(myset.connected(7, 8));
        System.out.println(myset.find(7));
        System.out.println(myset.connected(0, 4));
    }
}
