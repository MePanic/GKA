package Algorithmen;

import Interfaces.CapacityEdge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class GoldbergTarjan {

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
	
	public static <V extends Vertex, E extends CapacityEdge> Double[][] shortestGoldbergTarjan( Graph<V, E> g, int from, int to, int times){
		
		if ((g.getNumOfVertexs() < from) || (g.getNumOfVertexs() < to)) {
			throw new IllegalArgumentException("Vertex not found");
		}
		System.out.println("GoldbergTarjan:");
		// InitTime
		startTime = System.currentTimeMillis();
		// Fuehre n mal GoldbergTarjan aus, mit n = times
		Double[][] result = null;
		for (int i = 0; i < times; i++) {
			zugriffe = 0;
			result = GoldberTarjanAlgo(g, from, to);
		}
		System.out.println("Zugriffe: " + zugriffe);
		// PrintTime
		endTime = System.currentTimeMillis();
		time = endTime - startTime;
		System.out.println("Zeit bentigt: " + time + " Millisec");
		return result;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> Double[][] GoldberTarjanAlgo(Graph<V, E> g, int from, int to){
		int vNum = g.getNumOfVertexs();
		int[][] flow = new int[vNum][vNum];
		int[] distance = new int[vNum];
		int[][] resGraph = new int[vNum][vNum];
		for(E e : g.getEdges()){
			if(e.getId()[0] == from){ flow[e.getId()[0]][e.getId()[1]] = e.getValue().intValue(); }
			else { flow[e.getId()[0]][e.getId()[1]] = 0; }	
		}
		for(int i = 0; i < vNum; i++){ distance[i] = 0; }
		distance[from] = vNum;
		while(true){
		resGraph = resGraph(flow, from, to, g);
		
		for(E e: g.getEdges()){
			for(E f : g.getEdges()){
				int ve = e.getId()[1];
				int vf = f.getId()[0];
				if(ve == vf && flow[e.getId()[0]][ve] > flow[vf][f.getId()[1]]){
					boolean erlaubteResEdge = false;
					for(int i = 0; i < vNum; i++){
						if(resGraph[ve][i] != -1 && distance[ve] == (distance[i] +1)){ erlaubteResEdge = true;
							for(E h : g.getEdges()){
								if(h.getId()[0] == ve && h.getId()[1] == i){
									flow[ve][i] = flow[ve][i] + Math.min(resGraph[ve][i], flow[e.getId()[0]][ve] - flow[vf][f.getId()[1]]);					
								}
								else{
									flow[i][ve] = flow[i][ve] - Math.min(resGraph[ve][i], flow[e.getId()[0]][ve] - flow[vf][f.getId()[1]]);
								}
							}
							if(erlaubteResEdge){break;}
						}
						
					}
					if(!erlaubteResEdge){
						int temp = Integer.MAX_VALUE;
						for(E k : g.getEdges()){
							if(k.getId()[0] == ve){ temp = Math.min(temp, distance[k.getId()[1]]);}
							
						}
						distance[ve] = temp + 1;
						
					}
					
					
				}
				
			}
			
		}
		
		return null;
		}
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[][] resGraph(int[][] flow, int from, int to, Graph<V, E> g){
		int[][] result = new int[g.getNumOfVertexs()][g.getNumOfVertexs()];
		for(int i = 0; i < g.getNumOfVertexs(); i++){
			for(int j = 0; j < g.getNumOfVertexs(); j++){
				result[i][j] = -1;				
			}
			
		}
		for(E e : g.getEdges()){
			int ex = e.getId()[0];
			int ey = e.getId()[1];

			if(flow[ex][ey] > 0){

				result[ey][ex] = flow[ex][ey];	

			}
			if(flow[ex][ey] < e.getValue().intValue()){
				result[ex][ey] = e.getValue().intValue() - flow[ex][ey];

			}
		}
		
		return result;
	}
	
}
