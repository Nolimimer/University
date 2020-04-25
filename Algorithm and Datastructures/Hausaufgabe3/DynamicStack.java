/**
 * Die Klasse DynamicStack soll einen Stapel auf Basis der Klasse
 * {@link DynamicArray} implementieren.
 */
public class DynamicStack {
	private DynamicArray dynArr;

	/**
	 * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
	 */
	private int length;

	public int getLength() {
		return length;
	}

	public DynamicStack(int growthFactor, int maxOverhead) {
		dynArr = new DynamicArray(growthFactor, maxOverhead);
		length = 0;
	}

	/**
	 * Diese Methode legt ein Element auf den Stapel.
	 * 
	 * @param value
	 *            das Element, das auf den Stapel gelegt werden soll
	 */
	public void pushBack(int value) {
		//Anfangs setzen
		if (this.getLength() == 0) {
			dynArr.reportUsage(null, 1);
			dynArr.set(0, value);
		} else {
			Interval interval = new NonEmptyInterval(0, this.getLength() - 1);
			dynArr.reportUsage(interval, 1);
			dynArr.set(this.getLength(), value);
		}
		length++;
//		dynArr.sysout();
	}

	/**
	 * Diese Methode nimmt ein Element vom Stapel.
	 * 
	 * @return das entfernte Element
	 */
	public int popBack() {
		//Wenn man entfernen will, wenn aber leer
		if (this.getLength() == 0) {
			System.out.println("-- Leer --");
			return -1;
		} else {
			Interval interval = new NonEmptyInterval(0, this.getLength() - 1);
			int out = dynArr.get(this.getLength() - 1);
			dynArr.set(this.getLength()-1, -1);
			dynArr.reportUsage(interval, -1);
			if (length > 0) {
				length--;
			}
//			dynArr.sysout();
			return out;
		}
	}

	//TESTEN
//	public static void main(String[] args) {
//		DynamicStack dynStack = new DynamicStack(2, 4);
//		for (int i = 0; i < 10; i++) {
//			System.out.println("-- Dynamischer Stack|| Länge: " + dynStack.length + " --");
//			dynStack.pushBack(i + 1);
//		}
//		for (int i = 0; i < 10; i++) {
//			System.out.println("-- Dynamischer Stack|| Länge: " + dynStack.length + " --");
//			dynStack.popBack();
//		}
//	}
}
