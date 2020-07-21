public class Sort {
	public static void sort(String[] x) {
		sort(x, 0);
	}

	public static void sort(String[] x, int start) {
		if (start == x.length) {
			return;
		}
		int smallestIndex = findSmallest(x, start);
		swap(x, start, smallestIndex);
		sort(x, start+1);
	}

	public static void swap(String[] x, int i, int j) {
		String temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

	public static int findSmallest(String[] x, int start) {
		int smallestIndex = start;
		for (int i = start; i < x.length; i++) {
			int diff = x[i].compareTo(x[smallestIndex]);
			// if x[i] < x[smallestIndex], diff will be -1.
			if (diff < 0) smallestIndex = i;
		}
		return smallestIndex;
	}
}