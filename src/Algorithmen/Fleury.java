package Algorithmen;

	import java.util.ArrayList;
	import java.util.HashMap;

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
		static <V extends Vertex,E extends RatedEdge> ArrayList<V> shortestWayDijkstra(Graph<V,E> g,int from, int to, int times) {

			if ((g.getNumOfVertexs() < from) || (g.getNumOfVertexs() < to)) {
				throw new IllegalArgumentException("Vertex not found");
			}
			
			for(E e : g.getEdges()){
				if(e.getValue() < 0)
					System.out.println("Warnung: Negativ gewichtete Kanten gefunden! Das Ergebnis ist moeglicherweise nicht korrekt.");
			}
			
			System.out.println("Dijkstra:");
			//InitTime
			zstVorher = System.currentTimeMillis();
			//Fuehre n mal Dijkatra aus, mit n = times
			ArrayList<V> temp1 = new ArrayList<V>();
			for(int i=times;i>=0;i--){
				zugriffe = 0;
				temp1 = dijkstra(g,g.getV(from),g.getV(to));
			}
			System.out.println("Zugriffe: " + zugriffe);
			
			//Weg rueckwaerts berechnen
			ArrayList<V> temp2 = new ArrayList<V>();
			ArrayList<V> res = new ArrayList<V>();
			for(int i=to;i!=from;){
				temp2.add(g.getV(i));
				i = temp1.get(i).getId();
			}
			temp2.add(g.getV(from));
			
			//Liste invertieren
			for(int i=temp2.size();i>0;i--){
				res.add(temp2.get(i-1));
			}
			//PrintTime
			zstNachher = System.currentTimeMillis();
			time = zstNachher - zstVorher;
			System.out.println("Zeit bentigt: " + time + " Millisec");
			return res;
		}
		
		
	
	private static <V extends Vertex,E extends RatedEdge> ArrayList<E> doFleury(Graph<V,E> g,V from) {
		ArrayList<V> w = new ArrayList<V>();
		ArrayList<E> checked = new ArrayList<E>();
		w.add(from);
		V next = from;
		E edge;
		
		while(g.getEdges().containsAll(checked)){
			boolean added = false;
			for(E e : g.getAdjazentEdges(next)){
				if(!checked.contains(e) && g.teilgraphWithout(new ArrayList<V>(),checked).isSchnittkante(e)){
					checked.add(e);
					added = true;
				}
			}
			if(!added){
				for(E e : g.getAdjazentEdges(next)){
					if(!checked.contains(e)){
						checked.add(e);
					}
				}
			}
		}
		return checked;
		
		}
	}

