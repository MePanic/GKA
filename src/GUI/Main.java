package GUI;

import static Algorithmen.Utility.*;

import javax.swing.JFrame;

import Interfaces.Graph;
import Interfaces.NormalEdge;
import Interfaces.RatedEdge;
import Interfaces.Vertex;


public class Main {
	
		
		public static Graph<Vertex,RatedEdge> g2 = makeGraph(vertexs(8),re(2,1,20),re(2,3,25),re(1,4,30),re(2,4,15),re(4,3,40),re(4,6,24),re(6,5,5),re(5,7,19));
		
	public static void main(String[] args) {
//		Graph<Vertex,NormalEdge> graph = makeGraph(vertexs(8),ne(2,1),ne(2,3),ne(0,4),ne(4,3),ne(4,6),ne(6,5),ne(5,7));
		Frame frame = new Frame();
//		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
