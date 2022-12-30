package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	private Hashtable<VertexInterface, Integer> pi;
	
	public Pi() {
		this.pi = new Hashtable<VertexInterface, Integer>();
	}

	@Override
	public int getWeight(VertexInterface r) {
		return (int) pi.get(r);
	}

	@Override
	public int getWeightLink(VertexInterface r, VertexInterface y) {
		return 1;
	}

	@Override
	public void setWeight(VertexInterface r, int weight) {
		pi.put(r, weight);
	}
	
	public void print() {
		System.out.println(this.pi);
	}
}
