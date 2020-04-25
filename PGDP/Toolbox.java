public class Toolbox extends MiniJava {

	//Testet ob die Zahl gerade oder ungerade ist
	public static boolean even(int x) {

		boolean output = true;

		if (x > 0) {
			x = x - 2;
		}

		if (x < 0) {
			x = x + 2;
		}

		if (x == 0) {
			output = true;
			return output;
		}

		if (x == 1) {
			output = false;
			return output;
		}

		return even(x);
	}
	
	
	public static int evenSum(int n) {

		if (even(n) == true && n > 0) {
			return evenSum(n - 1) + n;
		}

		else if (even(n) == false && n > 0) {
			return evenSum(n - 1);
		}

		else if (even(n) == true && n < 0) {
			return evenSum(n + 1) + n;
		}

		else if (even(n) == false && n < 0) {
			return evenSum(n + 1);
		}

		else {
			return n;
		}

	}

	//Multiplikation
	public static int multiplication(int x, int y) {

		if (y > 0) {
			return multiplication(x, y - 1) + x;
		} else if (y < 0) {
			return multiplication(x, y + 1) - x;
		} else
			return y;

	}

	//Vertauscht Zahlen
	public static int[] change(int[] m, int a, int b) {

		// Funktioniert bei ungeraden Zahlen
		if (a == b) {
			return m;
		}

		// Funktioniert bei geraden Zahlen
		if (a - 1 == b) {
			return m;
		}

		// Vertauschen der Variablen
		int memory = m[a];
		m[a] = m[b];
		m[b] = memory;

		return change(m, a + 1, b - 1);
	}

	//Ruft change auf
	public static void reverse(int[] m) {

		change(m, 0, m.length - 1);

	}

	//Zählt die ungeraden Zahlen
	public static int numberOfOddIntegers(int[] m) {

		return counter(m, 0, 0);
	}

	//"Counter"
	public static int counter(int[] m, int x, int w) {

		if (x < m.length) {
			if (even(m[x]) == false) {
				return counter(m, x + 1, w + 1);
			}
			return counter(m, x + 1, w);
		}
		return w;
	}
	
	//Array erstellt mit Größe der Zahl der ungeraden Zahlen
	public static int[] filterOdd(int[] m) {

		int q = 0;
		int e = 0;
		
		int[]p = new int[numberOfOddIntegers(m)];
		
		return arraywithOddnumbers(m, q, p, e);

	}

	//Rekursive Funktion liefert Array mit ungeraden Zahlen zurück
	public static int[] arraywithOddnumbers(int[] m, int q, int[] p, int e) {

	if (q < m.length) {
		if ((even(m[q])) == false){
			 p[q-e] = m[q]; 
			 return arraywithOddnumbers(m, q + 1, p, e);
		}
		return arraywithOddnumbers (m, q + 1, p, e + 1);
	}
		
		return p;
	}
	
	//main
	public static void main(String[] args) {
		
	}
}
