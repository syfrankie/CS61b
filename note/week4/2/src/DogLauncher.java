import java.util.Comparator;
public class DogLauncher{
    public static void main(String[] args) {
        Dog d1 = new Dog("Alex", 7);
        Dog d2 = new Dog("Paul", 11);
        Dog d3 = new Dog("Katherine", 19);
        Dog[] group = new Dog[]{d1, d2, d3};
        Dog head = (Dog) Maximizer.max(group);
        head.bark();

        Comparator ns = Dog.getNameSort();
        if (ns.compare(d1, d2) > 0) {
            d1.bark();
        } else {
            d2.bark();
        }
    }
}
