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
		for (int i = 0; i < times; i++) {
			zugriffe = 0;
			result = EdmundKarpAlgo(g, from, to);
		}
		System.out.println("Zugriffe: " + zugriffe);
		// PrintTime
		endTime = System.currentTimeMillis();
		time = endTime - startTime;
		System.out.println("Zeit bentigt: " + time + " Millisec");
		
		for(int[] p : result){
			for(int q : p){
				System.out.print(q + " ");
			}
			System.out.println();
		}
		
		return null;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[][] EdmundKarpAlgo( Graph<V, E> g, int from, int to){
		int vNum = g.getNumOfVertexs();
		int[][] flow = new int[vNum][vNum];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		List<Integer> path = new ArrayList<Integer>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		
System.out.println("vNum" + vNum);
		
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
		
		while (!queue.isEmpty()) {
			zugriffe++;
			u = queue.poll();
				for (V n : g.getNeighbors(g.getV(u))) {
					zugriffe++;
					int valBetw;
					if (g.getValueBetween(g.getV(i), n) != null){ valBetw = g.getValueBetween(g.getV(i), n).intValue();}
					else { valBetw = 0; }
					if (valBetw - flow[i][n.getId()] > 0 && !parent.containsKey(n.getId())) {

						parent.put(n.getId(), i);

						capPath[i+1] = Math.min(capPath[i], g.getValueBetween(g.getV(i), n).intValue() - flow[i][n.getId()]);

						if(n.getId() == to){ x =  capPath[i+1]; end = true; break;}
						else{ queue.push(n.getId());}
					}
				}
if(end){System.out.println(":::::::::::::");}
				if(end){break;}
			
			i++;
			if(end){break;}
		}
		
		if(!end){x=0;}
	
			

		
		
		
		
		
//		if(x == 0){break;}
		maxFlow += x;
		tempV = to;
System.out.println("tempV: " + tempV);
		
		while(tempV != from){
System.out.println("tempV from: " + tempV + "--" + from + ":::" +  parent);
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
