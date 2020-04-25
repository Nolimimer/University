public class HeadList extends MiniJava {

	Entry head;
	int size;

	/**
	 * constructor empty HeadList
	 */
	HeadList() {
		head = null;
	}

	/**
	 * Appends a new element with value info to the end of this list
	 *
	 * @param info
	 *            value of the new element
	 */
	public void add(int info) {
		Entry current = head;
		// Falls die Liste nicht leer ist
		if (current != null) {
			// Suche das den letzten Teil der Liste
			while (current.next != null) {
				current = current.next;
			}
			Entry e = new Entry(null, null, info);
			size++;
			current.next = e;
			setHead(e);
		}
		// Falls die Liste leer ist
		else {
			head = new Entry(null, null, info);
			size++;
			setHead(head);

		}
	}

	/**
	 * Removes and returns the element at position index from this list.
	 *
	 * @param index
	 *            position of the element that is removed
	 * @return value of the removed element
	 */
	public int remove(int index) {
		Entry remove = head;
		// Falsche Indices abfangen oder leere Liste
		if (index < 0 || head == null || index > size - 1) {
			return Integer.MIN_VALUE;
		}
		// Head removen
		if (index == 0) {
			// Einelementige Liste und Head entfernen
			if (remove.next == null) {
				head = null;
				setHead(head);
				size--;
				return remove.elem;
			}
			remove.next.first = null;
			head = remove.next;
			setHead(head);
			size--;
			return remove.elem;
		}
		// Standardfall
		for (int i = 0; i < index - 1; i++) {
			remove = remove.next;
		} // man ist beim element remove, davor und dahinter betrachten
		remove.next = remove.next.next;
		size--;
		return remove.elem;
	}

	/**
	 * sets the head of each list element to newHead
	 *
	 * @param newHead
	 *            reference to the new head
	 */

	//Setzt die Heads für die ganze Liste von Anfang bis Ende
	private void setHead(Entry newHead) {
 		newHead = head;
		Entry setHead = newHead;
		while (setHead.next != null) {
			setHead = setHead.next;
			setHead.first = newHead;
			
		}
	}

	/**
	 * reverse the list example: [1,2,3,4,5] --> [5,4,3,2,1], [] --> [], [1] -->
	 * [1]
	 */
	public void reverse() {
			Entry current = null;
			Entry next = head;
			
			//Das 1Element zeigt auf das 0Element usw. -> Reverse
			while(next != null)
			{
				Entry reverse = next.next;
				next.next = current;
				current = next;
				next = reverse;
			}
			head = current;
			setHead(head);
		}

		


	@Override
	public String toString() {
		String out = "[";
		if (head != null) {
			out += head.elem;
			Entry tmp = head.next;
			while (tmp != null) {
				out = out + "," + tmp.elem;
				tmp = tmp.next;
			}
		}
		out += "]";
		return out;
	}

	public static void main(String[] args) {
		//HeadList l = new HeadList();
	
		

	}

	class Entry {

		Entry first;
		Entry next;
		int elem;

		public Entry(Entry first, Entry next, int elem) {
			this.first = first;
			this.next = next;
			this.elem = elem;
		}

	}

}