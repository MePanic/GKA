package Algorithmen;

import java.util.*;

import Interfaces.CapacityEdge;
import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class Dinic<V extends Vertex, E extends CapacityEdge> {
	static int zugriffe;
	static long startTime;
	static long endTime;
	static long time;
	public static long getLastTime() {
		return time;
	}

	public static long getLastZugriffe() {
		return zugriffe;
	}

	static <V extends Vertex, E extends CapacityEdge> Double[][] shortestWayDinic( Graph<V, E> g, int from, int to, int times){
		if ((g.getNumOfVertexs() < from) || (g.getNumOfVertexs() < to)) {
			throw new IllegalArgumentException("Vertex not found");
		}
		System.out.println("Dinic Algorithm:");
		startTime = System.currentTimeMillis();
		
		Double[][] result = null;
		
		for(int i = 0; i < times; i++){
			zugriffe = 0;
			result = DinicAlgo(g,from,to);			
		}
		endTime = System.currentTimeMillis();
		System.out.println("Zugriffe: " + zugriffe);
		time = endTime - startTime;
		System.out.println("Zeit: " + time);
			
		return result;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> Double[][] DinicAlgo(Graph<V, E> g, int from, int to){		
		Double[][] flow = new Double[g.getNumOfVertexs()][g.getNumOfVertexs()];
		int[][] resGraph = new int[g.getNumOfVertexs()][g.getNumOfVertexs()];
		for(int i = 0; i < g.getNumOfVertexs(); i++){
			for(int j = 0; j < g.getNumOfVertexs(); i++){
				flow[i][j] = 0.0;
			}		
		}
		
		resGraph = resGraph(flow, from, to, g);
		
		
		return flow;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[][] resGraph( Double[][] flow, int from, int to, Graph<V, E> g){
		int[][] result = new int[g.getNumOfVertexs()][g.getNumOfVertexs()];
		
		for(E e : g.getEdges()){
			int ex = e.getId()[0];
			int ey = e.getId()[1];
			if(flow[ex][ey].intValue() > 0){
				result[ey][ex] = flow[ex][ey].intValue();			
			}
			if(flow[ex][ey].intValue() < e.getValue().intValue()){
				result[ex][ey] = e.getValue().intValue() - flow[ex][ey].intValue();
			}
		}
		
		return result;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[] BSFforDinic(int from, int to, int[][] flow){
		List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int temp = 0;
		res.get(temp).add(from);
		

		
		return null;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[] BSFHelp(int from, int to, int[][] flow){
		
		
		return null;
	}
}

	
