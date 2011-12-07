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
		System.out.println("GoldbergTarjan:");
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
		List<Integer> path = new ArrayList<Integer>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		Set<Integer> visited = new HashSet<Integer>();
//System.out.println("edge betw ");
		
		int maxFlow = 0;
		int tempV;
		int x = 0;
		boolean end;
		
		while(true){
			
		end = false;	
			
		int i = 0;
		int u = 0;
		queue.add(from);
		int[] capPath = new int[g.getNumOfVertexs()];
		capPath[0] = Integer.MAX_VALUE;
		while (queue.size() > 0) {
//System.out.println("queue: " + queue + " parent: " + parent);
			zugriffe++;
			u = queue.poll();

				for (V n : g.getNeighbors(g.getV(u))) {
//System.out.println("n: " + (n.getId()+1) + " u: " + (u+1));	
					zugriffe++;
					int flowDiff;
					if (g.getValueBetween(g.getV(u), n) != null){ flowDiff = g.getValueBetween(g.getV(u), n).intValue() - flow[i][n.getId()];}
					else { flowDiff = 0; }
//System.out.println("capa: " + g.getValueBetween(g.getV(u), n) + " flow: " + flow[u][n.getId()] + " flowDiff: " + flowDiff + " edge: " + g.getEdgeBetween(g.getV(u), n));	
					if (flowDiff > 0 && !visited.contains(n.getId())) {

						parent.put(n.getId(), u);
						visited.add(n.getId());
						capPath[n.getId()] = Math.min(capPath[u], flowDiff);

						if(n.getId() == to){ x =  capPath[i+1]; end = true; break;}
						else{ queue.add(n.getId());}
					}
				}
//if(end){System.out.println(":::::::::::::");}
				if(end){break;}
			
			i++;
			if(end){break;}
		}
		
		if(!end){x=0;}
	
			

		
		
		
		
		
//		if(x == 0){break;}
		maxFlow += x;
		tempV = to;
//System.out.println("tempV: " + tempV);
		
		while(tempV != from){
//System.out.println("tempV from: " + tempV + "--" + from + ":::" +  parent);
			int tempVPre = parent.get(tempV);
			flow[tempVPre][tempV] += x;
			flow[tempV][tempVPre] -= x;
			tempV = tempVPre;
		}
		return flow;
		}
//		return flow;
	}
	


}
