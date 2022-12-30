package tp04_3;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;

public class MainTest {
	
	/*
	 * permet de tester la sauvegarde d'un labyrinthe et son chargement
	 */
	public static void main(String[] args) {
		
		VertexInterface[][] boxes = new VertexInterface[10][10];
		GraphInterface maze = new Maze(boxes,10,10);
		try {
			maze.initFromTextFile("data/labyrinthe.txt");
		} catch (MazeReadingException e){
			System.out.println(e);
		}
		
		System.out.println();
		System.out.println();
		
		maze.printMaze();
		
		System.out.println();
		System.out.println();
		try {
			maze.saveToTextFile("data/labyrinthe2.txt");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
