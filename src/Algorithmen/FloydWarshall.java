package Algorithmen;

import java.util.ArrayList;
import java.util.Arrays;

import Interfaces.Graph;
import Interfaces.RatedEdge;
import Interfaces.Vertex;

public class FloydWarshall {
	static int zugriffe; 
	static long zstVorher = 0;
	static long zstNachher = 0;
	static long time = 0;

	public static long getLastTime(){
		return time;
	}

	public static long getLastZugriffe(){
		return zugriffe;
	}
	

	static <V extends Vertex,E extends RatedEdge> ArrayList<V> shortestWayFloydWarshall(Graph<V,E> g,int from, int to, int times) {
		if ((g.getNumOfVertexs() < from) || (g.getNumOfVertexs() < to)) {
			throw new IllegalArgumentException("Vertex not found");
		}
		System.out.println("FloydWarshall:");
		//InitTime
		zstVorher = System.currentTimeMillis();
		//Fuehre n mal FloydWarshall aus, mit n = times
		ArrayList<V> res = new ArrayList<V>();
		ArrayList<Integer> templ = new ArrayList<Integer>();
		for(int i=times;i>=0;i--){
			zugriffe = 0;
			Double[][][] tempm = floydWarshall(g);
			templ = getshortestWay(tempm, from, to);
		}
		if (templ.size() > 0)
			res.add(0,g.getV(from));
		for(Integer t : templ){
			res.add(g.getV(t));
			zugriffe++;
			}
		System.out.println("Zugriffe: " + zugriffe);
		//PrintTime
		zstNachher = System.currentTimeMillis();
		time = zstNachher - zstVorher;
		System.out.println("Zeit bentigt: " + time + " Millisec");
		return res;
	}
	
	private static ArrayList<Integer> getshortestWay(Double[][][] m,int from, int to) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Double[][] dm = m[0];
		Double[][] tm = m[1];

		Double i = tm[to][from];
		Double i2 = dm[to][from];
		if(i2 < Double.POSITIVE_INFINITY){
		if(i == 0){
			zugriffe++;
			res.add(to);
			return res;
		}else{
			res.addAll(getshortestWay(m, from, i.intValue()));
			res.addAll(getshortestWay(m, i.intValue(), to));
		return res;
		}
	}
			return res;
	}


	
	private static <V extends Vertex,E extends RatedEdge> Double[][][] floydWarshall(Graph<V,E> g) {
		
	int length = g.getNumOfVertexs()-1;
	 Double[][] dm = new Double[length+1][length+1];
	 Double[][] tm = new Double[length+1][length+1];
		
	 	for(int i=0;i<=length;i++){
		 	for(int j=0;j<=length;j++){
		 		zugriffe++;
		 		tm[i][j] = 0.0;
		 		if(!(i==j)){
		 			if(g.getMatrix()[i][j] == null){
		 				dm[i][j] = Double.POSITIVE_INFINITY;
		 			}
		 			else{
		 				dm[i][j] = g.getMatrix()[i][j];
		 			}	
		 		}else{
		 			dm[i][j] = 0.0;
		 		}
		 	}
	 	}
		
	 	for(int i=0;i<=length;i++){
		 	for(int j=0;j<=length;j++){
		 		if(!(i==j)){
			 	for(int k=0;k<=length;k++){
			 		zugriffe++;
			 		if(!(j==k)){
			 			if(dm[i][k] != Math.min(dm[i][k],(dm[i][j]+dm[j][k])))
			 			{
			 				dm[i][k] = Math.min(dm[i][k],(dm[i][j]+dm[j][k]));
			 				tm[i][k] = (double) j;
			 				if(dm[i][i] < 0){throw new RuntimeException("Negativer Kreis gefunden");}
			 			}
			 		}
		 		}	
		 		}
		 	}	
	 	}
	 	return new Double[][][]{dm,tm}; 
	}
}


//package Algorithmen;
//
//import java.util.*;
//
//import Interfaces.Graph;
//import Interfaces.RatedEdge;
//import Interfaces.Vertex;
//
//public class FloydWarshall {
//
//		static int zugriffe = 0;
//private static long before;
//private static long after;
//static long time = 0;
//
//public static long getLastTime(){
//	return time;
//}
//
//public static long getLastZugriffe(){
//	return zugriffe;
//}
//	
//	public static <V extends Vertex,E extends RatedEdge> ArrayList<V> shortestWayFloydWarshall(Graph<V,E> g,int from, int to, int times){
//		ArrayList<ArrayList<Double>> distance = new ArrayList<ArrayList<Double>>();
//		ArrayList<ArrayList<Double>> transit = new ArrayList<ArrayList<Double>>();
//		ArrayList<V> res = new ArrayList<V>();
//		int size = g.getVertexs().size();
//		before = System.currentTimeMillis();
//		for (int f = 0; f < times; f++){
//		// Initialisieren
//			res = new ArrayList<V>();
//			distance = new ArrayList<ArrayList<Double>>();
//			transit = new ArrayList<ArrayList<Double>>();
//			zugriffe = 0;
//		for (int i = 0; i < size; i++) {
//			distance.add(i, new ArrayList<Double>());
//			transit.add(i, new ArrayList<Double>());
//			for (int k = 0; k < g.getVertexs().size(); k++) {	
//				if (i == k){
//					distance.get(i).add(k, 0.0);
//				} else {
//					distance.get(i).add(k, Double.POSITIVE_INFINITY);	
//				}
//				transit.get(i).add(k, 0.0);
//				zugriffe++;
//			}
//		}
//		for(E e : g.getEdges()){		
//				zugriffe++;
//				distance.get(e.getId()[0]).set(e.getId()[1], e.getValue());
//		}
//		
//		for (int l = 0; l < size; l++){
//			for (int m = 0; m < size; m++){
//				if (l != m){
//					for (int n = 0; n < size; n++){
//						zugriffe++;
//						if (distance.get(m).get(n) > (distance.get(m).get(l) + distance.get(l).get(n))) { transit.get(m).set(n, Integer.valueOf(l).doubleValue()+1); }
//						distance.get(m).set(n, Math.min(distance.get(m).get(n), (distance.get(m).get(l) + distance.get(l).get(n))));
//						if (distance.get(m).get(m) < 0.0) { throw new IllegalArgumentException("FUU"); }
//					}
//				}
//			}
//		}
//		
//		int txx = transit.get(from).get(to).intValue()-1;
//
//		if (txx == 0){
//			res.add(g.getV(from));
//			res.add(g.getV(to));
//		} else {
//			for (V x : shortestWay(g, transit, from, txx)){				
//				res.add(x);			
//			}
//			res.remove(res.size()-1);
//			for (V x : shortestWay(g, transit, txx, to)){				
//				res.add(x);			
//			}
//		}
//		}
//		after = System.currentTimeMillis();
//		System.out.println("Floyd Warshall:");
//		System.out.println("Zugriffe: " + zugriffe);
//		time = after - before;
//		System.out.println("Zeit bentigt: " + time + " Millisec");
//		return res;
//	}
//	
//	
//	private static <V extends Vertex, E extends RatedEdge> ArrayList<V> shortestWay(Graph<V,E> g, ArrayList<ArrayList<Double>> t, int from, int to){
//		ArrayList<V> res = new ArrayList<V>();
//
//		int txx = t.get(from).get(to).intValue()-1;
//
//		if (t.get(from).get(to).compareTo(0.0) == 0){
//			res.add(g.getV(from));
//			res.add(g.getV(to));
//		} else {
//			for (V x : shortestWay(g, t, from, txx)){				
//				res.add(x);			
//			}
//			res.remove(res.size()-1);
//			for (V x : shortestWay(g, t, txx, to)){				
//				res.add(x);			
//			}
//			
//		}
//
//		return res;
//	}
//	
//}
