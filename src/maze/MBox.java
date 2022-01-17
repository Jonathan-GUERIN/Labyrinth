package maze;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface{
	private int x;
	private int y;
	private final int[] ref = new int[2];
	//private int weight = 999999; //??
	//private VertexInterface dad; //??
	private GraphInterface maze;
	
	public MBox(int x, int y, GraphInterface maze) {
		this.x = x;
		this.y = y;
		this.ref[0] = x;
		this.ref[1] = y;
		this.maze = maze;
	}

	@Override
	public boolean equals(VertexInterface sommet) {
		int[] coord1 = sommet.getRef();
		int[] coord2 = this.getRef();
		if((coord1[0]==coord2[0])&&(coord1[1]==coord2[1])) {
			return true;
		}
		return false;
	}
	

	public int[] getRef() {
		return this.ref;
	}
	
	protected abstract String getLabel();
	
	public void printLabel() {
		System.out.print(this.getLabel()+" ");
	}
	
	public abstract String getLabel2();
	
	public void printPos() {
		System.out.println(this.getLabel()+" x="+this.x+" "+"y="+this.y);
	}
}
