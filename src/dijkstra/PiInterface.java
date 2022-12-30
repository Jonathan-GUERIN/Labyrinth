package dijkstra;

public interface PiInterface {
	public int getWeight(VertexInterface r); // Demande l'attribut enregistré dans le poids avec y.getWeight()
	public int getWeightLink(VertexInterface r, VertexInterface y); //Delegation graph.getWeightLink(x,y)
	public void setWeight(VertexInterface r, int weight); // Fixe le poid du sommet en appelant r.setWeight(int);
	public void print();
}
