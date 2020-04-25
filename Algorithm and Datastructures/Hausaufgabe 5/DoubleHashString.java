import java.util.ArrayList;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden, Strings zu
 * hashen.
 */
public class DoubleHashString implements DoubleHashable<String> {
	int size;
	String[] array;

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashString} Objekt für
	 * einen gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashString(int size) {
		this.array = new String[size - 1];
		this.size = size;
	}

	/**
	 * Diese Methode berechnet h(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hash(String key) {
		long hash = 0;

		hash = hashTick(key);

		return hash;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hashTick(String key) {
		long hash = 0;
		int i = 0;
		
		int keyNumber = transformToNumber(key);

		while (array[(int) h(keyNumber, i)] != null) {
			i++;
		}
		;

		array[(int) h(keyNumber, i)] = key;
		return h(keyNumber, i);
	}
	
	private int transformToNumber(String key){
		int number = 0;
		for (int i = 0; i < key.length(); i++){
			number += key.charAt(i);
		}
		System.out.println(number);
		return number;
	}

	private long h(int key, int i) {

		// Formel nach Tutorübung
		int hash = ((3 * key + 1) % size + i * (1 + (key % size - i))) % size;
		return hash;

	}
	
	public static void main(String[] args){
		DoubleHashString dHashS = new DoubleHashString(27);
	}
}
