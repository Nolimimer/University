
public class GGTTable extends MiniJava {

	public static void main(String[] args) {

		int reihe = 0;

		int number = readInt("Wie groﬂ soll die Tabelle sein?");
		if (number < 1) {
			write("Bitte geben sie eine positive Zahl ein!");
			System.exit(0);
		}
		System.out.print("  ");
		while (reihe < number) // Grundreihe oben von 1 bis zu x
		{
			reihe += 1;
			if (reihe < 10) {
				System.out.print("  " + reihe);
			}
			if (reihe >= 10) {
				System.out.print(" " + reihe);
			} // Zweistellige Zahlen brauchen mehr Platz als einstellige
		}
		int spalte = 0;

		while (spalte < number) { //weitere Spalten werden bis x erstellt

			spalte += 1;
			if (spalte < 10)
				System.out.print("\n  " + spalte); //n‰chste Spalte wird angefangen
			if (spalte >= 10)
				System.out.print("\n " + spalte);
			int n‰chstereihe = 1;

			while (n‰chstereihe <= number) { //reihen werden bis zum Ende vortgef¸hrt
				int x = n‰chstereihe;
				int y = spalte;
				int result;

				while (x != y) { //der kleinste gemeinsame Teiler wird ermittelt
					if (x > y) {
						x = x - y;
						result = x;
					} else {
						y = y - x;
						result = y;
					}
				}
				if (x == y)
					result = n‰chstereihe;
				result = x;
				if (result < 10)
					System.out.print(" " + result + " ");  //Ausgabe der Ergebnisse
				if (result >= 10)
					System.out.print(" " + result);
				n‰chstereihe += 1;
			}
		}

	}
}
