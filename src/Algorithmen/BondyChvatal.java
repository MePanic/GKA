package Algorithmen;

import java.util.ArrayList;

import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class BondyChvatal {
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

	static <V extends Vertex, E extends Edge> ArrayList<V> bondyChvatal(Graph<V, E> g, int source, int times) {

		if (g.getNumOfVertexs() < source) {
			throw new IllegalArgumentException("Vertex not found");
		}

		System.out.println("Bondy Chvatal:");
		// InitTime
		zstVorher = System.currentTimeMillis();
		// Fuehre n mal Bondy Chvatal aus, mit n = times
		ArrayList<V> res = new ArrayList<V>();
		for (int i = times; i >= 0; i--) {
			zugriffe = 0;
			res = doBondyChvatal(g, g.getV(source));
		}
		System.out.println("Zugriffe: " + zugriffe);

		// PrintTime
		zstNachher = System.currentTimeMillis();
		time = zstNachher - zstVorher;
		System.out.println("Zeit bentigt: " + time + " Millisec");
		return res;
	}

	private static <V extends Vertex, E extends Edge> ArrayList<V> doBondyChvatal(Graph<V, E> g, V from) {
		ArrayList<V> res = new ArrayList<V>();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return res;

	}
}
