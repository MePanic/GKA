package Algorithmen;

import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class BFS {
	public static int zugriffe; 
	static long zstVorher = 0;
	static long zstNachher = 0;
	public static long time = 0;
	
	public static long getLastTime(){
		return time;
	}
	
	public static long getLastZugriffe(){
		return zugriffe;
	}
	
	//getComponent:
	//Ermittelt durch Breitensuche alle Ecken der Komponente in der source sich befindet und gibt sie als Liste zur�ck.
	//Falls source kein Eckn ist, wird eine Exception geworfen.
	static <V extends Vertex,E extends Edge> ArrayList<V> getComponent(Graph<V,E> g,int from, int times) {
		
			if (g.getNumOfVertexs() < from) {
				return	new ArrayList<V>();
			}
			//InitTime
			zstVorher = System.currentTimeMillis();
			
//			System.out.println("getComponents:");
			//InitTime
			zstVorher = System.currentTimeMillis();
			//F�hre n mal die Breitensuche aus, mit n = times
			ArrayList<V> res = new ArrayList<V>();
			for(int i=times;i>=0;i--){
				zugriffe = 0;
				res = breitensuche(g,g.getV(from), g.getV(from));
			}
//			System.out.println("Zugriffe: " + zugriffe);
			//PrintTime
			zstNachher = System.currentTimeMillis();
			time = zstNachher - zstVorher;
//			System.out.println("Zeit bentigt: " + time + " Millisec");
			return res;
		
	}
	
		//ShortestWayBFS:
		//Ermittelt durch Breitensuche den k�rzesten Weg von source zu target und gibt ihn als Liste zur�ck.
		//Falls kein Weg vorhanden ist, wird eine leere Liste zur�ck gegeben.
		//Falls source oder target keine Ecken sind, wird eine Exception geworfen.
	static <V extends Vertex,E extends Edge> ArrayList<V> shortestWayBFS(Graph<V,E> g, int source, int target, int times) {	
			
			if ((g.getNumOfVertexs() < source) || (g.getNumOfVertexs() < target)) {
				return	new ArrayList<V>();
			}
			//InitTime
			zstVorher = System.currentTimeMillis();
			
			System.out.println("Shortest Way BFS:");
			//Wenn Start und Ziel gleich sind, gib Start/Ziel zur�ck
			if (source == target) {
				System.out.println("Zugriffe: " + 0);
				ArrayList<V> res = new ArrayList<V>();
				res.add(g.getV(source));
					System.out.println("Zeit bentigt: "+ 0+ " Millisec");
				return res;
			}
			//F�hre n mal die Breitensuche aus, mit n = times
			ArrayList<V> res = new ArrayList<V>();
			for(int i=times;i>=0;i--){
				zugriffe = 0;
				res = breitensuche(g,g.getV(source), g.getV(target));
				
				
				
				
			}
			//Print
			System.out.println("Zugriffe: " + zugriffe);
			zstNachher = System.currentTimeMillis();
			time = zstNachher - zstVorher;
			System.out.println("Zeit bentigt: " + time + " Millisec");
			return res;
	}
	
	
	private static <V extends Vertex,E extends Edge> ArrayList<V> breitensuche(Graph<V,E> g,V from, V to) {
		// Initialisierung
		HashMap<Integer, ArrayList<V>> temp = new HashMap<Integer, ArrayList<V>>();
		ArrayList<V> visited = new ArrayList<V>();
		int i = 0;
		visited.add(from); // start mit "from"
		zugriffe++;
		temp.put(i, new ArrayList<V>());
		temp.get(i).add(from);

		// Suchen
		while (!temp.get(i).isEmpty()) {
			zugriffe++;
			temp.put(i + 1, new ArrayList<V>());
			for (V v : temp.get(i)) {
				zugriffe++;
				for (V n : g.getNeighbors(v)) {
					zugriffe++;
					if (!visited.contains(n)) {
						if (n == to) {
							ArrayList<V> res = new ArrayList<V>((temp.keySet().size()));
							for (int c = 0; c < (temp.keySet().size()-1); c++) {
								zugriffe++;
								res.add(c,temp.get(c).get(temp.get(c).size()-1));
							}
							
								res.add(n);
							return res;
						}
						visited.add(n);
						zugriffe++;
						temp.get(i + 1).add(n);
					}
				}
			}
			i++;
		}
//		// Ausgabe (Rckweg suchen)
		ArrayList<V> res = new ArrayList<V>();
		for (ArrayList<V> v : temp.values()) {
			zugriffe++;
			res.addAll(v);
		}
		return res;
	}
}
