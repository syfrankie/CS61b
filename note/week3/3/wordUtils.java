public class wordUtils {
    public static String findLongest(List61b<String> mylist){
        int maxIdx = 0;
        for (int i=0; i < mylist.size(); i++) {
            String curr = mylist.get(i);
            String longest = mylist.get(maxIdx);
            if (curr.length() > longest.length()) {
                maxIdx = i;
            }
        }
        return mylist.get(maxIdx);
    }
    public static void main(String[] args){
        List61b<String> mylist = new SLList<>();
        mylist.addLast("alex");
        mylist.addFirst("paul");
        mylist.addLast("catherine");
        System.out.println(findLongest(mylist));
    }
}
