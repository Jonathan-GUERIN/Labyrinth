package dijkstra;

public interface ASetInterface {
	//On en spécifie pas le constructeur, A sera vide
	public void add(VertexInterface sommet); //Il faut aggrandir l'ensemble A
	public boolean has(VertexInterface sommet); //Il faut chercher si un sommet se trouve dans A
}
