package Algorithmen;

import java.util.*;

import Interfaces.Graph;
import Interfaces.RatedEdge;
import Interfaces.Vertex;

public class FloydWarshall {

private static long before;
private static long after;
	
	public static <V extends Vertex,E extends RatedEdge> ArrayList<V> shortestWayFloydWarshall(Graph<V,E> g,int from, int to, int times){
		ArrayList<ArrayList<Double>> distance = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> transit = new ArrayList<ArrayList<Double>>();
		int zugriffe = 0;
		ArrayList<V> res = new ArrayList<V>();
		int size = g.getVertexs().size();
		before = System.currentTimeMillis();
		for (int f = 0; f < times; f++){
		// Initialisieren
			res = new ArrayList<V>();
			distance = new ArrayList<ArrayList<Double>>();
			transit = new ArrayList<ArrayList<Double>>();
			zugriffe = 0;
		for (int i = 0; i < size; i++) {
			distance.add(i, new ArrayList<Double>());
			transit.add(i, new ArrayList<Double>());
			for (int k = 0; k < g.getVertexs().size(); k++) {	
				if (i == k){
					distance.get(i).add(k, 0.0);
				} else {
					distance.get(i).add(k, Double.POSITIVE_INFINITY);	
				}
				transit.get(i).add(k, 0.0);
				zugriffe++;
			}
		}
		for(E e : g.getEdges()){		
				zugriffe++;
				distance.get(e.getId()[0]).set(e.getId()[1], e.getValue());
		}
		
		for (int l = 0; l < size; l++){
			for (int m = 0; m < size; m++){
				if (l != m){
					for (int n = 0; n < size; n++){
						zugriffe++;
						if (distance.get(m).get(n) > (distance.get(m).get(l) + distance.get(l).get(n))) { transit.get(m).set(n, Integer.valueOf(l).doubleValue()+1); }
						distance.get(m).set(n, Math.min(distance.get(m).get(n), (distance.get(m).get(l) + distance.get(l).get(n))));
						if (distance.get(m).get(m) < 0.0) { throw new IllegalArgumentException("FUU"); }
					}
				}
			}
		}
		
		int txx = transit.get(from).get(to).intValue()-1;

		if (txx == 0){
			res.add(g.getV(from));
			res.add(g.getV(to));
		} else {
			for (V x : shortestWay(g, transit, from, txx)){				
				res.add(x);			
			}
			res.remove(res.size()-1);
			for (V x : shortestWay(g, transit, txx, to)){				
				res.add(x);			
			}
		}
		}
		after = System.currentTimeMillis();
		System.out.println("Floyd Warshall:");
		System.out.println("Zugriffe: " + zugriffe);
		System.out.println("Zeit bentigt: " + (after - before) + " Millisec");
		return res;
	}
	
	
	private static <V extends Vertex, E extends RatedEdge> ArrayList<V> shortestWay(Graph<V,E> g, ArrayList<ArrayList<Double>> t, int from, int to){
		ArrayList<V> res = new ArrayList<V>();

		int txx = t.get(from).get(to).intValue()-1;

		if (t.get(from).get(to).compareTo(0.0) == 0){
			res.add(g.getV(from));
			res.add(g.getV(to));
		} else {
			for (V x : shortestWay(g, t, from, txx)){				
				res.add(x);			
			}
			res.remove(res.size()-1);
			for (V x : shortestWay(g, t, txx, to)){				
				res.add(x);			
			}
			
		}

		return res;
	}
	
}
