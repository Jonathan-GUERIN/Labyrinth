package maze;

import dijkstra.GraphInterface;

public class WBox extends MBox{
	private final String label = "W";
	
	/*
	 * Instance d'une case mur
	 */
	public WBox(GraphInterface maze, int x, int y) {
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
