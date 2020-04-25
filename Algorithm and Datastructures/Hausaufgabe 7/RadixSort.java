import java.util.ArrayList;
import java.util.Random;

public class RadixSort {

	private int[] sortedArray;

	private int key(Integer element, int digit) throws IllegalArgumentException {
		int key = -1;

		if (element.intValue() < 0) {
			throw new IllegalArgumentException();
		}

		String string = element.toString();
		if (string.length() - 1 < digit) {
			key = 0;
		} else {
			for (int i = string.length() - 1; i >= ((string.length() - 1) - digit); i--) {
				String tmpKey = string.charAt(i) + "";
				key = Integer.valueOf(tmpKey);
			}
		}

		return key;
	}

	private void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {
		int k = 0;
		int[] tmpArray = new int[elements.length];
		for (int i = 0; i < buckets.length; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				try {
					tmpArray[k++] = buckets[i].get(j);
				} catch (NullPointerException e) {

				}
			}
		}
		sortedArray = tmpArray;
	}

	private void kSort(Integer[] elements, int digit) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] buckets = new ArrayList[10];
		for (int i = 0; i < 10; i++) {
			buckets[i] = new ArrayList<>();
		}
		for (int j = 0; j < elements.length; j++) {
			buckets[key(elements[j].intValue(), digit)].add(elements[j]);
		}
		concatenate(buckets, elements);
	}

	private Integer[] convertToInteger(int[] array) {
		Integer[] output = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			output[i] = array[i];
		}
		return output;
	}

	private int findMaxNumberOfDigits(Integer[] elements) {
		Integer max = 0;
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].intValue() > max)
				max = elements[i].intValue();
		}
		return max.toString().length();	
	}

	public void sort(Integer[] elements) {
		this.sortedArray = new int[elements.length];
		kSort(elements, 0);
		for (int i = 1; i < findMaxNumberOfDigits(elements); i++) {
			kSort(convertToInteger(getSortedArray()), i);
		}
	}

	public int[] getSortedArray() {
		return sortedArray;
	}


}
