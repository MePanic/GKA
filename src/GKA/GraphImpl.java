package GKA;

import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Vertex;

public class GraphImpl<V extends Vertex,E extends Edge> implements Graph<V,E>{
	
	private final HashMap<V,ArrayList<V>> Neighborlist = new HashMap<V,ArrayList<V>>();
	private final ArrayList<V> Ecken = new ArrayList<V>();
	private final ArrayList<E> Kanten = new ArrayList<E>();
	private final Double[][] matrix;
	private final int NoOfVertexs;
	private final int NoOfEdges;
	
	public static <V extends Vertex,E extends Edge> Graph<V,E> valueOf(Boolean directed,ArrayList<V> vertexs,E... edges) {
		return new GraphImpl<V,E>(directed, vertexs, edges);}
		
	private GraphImpl(Boolean directed,ArrayList<V> vertexs, E... edges) {
		NoOfVertexs = vertexs.size();
		matrix = new Double[NoOfVertexs][NoOfVertexs];
		
		for (V v : vertexs) {
			Ecken.add(v);
			Neighborlist.put(v, new ArrayList<V>());
		}
		
		int edgesTemp = 0;
		for (E e : edges) {
			Kanten.add(e);
			edgesTemp++;
			matrix[e.getId()[0]][e.getId()[1]] = e.getValue();
			Neighborlist.get(getV(e.getId()[0])).add(getV(e.getId()[1]));
			if(!directed){
			matrix[e.getId()[1]][e.getId()[0]] = e.getValue();
			Neighborlist.get(getV(e.getId()[1])).add(getV(e.getId()[0]));
			}
		}
		this.NoOfEdges = edgesTemp;
	}
	
	public V getV(int id) {
		if(id<NoOfVertexs)
		return this.Ecken.get(id);
		else
			throw new IllegalArgumentException();
	}

	public ArrayList<V> getVertexs(){
		return Ecken;
	}
	
	public ArrayList<E> getEdges(){
		return Kanten;
	}
	
	public Double[][] getMatrix(){
		return matrix;
	}
	
	public int getNumOfVertexs(){
		return NoOfVertexs;
	}
	
	public int getNumOfEdges(){
		return NoOfEdges;
	}
	
	public Double getValueBetween(V source, V target){
		return matrix[source.getId()][target.getId()];
	}
	
	public E getEdgeBetween(V source, V target) {
		if(source.getId()>NoOfVertexs || target.getId()>NoOfVertexs)
			throw new IllegalArgumentException();
		else
			for(E e : Kanten){
				if(e.getId()[0] == source.getId() && e.getId()[1] == target.getId()){
					return e;
				}
			}
		return null;
	}
	
	public ArrayList<V> getNeighbors(V v){
		return Neighborlist.get(v);
	}
	
	public void ausgabe() {
		System.out.println("Adjazent List:");
		for (V v : Ecken) {
			System.out.println(v + ": " + getNeighbors(v).toString());
		}
	}
}
