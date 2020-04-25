/**
 * Die Klasse StackyQueue soll eine Warteschlange auf Basis der Klasse
 * {@link DynamicStack} implementieren. Es soll ausschließlich die Klasse
 * {@link DynamicStack} zur Datenspeicherung verwendet werden.
 */
public class StackyQueue {
	private DynamicStack dynStack;

	private int length;

	/**
	 * Diese Methode ermittelt die Länge der Warteschlange.
	 * 
	 * @return die Länge der Warteschlange
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Dieser Kontruktor initialisiert eine neue Schlange.
	 * 
	 * @param growthFactor
	 * @param maxOverhead
	 */
	public StackyQueue(int growthFactor, int maxOverhead) {
		dynStack = new DynamicStack(growthFactor, maxOverhead);
		length = 0;
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		dynStack.pushBack(value);
		length++;
	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		if (length == 0) {
			length++;
			System.out.println("-- Leerer Speicher --");
		} else {
			int[] arr = new int[getLength() - 1];
			for (int i = 0; i < getLength() - 1; i++) {
				System.out.println("-- popBack --");
				arr[i] = dynStack.popBack();
			}
			dynStack.popBack();
			for (int i = arr.length-1; i >= 0; i--) {
				System.out.println("-- pushBack --");
				dynStack.pushBack(arr[i]);
			}

		}

		length--;
		return -1;
	}
//TESTEN
//	public static void main(String[] args) {
//		StackyQueue stackyQ = new StackyQueue(2, 4);
//		for (int i = 0; i < 5; i++) {
//			System.out.println("Enqueue");
//			stackyQ.enqueue(i + 10);
//		}
//
//		for (int i = 0; i <4; i++) {
//			System.out.println("Dequeue");
//			stackyQ.dequeue();
//		}
//		 for (int i = 0; i < 6; i++) {
//		 System.out.println("Enqueue");
//		 stackyQ.enqueue(i + 20);
//		 }
//	}
}
