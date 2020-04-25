import java.util.ArrayList;
import java.util.Scanner;

public class RadixchenTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		while (scanner.hasNextInt()) {
			arrayList.add(scanner.nextInt());
		}
		
		int numbersBiggerThanNull = 0;
		for (Integer j : arrayList) {
			if (j < 0){
				
			} else {
			numbersBiggerThanNull++;}
		}
		
		Integer[] elements = new Integer[numbersBiggerThanNull];
		
		int i = 0;
		for (Integer j : arrayList) {
			if (j < 0){
				
			} else {
			elements[i++] = j;}
		}
		
		RadixSort rS = new RadixSort();
		rS.sort(elements);
		for (int k = 0; k < rS.getSortedArray().length; k++){
			System.out.println(rS.getSortedArray()[k] + " ");
		}
		
	}
}
