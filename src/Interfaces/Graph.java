package Interfaces;

import java.util.ArrayList;

public interface Graph<V extends Vertex,E extends Edge> {
	
	public void ausgabe();
	
	public V getV(int id);
	
	public ArrayList<V> getVertexs();
	
	public ArrayList<E> getEdges();
	
	//Gibt die Adjazenzmatrix zur�ck
	
	public Double[][] getMatrix();
	
	public int getNumOfVertexs();
	
	public int getNumOfEdges();
	
	public Double getValueBetween(V source, V target);
	
	public E getEdgeBetween(V source, V target);
	
	public ArrayList<V> getNeighbors(V v);
}
