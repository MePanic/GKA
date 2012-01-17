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
		
		if(res.size()==0){System.out.println("Kein Hamiltonkreis!");}
		else{ System.out.println(res);}
		
		return res;
	}

	private static <V extends Vertex, E extends Edge> ArrayList<V> doBondyChvatal(Graph<V, E> g, V from) {

		ArrayList<E> newEdges = new ArrayList<E>();
		newEdges.addAll(g.getEdges());
		ArrayList<V> hamPath = new ArrayList<V>();
		ArrayList<E> resEdges = new ArrayList<E>();

		boolean changed = true;
		V prevVert = from;
		V vert = from;
		int grad;
		int eigenGrad;
		int runs = 0;
		while(changed){
			runs++;
			resEdges.clear();
			resEdges.addAll(newEdges);
			eigenGrad = 0;
			for(E e : resEdges){
				if(e.getId()[0] == vert.getId() || e.getId()[1] == vert.getId()){
					eigenGrad ++;
				}				
			}
			
			for(E e : resEdges){
				if(e.getId()[0] == vert.getId()){
					grad = 0;
					for(E f : resEdges){
						if(f.getId()[0] == e.getId()[1] ||f.getId()[1] == e.getId()[1]){
							grad++;
						}
					}
					if(grad > 2 && eigenGrad > 2){newEdges.remove(e); eigenGrad--;}
					
				}
				if(e.getId()[1] == vert.getId()){
					grad = 0;
					for(E f : resEdges){
						if(f.getId()[0] == e.getId()[0] ||f.getId()[1] == e.getId()[0]){
							grad++;
						}
					}
					if(grad > 2 && eigenGrad > 2){newEdges.remove(e); eigenGrad--;}
					
				}
			}
				
				

				for(E h : newEdges){
					if((h.getId()[0] == vert.getId() && h.getId()[1] != prevVert.getId())){
						prevVert = vert;
						vert = g.getV(h.getId()[1]);
						continue;
					}
						
					if(h.getId()[1] == vert.getId() && h.getId()[0] != prevVert.getId()){
						prevVert = vert;
						vert = g.getV(h.getId()[0]);
						continue;
					}
					
				}
			
			
			if(vert == from){changed = false;}
			if(runs > g.getNumOfVertexs()){break;}
		}
		
		if(newEdges.size() != g.getNumOfVertexs()){
			return new ArrayList<V>();
		}
		
		hamPath.add(from);
		E tempEdge = null;
		for(int z = 0; z < g.getNumOfVertexs()-1; z++){
			for(E p : newEdges){
				if(p.getId()[0] == hamPath.get(hamPath.size()-1).getId() && tempEdge != p){
					hamPath.add(g.getV(p.getId()[1]));
					tempEdge = p;
					break;
				}
				if(p.getId()[1] == hamPath.get(hamPath.size()-1).getId()&& tempEdge != p){
					hamPath.add(g.getV(p.getId()[0]));
					tempEdge= p;
					break;
				}
			}
			
		}
		
		return hamPath;

	}
}
