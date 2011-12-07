package Algorithmen;

import java.util.*;

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
		int[][] result = null;
		for (int i = 0; i < times; i++) {
			zugriffe = 0;
			result = GoldberTarjanAlgo(g, from, to);
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
	
	private static <V extends Vertex, E extends CapacityEdge> int[][] GoldberTarjanAlgo(Graph<V, E> g, int from, int to){
		int vNum = g.getNumOfVertexs();
		int[][] flow = new int[vNum][vNum];
		int[] distance = new int[vNum];
		int[][] resGraph = new int[vNum][vNum];
		int[][] capa = new int[vNum][vNum];
		List<int[]> resEdges = new ArrayList<int[]>();
		for(E e : g.getEdges()){
			if(e.getId()[0] == from){ flow[e.getId()[0]][e.getId()[1]] = e.getValue().intValue(); }
			else { flow[e.getId()[0]][e.getId()[1]] = 0; }	
			capa[e.getId()[0]][e.getId()[1]] = e.getValue().intValue();
		}
		for(int i = 0; i < vNum; i++){ distance[i] = 0; }
		distance[from] = vNum;
		boolean active = true;
for(int z = 0; z < 50; z++){
//		while(active){
		active = false;
		resGraph = resGraph(flow, from, to, g, resEdges);

//for(int[] p : resEdges){
//for(int q : p){
//System.out.print(q + " ");
//}
//System.out.println();
//}
//System.out.println("----------------");
//for(int[] p : flow){
//for(int q : p){
//System.out.print(q + " ");
//}
//System.out.println();
//}
System.out.print("distance: ");
for(int w : distance){
System.out.print(w);
}
System.out.println();		
		for(E e: g.getEdges()){
			if(active){break;}
			for(E f : g.getEdges()){
				if(active){break;}
				int ve = e.getId()[1];
				int vf = f.getId()[0];
				int[] flowOnV = calcFlowOnVertex(ve, g, flow);
//System.out.println("ve, vf: " + ve +" "+ vf);
//System.out.println("flowOnV: " + flowOnV[0] + " " + flowOnV[1]);
				if(ve == vf && ve != from && ve != to && flowOnV[0] > flowOnV[1]){
//System.out.println(ve + "--" + vf);
//System.out.println(flow[e.getId()[0]][ve] + "------" + flow[vf][f.getId()[1]]);
					active = true;
					boolean erlaubteResEdge = false;
					for(int i = 0; i < vNum; i++){	
						// Erlaubter Knoten
						if(resGraph[ve][i] != -1 && distance[ve] == (distance[i] +1)){ 
							erlaubteResEdge = true;
							for(E h : g.getEdges()){
								if(h.getId()[0] == ve && h.getId()[1] == i){
									flow[ve][i] = flow[ve][i] + Math.min(resGraph[ve][i], flowOnV[0] - flowOnV[1]);					
//System.out.println("vor");
								}
								else{
									flow[i][ve] = flow[i][ve] - Math.min(resGraph[ve][i], flowOnV[0] - flowOnV[1]);
//System.out.println("nicht vor");
								}
							}							
						} else {											
							int temp = Integer.MAX_VALUE;
							for(int[] k : resEdges){
								if(k[0] == ve && k[2] == 1 && distance[k[1]] >= distance[ve]){ temp = Math.min(temp, distance[k[1]]);
System.out.println("resEdges: " + k[0] + " - " + k[1] + " ve " + ve);
								}								
							}
							distance[ve] = temp + 1;
System.out.println(ve + "--" + temp);
						}
						break;
					}
				}
			}
		}
		
//for(int[] p : flow){
//for(int q : p){
//System.out.print(q + " ");
//}
//System.out.println();
//}
//System.out.println("..........................");	
		}
		return flow;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[][] resGraph(int[][] flow, int from, int to, Graph<V, E> g, List<int[]> set){
		set.clear();
		int[] temp;
		int[][] result = new int[g.getNumOfVertexs()][g.getNumOfVertexs()];
		for(int i = 0; i < g.getNumOfVertexs(); i++){
			for(int j = 0; j < g.getNumOfVertexs(); j++){
				result[i][j] = -1;				
			}
			
		}
		for(E e : g.getEdges()){
			int ex = e.getId()[0];
			int ey = e.getId()[1];
//System.out.println("resGraph ex ey: " + ex + " " + ey);
			if(flow[ex][ey] > 0){
//System.out.println("blub");
				result[ey][ex] = flow[ex][ey];
				temp = new int[3];
				temp[0] = ey;
				temp[1] = ex;
				temp[2] = -1;
				set.add(temp);
			}
			if(flow[ex][ey] < e.getValue().intValue()){
				result[ex][ey] = e.getValue().intValue() - flow[ex][ey];
				temp = new int[3];
				temp[0] = ex;
				temp[1] = ey;
				temp[2] = 1;
				set.add(temp);
			}
		}
		
		return result;
	}
	
	private static <V extends Vertex, E extends CapacityEdge> int[] calcFlowOnVertex(int v, Graph<V, E> g, int[][] flow){
		int[] res = new int[2];
		
		for(E e : g.getEdges()){
//System.out.println("edge, flow: " + x[0] + " " + x[1] + " " + flow[x[0]][x[1]]);
			if(e.getId()[1] == v){res[0]+= flow[e.getId()[0]][e.getId()[1]];}
			if(e.getId()[0] == v){res[1]+= flow[e.getId()[0]][e.getId()[1]];}
		}
		
		return res;
	}
	
}
