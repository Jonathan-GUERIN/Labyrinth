package dijkstra;

public interface PreviousInterface {
	public VertexInterface getDad(VertexInterface y); // Obtenir le pere de y y.getDad()
	public void setDad(VertexInterface y, VertexInterface pere); // Delegation y.getDad()
	public void printPrevious();
	// Doit permettre de remonter tout le chemin >> ok
}
