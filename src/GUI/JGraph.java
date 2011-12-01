package GUI;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Vertex;


import com.mxgraph.view.mxGraph;

public class JGraph extends mxGraph {
	private static final HashMap<Integer,Object> VertexObjectList = new HashMap<Integer,Object>();
	private static final HashMap<Integer,Object> EdgeObjectList = new HashMap<Integer,Object>();

	
	public <V extends Vertex,E extends Edge> JGraph(Graph<V,E> graph,Dimension d){
		int width = d.width;
		int height = d.height;
	
		double schritt = 360/graph.getNumOfVertexs();
		
		
		for (Double temp = 0.0; temp<graph.getNumOfVertexs();temp++) {
			
			double hyp =  (((height/2)-50) * Math.cos(((90-((schritt*temp)/2))/ 180 * Math.PI)) * 2);
			double gk = ( Math.cos((90-((schritt*temp)/2))/ 180 * Math.PI) * hyp);
			double x,y;
			if(temp > (graph.getNumOfVertexs()/2)){
			x = ((width/2)-50)+Math.sqrt((hyp*hyp) - (gk*gk));
			}else {
			x = (width/2)-50-Math.sqrt((hyp*hyp) - (gk*gk));
			}
			y = (height)-100-gk;

			VertexObjectList.put(temp.intValue(),insertVertex(getDefaultParent(), null, ((Integer)(temp).intValue()).toString(), x, y, 40,20,"strokeColor=black;fontColor=black"));
		}
		
		Integer edgesTemp = 1;
				for(E e : graph.getEdges()){
					EdgeObjectList.put(edgesTemp,insertEdge(getDefaultParent(), null, "e" + edgesTemp + " (" + e.getValue() +")                 ", VertexObjectList.get(e.getId()[0]), VertexObjectList.get(e.getId()[1]),"strokeColor=black;fillColor=black"));
					edgesTemp++;
				if(!graph.isDirected()){
					EdgeObjectList.put((edgesTemp+ graph.getNumOfEdges()),insertEdge(getDefaultParent(), null, "", VertexObjectList.get(e.getId()[1]), VertexObjectList.get(e.getId()[0]),"strokeColor=black;fillColor=black"));
					}
				}
			}
	
	
	public <V extends Vertex> void highlightPath(List<V> tl) {
			setCellStyle("strokeColor=black;",EdgeObjectList.values().toArray());

			setCellStyle("strokeColor=black;fontColor=black",VertexObjectList.values().toArray());
		
		if (tl.size() == 0) return;
		for(Integer i = 1; i < tl.size();i++){	
			setCellStyle("strokeColor=red;fillColor=green;fontColor=red", getEdgesBetween(VertexObjectList.get(tl.get(i-1).getId()),VertexObjectList.get(tl.get(i).getId()))) ;
		}
	}
	

	
	public <V extends Vertex> void highlightFull(List<V> tl) {
			setCellStyle("strokeColor=black;",EdgeObjectList.values().toArray());
			setCellStyle("strokeColor=black;fontColor=black",VertexObjectList.values().toArray());
			for(V o : tl){

				setCellStyle("strokeColor=red;fontColor=red",new Object[]{VertexObjectList.get(o.getId())});
			setCellStyle("strokeColor=red;fontColor=red",getEdges(VertexObjectList.get(o.getId())));
			}
	}
	
	
	}

