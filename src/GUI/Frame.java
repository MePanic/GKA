package GUI;

import static Algorithmen.Utility.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import Algorithmen.BFS;
import Algorithmen.Dijkstra;
import Algorithmen.FloydWarshall;
import Algorithmen.FordFulkerson;
import Interfaces.CapacityEdge;
import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.RatedEdge;
import Interfaces.Vertex;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import com.mxgraph.swing.mxGraphComponent;

public class Frame extends JFrame {
	JGraph jg;
	private static final long serialVersionUID = -2707712944901661771L;
	Graph<?, ?> g;
	Graph<Vertex, RatedEdge> gR;
	Graph<Vertex, CapacityEdge> gF;
	Container content;
	Frame me;
	Component graphComponent = new JPanel();
	JButton buttonBFS;
	JButton buttonCMP;
	JButton buttonDJ;
	JButton buttonFW;
	JButton buttonFF;
	JButton buttonEK;

	public Frame() {
		super("GKA");
		me = this;
		setSize(1280, 720);

		// Time / Zugriffe
		final JLabel timeTxtField = new JLabel("Time: 0", Label.LEFT);
		timeTxtField.setPreferredSize(new Dimension(120, 30));
		
		final JLabel zugriffeTxtField = new JLabel("Zugriffe: 0", Label.LEFT);
		zugriffeTxtField.setPreferredSize(new Dimension(120, 30));
		
		// Felder von BFS
		JLabel txtFieldBFS1 = new JLabel("von", Label.LEFT);
		
		final JFormattedTextField numFieldBFS1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldBFS1.setHorizontalAlignment(JTextField.RIGHT);
		numFieldBFS1.setValue(0);
		
		JLabel txtFieldBFS2 = new JLabel("bis", Label.LEFT);
		
		final JFormattedTextField numFieldBFS2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldBFS2.setHorizontalAlignment(JTextField.RIGHT);
		numFieldBFS2.setValue(0);

		// Felder von Komponente
		JLabel txtFieldCMP1 = new JLabel("von", Label.LEFT);
		
		final JFormattedTextField numFieldCMP1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldCMP1.setHorizontalAlignment(JTextField.RIGHT);
		numFieldCMP1.setValue(0);
		
		JLabel txtFieldCMP2 = new JLabel();
		
		JLabel numFieldCMP2 = new JLabel();

		// Felder von Dijkstra
		JLabel txtFieldDJ1 = new JLabel("von", Label.LEFT);
		
		final JFormattedTextField numFieldDJ1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldDJ1.setHorizontalAlignment(JTextField.RIGHT);
		numFieldDJ1.setValue(0);
		
		JLabel txtFieldDJ2 = new JLabel("bis", Label.LEFT);
		
		final JFormattedTextField numFieldDJ2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldDJ2.setHorizontalAlignment(JTextField.RIGHT);
		numFieldDJ2.setValue(0);

		// Felder von Floyd-Warshall
		JLabel txtFieldFW1 = new JLabel("von", Label.LEFT);
		
		final JFormattedTextField numFieldFW1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldFW1.setHorizontalAlignment(JTextField.RIGHT);
		numFieldFW1.setValue(0);
		
		JLabel txtFieldFW2 = new JLabel("bis", Label.LEFT);
		
		final JFormattedTextField numFieldFW2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldFW2.setHorizontalAlignment(JTextField.RIGHT);
		numFieldFW2.setValue(0);

		// Felder von FordFulkerson
		JLabel txtFieldFF1 = new JLabel("von", Label.LEFT);
		
		final JFormattedTextField numFieldFF1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldFF1.setHorizontalAlignment(JTextField.RIGHT);
		numFieldFF1.setValue(0);
		
		JLabel txtFieldFF2 = new JLabel("bis", Label.LEFT);
		
		final JFormattedTextField numFieldFF2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldFF2.setHorizontalAlignment(JTextField.RIGHT);
		numFieldFF2.setValue(0);
		
		// Felder von EdmundKarp
		JLabel txtFieldEK1 = new JLabel("von", Label.LEFT);
		
		final JFormattedTextField numFieldEK1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldEK1.setHorizontalAlignment(JTextField.RIGHT);
		numFieldEK1.setValue(0);
		
		JLabel txtFieldEK2 = new JLabel("bis", Label.LEFT);
		
		final JFormattedTextField numFieldEK2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		numFieldEK2.setHorizontalAlignment(JTextField.RIGHT);
		numFieldEK2.setValue(0);

		// Buttons
		buttonBFS = new JButton("<HTML><font size=2><CENTER><BODY>Kuerzester Weg<BR>(BFS)</BODY></font></HTML>");
		ActionListener actionListenerBFS = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(numFieldBFS1.getText().equals("")) && !(numFieldBFS2.getText().equals(""))) {
					Integer intValue1 = Integer.parseInt(numFieldBFS1.getText());
					Integer intValue2 = Integer.parseInt(numFieldBFS2.getText());
					jg.highlightPath(shortestWayBFS((Graph<Vertex, Edge>) g,intValue1, intValue2, 1000));
					timeTxtField.setText("Time: " + BFS.getLastTime() + "ms");
					zugriffeTxtField.setText("Zugriffe: "+ BFS.getLastZugriffe());
				}
				return;
			}
		};
		buttonBFS.addActionListener(actionListenerBFS);

		buttonCMP = new JButton("<HTML><font size=2><CENTER><BODY>Komponente<BR>(BFS)</BODY></font></HTML>");
		ActionListener actionListenerCMP = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(numFieldBFS1.getText().equals(""))) {
					Integer intValue1 = Integer.parseInt(numFieldCMP1.getText());
					jg.highlightFull(getComponentBFS((Graph<Vertex, Edge>) g,intValue1, 1000));
					timeTxtField.setText("Time: " + BFS.getLastTime() + "ms");
					zugriffeTxtField.setText("Zugriffe: "+ BFS.getLastZugriffe());
				}
				return;
			}
		};
		buttonCMP.addActionListener(actionListenerCMP);

		buttonDJ = new JButton("<HTML><font size=2><CENTER><BODY>Kuerzester Weg<BR>(Dijkstra)</BODY></font></HTML>");
		ActionListener actionListenerDJ = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(numFieldBFS1.getText().equals(""))&& !(numFieldBFS2.getText().equals(""))) {
					Integer intValue1 = Integer.parseInt(numFieldDJ1.getText());
					Integer intValue2 = Integer.parseInt(numFieldDJ2.getText());
					jg.highlightPath(shortestWayDijkstra((Graph<Vertex, RatedEdge>) g, intValue1, intValue2,1000));
					timeTxtField.setText("Time: " + Dijkstra.getLastTime()+ "ms");
					zugriffeTxtField.setText("Zugriffe: "+ Dijkstra.getLastZugriffe());
				}
				return;
			}
		};
		buttonDJ.addActionListener(actionListenerDJ);

		buttonFW = new JButton("<HTML><font size=2><CENTER><BODY>Kuerzester Weg<BR>(Floyd-Warshall)</BODY></font></HTML>");
		ActionListener actionListenerFW = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(numFieldFW1.getText().equals(""))	&& !(numFieldFW2.getText().equals(""))) {
					Integer intValue1 = Integer.parseInt(numFieldFW1.getText());
					Integer intValue2 = Integer.parseInt(numFieldFW2.getText());
					jg.highlightPath(shortestWayFloydWarshall((Graph<Vertex, RatedEdge>) g, intValue1, intValue2,1000));
					timeTxtField.setText("Time: " + FloydWarshall.getLastTime()	+ "ms");
					zugriffeTxtField.setText("Zugriffe: "+ FloydWarshall.getLastZugriffe());
				}
				return;
			}
		};
		buttonFW.addActionListener(actionListenerFW);

		buttonFF = new JButton("<HTML><font size=2><CENTER><BODY>Groesster Fluß<BR>(Ford-Fulkerson)</BODY></font></HTML>");
		ActionListener actionListenerFF = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(numFieldFF1.getText().equals(""))	&& !(numFieldFF2.getText().equals(""))) {
					Integer intValue1 = Integer.parseInt(numFieldFF1.getText());
					Integer intValue2 = Integer.parseInt(numFieldFF2.getText());
					jg.setFlow(shortestWayFordFulkerson((Graph<Vertex, CapacityEdge>) g, intValue1, intValue2,1000));
					timeTxtField.setText("Time: " + FordFulkerson.getLastTime()	+ "ms");
					zugriffeTxtField.setText("Zugriffe: "+ FordFulkerson.getLastZugriffe());
				}
				return;
			}
		};
		buttonFF.addActionListener(actionListenerFF);

		buttonEK = new JButton("<HTML><font size=2><CENTER><BODY>Groesster Fluss<BR>(Edmund-Karp)</BODY></font></HTML>");
		ActionListener actionListenerEK = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(numFieldEK1.getText().equals(""))	&& !(numFieldEK2.getText().equals(""))) {
					Integer intValue1 = Integer.parseInt(numFieldEK1.getText());
					Integer intValue2 = Integer.parseInt(numFieldEK2.getText());
					jg.setFlow(shortestWayEdmundKarp((Graph<Vertex, CapacityEdge>) g, intValue1, intValue2,1000));
					timeTxtField.setText("Time: " + FordFulkerson.getLastTime()	+ "ms");
					zugriffeTxtField.setText("Zugriffe: "+ FordFulkerson.getLastZugriffe());
				}
				return;
			}
		};
		buttonEK.addActionListener(actionListenerEK);
		
		
		
		// DropdownMenu
		JMenuBar dropDownMenu = new JMenuBar();
		JMenu men = new JMenu("new Graph");

		JMenuItem menuItem1 = new JMenuItem("Normal Graph", KeyEvent.VK_N);
		men.add(menuItem1);
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GFrame(1, me);
			}
		});

		JMenuItem menuItem2 = new JMenuItem("Rated Graph", KeyEvent.VK_R);
		men.add(menuItem2);
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GFrame(2, me);
			}
		});

		JMenuItem menuItem3 = new JMenuItem("Flow Graph", KeyEvent.VK_F);
		men.add(menuItem3);
		menuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GFrame(3, me);
			}
		});

		dropDownMenu.add(men);
		setJMenuBar(dropDownMenu);

		// Container f�r alles
		content = getContentPane();

		// Menu-Container
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(1, 2));
		menu.setPreferredSize(new Dimension(240, 30));

		// menu1=buttons / menu2=felder
		JPanel menu1 = new JPanel();
		menu1.setLayout(new GridLayout(15, 1));

		JPanel menu2 = new JPanel();
		menu2.setLayout(new GridLayout(15, 1));
		menu2.setPreferredSize(new Dimension(80, 30));

		// Time/Zugriffe einf�gen
		menu1.add(timeTxtField);
		menu2.add(zugriffeTxtField);

		// Buttons zum menu hinzuf�gen
		menu1.add(buttonBFS, BorderLayout.WEST);
		menu1.add(buttonCMP, BorderLayout.NORTH);
		menu1.add(buttonDJ, BorderLayout.NORTH);
		menu1.add(buttonFW, BorderLayout.NORTH);
		menu1.add(buttonFF, BorderLayout.NORTH);
		menu1.add(buttonEK, BorderLayout.NORTH);

		// BFS Felder zusammenf�gen
		Container felderBFS = new Container();
		felderBFS.setLayout(new GridLayout(2, 2));
		felderBFS.add(txtFieldBFS1, BorderLayout.WEST);
		felderBFS.add(numFieldBFS1, BorderLayout.EAST);
		felderBFS.add(txtFieldBFS2, BorderLayout.WEST);
		felderBFS.add(numFieldBFS2, BorderLayout.EAST);

		// Komponente Felder zusammenf�gen
		Container felderCMP = new Container();
		felderCMP.setLayout(new GridLayout(2, 2));
		felderCMP.add(txtFieldCMP1, BorderLayout.WEST);
		felderCMP.add(numFieldCMP1, BorderLayout.EAST);
		felderCMP.add(txtFieldCMP2, BorderLayout.WEST);
		felderCMP.add(numFieldCMP2, BorderLayout.EAST);

		// Dijkstra Felder zusammenf�gen
		Container felderDJ = new Container();
		felderDJ.setLayout(new GridLayout(2, 2));
		felderDJ.add(txtFieldDJ1, BorderLayout.WEST);
		felderDJ.add(numFieldDJ1, BorderLayout.EAST);
		felderDJ.add(txtFieldDJ2, BorderLayout.WEST);
		felderDJ.add(numFieldDJ2, BorderLayout.EAST);

		// FloydWarshall Felder zusammenf�gen
		Container felderFW = new Container();
		felderFW.setLayout(new GridLayout(2, 2));
		felderFW.add(txtFieldFW1, BorderLayout.WEST);
		felderFW.add(numFieldFW1, BorderLayout.EAST);
		felderFW.add(txtFieldFW2, BorderLayout.WEST);
		felderFW.add(numFieldFW2, BorderLayout.EAST);

		// FordFulkerson Felder zusammenf�gen
		Container felderFF = new Container();
		felderFF.setLayout(new GridLayout(2, 2));
		felderFF.add(txtFieldFF1, BorderLayout.WEST);
		felderFF.add(numFieldFF1, BorderLayout.EAST);
		felderFF.add(txtFieldFF2, BorderLayout.WEST);
		felderFF.add(numFieldFF2, BorderLayout.EAST);

		// EdmundKapr Felder zusammenf�gen
		Container felderEK = new Container();
		felderEK.setLayout(new GridLayout(2, 2));
		felderEK.add(txtFieldEK1, BorderLayout.WEST);
		felderEK.add(numFieldEK1, BorderLayout.EAST);
		felderEK.add(txtFieldEK2, BorderLayout.WEST);
		felderEK.add(numFieldEK2, BorderLayout.EAST);
		
		// Felder zum menu hinzuf�gen
		menu2.add(felderBFS, BorderLayout.EAST);
		menu2.add(felderCMP, BorderLayout.EAST);
		menu2.add(felderDJ, BorderLayout.EAST);
		menu2.add(felderFW, BorderLayout.EAST);
		menu2.add(felderFF, BorderLayout.EAST);
		menu2.add(felderEK, BorderLayout.EAST);

		// Menus zusammenf�gen
		menu.add(menu1, BorderLayout.WEST);
		menu.add(menu2, BorderLayout.EAST);
		content.add(BorderLayout.WEST, menu);

		// Anfangsgraph einlesen
		setGraph(Main.g2,3);
		
//		disableButtons(3);

		// Graph dazu
		content.add(graphComponent);
	}

	
	public void setGraph(Graph<?, ?> graph,int type) {
		disableButtons(type);
		invalidate();

		g = graph;
		jg = new JGraph(graph, me.getSize(),type);
		graphComponent = new mxGraphComponent(jg);

		content = getContentPane();

		if (content.getComponents().length > 1) {
			content.remove(content.getComponents()[1]);
		}
		content.add(graphComponent);

		validate();
		repaint();
	}

	public void disableButtons(int wahl) {
		switch (wahl) {
		// normaler Graph
		case 1: {
			buttonBFS.setEnabled(true);
			buttonCMP.setEnabled(true);
			buttonDJ.setEnabled(false);
			buttonFW.setEnabled(false);
			buttonFF.setEnabled(false);
			buttonEK.setEnabled(false);
			break;
		}
		// gewichteter Graph
		case 2: {
			buttonBFS.setEnabled(true);
			buttonCMP.setEnabled(true);
			buttonDJ.setEnabled(true);
			buttonFW.setEnabled(true);
			buttonFF.setEnabled(false);
			buttonEK.setEnabled(false);
			break;
		}
		// Flus Graph
		case 3: {
			buttonBFS.setEnabled(true);
			buttonCMP.setEnabled(true);
			buttonDJ.setEnabled(false);
			buttonFW.setEnabled(false);
			buttonFF.setEnabled(true);
			buttonEK.setEnabled(true);
			break;
		}
		}
	}

}
