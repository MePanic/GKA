package Tests;

import org.junit.Test;

import static Algorithmen.Utility.*;
import static Algorithmen.GoldbergTarjan.*;
import static Algorithmen.EdmundKarp.*;
import Interfaces.CapacityEdge;
import Interfaces.Graph;
import Interfaces.NormalEdge;
import Interfaces.RatedEdge;
import Interfaces.Vertex;



public class GraphTest {

										//0...7
	Graph<Vertex,NormalEdge> g1 = makeGraph(vertexs(7),ne(2,1),ne(2,3),ne(0,4),ne(4,3),ne(4,6),ne(6,5),ne(5,7));
	Graph<Vertex,RatedEdge> g2 = makeGraph(vertexs(8),re(2,1,20),re(2,3,25),re(1,4,30),re(2,4,15),re(4,3,40),re(4,6,24),re(6,5,5),re(5,7,19));
	Graph<Vertex,CapacityEdge> g3 = makeGraph(vertexs(8),ce(2,1,20),ce(2,3,25),ce(1,4,30),ce(0,4,15),ce(4,3,40),ce(4,6,24),ce(6,5,5),ce(5,7,19));
	Graph<Vertex,NormalEdge> g4 = makeDigraph(vertexs(8),ne(2,1),ne(2,3),ne(1,4),ne(0,4),ne(4,3),ne(4,6),ne(6,5),ne(5,7));
	Graph<Vertex,RatedEdge> g5 = makeDigraph(vertexs(8),re(0,1,20),re(1,2,25),re(1,3,30),re(2,4,15),re(3,5,40),re(4,6,24),re(5,7,5),re(6,8,19),re(7,8,18));
	Graph<Vertex,CapacityEdge> g6 = makeDigraph(vertexs(8),ce(0,1,20),ce(1,2,25),ce(1,3,30),ce(2,4,15),ce(3,5,40),ce(4,6,24),ce(5,7,5),ce(6,8,19),ce(7,8,18));
	Graph<Vertex,CapacityEdge> g7 = makeDigraph(vertexs(5),ce(0,1,3),ce(0,3,2),ce(1,2,2),ce(2,3,1),ce(3,4,2),ce(2,5,2),ce(3,1,2),ce(3,4,2),ce(4,5,4));
	
	public static Graph<Vertex,RatedEdge> g = makeGraph(vertexs(8),re(2,1,20),re(2,3,25),re(1,4,30),re(2,4,15),re(4,3,40),re(4,6,24),re(6,5,5),re(5,7,19));
	@Test
	public void test() {	
	
//		g1.ausgabe();
//		System.out.println(shortestWayBFS(g1,1,1,1000));	
//		System.out.println(shortestWayBFS(g2,1,7,1000));	
//		System.out.println(getComponentBFS(g1,5,1000));
//		System.out.println(shortestWayBFS(g1,1,6,1000));		
//		System.out.println(shortestWayDijkstra(g2,1,6,1000));
//		System.out.println(shortestWayFloydWarshall(g2,1,6,1000));				
//		System.out.println(shortestWayDijkstra(g5,0,8,1000));
//		System.out.println(shortestWayFloydWarshall(g,0,7,1000));	
		Double[][] m = shortestWayFordFulkerson(g7,0,5,1000);
//		System.out.println(shortestGoldbergTarjan(g3,1,5,1));
		Double[][] n = shortestWayEdmundKarp(g7,0,5,1000);
//		for(Double[] d1 : m){
//			System.out.println();
//			
//			for(Double d2 : d1){
//				System.out.print(d2 + " ");
//			}
//		}

	}

}
