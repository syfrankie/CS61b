import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {

        int max = 0;
        for (String a : asciis) {
            max = max > a.length() ? max : a.length();
        }

        for (int i = 1; i <= max; i++) {
            asciis = sortHelperLSD(asciis, i);
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param idx The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int idx) {
        int len = asciis.length;
        String[] sorted = new String[len];

        int[] digits = new int[len];
        for (int i = 0; i < len; i++) {
            int d = asciis[i].charAt(asciis[0].length() - idx);
            digits[i] = d;
        }

        int[] counts = new int[256];
        for (int d : digits) {
            counts[d] += 1;
        }

        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                counts[i] += pos;
                pos = counts[i];
            }
        }

        for (int i = 0; i < len; i++) {
            int posi = digits[i];
            counts[posi] -= 1;
            int place = counts[posi];
            sorted[place] = asciis[i];
        }

        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] testArr = {"angle", "watch", "water", "tesla", "apple", "alien", "zelda", "12321", "!*^&!"};
        String[] result = sort(testArr);
        System.out.println(Arrays.toString(result));
    }
}
