package dijkstra;

import java.util.Hashtable;

public class Previous implements PreviousInterface {
	private Hashtable<VertexInterface, VertexInterface> previous;
	
	public Previous() {
		this.previous = new Hashtable<VertexInterface, VertexInterface>();
	}
	@Override
	public VertexInterface getDad(VertexInterface y) {
		return (VertexInterface) this.previous.get(y);
	}

	@Override
	public void setDad(VertexInterface y, VertexInterface pere) {
		this.previous.put(y, pere);
	}
	
	public void printPrevious() {
		System.out.println(this.previous);
	}

}
