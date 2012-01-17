package GUI;

import static Algorithmen.Utility.*;

import javax.swing.JFrame;

import Interfaces.CapacityEdge;
import Interfaces.Graph;
import Interfaces.NormalEdge;
import Interfaces.Vertex;


public class Main {
	
		
//		public static Graph<Vertex,RatedEdge> g2 = makeGraph(vertexs(8),re(2,1,20),re(2,3,25),re(1,4,30),re(2,4,15),re(4,3,40),re(4,6,24),re(6,5,5),re(5,7,19));
//		public static Graph<Vertex,CapacityEdge> g2 = makeDigraph(vertexs(8),ce(0,1,20),ce(1,2,25),ce(1,3,30),ce(2,4,15),ce(3,5,40),ce(4,6,24),ce(5,7,5),ce(6,8,19),ce(7,8,18));
//		public static Graph<Vertex,CapacityEdge> g2 = makeDigraph(vertexs(5),ce(0,1,3),ce(0,3,2),ce(1,2,-2),ce(2,3,1),ce(3,4,2),ce(2,5,2),ce(3,1,-2),ce(4,2,1),ce(4,5,4));
//		public static Graph<Vertex,NormalEdge> g2 = makeGraph(vertexs(5),ne(0,1),ne(0,3),ne(0,4),ne(0,5),ne(1,2),ne(1,3),ne(1,4),ne(2,3),ne(3,4),ne(4,5));
		public static Graph<Vertex,NormalEdge> g2 = makeGraph(vertexs(4),ne(0,1),ne(1,2),ne(2,3),ne(3,0),ne(0,4),ne(1,4),ne(2,4),ne(3,4));

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
