import java.util.ArrayList;
import java.util.Random;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden, Integer zu
 * hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {

	private int size;

	public int[] array;
	public int elements;
	
	public static int i;
	public static int collision = 0;

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashInt} Objekt für
	 * einen gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashInt(int size) {
		this.array = new int[size - 1];
		this.size = size;
	}

	/**
	 * Diese Methode berechnet h(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hash(Integer key) {

		long hash = 0;

		hash = hashTick(key);

		return hash;
	}
	
	public boolean isFull(){
		return (array.length - elements) <= 0;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hashTick(Integer key) {

		long hash = 0;
		i = 0;

		while (array[(int) h(key, i)] != 0) {
			i++;
			collision++;
		}
		;

		array[(int) h(key, i)] = key;
		elements++;
		return h(key, i);
	}

	private long h(int key, int i) {

		// Formel nach Tutorübung
		int hash = ((3 * key + 1) % size + i * (1 + (key % size - i))) % size;
		return hash;

	}

	public static void main(String[] args) {
		DoubleHashInt dHashInt = new DoubleHashInt(27);
		

	}

}
