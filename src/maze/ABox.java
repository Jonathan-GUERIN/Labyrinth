package maze;

import dijkstra.GraphInterface;

public class ABox extends MBox{
	private final String label = "A";
	
	/*
	 * Instance de la case d'arriv�e
	 */
	public ABox(GraphInterface maze, int x, int y) {
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
