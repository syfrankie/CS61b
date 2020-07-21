import java.util.Comparator;
public class Dog implements Comparable<Dog>{
    public String name;
    public int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareWith(Dog other) {
        return this.size - other.size;
    }

    public static class nameSort implements Comparator<Dog>{
        public int compare(Dog d1, Dog d2) {
            return d1.name.compareTo(d2.name);
        }
    }

    public static Comparator<Dog> getNameSort() {
        return new nameSort();
    }
}
