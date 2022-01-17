package maze;

import dijkstra.GraphInterface;

public class EBox extends MBox{
	private final String label = "E";

	public EBox(GraphInterface maze, int x, int y) {
		super(x, y, maze);
	}
	
	public String getLabel() {
		return this.label;
	}

	@Override
	public String getLabel2() {
		return this.label;
	}
}
