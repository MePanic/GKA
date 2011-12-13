package Algorithmen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Interfaces.CapacityEdge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class FordFulkerson {
	static int zugriffe;
	static long zstVorher = 0;
	static long zstNachher = 0;
	static long time = 0;

	public static long getLastTime() {
		return time;
	}

	public static long getLastZugriffe() {
		return zugriffe;
	}

	static <V extends Vertex, E extends CapacityEdge> Double[][] shortestWayFordFulkerson(Graph<V, E> g, int from, int to, int times) {

		if ((g.getNumOfVertexs() < from) || (g.getNumOfVertexs() < to)) {
			throw new IllegalArgumentException("Vertex not found");
		}
		System.out.println("FordFulkerson:");
		// InitTime
		zstVorher = System.currentTimeMillis();
		// Fuehre n mal FordFulkerson aus, mit n = times
		Double[][] m = null;
		for (int i = times; i >= 0; i--) {
			zugriffe = 0;
			m = doFordFulkerson(g, g.getV(from), g.getV(to));
		}
		System.out.println("Zugriffe: " + zugriffe);
		// PrintTime
		zstNachher = System.currentTimeMillis();
		time = zstNachher - zstVorher;
		System.out.println("Zeit bentigt: " + time + " Millisec");
		
		System.out.println("Maximaler Fluss: ");
		for(Double[] p : m){
			for(Double q : p){
				System.out.print(q + " ");
			}
			System.out.println();
		}
		
		return m;

	}

	private static <V extends Vertex, E extends CapacityEdge> Double[][] doFordFulkerson(Graph<V, E> g, V from, V to) {
		Double[][] m = new Double[g.getNumOfVertexs()][g.getNumOfVertexs()];
		for (int i = 0; i < g.getNumOfVertexs(); i++) {
			Arrays.fill(m[i], 0.0);
		}

		if (from == to){
			return m;
		}
		HashMap<V, Double[]> map = new HashMap<V, Double[]>();
		map.put(from, new Double[] { 0.0, Double.POSITIVE_INFINITY });

		ArrayList<V> inspiziert = new ArrayList<V>();

		while (true) {

			//2.b
			V v = null;
			for (V t : map.keySet()) {
				if (!inspiziert.contains(t))
					v = t;
			}
				//2.a
			if(v == null){
//				// 4.
				return m;
			}
			for (E e : g.getAdjazentEdges(v)) {
				//Bei vorw�rtskante
				if (e.getId()[0] == v.getId() 
					&& !(map.keySet().contains(g.getV(e.getId()[1]))) 
					&& e.getValue() > m[e.getId()[0]][e.getId()[1]]) {
					
					V tempvert = g.getV(e.getId()[1]);
					map.put(tempvert,new Double[] {(double) v.getId(),Math.min(map.get(v)[1],e.getValue()- m[e.getId()[0]][e.getId()[1]]) });
				}
				//Bei R�ckw�rtskante
				if (e.getId()[1] == v.getId()
					&& !(map.keySet().contains(g.getV(e.getId()[0])))
					&& 0 < m[e.getId()[1]][e.getId()[0]]) {
					
					V tempvert = g.getV(e.getId()[0]);
					map.put(tempvert,new Double[] {(double) -v.getId(),Math.min(map.get(v)[1],m[e.getId()[1]][e.getId()[0]]) });
				}
			}
			inspiziert.add(v);

			// 3.
			if (map.keySet().contains(to)) {
				double differenz = map.get(to)[1];
				V now = to;
				V next = g.getV(Math.abs(map.get(now)[0].intValue()));
				Double value = map.get(to)[0];
				
				while (now != next) {
					if (value >= 0) {
						m[next.getId()][now.getId()] += differenz;	
					} else {
						m[now.getId()][next.getId()] -= differenz;	
					}
					now = next;
					next = g.getV(Math.abs(map.get(now)[0].intValue()));
				}
				inspiziert.clear();
				Double[] restore = new Double[] { map.get(from)[0],	map.get(from)[1] };
				map.clear();
				map.put(from, restore);
			}
			
			
		}
	}
}
