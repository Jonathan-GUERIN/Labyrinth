package tp04_3;

import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.MBox;
import maze.Maze;
import maze.WBox;

public class Main {

	public static void main(String[] args) {
		VertexInterface[][] boxes = new VertexInterface[3][3];
		GraphInterface maze = new Maze(boxes,3,3);
		boxes[0][0] = new DBox(maze,0,0);
		boxes[0][1] = new EBox(maze,0,1);
		boxes[0][2] = new EBox(maze,0,2);
		boxes[1][0] = new WBox(maze,1,0);
		boxes[1][1] = new WBox(maze,1,1);
		boxes[1][2] = new EBox(maze,1,2);
		boxes[2][0] = new ABox(maze,2,0);
		boxes[2][1] = new EBox(maze,2,1);
		boxes[2][2] = new EBox(maze,2,2);
		
		boxes[0][0].printLabel();
		boxes[0][1].printLabel();
		boxes[0][2].printLabel();
		System.out.println();
		boxes[1][0].printLabel();
		boxes[1][1].printLabel();
		boxes[1][2].printLabel();
		System.out.println();
		boxes[2][0].printLabel();
		boxes[2][1].printLabel();
		boxes[2][2].printLabel();
		
		System.out.println();
		System.out.println();
		
		maze.printMaze();
		
		MBox box1 = new EBox(maze,0,0);
		VertexInterface box2 = new EBox(maze,0,0);
		box1.printLabel();
		box2.printLabel();
		
		System.out.println();
		System.out.println();
		
		
		System.out.println(maze.links(new EBox(maze,0,0), new EBox(maze,1,0)));
		System.out.println(maze.links(boxes[0][1], boxes[0][2]));
		
		ArrayList<VertexInterface> All = maze.getAllVertex();
		System.out.println();
		System.out.println(All);
		
		
		VertexInterface box3 = new EBox(maze,0,0);
		box3.printLabel();
		
		System.out.println();
		String a = "ab";
		char b = 'a';
		System.out.println(b);
		String s=String.valueOf(b);  
		System.out.println(s);
		
		ArrayList<VertexInterface> successors = maze.getSuccessors(new DBox(maze,0,2));
		System.out.println();
		ArrayList<VertexInterface> successors2 = maze.getSuccessors(boxes[0][2]);
		System.out.println();
		VertexInterface successor = successors2.get(0);
		successor.printLabel();
		System.out.print(successor.getRef()[0]);
		System.out.println(successor.getRef()[1]);
	}

}
