package maze;

import dijkstra.GraphInterface;

public class DBox extends MBox{
	private final String label = "D";

	public DBox(GraphInterface maze, int x, int y) {
		super(x, y, maze);
	}
	
	protected String getLabel() {
		return this.label;
	}

	@Override
	public String getLabel2() {
		return this.label;
	}

}
