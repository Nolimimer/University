import java.util.ArrayList;

public class ConnectedComponents {
	private BFS search;

	public ConnectedComponents(Graph graph) {
		parents = new ArrayList<>();
		search = new BFS(graph);
	}

	public ArrayList<Graph.Node> parents;

	/**
	 * Zählt alle Zusammenhangskomponenten im gegebenen Graphen g
	 */
	public int countConnectedComponents(Graph g) {
		
		if (g == null) {
			throw new RuntimeException("Der Graph muss initalisiert sein");
		}
		
		
		
		parents = new ArrayList<>();
		
	
		// Alle Knoten durchlaufen
		OuterLoop:
		for (int i = 0; i < g.getKnotenZahl(); i++) {
			// Wenn bereits in Zusammenhangskomponente => weitersuchen
			for (Graph.Node node : parents) {
				if (node.equals(search.getParent(g.getNode(i)))) {
					continue OuterLoop;
				}
			}
			
			// Sonst Breitensuche ausführen
			search.sssp(g.getNode(i));
			// Anfangspunkt der Breitensuche hinzufügen
			parents.add(g.getNode(i));
		}
		return parents.size();
	}	
}