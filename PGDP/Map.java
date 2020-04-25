
public class Map implements Runnable {

	public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
		int counter = 0;
		Thread[] t = new Thread[n];
		try {
			if (n <= 0) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException ex) {
			System.out.println("Es muss mindestends ein Thread gestartet werden!");
		}
		try {
			if (f == null || a == null || b == null) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException ex) {
			System.out.println("Die Parameter müssen gefüllt sein!");
		}


		Integer[] speicher = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {
			speicher[i] = (Integer) a[i];
		}

		int regionsToDo = a.length % n;
		for (int i = 0; i < regionsToDo; i++) {
			int arr[] = new int[a.length / n + 1];
			for (int j = 0; j < a.length / n + 1; j++) {
				arr[j] = speicher[counter];
				counter++;
			}
		}

		int regionsLeftToDo = (a.length - counter) / n;
		for (int i = 0; i < regionsLeftToDo; i++) {
			int arr[] = new int[a.length / n];
			for (int j = 0; i < a.length / n; j++) {
				arr[j] = speicher[counter];
				counter++;
				System.out.println(counter);
			}
		}

		for (int i = 0; i < n; i++) {
			t[i] = new Thread();
			t[i].start();

		}

		for (int i = 0; i < n; i++) {
			t[i].join();
			t[i].interrupt();
		}
	}

	@Override
	public void run() {
		IntToString f = new IntToString();
		f.apply(null);
	}

	public static void main(String[] args) {
		IntToString f = new IntToString();
		int n = 2;
		Integer[] a = { 1, 2, 3 };
		String[] b = { "", "", "" };
		try {
			map(f, a, b, n);
		} catch (InterruptedException e) {
		}
	}
}
