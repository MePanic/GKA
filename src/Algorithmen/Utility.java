package Algorithmen;

import java.util.ArrayList;

import GKA.EdgeCapacityImpl;
import GKA.EdgeRatedImpl;
import GKA.GraphImpl;
import GKA.NormalEdgeImpl;
import GKA.VertexImpl;
import Interfaces.CapacityEdge;
import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.NormalEdge;
import Interfaces.RatedEdge;
import Interfaces.Vertex;

public class Utility {

	public static ArrayList<Vertex> vertexs(int  ZeroToN){
		if (ZeroToN < 0) {
			throw new IllegalArgumentException("Only positive Number of Vertex");
		}
		ArrayList<Vertex> l2 = new ArrayList<Vertex>();
		for(int i=0;i<=ZeroToN;i++){
			l2.add(VertexImpl.valueOf(i));};
		return l2;
	}
	
	public static NormalEdge ne(int source,int target){
		return NormalEdgeImpl.valueOf(source, target);
	}
	
	public static RatedEdge re(int source,int target,Double value){
		return EdgeRatedImpl.valueOf(source, target, value);
	}
	public static RatedEdge re(int source,int target,int value){
		Double temp = (double) value;
		return EdgeRatedImpl.valueOf(source, target,temp);
	}
	
	public static CapacityEdge ce(int source,int target,Double value){
		return EdgeCapacityImpl.valueOf(source, target, value);
	}
	public static CapacityEdge ce(int source,int target,int value){
		Double temp = (double) value;
		return EdgeCapacityImpl.valueOf(source, target,temp);
	}

	public static <V extends Vertex,E extends Edge> Graph<V,E> makeGraph(ArrayList<V> vertexs, E... edges){
		return GraphImpl.valueOf(false,vertexs,edges);
	}
	
	public static <V extends Vertex,E extends Edge> Graph<V,E> makeDigraph(ArrayList<V> vertexs, E... edges){
		return GraphImpl.valueOf(true,vertexs,edges);
	}
	
	//ShortestWayBFS:
	//Ermittelt durch Breitensuche den k�rzesten Weg von source zu target und gibt ihn als Liste zur�ck.
	//Falls kein Weg vorhanden ist, wird eine leere Liste zur�ck gegeben.
	//Falls source oder target keine Ecken sind, wird eine Exception geworfen.
	public static <V extends Vertex,E extends Edge> ArrayList<V> shortestWayBFS(Graph<V,E> g, int from, int to, int times) {	
		return BFS.shortestWayBFS(g, from, to, times);
	}
	
	//getComponent:
	//Ermittelt durch Breitensuche alle Ecken der Komponente in der source sich befindet und gibt sie als Liste zur�ck.
	//Falls source kein Eckn ist, wird eine Exception geworfen.
	public static <V extends Vertex,E extends Edge> ArrayList<V> getComponent(Graph<V,E> g,int from, int times) {
		return BFS.getComponent(g, from, times);
	}
	
	//shortestWayDijkstra:
	//Ermittelt durch den Dijkstra-Algorithmus den kuerzesten Weg von source nach target unter beruecksichtigung der 
	//Kantengewichtung und gibt ihn als Liste zurueck. Bei negativen Gewichtungen kann der Algorithmus falsche Ergebnisse liefern!
	//Falls kein Weg vorhanden ist, wird eine leere Liste zurueck gegeben.
	//Falls source oder target keine Ecken sind, wird eine Exception geworfen.
	public static <V extends Vertex,E extends RatedEdge> ArrayList<V> shortestWayDijkstra(Graph<V,E> g,int from, int to, int times) {
		return Dijkstra.shortestWayDijkstra(g, from, to, times);
		}
	
	//shortestWayFloydWarshall:
	public static <V extends Vertex,E extends RatedEdge> ArrayList<V> shortestWayFloydWarshall(Graph<V,E> g,int from, int to, int times) {
		return FloydWarshall.shortestWayFloydWarshall(g, g.getV(from).getId(), g.getV(to).getId(), times);
		}
	}


