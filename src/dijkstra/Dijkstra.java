package dijkstra;

import java.util.ArrayList;

public class Dijkstra {
	private static PreviousInterface dijkstra(GraphInterface g, 
			VertexInterface r,
			ASetInterface a, 
			PiInterface pi, 
			PreviousInterface previous) {
		a.add(r);
		VertexInterface pivot = r; //meme adresse memoire pour pivot et r
		pi.setWeight(r, 0);
		ArrayList<VertexInterface> All = g.getAllVertex();
		ArrayList<VertexInterface> notAll = g.getAllVertex();  //risque pas d'etre la meme adresse?
		
		System.out.println();
		int n = All.size();
		System.out.println("n= "+n);
		for (VertexInterface x : g.getAllVertex()) {
			pi.setWeight(x, 999999);
		}
		
		pi.setWeight(r, 0);
		pi.print();
		System.out.println(pi);
		System.out.println();
		for (int j = 1; j < n; j++) {
			System.out.println("j= "+j);
			System.out.print("pivot: ");
			pivot.printPos();
			System.out.println("successors: ");

			for (VertexInterface y : g.getSuccessors(pivot)) {
				if (!(a.has(y))) {
					System.out.println(true);
					System.out.print("somme: ");
					System.out.println(pi.getWeight(pivot)+pi.getWeightLink(pivot, y));
					System.out.print("pivot: ");
					System.out.println(pi.getWeight(pivot));
					System.out.print("link: ");
					System.out.println(pi.getWeightLink(pivot, y));
					System.out.print("y: ");
					System.out.println(pi.getWeight(y));
					if ((pi.getWeight(pivot)+pi.getWeightLink(pivot, y)) < pi.getWeight(y)) {
						System.out.println();
						pi.setWeight(y, pi.getWeight(pivot)+pi.getWeightLink(pivot, y));
						previous.setDad(y, pivot);
					}
				}
			}
			int min = 999999;
			VertexInterface x_min = pivot;
			notAll.remove(pivot);
			System.out.print("notAll: ");
			System.out.println(notAll);
			for (VertexInterface x : notAll) {
				if (pi.getWeight(x)<min) {
					min = pi.getWeight(x);
					x_min = x;
				}
			}
			pivot = x_min;  // si pas trouvé, ce sera le pivot, donc pas de changement
			System.out.print("new pivot: ");
			pivot.printPos();
			a.add(pivot);  //double = pas de prob
			System.out.print("aset: ");
			System.out.println(a);
			notAll.remove(pivot);  //deja enlevé, pas de prob
			System.out.print("notAll: ");
			System.out.println(notAll);
			System.out.println();
		}
		return previous;
	}
	
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
		ASetInterface aset = new ASet();
		PiInterface pi = new Pi();
		PreviousInterface previous = new Previous();
		return dijkstra(g, r, aset, pi, previous);
	}
}
