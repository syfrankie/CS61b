public class Maximizer {
    public static Comparable max(Comparable[] items) {
        int maxIdx = 0;
        for (int i = 0; i < items.length; i++) {
            int temp = items[i].compareWith(items[maxIdx]);
            if (temp > 0) {
                maxIdx = i;
            }
        }
        return items[maxIdx];
    }
}