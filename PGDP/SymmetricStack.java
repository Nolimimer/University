
public class SymmetricStack {

	private int[] data;
	private int first;
	private int last;

	public SymmetricStack() {
		data = new int[2];
		first = -1;
		last = -1;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public int getNumberOfElements() {
		if (first == -1) {
			return 0;
		}
		if (first == last) {
			return 1;
		}
		int j = 0;
		if (first < last) {
			for (int i = first; i < last + 1; i++) {
				j++;
			}
		} else {
			for (int i = first; i < data.length; i++) {
				j++;
			}
			for (int i = 0; i < last + 1; i++) {
				j++;
			}
		}
		return j;
	}

	
	
	//increase Methode die mit Methode aufgerufen wird
	public void increase() {
		int[] datanew = new int[data.length * 2];
		int j = 0;
		// Normalfall
		if (first < last) {
			for (int i = first; i < last; i++) {
				datanew[j] = data[i];
				j++;
			}
		} else { // Vorne im Array Zahlen die nach darauffolgenden Zahlen kommen
			for (int i = first; i < data.length; i++) {
				datanew[j] = data[i];
				j++;
			}
			for (int i = 0; i < last + 1; i++) {
				datanew[j] = data[i];
				j++;
			}
		}
		// Neues Array und first/last setzen
		data = datanew;
		last = j - 1;
		first = 0;
		inthemiddle();
	}

	public void inthemiddle() {
		int[] datanew = new int[data.length];
		int j = ((data.length / 2) / 2); // eventuell -1;
		for (int i = 0; i <= last; i++) {
			datanew[j] = data[i];
			j++;
		}
		data = datanew;
		first = ((data.length / 2) / 2);
		last = j - 1;
	}

	public void decrease() {
		if (getNumberOfElements() <= data.length / 4) {
			int[] datanew = new int[data.length / 2];
			int j = 0;
			if (first == last) {
				datanew[0] = data[first];
				first = 0;
				last = 0;
				data = datanew; // Hier war der Fehler!!
				return;
			}
			if (first < last) {
				for (int i = first; i < last + 1; i++) {
					datanew[j] = data[i];
					j++;
				}
			} else {
				for (int i = first; i < data.length; i++) {
					datanew[j] = data[i];
					j++;
				}
				for (int i = 0; i < last + 1; i++) {
					datanew[j] = data[i];
					j++;
				}
			}
			last = --j;
			first = 0;
			data = datanew;
			inthemiddle();
		}
	}

	public boolean isEmpty() {
		return first == -1;
	}

	public boolean isFull() {
		int testfirst = first;
		int testlast = last;

		testfirst = (testfirst - 1) % data.length;
		if (testfirst < 0) {
			testfirst = data.length - 1;
		}

		if (testfirst == last) {
			return true;
		}

		testlast = (testlast + 1) % data.length;
		if (testlast == first) {
			return true;
		}

		return false;
}
	

	// vorne dranstellen
	public void prepend(int x) {
		if (isEmpty()) {
			first = last = data.length / 2;
			data[first] = x;
		} else {
			first = (first - 1) % data.length;
			if (first < 0) {
				first = data.length - 1;
			}
			if (first == last) {
				increase();
				data[first] = x;
			} else {
				data[first] = x;
			}
		}
	}

	// hinten dranstellen
	public void append(int x) {
		if (isEmpty()) {
			first = last = data.length / 2;
			data[last] = x;
		} else {
			last = (last + 1) % data.length;
			if (first == last) {
				increase();
				data[last] = x;
			} else {
				data[last] = x;
			}
		}
	}

	public void removeFirst() {
		if (!isEmpty()) {

			if (getNumberOfElements() == 1) {
				first = -1;
				last = -1;
				return;
			}
			data[first] = 0;
			first = (first + 1) % data.length;
			if (getNumberOfElements() <= data.length / 4) {
				decrease();
			}
		}
	}

	public void removeLast() {
		if (!isEmpty()) {

			if (getNumberOfElements() == 1) {
				first = -1;
				last = -1;
				return;
			}

			data[last] = 0;
			last = (last - 1) % data.length;
			if (last < 0) {
				last = data.length - 1;
			}
			if (getNumberOfElements() <= data.length / 4) {
				decrease();
			}
		}
	}

	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < data.length; i++) {
			if (first <= last && (i < first || i > last))
				out += "* ";
			if (first <= last && i > first && i < last)
				out += " " + data[i];
			if (first > last && i > last && i < first)
				out += "* ";
			if (first > last && (i > first || i < last))
				out += " " + data[i];
			if (i == first)
				out = out + "(" + data[i];
			if (i == last)
				out += (first == last ? "" : " " + data[i]) + ")";
		}
		return out;
	}

	public static void main(String[] args) {
		
	}
}
