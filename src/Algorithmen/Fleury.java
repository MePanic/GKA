package Algorithmen;

	import java.util.ArrayList;
	import java.util.HashMap;

import Interfaces.Edge;
	import Interfaces.Graph;
	import Interfaces.RatedEdge;
import Interfaces.Vertex;
public class Fleury {
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
		static <V extends Vertex,E extends Edge> ArrayList<V> fleury(Graph<V,E> g,int source, int times) {

			if (g.getNumOfVertexs() < source) {
				throw new IllegalArgumentException("Vertex not found");
			}
			
			
			System.out.println("Fleury:");
			//InitTime
			zstVorher = System.currentTimeMillis();
			//Fuehre n mal Fleury aus, mit n = times
			ArrayList<V> res = new ArrayList<V>();
			for(int i=times;i>=0;i--){
				zugriffe = 0;
				res = doFleury(g,g.getV(source));
			}
			System.out.println("Zugriffe: " + zugriffe);
			
			//PrintTime
			zstNachher = System.currentTimeMillis();
			time = zstNachher - zstVorher;
			System.out.println("Zeit bentigt: " + time + " Millisec");
			return res;
		}
		
		
	
	private static <V extends Vertex,E extends Edge> ArrayList<V> doFleury(Graph<V,E> g,V from) {
		ArrayList<V> w = new ArrayList<V>();
		ArrayList<E> checked = new ArrayList<E>();
		w.add(from);
		V next = from;
		boolean added = true;

		for(V e : g.getVertexs()){
			zugriffe++;
			if(g.getNeighbors(e).size()%2 > 0)
			return w;
		}
		
		while(!checked.containsAll(g.getEdges())){
			added = false;
			for(V e : g.getNeighbors(next)){
				zugriffe++;
				if(!checked.containsAll(g.getEdgeBetween(next,e)) && !g.teilgraphWithout(new ArrayList<V>(),checked).isSchnittkante(g.getEdgeBetween(next,e))){
					checked.addAll(g.getEdgeBetween(next,e));
					added = true;
					next = e;
					w.add(e);
					break;
				}
			}
			if(!added){
				for(V e : g.getNeighbors(next)){
					zugriffe++;
					if(!checked.containsAll(g.getEdgeBetween(next,e))){
						
						checked.addAll(g.getEdgeBetween(next,e));
					
				added = true;
				next = e;
				w.add(e);
				break;
				}
				}
			}
			
		}
		return w;
		
		}
	}

