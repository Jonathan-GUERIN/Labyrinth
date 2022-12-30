package dijkstra;

import java.util.HashSet;

public class ASet implements ASetInterface {
	private HashSet<VertexInterface> aset;
	
	public ASet() {
		this.aset = new HashSet<VertexInterface>();
	}
	@Override
	public void add(VertexInterface sommet) {
		aset.add(sommet);
	}

	@Override
	public boolean has(VertexInterface sommet) {
		if(aset.contains(sommet)){		//On peut utiliser this ici à la place de super, pourquoi?
			return true;
		}
		return false;
	}

}
