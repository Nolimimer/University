import java.util.ArrayList;

public class BinomialHeap {

	BinomialTreeNode binomialTreeNode;
	ArrayList<BinomialTreeNode> arrayList;

	/**
	 * Dieser Konstruktor baut einen leeren Haufen.
	 */
	public BinomialHeap() {
		this.binomialTreeNode = null;
		this.arrayList = new ArrayList<>();
	}

	/**
	 * Diese Methode ermittelt das minimale Element im binomialen Haufen. Wenn
	 * es dieses nicht gibt (bei leerem Haufen), soll eine RuntimeException
	 * geworfen werden.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		if (arrayList.size() == 0) {
			throw new RuntimeException("Leerer Haufen");
		} else {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < arrayList.size(); i++) {
				if (arrayList.get(i).min() < min) {
					min = arrayList.get(i).min();
				}
			}
			return min;
		}
	}

	/**
	 * Diese Methode fügt einen Schlüssel in den Haufen ein.
	 *
	 * @param key
	 *            der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if (arrayList.size() == 0) {
			arrayList.add(new BinomialTreeNode(key));
		} else {
			arrayList.add(new BinomialTreeNode(key));

			merge();

		}
	}

	private void merge() {
		for (int i = 0; i < arrayList.size(); i++) {
			for (int j = 0; j < arrayList.size(); j++) {
				
				if (i == j) {
					continue;
				} else {
					if (arrayList.get(i).rank() == arrayList.get(j).rank()) {
//						System.out.println(arrayList.get(i).rank());
//						System.out.println(arrayList.get(j).rank());
//						System.out.println("Merge");
						BinomialTreeNode tmpBinTreeNode = BinomialTreeNode.merge(arrayList.get(i), arrayList.get(j));
						
						arrayList.set(i, tmpBinTreeNode);
						arrayList.remove(j);
//						for (int k = 0; k < arrayList.size(); k++){
//							System.out.println("Rank von " + k + " " +  arrayList.get(k).rank());
//						}
						merge();
					}
				}
			}
		}
	}

	/**
	 * Diese Methode entfernt das minimale Element aus dem binomialen Haufen und
	 * gibt es zurück.
	 *
	 * @return das minimale Element
	 */
	public int deleteMin() {
		int min = min();

		ArrayList<Integer> binTreeNodeArray = new ArrayList<>();
		for (int i = 0; i < arrayList.size(); i++) {
			if (min == arrayList.get(i).min()) {
				binTreeNodeArray = arrayList.get(i).getArrayList();
				arrayList.remove(arrayList.get(i));
				break;
			}
		}
		for (int j = 0; j < binTreeNodeArray.size(); j++) {
			insert(binTreeNodeArray.get(j));
		}

		merge();
		return min;
	}

	public String toString() {
		String out = "";
		for (int i = 0; i < arrayList.size(); i++) {
			out = arrayList.get(i).min() + " ";
		}
		return out;
	}
	
//	public static void main(String[] args){
//		BinomialHeap binHeap = new BinomialHeap();
//		for (int i = 0; i < 10; i++){
//			System.out.println("insert " + 34);
//			binHeap.insert(34 + i);
//
//		}
//		for (int j = 0; j < 10; j++){
//			System.out.println("Delete");
//		System.out.println(binHeap.deleteMin());
//		}
//		
//	}

}
