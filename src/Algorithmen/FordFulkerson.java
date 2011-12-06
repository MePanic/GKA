package Algorithmen;

import java.util.ArrayList;
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

	static <V extends Vertex, E extends CapacityEdge> Double[][] shortestWayFordFulkerson(
			Graph<V, E> g, int from, int to, int times) {

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
		return m;

	}

	private static <V extends Vertex, E extends CapacityEdge> Double[][] doFordFulkerson(Graph<V, E> g, V from, V to) {
		Double[][] m = new Double[g.getNumOfVertexs()][g.getNumOfVertexs()];
		for (int i = 0; i < g.getNumOfVertexs(); i++) {
			for (int i2 = 0; i2 < g.getNumOfVertexs(); i2++) {
				m[i][i2] = 0.0;
			}
		}
		HashMap<V, Double[]> map = new HashMap<V, Double[]>();
		map.put(from, new Double[] { 0.0, Double.POSITIVE_INFINITY });

		ArrayList<V> inspiziert = new ArrayList<V>();

		// 2.
		while (!inspiziert.contains(to)) {

				// 4.
			if (inspiziert.containsAll(map.keySet())){
				return m;
			}
			V v = null;
			for (V t : map.keySet()) {
				if (!inspiziert.contains(t))
					v = t;
			}

			inspiziert.add(v);
			System.out.println("zufallsEcke" + v.getId());
			for (E e : g.getAdjazentEdges(v)) {
				//Bei vorw�rtskante
				if (e.getId()[0] == v.getId()&& !(inspiziert.contains(g.getV(e.getId()[1])))&& e.getValue() > m[e.getId()[0]][e.getId()[1]]) {
					V tempvert = g.getV(e.getId()[1]);
					map.put(tempvert,new Double[] {(double) v.getId(),Math.min(map.get(v)[1],e.getValue()- m[e.getId()[0]][e.getId()[1]]) });
					System.out.println(map.get(tempvert)[0] + " "+ map.get(tempvert)[1]);
				}
				//Bei R�ckw�rtskante
				if (e.getId()[1] == v.getId()&& !(inspiziert.contains(g.getV(e.getId()[0])))&& 0 < m[e.getId()[0]][e.getId()[1]]) {
					V tempvert = g.getV(e.getId()[0]);
					map.put(tempvert,new Double[] {(double) -v.getId(),Math.min(map.get(v)[1],m[e.getId()[0]][e.getId()[1]]) });
				}
			}

			// 3.
			if (map.keySet().contains(to)) {
				double differenz = map.get(to)[1];

				V now = to;
				V next = g.getV(Math.abs(map.get(now)[0].intValue()));
				Double value = map.get(to)[0];
				while (now != next) {
					if (value >= 0) {
						m[now.getId()][next.getId()] += differenz;
					} else {
						m[now.getId()][next.getId()] -= differenz;
					}
					now = next;
					next = g.getV(Math.abs(map.get(now)[0].intValue()));
					System.out.println(now.getId());
					System.out.println(next.getId());
				}
				inspiziert.remove(inspiziert.get(inspiziert.size() - 1));
				Double[] restore = new Double[] { map.get(from)[0],	map.get(from)[1] };
				map.clear();
				map.put(from, restore);

				// if(!map.keySet().contains(to))
				// return null;

				for (int i = 0; i < g.getNumOfVertexs(); i++) {
					System.out.println();
					for (int i2 = 0; i2 < g.getNumOfVertexs(); i2++) {

						System.out.print(m[i][i2]);
					}
				}
			}
		}
		return m;
	}
}
