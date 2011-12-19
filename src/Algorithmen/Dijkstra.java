package Algorithmen;

import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Graph;
import Interfaces.RatedEdge;
import Interfaces.Vertex;

public class Dijkstra {
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
	//shortestWayDijkstra:
	//Ermittelt durch den Dijkstra-Algorithmus den kuerzesten Weg von source nach target unter beruecksichtigung der 
	//Kantengewichtung und gibt ihn als Liste zurueck. Bei negativen Gewichtungen kann der Algorithmus falsche Ergebnisse liefern!
	//Falls kein Weg vorhanden ist, wird eine leere Liste zurueck gegeben.
	//Falls source oder target keine Ecken sind, wird eine Exception geworfen.
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
	
	
	
	
	
	
	private static <V extends Vertex,E extends RatedEdge> ArrayList<V> dijkstra(Graph<V,E> g,V from, V to) {
		ArrayList<V> res = new ArrayList<V>();
		ArrayList<V> visited = new ArrayList<V>();
		HashMap<V,Double> entfernung = new HashMap<V,Double>();
		HashMap<V,V> vorgaenger = new HashMap<V,V>();
		// Initialisieren
		for (V e : g.getVertexs()) {
			zugriffe++;
			entfernung.put(e, Double.POSITIVE_INFINITY);
			vorgaenger.put(e, null);
			visited.add(e);
		}
		entfernung.put(from,0.0);
		vorgaenger.put(from,from);
		zugriffe++;

		//Solange noch unbesuchte Ecken vorhanden sind...
		while (true) {
			V vh = null;
			double min = Double.POSITIVE_INFINITY;
			// unbesuchte Ecke mit kleinster entfernung suchen
			for(V e : visited){
				zugriffe++;
				if(!(e == null) && entfernung.get(e) < min) {
						min = entfernung.get(e);
						vh = e;
				}
			}
			//Brich ab wenn keine knoten mehr in der Komponente sind
			if(vh ==null){
				for(V e : g.getVertexs()) {
					zugriffe++;
					res.add(vorgaenger.get(e));
				}
				return res;
			}
			
			visited.remove(vh);
			zugriffe++;
			// Suche unbesuchte Nachbarn dieser Ecke und prfe ihre entfernung
			for(V e : g.getNeighbors(vh)){
				zugriffe++;
				if(visited.contains(e) && (entfernung.get(e) > (entfernung.get(vh) + g.getValueBetween(vh,e)))){
					entfernung.put(e,entfernung.get(vh) + g.getValueBetween(vh,e));
					vorgaenger.put(e, vh);
					zugriffe++;
				}
			}
		}
	}
}
