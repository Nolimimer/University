public class Mergesort implements SortingBase {

	public void sort(int[] numbers) {
		merge(numbers, 0, numbers.length-1);
	}


	private void merge(int[] a, int l, int r) {
		int[] b = new int[a.length];
		if (l == r)
			return;
		int m = (r + l) / 2;
		merge(a, l, m);
		merge(a, m + 1, r);
		int j = l;
		int k = m + 1;
		for (int i = 0; i <= r - l; i++){
			if (j > m) {
				b[i] = a[k];
				k++;
			} else if (k > r) {
				b[i] = a[j];
				j++;
			} else if (a[j] <= a[k]) {
				b[i] = a[j];
				j++;
			} else {
				b[i] = a[k];
				k++;
			}
		}

		for (int i = 0; i <= r - l; i++)
			a[l + i] = b[i];
	}

	public String getName() {
		return "Mergesort";
	}

	private String toString(int[] numbers) {
		String out = "";
		for (int i : numbers)
			out += "  " + i;
		return out;
	}

	public static void main(String[] args) {
		int[] array = { 1, 4, 8, 20, 11, 12, 30, 50 };
		Mergesort mergeSort = new Mergesort();
		System.out.println(mergeSort.toString(array));
		mergeSort.sort(array);
		System.out.println(mergeSort.toString(array));
	}
}
