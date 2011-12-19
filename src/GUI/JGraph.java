package GUI;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Vertex;


import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

public class JGraph extends mxGraph {
	private static final HashMap<Integer,Object> VertexObjectList = new HashMap<Integer,Object>();
	private static final HashMap<Integer,Object> EdgeObjectList = new HashMap<Integer,Object>();

	
	public <V extends Vertex,E extends Edge> JGraph(Graph<V,E> graph,Dimension d,int type){
		double width = d.width;
		double height = d.height;
	
		double schritt = 360.0/graph.getNumOfVertexs();
		
		
		for (Double temp = 0.0; temp<graph.getNumOfVertexs();temp++) {
			
			double hyp =  (((height/2.0)-50.0) * Math.cos(((90.0-((schritt*temp)/2))/ 180.0 * Math.PI)) * 2.0);
			double gk = ( Math.cos((90.0-((schritt*temp)/2.0))/ 180.0 * Math.PI) * hyp);
			double x,y;
			if(temp > (graph.getNumOfVertexs()/2.0)){
			x = ((width/2.0)-50.0)+Math.sqrt((hyp*hyp) - (gk*gk));
			}else {
			x = (width/2.0)-50.0-Math.sqrt((hyp*hyp) - (gk*gk));
			}
			y = (height)-100.0-gk;

			VertexObjectList.put(temp.intValue(),insertVertex(getDefaultParent(), null, ((Integer)(temp).intValue()).toString(), x, y, 40,20,"strokeColor=black;fontColor=black"));
		}
		
		Integer edgesTemp = 1;
				for(E e : graph.getEdges()){
					String value = "";
					if(type == 1)
						value = "e" + edgesTemp + "                 ";
					if(type == 2)
						value = "e" + edgesTemp + " (" + e.getValue() +")                 ";
					if(type == 3)
						value = "e" + edgesTemp + " (0/" + e.getValue() +")                ";
					
					EdgeObjectList.put(edgesTemp,insertEdge(getDefaultParent(), null,value, VertexObjectList.get(e.getId()[0]), VertexObjectList.get(e.getId()[1]),"strokeColor=black;fillColor=black"));
					edgesTemp++;
				if(!graph.isDirected()){
					EdgeObjectList.put((edgesTemp+ graph.getNumOfEdges()),insertEdge(getDefaultParent(), null, "", VertexObjectList.get(e.getId()[1]), VertexObjectList.get(e.getId()[0]),"strokeColor=black;fillColor=black"));
					}
				}
				
//				setCellsSelectable(false);
				setCellsLocked(true);
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
	
	public void setFlow(Double[][] m) {
		setCellStyle("strokeColor=black;",EdgeObjectList.values().toArray());
		setCellStyle("strokeColor=black;fontColor=black",VertexObjectList.values().toArray());
		for(int i1 = 0;i1<m.length; i1++){
			for(int i2 = 0;i2<m[i1].length; i2++){
				if(m[i1][i2] > 0){
		System.out.println(m[i1][i2]);
				mxCell o = ((mxCell)getEdgesBetween(VertexObjectList.get(i1),VertexObjectList.get(i2))[0]);
					String[] s = ((String) o.getValue()).split("/");
					cellLabelChanged(o, s[0].substring(0,4) +  m[i1][i2] + "/" + s[1], false) ;
					setCellStyle("strokeColor=red;fontColor=red",getEdgesBetween(VertexObjectList.get(i1),VertexObjectList.get(i2)));
				}
			}
		}
}
	
	
	}

