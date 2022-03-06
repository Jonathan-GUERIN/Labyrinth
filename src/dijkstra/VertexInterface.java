package dijkstra;

public interface VertexInterface {
	/*
	 * // Le poid d'un sommet pas pr�cis� au d�but ou � 0
	 * //public void setWeight(int w); //poids(r)=0
	 * //public int getWeight(); // On besoin de regarder le poids associ� au sommet, sera acc�d�e via les m�thodes de l'objet Pi!
	 * //public void setDad(VertexInterface sommet);
	 * //public VertexInterface getDad();
	 */
	
	public boolean equals(VertexInterface sommet); // pour A.has, il faut regarder si le sommet a d�j� �t� �tudi�
	public int[] getRef();
	public void printLabel();
	public String getLabel2();
	public void setBoxHovered(boolean bool);
	public boolean getBoxHovered();
	//public ArrayList<Vertex> listSuccesors(); // Regarder tous les successeurs du sommet en utilisant GraphInterface
	
	/*
	 * // le premier while commencera par la condition des successeurs
	 * // Pour le pivot, y sera r et pas un clone (pas de new)
	 */
	public void printPos();
}
