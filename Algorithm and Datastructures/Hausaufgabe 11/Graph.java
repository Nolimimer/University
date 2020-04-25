import java.util.ArrayList;

public class Graph {
	public class Node {

		private Integer index;
		public int distanz;
		public Graph.Node Vorgängerknoten;
		public ArrayList<Graph.Node> weggehendeKanten;
		public ArrayList<Graph.Node> hinführendeKanten;

		// Konstruktor für Knoten
		public Node(int index) {
			this.index = index;
			this.distanz = 0;
			this.Vorgängerknoten = null;
			this.weggehendeKanten = new ArrayList<>();
			this.hinführendeKanten = new ArrayList<>();
		}

	}

	private int knotenZahl;
	private ArrayList<Graph.Node> nodes;

	public Graph() {
		knotenZahl = 0;
		nodes = new ArrayList<>();
	}

	/**
	 * Erstellt einen neuen Knoten, und gibt den Index dieses Knotens zurück. Der
	 * erste erstellte Knoten sollte Index 0 haben. Knoten, die direkt nacheinander
	 * erstellt werden, sollten aufsteigende Indices haben.
	 */
	public Integer addNode() {
		nodes.add(new Node(knotenZahl));
		knotenZahl++;
		return knotenZahl - 1;
	}

	/**
	 * Liefert den Knoten zum angegebenen Index zurück
	 */
	public Graph.Node getNode(Integer id) {
		if (id >= knotenZahl) {
			throw new RuntimeException("Diesen Knoten gibt es nicht");
		}
		for (Graph.Node node : nodes) {
			if (node.index == id) {
				return node;
			}
		}
		return null;
	}
	
	public int getKnotenZahl() {
		return knotenZahl;
	}

	/**
	 * Fügt zwischen den beiden angegebenen Knoten eine (ungerichtete) Kante hinzu.
	 */
	public void addEdge(Graph.Node a, Graph.Node b) {
		if (a == null || b == null) {
			throw new RuntimeException("Die Knoten müssen existieren, die zusammengefügt werden sollen");
		}
		a.weggehendeKanten.add(b);
		a.hinführendeKanten.add(b);
		b.weggehendeKanten.add(a);
		b.hinführendeKanten.add(a);
	}
}
