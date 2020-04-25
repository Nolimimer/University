import java.util.ArrayList;

public class BinomialTreeNode {

	private ArrayList<Integer> arrayList = new ArrayList<>();
	private int minimum;

	public BinomialTreeNode(int key) {
		this.arrayList = new ArrayList<>();
		this.minimum = key;
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		return minimum;
	}
	
	
	/**
	 * Gibt den Rang des Teilbaumes zurÃ¼ck.
	 *
	 * @return der Rang des Teilbaumes
	 */
	public int rank() {
		return arrayList.size();
	}
	
	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * TeilbÃ¤umen zurÃ¼ck, in die der aktuelle Baum zerfÃ¤llt, wenn man den
	 * Knoten entfernt.
	 *
	 * @return die Menge von TeilbÃ¤umen
	 */
	
		
	public BinomialTreeNode[] deleteMin() {
		return null;
	}
	
	public ArrayList<Integer> deleteMinList() {
		return arrayList;
	}

	/**
	 * Diese Methode vereint zwei BÃ¤ume des gleichen Ranges.
	 *
	 * @param a
	 *            der erste Baum
	 * @param b
	 *            der zweite Baum
	 * @return denjenigen der beiden BÃ¤ume, an den der andere angehÃ¤ngt wurde
	 */

	
	public ArrayList<Integer> getArrayList(){
		return this.arrayList;
	}
	
	public void setArrayList(ArrayList<Integer> arrayList){
		this.arrayList = arrayList;
	}
	
	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		if (a.rank()!= b.rank()) {
			throw new RuntimeException("Baeume muessen vom gleichen Rang sein!");
		}
		
		if (a.min() > b.min()) {
			BinomialTreeNode tmpBinTreeNode = new BinomialTreeNode(b.minimum);
			for (int i = 0; i < b.arrayList.size(); i++){
			tmpBinTreeNode.arrayList.add(b.arrayList.get(i));
			}
			tmpBinTreeNode.arrayList.add(a.min());
			for (int j = 0; j < a.getArrayList().size(); j++){
				tmpBinTreeNode.arrayList.add(a.arrayList.get(j));
			}
//			System.out.println("Größe " + tmpBinTreeNode.rank());
//			
			return tmpBinTreeNode;

		} else {
			BinomialTreeNode tmpBinTreeNode = new BinomialTreeNode(a.minimum);
			for (int i = 0; i < a.arrayList.size(); i++){
			tmpBinTreeNode.arrayList.add(a.arrayList.get(i));
			}
			tmpBinTreeNode.arrayList.add(b.min());
			for (int j = 0; j < b.getArrayList().size(); j++){
				tmpBinTreeNode.arrayList.add(b.arrayList.get(j));
			}
//			System.out.println("Größe " + tmpBinTreeNode.rank());
			
			return tmpBinTreeNode;
		}
	}
}
