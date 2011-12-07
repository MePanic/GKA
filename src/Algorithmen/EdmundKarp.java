package Algorithmen;

import java.util.*;

import Interfaces.CapacityEdge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class EdmundKarp {
	
	static int zugriffe;
	static long startTime;
	static long endTime;
	static long time;
	static int maxFlow;
	public static long getLastTime() {
		return time;
	}

	public static long getLastZugriffe() {
		return zugriffe;
	}
	
	public static <V extends Vertex, E extends CapacityEdge> Double[][] shortestWayEdmundKarp( Graph<V, E> g, int from, int to, int times){
		
		if ((g.getNumOfVertexs() < from) || (g.getNumOfVertexs() < to)) {
			throw new IllegalArgumentException("Vertex not found");
		}
		System.out.println();
		System.out.println("Edmund und Karp:");
		// InitTime
		startTime = System.currentTimeMillis();
		// Fuehre n mal GoldbergTarjan aus, mit n = times
		int[][] result = null;
		Double[][] res = new Double[g.getNumOfVertexs()][g.getNumOfVertexs()];
		for (int i = 0; i < times; i++) {
			zugriffe = 0;
			result = EdmundKarpAlgo(g, from, to);
		}
		System.out.println("Zugriffe: " + zugriffe);
		// PrintTime
		endTime = System.currentTimeMillis();
		time = endTime - startTime;
		System.out.println("Zeit bentigt: " + time + " Millisec");
		
		for(int i = 0; i < g.getNumOfVertexs(); i++){
			for(int k = 0; k < g.getNumOfVertexs(); k++){
				if(result[i][k] > 0){ res[k][i] = (double) result[i][k];}
				else { res[k][i] = 0.0;}
			}
		}
			
		System.out.println("Maximaler Fluss: " + maxFlow);
		for(Double[] p : res){
			for(Double q : p){
				System.out.print(q + " ");
			}
			System.out.println();
		}
		
		return res;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[][] EdmundKarpAlgo( Graph<V, E> g, int from, int to){
		int vNum = g.getNumOfVertexs();
		int[][] flow = new int[vNum][vNum];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int[] parent = new int[vNum];
		
		while(true){
			parent = new int[vNum];	
			Arrays.fill(parent, -1);
			parent[from] = from;
			zugriffe++;
			int u = 0;
			queue.clear();
			queue.add(from);
			int[] capPath = new int[g.getNumOfVertexs()];
			zugriffe++;
			capPath[from] = Integer.MAX_VALUE;
			zugriffe++;
			BACK:
			while (!queue.isEmpty()) {

				u = queue.poll();
					for (V n : g.getNeighbors(g.getV(u))) {
						zugriffe++;
						if (g.getValueBetween(g.getV(u), n).intValue() - flow[u][n.getId()] > 0 && parent[n.getId()] == -1) {
	
							parent[n.getId()] = u;
							zugriffe++;
							capPath[n.getId()] = Math.min(capPath[u], g.getValueBetween(g.getV(u), n).intValue() - flow[u][n.getId()]);
							zugriffe++;
							if(n.getId() != to){ 
								queue.add(n.getId());
							}
							else{ 
								int tempV = n.getId();
								while(parent[tempV] != tempV){
									int tempVPre = parent[tempV];
									flow[tempVPre][tempV] += capPath[to];
									zugriffe++;
									flow[tempV][tempVPre] -= capPath[to];
									zugriffe++;
									tempV = tempVPre;
								}
								break BACK;
							}
						}
					}
			}		
			if(parent[to] == -1){
				maxFlow = 0;
				for(int y : flow[from]){
					maxFlow += y;
					zugriffe++;
				}
				return flow;
			}
		}
	}
}