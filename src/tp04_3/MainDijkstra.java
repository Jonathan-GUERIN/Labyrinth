package tp04_3;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;

public class MainDijkstra {

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
		
		int i_d = 99;
		int j_d = 99;
		String label;
		for(VertexInterface[] list : boxes) {
			for(VertexInterface box : list) {
				label = box.getLabel2();
				if(label == "D") {
					i_d = box.getRef()[0];
					j_d = box.getRef()[1];
				}
			}
		}
		System.out.println(i_d);
		System.out.println(j_d);
		
		System.out.println();
		System.out.println();
		
		VertexInterface depart = boxes[i_d][j_d];
		PreviousInterface previous = Dijkstra.dijkstra(maze, depart);
		System.out.println();
		System.out.print("previous: ");
		previous.printPrevious();
		
	}

}
