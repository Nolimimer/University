/**
 * Die Klasse RingQueue soll eine zirkuläre Warteschlange auf Basis der Klasse
 * {@link DynamicArray} implementieren.
 */
public class RingQueue {
	private DynamicArray dynArr;

	private int size;

	private int from;

	private int to;

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

	/**
	 * Dieser Konstruktor erzeugt eine neue Ringschlange. Ein leere Ringschlange
	 * habe stets eine Größe von 0, sowie auf 0 gesetzte Objektvariablen to und
	 * from.
	 * 
	 * @param growthFactor
	 *            der Wachstumsfaktor des zugrundeliegenden dynamischen Feldes
	 * @param maxOverhead
	 *            der maximale Overhead des zugrundeliegenden dynamischen Feldes
	 */
	public RingQueue(int growthFactor, int maxOverhead) {
		dynArr = new DynamicArray(growthFactor, maxOverhead);
		size = 0;
		from = 0;
		to = 0;
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		if (isEmpty()) {
			System.out.println("-- Leeres Array wurde befüllt --");
			dynArr.reportUsage(null, 1);
			from = 0;
			to = 0;
			dynArr.set(0, value);
		} else {
			// Da ist dann das Array vergrößert worden
			if (size == dynArr.getInnerLength()){
				System.out.println("-- Array wurde vergrößert --");
				from = 0;
				to = size - 1;
			}
			System.out.println("-- Bisher wird genutzt: " + from + " bis " + to + " --");
			Interval interval = new NonEmptyInterval(from, to);
			try {
			dynArr.reportUsage(interval, 1);
			} catch (Exception e){
				from = 0;
				to = size -1;
				interval = new NonEmptyInterval(from, to);
				dynArr.reportUsage(interval, 1);
			}
			if (to +  1> dynArr.getInnerLength() - 1){
				System.out.println("-- Speicher fügt jetzt vorne hinzu --");
			to = 0;
			} else {
			to++;
			}

			dynArr.set(to, value);

		}
		size++;
//		dynArr.sysout();
	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		if (isEmpty()) {
			return -1;
		} else {
		
			System.out.println("-- Bisher wird genutzt: " + from + " bis " + to + " --");
			int out = dynArr.get(from);
			dynArr.set(from, -1);
			Interval interval = new NonEmptyInterval(from, to);
			dynArr.reportUsage(interval, -1);
			if (from < to){
				from++;
			} else {
				from = 0;
				to = size - 2;
			}
			
			size--;
//			dynArr.sysout();
			return out;

		}

	}

	//TESTEN
//	public static void main(String[] args) {
//		RingQueue ringQ = new RingQueue(2, 4);
//		for (int i = 0; i < 5; i++) {
//			System.out.println(">>Enqueue<<");
//			ringQ.enqueue(i + 12);
//		}
//		for (int i = 0; i < 3; i++) {
//			System.out.println(">>Dequeue<<");
//			ringQ.dequeue();
//		}
//		for (int i = 0; i < 20; i++) {
//			System.out.println(">>Enqueue<<");
//			ringQ.enqueue(i + 10);
//		}
//		ringQ.dequeue();
//	}
}
