import java.util.Random;
import java.util.Arrays;
import java.util.LinkedList;

public class Program {
	public static void main(String[] args) {
		// Anzahl der zu sortierenden Elemente
		// int n = 10;
		// int n = 100;
		// int n = 1000;
		// int n = 10000;
		// int n = 100000;
		// int n = 1000000;
		// int n = 10000000;

		// TODO: Zahlen für den Vergleich vorbereiten
		int a = 10;
		int b = 100;
		int c = 1_000;
		int d = 10_000;
		int e = 100_000;
		int f = 1_000_000;
		int g = 10_000_000;

		for (char j = 'a'; j < 'g'; j++) {
			// Hier große N ab- und anstellen
			boolean largeNumbersActivated = false;
			if ((j == 'e' || j == 'f' || j == 'g') && largeNumbersActivated) {
				return;
			}
			int n = 0;
			switch (j) {
			case 'a': {
				n = a;
				break;
			}
			case 'b': {
				n = b;
				break;
			}
			case 'c': {
				n = c;
				break;
			}
			case 'd': {
				n = d;
				break;
			}
			case 'e': {
				n = e;
				break;
			}
			case 'f': {
				n = f;
				break;
			}
			default: {
				n = g;
				break;
			}
			}
			Random r = new Random();
			int[] numbers = new int[n];
			for (int l = 0; l < 3; l++) {
				for (int i = 0; i < n; i++) {
					// Mit zufälligen Zahlen initialisieren
					// numbers[i] = ...

					if (l == 0) {
						numbers[i] = r.nextInt(n + 1);
					}

					// Mit bereits sortierten Zahlen initialisieren
					// numbers[i] = ...

					if (l == 1) {
						numbers[i] = i;
					}

					// Mit invers sortierten Zahlen initialisieren
					// numbers[i] = ...

					if (l == 2) {
						numbers[i] = n - i;
					}

				}

				for (int k = 0; k < 2; k++) {
					printArray("vorher: ", numbers);
					LinkedList<SortingBase> implementations = new LinkedList<SortingBase>();
					// TODO: Hier alle Implementierungen hinzufügen

					Mergesort mergesort = new Mergesort();
					Quicksort quicksort = new Quicksort();
					if (k == 1) {
						implementations.add(mergesort);
					} else {
						implementations.add(quicksort);
					}

					for (SortingBase sorter : implementations) {
						int[] numbersImpl = Arrays.copyOf(numbers, n);
						long startTime = System.currentTimeMillis();
						sorter.sort(numbersImpl);
						long estimatedTime = System.currentTimeMillis() - startTime;

						printArray(sorter.getName() + ": ", numbersImpl);
						if (estimatedTime > 0)
							System.out.println("Zeit: " + estimatedTime + "ms");
						else
							System.out.println("Zeit: <1ms");
						checkSorting(numbersImpl);
						System.out.println();
					}
				}
			}

		}
	}

	public static void printArray(String prefix, int[] numbers) {
		int displayCount = numbers.length;
		String ext = "";
		if (numbers.length > 15) {
			displayCount = 15;
			ext = "...";
		}
		System.out.print(prefix);
		for (int i = 0; i < displayCount - 1; i++)
			System.out.print(numbers[i] + ", ");
		System.out.println(numbers[displayCount - 1] + ext);
	}

	public static void checkSorting(int[] numbers) {
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i - 1] > numbers[i])
				System.out.println("! FEHLER: " + numbers[i - 1] + " > " + numbers[i]);
		}
	}
}
