public class MatrixMultOptimization extends MiniJava {

	public static int f(int[][] mm) {

		return f(mm, 0, mm.length - 1);
	}

	public static int f(int[][] mm, int i, int j) {
	
		int minimum = Integer.MAX_VALUE; //bzw. einfach größte Integerzahl eingeben
		
		if (i < j) {
			for (int x = i; x < j; x++) {
				int speicher =  f(mm, i, x) + (f(mm, x + 1, j) + (mm[i][0] * mm[x][1] * mm[j][1]));
				if (speicher < minimum) minimum = speicher;}
				return minimum;
				
	
		}

		
		if (i == j) {

				return 0;

		}
		return 0;
		
	}

	public static void main(String[] args) {


	}
}
