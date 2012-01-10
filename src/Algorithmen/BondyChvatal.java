package Algorithmen;

import java.util.ArrayList;

import static Algorithmen.Utility.*;
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

		Graph<V, E> hull = makeGraphl(g.getVertexs(), g.getEdges());
		ArrayList<E> newEdges = new ArrayList<E>();
		newEdges.addAll(g.getEdges());
		ArrayList<V> blub = g.getVertexs();
		ArrayList<V> hamPath = new ArrayList<V>();
		ArrayList<E> resEdges = new ArrayList<E>();

		boolean changed = true;
		while(changed){
			changed = false;
			hull = makeGraphl(blub, newEdges);
			
			for(int i = 0; i < (hull.getNumOfVertexs()-1); i++){
				for(int k = i+1; k < hull.getNumOfVertexs(); k++){
					if(g.getEdgeBetween(g.getV(i), hull.getV(k)).size() == 0 
					&& ((hull.getNeighbors(hull.getV(i)).size() + hull.getNeighbors(hull.getV(k)).size()) >= hull.getNumOfVertexs())
					&& !hull.getEdges().contains(ne(i,k))){
						newEdges.add((E) ne(i, k));
						zugriffe++;
						changed = true;
					}
				}
			}
		}
		
		changed = true;
		resEdges.addAll(hull.getEdges());
		hamPath.add(from);
		while(changed){

			changed = false;
			hull = makeGraphl(blub, resEdges);
			V actV = hamPath.get(hamPath.size()-1);
			for(V v : hull.getNeighbors(actV)){
				if(hull.getEdges().contains((E) ne(actV.getId(), v.getId()))&& !hamPath.contains(v)){
					hamPath.add(v);
					zugriffe++;
					for(V x: hull.getNeighbors(v)){
						if(hamPath.contains(x) && hamPath.get(hamPath.size()-2) != x ){
							resEdges.remove(ne(v.getId(), x.getId()));
							resEdges.remove(ne(x.getId(), v.getId()));
							zugriffe++;
						}
					}
					changed = true;
					break;
				}
			}
		}
		if(newEdges.contains((E) ne(hamPath.get(0).getId(), hamPath.get(hamPath.size()-1).getId()))
		|| newEdges.contains((E) ne(hamPath.get(hamPath.size()-1).getId(), hamPath.get(0).getId()))){
			hamPath.add(hamPath.get(0));	
		}
		System.out.println("..................");
		for(E a : resEdges){

			System.out.println(a.getId()[0] + "" + a.getId()[1]);

		}
		System.out.println("..................");
		System.out.println(hamPath);

		return hamPath;

	}
}
