package GUI;

import static Algorithmen.Utility.*;

import javax.swing.JFrame;

import Interfaces.CapacityEdge;
import Interfaces.Graph;
import Interfaces.NormalEdge;
import Interfaces.RatedEdge;
import Interfaces.Vertex;


public class Main {
	
		
//		public static Graph<Vertex,RatedEdge> g2 = makeGraph(vertexs(8),re(2,1,20),re(2,3,25),re(1,4,30),re(2,4,15),re(4,3,40),re(4,6,24),re(6,5,5),re(5,7,19));
		public static Graph<Vertex,CapacityEdge> g2 = makeDigraph(vertexs(8),ce(0,1,20),ce(1,2,25),ce(1,3,30),ce(2,4,15),ce(3,5,40),ce(4,6,24),ce(5,7,5),ce(6,8,19),ce(7,8,18));
		
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
