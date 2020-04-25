
/**
 * Die Klasse BinSea stellt Methoden bereit, in sortierten Feldern binär nach
 * Wertebereichen zu suchen.
 */
class BinSea {
	/**
	 * Diese Methode sucht nach einem Wert in einem einem sortierten Feld. Sie
	 * soll dabei dazu verwendet werden können, den ersten bzw. letzten Index in
	 * einem Intervall zu finden. Wird kein passender Index gefunden, wird -1
	 * zurückgegeben.
	 * 
	 * Beispiel:
	 * 
	 * 0 <-----------------------> 5 sortedData: [-10, 33, 50, 99, 123, 4242 ]
	 * value: 80 ^ ^ | | | `- Ergebnis (3) für ersten Index im Intervall, da 99
	 * | als erster Wert im Feld größer als 80 ist | `- Ergebnis (2) für letzten
	 * Index im Intervall, da 50 als letzter Wert kleiner als 80 ist
	 * 
	 * @param sortedData
	 *            das sortierte Feld, in dem gesucht wird
	 * @param value
	 *            der Wert der Intervallbegrenzung, der dem gesucht wird
	 * @param lower
	 *            true für untere Intervallbegrenzung, false für obere
	 *            Intervallbegrenzung
	 * @return der passende Index, -1 wenn dieser nicht gefunden werden kann
	 */

	private static int search(int[] sortedData, int value, boolean lower) {
		try {
			// Interval bestimmen
			int left = 0;
			int right = sortedData.length - 1;

			while (left <= right) {
				int PivotIndex = (right + left) / 2;
				if (sortedData[PivotIndex] == value) {
					return -1;
					// Endbedingung
				} else if (sortedData[PivotIndex] < value && sortedData[PivotIndex + 1] > value) {
					if (lower != true) {
						return PivotIndex;
					}
					return PivotIndex + 1;
					// rechte Seite weitersuchen
				} else if (sortedData[PivotIndex] > value) {
					right = PivotIndex - 1;
					// linke Seite weitersuchen
				} else if (sortedData[PivotIndex] < value) {
					left = PivotIndex + 1;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] array = { -10, 33, 50, 99, 123, 4242 };

		System.out.println(search(array, 80, false));

		System.out.println(search(array, new Interval() {

			@Override
			public int getTo() {
				return 700;
			}

			@Override
			public int getFrom() {
				return 80;
			}
		}).toString());

	}

	/**
	 * Diese Methode sucht ein Intervall von Indices eines sortierten Feldes,
	 * deren Werte in einem Wertebereich liegen. Es soll dabei binäre Suche
	 * verwendet werden.
	 * 
	 * Beispiel: 0 <-----------------------> 5 sortedData: [-10, 33, 50, 99,
	 * 123, 4242 ] valueRange: [80;700] ^ ^ | | | `- Ergebnis (4) für obere
	 * Intervallbegrenzung, | da 123 als letzter Wert kleiner oder gleich 700
	 * ist | `- Ergebnis (3) für untere Intervallbegrenzung, da 99 als erster
	 * Wert größer oder gleich 80 ist
	 * 
	 * @param sortedData
	 *            das sortierte Feld, in dem gesucht wird
	 * @param valueRange
	 *            der Wertebereich, zu dem Indices gesucht werden
	 */
	public static Interval search(int[] sortedData, Interval valueRange) {
		// From, To bestimmen und Interval zurückgeben
		int tmpFrom = search(sortedData, valueRange.getFrom(), true);
		int tmpTo = search(sortedData, valueRange.getTo(), false);
		Interval out = Interval.fromArrayIndices(tmpFrom, tmpTo);
		return out;
	}

}
