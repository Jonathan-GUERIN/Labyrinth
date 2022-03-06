package tp04_3;

import java.util.ArrayList;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;

public class TestDijkstra {
	
	/*
	 * permet de tester la fonction de chargement et de sauvegarde d'un labyrinthe,
	 * puis de résoudre le labyrinthe
	 */
	public static void main(String[] args) {
		VertexInterface[][] boxes = new VertexInterface[5][12];
		GraphInterface maze = new Maze(boxes,5,12);
		try {
			maze.initFromTextFile("data/labyrinthe4.txt");
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
		String labelD;
		for(VertexInterface[] list : boxes) {
			for(VertexInterface box : list) {
				labelD = box.getLabel2();
				if(labelD == "D") {
					System.out.println("departure: "+box.getRef()[0]+"; "+box.getRef()[1]);
					i_d = box.getRef()[0];
					j_d = box.getRef()[1];
				}
			}
		}
		
		System.out.println();
		System.out.println();
		
		VertexInterface depart = boxes[i_d][j_d];
		PreviousInterface previous = Dijkstra.dijkstra(maze, depart);
		System.out.println();
		System.out.print("previous: ");
		previous.printPrevious();
		
		int i_a = 99;
		int j_a = 99;
		String labelA;
		VertexInterface arrival = boxes[i_d][j_d];
		for(VertexInterface[] list : boxes) {
			for(VertexInterface box : list) {
				labelA = box.getLabel2();
				if(labelA == "A") {
					System.out.println("arrival: x="+box.getRef()[0]+"; y="+box.getRef()[1]);
					i_a = box.getRef()[0];
					j_a = box.getRef()[1];
					arrival = box;
				}
			}
		}
		VertexInterface currentCase = arrival;
		ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();
		while(currentCase!=null) {
			currentCase.printPos();
			if((currentCase.getLabel2()!="A")&&(currentCase.getLabel2()!="D")){
				path.add(currentCase);
			}
			currentCase = previous.getDad(currentCase);
		}
		
		System.out.println();
		System.out.println("Initial Maze:");
		maze.printMaze();
		System.out.println();
		System.out.println("Solved Maze:");
		for(VertexInterface[] line : boxes) {
			for(VertexInterface box : line) {
				if(path.contains(box)) {
					System.out.print(". ");
				} else {
					System.out.print(box.getLabel2()+" ");
				}
			}
			System.out.println();
		}
	}

}
