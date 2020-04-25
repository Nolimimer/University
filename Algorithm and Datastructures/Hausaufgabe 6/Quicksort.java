public class Quicksort implements SortingBase {
	public void sort(int[] numbers) {
		quickSort(numbers, 0, numbers.length-1);
	}
	
	private void quickSort(int[] a, int l, int r){
		if (l < r) {
			int p = a[r];
			int i = l - 1;
			int j = r;
			do {
				do { i++; } while (a[i] < p);
				do { j--; } while (j >= l && a[j] > p);
				if (i < j) swap(a,i,j);
	
			} while (i < j);
			swap (a,i,r);
			quickSort(a,l,i-1);
			quickSort(a, i + 1, r);
			
		}
	}
	
	public void swap (int[] a, int i, int j){
		 int tmpj = a[j];
		 int tmpi = a[i];
		 a[i] = tmpj;
		 a[j] = tmpi;
	}

	public String getName() {
		return "Quicksort";
	}
	
	public String toString(int[] numbers) {
		String out = "";
		for (int i : numbers)
			out += "  " + i;
		return out;
	}
	
	public static void main(String[] args) {
		int[] array = { 1, 4, 8, 20, 11, 12, 30, 50 };
		Quicksort quickSort = new Quicksort();
		System.out.println(quickSort.toString(array));
		quickSort.sort(array);
		System.out.println(quickSort.toString(array));
	}
}
