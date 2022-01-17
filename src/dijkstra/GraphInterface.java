package dijkstra;

import java.util.ArrayList;

import maze.MazeReadingException;

public interface GraphInterface {
	// Il faut initialiser le graph avec toutes les liaisons
	// Il faut appeler le constructeur et le graph contient la liste des liaisons
	// Une liste, chaque �l�ment [sommet x, sommet y, poids liaison]
	// Arraylist de Tableau
	public boolean links(VertexInterface r, VertexInterface y);
	public int getWeightLink(VertexInterface r, VertexInterface y);  //Cette fonction renverra toujours 1 donc pas vraiment utile et meme pas si facile � enregistrer
	public ArrayList<VertexInterface> getAllVertex(); // Il faut parcourir tout l'ensemble des sommet pour tester si successeur ou dans A
	public ArrayList<VertexInterface> getSuccessors(VertexInterface r); // donne les successeurs de r
	// Ensuite on teste si ces successeurs sont dans A ou pas
	
	//Pour eviter la boucle
	//public ArrayList<VertexInterface> notInA(); // Parcours allVertex et teste si le sommet est dans A avec A.has
	//on scinde le code pour �viter le r�p�ter
	public void printMaze();
	public void initFromTextFile(String fileName) throws MazeReadingException;
	public void saveToTextFile(String fileName);
}
