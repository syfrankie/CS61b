package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        for (Oomage oomage: oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum] += 1;
        }
        int[] temp = helper(bucket);
        int count = oomages.size();
        return ((count/50) < temp[0] && temp[1] < (count/2.5));
    }

    public static int[] helper(int[] bucket) {
        int[] out = new int[]{bucket[0], bucket[0]};
        for (int b: bucket) {
            if (b < out[0]) out[0] = b;
            else if (b > out[1]) out[1] = b;
        }
        return out;
    }
}
