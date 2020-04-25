import java.util.ArrayList;

public class BFS {
	public BFS(Graph graph) {
		this.queue = new ArrayList<>();
		this.graph = new Graph();
	}

	// Queue
	private ArrayList<Graph.Node> queue;
	private int max;
	private Graph graph;
	
	/**
	 * Führt eine Breitensuche vom Startknoten "start" aus, um das SSSP-Problem zu
	 * lösen.
	 */
	public void sssp(Graph.Node start) {

		if (start == null) {
			throw new RuntimeException("Knoten muss existieren");
		}
		
		for (int i = 0; i < graph.getKnotenZahl(); i++) {
			graph.getNode(i).distanz = 0;
			graph.getNode(i).Vorgängerknoten = null;
		}
		
		// Initalisierung mit Anfangsknoten
		max = 0;
		start.distanz = 0;
		start.Vorgängerknoten = start;
		queue.add(start);

		// Breitensuche
		/*
		 * 
		 * 
		 * Um aus Breitensuche eine Tiefensuche zu machen muss man
		 * statt einer Warteschlange einen Stack nehmen
		 * 
		 * 
		 * 
		 */
		while (!queue.isEmpty()) {
			Graph.Node current = queue.get(0);
			queue.remove(queue.get(0));
			for (int i = 0; i < current.weggehendeKanten.size(); i++) {
				// Wenn noch nicht besucht 
				if (current.weggehendeKanten.get(i).Vorgängerknoten == null) {
					queue.add(current.weggehendeKanten.get(i));
					current.weggehendeKanten.get(i).distanz = current.distanz + 1;
					max(current.distanz + 1);
					current.weggehendeKanten.get(i).Vorgängerknoten = current.Vorgängerknoten;
				}
			}
		}

	}

	private void max(int b) {
		if (this.max > b) {
		} else {
			this.max = b;
		}
	}

	/**
	 * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion die Tiefe des Knotens
	 * n vom Startknoten überprüft werden.
	 */
	public Integer getDepth(Graph.Node n) {
		if (n == null) {
			throw new RuntimeException("Knoten muss exisitern");
		}
		return n.distanz;
	}

	/**
	 * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion der Vaterknoten des
	 * Knotens n in Richtung Startknoten abgefragt werden.
	 */
	public Graph.Node getParent(Graph.Node n) {
		while (n.Vorgängerknoten != null && !(n.Vorgängerknoten.equals(n))) {
			return getParent(n.Vorgängerknoten);
		}
		return n;
	}

}