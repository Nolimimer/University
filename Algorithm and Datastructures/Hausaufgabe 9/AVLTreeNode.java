import java.util.ArrayList;

/**
 * Diese Klasse implementiert einen Knoten in einem AVL-Baum.
 */
public class AVLTreeNode {
	/**
	 * Diese Variable enthält den Schlüssel, den der Knoten speichert.
	 */
	private int key;

	/**
	 * Diese Variable speichert die Balancierung des Knotens - wie in der
	 * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
	 * um eins tiefer ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
	 * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
	 * rechte Teilbaum tiefer ist.
	 */
	private int balance = 0;

	private AVLTreeNode left = null;
	private AVLTreeNode right = null;

	public AVLTreeNode(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}

	/**
	 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
	 *
	 * @return die ermittelte Höhe
	 */
	public static int height(AVLTreeNode avlTreeNode) {
		if (avlTreeNode == null) {
			return 0;
		} else {
			return 1 + max(height(avlTreeNode.left), height(avlTreeNode.right));
		}
	}

	public static int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	public boolean validAVL() {
		boolean valid = true;
		if (valid) {
			AVLTreeNode tmpAVLTreeNode = this;

			if (tmpAVLTreeNode.left == null) {
				return true;
			}

			if (tmpAVLTreeNode.balance > 1 || tmpAVLTreeNode.balance < -1) {
				valid = false;
				return false;
			}

			tmpAVLTreeNode.left.validAVL();

			if (tmpAVLTreeNode.right == null) {
				return true;
			}

			tmpAVLTreeNode.right.validAVL();

			return true;
		} else {
			return false;
		}

	}

	/**
	 * Diese Methode sucht einen Schlüssel im Baum.
	 *
	 * @param key
	 *            der zu suchende Schlüssel
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		AVLTreeNode tmpAVLTreeNode = this;
		while (true) {
			if (key == tmpAVLTreeNode.key) {
				return true;
			}
			if (key > tmpAVLTreeNode.key && tmpAVLTreeNode.right == null) {
				return false;
			} else if (key < tmpAVLTreeNode.key && tmpAVLTreeNode.left == null) {
				return false;
			} else if (key > tmpAVLTreeNode.key && tmpAVLTreeNode.right != null) {
				tmpAVLTreeNode = tmpAVLTreeNode.right;
			} else if (key < tmpAVLTreeNode.key && tmpAVLTreeNode.left != null) {
				tmpAVLTreeNode = tmpAVLTreeNode.left;
			}
		}

	}

	// Rekursive Implementation
	public boolean findRec(int key) {
		AVLTreeNode tmpAVLTreeNode = this;
		if (key == tmpAVLTreeNode.key) {
			return true;
		} else if (key > tmpAVLTreeNode.key && tmpAVLTreeNode.right == null) {
			return false;
		} else if (key < tmpAVLTreeNode.key && tmpAVLTreeNode.left == null) {
			return false;
		} else if (key > tmpAVLTreeNode.key && tmpAVLTreeNode.right != null) {
			tmpAVLTreeNode = tmpAVLTreeNode.right;
//			System.out.println("rechts");
			return tmpAVLTreeNode.findRec(key);
		} else if (key < tmpAVLTreeNode.key && tmpAVLTreeNode.left != null) {
			tmpAVLTreeNode = tmpAVLTreeNode.left;
//			System.out.println("links");
			return tmpAVLTreeNode.findRec(key);
		}

		return false;

	}

	private ArrayList<AVLTreeNode> avlTreeNodeArray = new ArrayList<>();

	public void insert(int key) {
		AVLTreeNode tmpAVLTreeNode = this;
		boolean notFinished = true;
		while (notFinished) {
			if (key == tmpAVLTreeNode.key) {
				throw new RuntimeException("Dieses Element gibt es schon");
			}
			// Einzuf�gender Key ist gr��er als der aktuelle Knoten
			// -> links weiter rechts
			if (key > tmpAVLTreeNode.key && tmpAVLTreeNode.right == null) {
				tmpAVLTreeNode.balance++;
				tmpAVLTreeNode.right = new AVLTreeNode(key);
				avlTreeNodeArray.add(tmpAVLTreeNode.right);
				notFinished = false;
//				System.out.println("neuer Knoten wurde erstellt");
			} else if (key < tmpAVLTreeNode.key && tmpAVLTreeNode.left == null) {
				tmpAVLTreeNode.balance--;
				tmpAVLTreeNode.left = new AVLTreeNode(key);
				avlTreeNodeArray.add(tmpAVLTreeNode.left);
				notFinished = false;
//				System.out.println("neuer Knoten wurde erstellt");
			} else if (key > tmpAVLTreeNode.key && tmpAVLTreeNode.right != null) {
				tmpAVLTreeNode.balance++;
				tmpAVLTreeNode = tmpAVLTreeNode.right;
//				System.out.println("Weiter rechts");
			} else if (key < tmpAVLTreeNode.key && tmpAVLTreeNode.left != null) {
				tmpAVLTreeNode.balance--;
				tmpAVLTreeNode = tmpAVLTreeNode.left;
//				System.out.println("Weiter links");
			} else {
				throw new RuntimeException("Fehler in insert");
			}

		}
	
	}
	
	public boolean validBalance(){
		for (AVLTreeNode avlTreeNode : avlTreeNodeArray) {
			if (avlTreeNode.balance > 1 || avlTreeNode.balance < -1){
				return false;
			}
		}return true;
	}

	public void updateBalance(AVLTreeNode avlTreeNode) {
		if (avlTreeNode == null) {
			return;
		} else {
			avlTreeNode.balance = height(avlTreeNode.left) - height(avlTreeNode.right);
		}
		if (avlTreeNode.right != null) {
			avlTreeNode.right.updateBalance(avlTreeNode);
		}
		if (avlTreeNode.left != null) {
			avlTreeNode.left.updateBalance(avlTreeNode);
		}

	}

	public void rebalance() {
		for (AVLTreeNode avlTreeNode : avlTreeNodeArray) {
			if (avlTreeNode.balance > 1) {
				rotateRight(avlTreeNode);
			}
			if (avlTreeNode.balance < -1) {
				rotateLeft(avlTreeNode);
			}
		}

	}
	
	public void rotateRight(AVLTreeNode avlTreeNode){
		AVLTreeNode tmpAVLTreeNode = avlTreeNode;
		// Z wird zum Knoten
		avlTreeNode.right = avlTreeNode;
		avlTreeNode.left = tmpAVLTreeNode;
		// rechter Knoten wird eins nach oben geschoben
	}
	
	public void rotateLeft(AVLTreeNode avlTreeNode){
		AVLTreeNode tmpAVLTreeNode = avlTreeNode;
		// > wird zum Knoten
		avlTreeNode.left = avlTreeNode;
		avlTreeNode.right = tmpAVLTreeNode;
		// rechter Knoten wird eins nach oben geschoben
	}

	
	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @param sb
	 *            der StringBuilder f�r die Ausgabe
	 */
	public void dot(StringBuilder sb) {
		dotNode(sb, 0);
	}

	private int dotNode(StringBuilder sb, int idx) {
		sb.append(String.format("\t%d [label=\"%d, b=%d\"];\n", idx, key, balance));
		int next = idx + 1;
		if (left != null)
			next = left.dotLink(sb, idx, next, "l");
		if (right != null)
			next = right.dotLink(sb, idx, next, "r");
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next, String label) {
		sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
		return dotNode(sb, next);
	};

//	public static void main(String[] args) {
//		AVLTreeNode avlTreeNode = new AVLTreeNode(10);
//		avlTreeNode.insert(4);
//		avlTreeNode.insert(3);
//		avlTreeNode.insert(12);
//		avlTreeNode.insert(50);
//		avlTreeNode.insert(49);
//		avlTreeNode.insert(100);
//		avlTreeNode.insert(1);
//		avlTreeNode.insert(101);
//		avlTreeNode.insert(102);
//		System.out.println("H�HE: " + avlTreeNode.height(avlTreeNode));
//		// System.out.println(avlTreeNode.find(49));
//		// System.out.println(avlTreeNode.findRec(100));
//		System.out.println(avlTreeNode.validAVL());
//		StringBuilder strBuilder = new StringBuilder();
//		avlTreeNode.dot(strBuilder);
//		System.out.println(strBuilder);
//
//	}
}
