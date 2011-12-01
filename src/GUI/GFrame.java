package GUI;

import static Algorithmen.Utility.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaces.CapacityEdge;
import Interfaces.NormalEdge;
import Interfaces.RatedEdge;

public class GFrame extends JFrame {

	private static final long serialVersionUID = 4987365458342114829L;
	int wahl;
	boolean directed = false;
	JPanel felder;
	final JFormattedTextField nvb1; // anzahl Ecken
	ArrayList<JFormattedTextField[]> fieldlist = new ArrayList<JFormattedTextField[]>();
	Frame dad;

	public GFrame(int wahl, Frame that) {
		super("Graph erstellen");
		this.wahl = wahl;		//1 = normaler, 2 = gewichteter, 3 = Fluß Graph
		setVisible(true);
		setSize(400, 740);
		dad = that;

		Container content = getContentPane();
		content.setLayout(new GridLayout(1, 2));

		JPanel options = new JPanel();
		
		//gewählter graph
		JLabel txtFieldTitel = new JLabel("Normaler Graph");
		txtFieldTitel.setPreferredSize(new Dimension(100, 30));
		options.add(txtFieldTitel);

		// directed checkbox
		JCheckBox cb1 = new JCheckBox("gerichtet");
		cb1.setPreferredSize(new Dimension(100, 30));
		options.add(cb1);
		cb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (directed == false) {
					directed = true;
		    		System.out.println(directed);
				} else {
					directed = false;
		    		System.out.println(directed);
				}
			}
		});
		
		// Ecken Hinweis
		JLabel vb1 = new JLabel("Ecken 0 - n");
		vb1.setPreferredSize(new Dimension(100, 30));
		options.add(vb1);
		
		// Ecken Textfeld
		nvb1 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		nvb1.setHorizontalAlignment(JTextField.RIGHT);
		nvb1.setPreferredSize(new Dimension(100, 30));
		nvb1.setValue(null);
		options.add(nvb1);

		//button create
		JButton b1 = new JButton("create");
		b1.setPreferredSize(new Dimension(100, 30));

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (!(nvb1.getText().equals(""))) {
					makeNGraph();
				}
			}
		};
		b1.addActionListener(actionListener);
		options.add(b1);

		
		// Linke Seite
		//Container für die Kantenfelder
		felder = new JPanel();
		felder.setLayout(new GridLayout(21, 1));
		JLabel txtField;
		if(wahl == 1)
			txtField = new JLabel("Source/Target");
		else
			txtField = new JLabel("Source/Target/Value");
			
		felder.add(txtField);

		// erstelle i eingabefelder für Kanten
		for (int i = 0; i < 20; i++) {
			addFieldN(felder);
		}
		
		//alles zusammenfügen
		content.add(felder);
		content.add(options);
	}

	private void makeNGraph() {
		
		switch (wahl) {
		case 1:{
			ArrayList<NormalEdge> ln = new ArrayList<NormalEdge>();
			//Kantenfelder auslesen
			for (JFormattedTextField[] f : fieldlist) {
				if (!(f[0].getText().equals("")) && !(f[1].getText().equals(""))) {
					ln.add(ne(Integer.parseInt(f[0].getText()),Integer.parseInt(f[1].getText())));
				}
			}
			//Graph erstellen
			if (directed) {
				dad.setGraph(makeDigraphl(vertexs(Integer.parseInt(nvb1.getText())),ln));
			} else {
				dad.setGraph(makeGraphl(vertexs(Integer.parseInt(nvb1.getText())),ln));
			}
			//erlaubte Buttons einstellen
			dad.disableButtons(wahl);
			break;
			}
		case 2:{
			ArrayList<RatedEdge> lr = new ArrayList<RatedEdge>();
	
			for (JFormattedTextField[] f : fieldlist) {
				if (!(f[0].getText().equals("")) && !(f[1].getText().equals(""))) {
					lr.add(re(Integer.parseInt(f[0].getText()),Integer.parseInt(f[1].getText()),Integer.parseInt(f[2].getText())));
				}
			}
			if (directed) {
				dad.setGraph(makeDigraphl(vertexs(Integer.parseInt(nvb1.getText())),lr));
			} else {
				dad.setGraph(makeGraphl(vertexs(Integer.parseInt(nvb1.getText())),lr));
			}
			dad.disableButtons(wahl);
			break;
			}
		case 3:{
			ArrayList<CapacityEdge> lc = new ArrayList<CapacityEdge>();
		
			for (JFormattedTextField[] f : fieldlist) {
				if (!(f[0].getText().equals("")) && !(f[1].getText().equals(""))) {
					lc.add(ce(Integer.parseInt(f[0].getText()),Integer.parseInt(f[1].getText()),Integer.parseInt(f[2].getText())));
				}
			}
			if (directed) {
				dad.setGraph(makeDigraphl(vertexs(Integer.parseInt(nvb1.getText())),lc));
			} else {
				dad.setGraph(makeGraphl(vertexs(Integer.parseInt(nvb1.getText())),lc));
			}
			dad.disableButtons(wahl);
			break;
			}
		}
		
		
		
		

	}

	private void addFieldN(JPanel p) {

		JPanel f = new JPanel();
		if(wahl == 1){
		f.setLayout(new GridLayout(1, 2));
		}else{
		f.setLayout(new GridLayout(1, 3));
		}

		final JFormattedTextField source = new JFormattedTextField(NumberFormat.getIntegerInstance());
		source.setHorizontalAlignment(JTextField.RIGHT);
		source.setValue(null);
		source.setPreferredSize(new Dimension(40, 20));
		
		final JFormattedTextField target = new JFormattedTextField(NumberFormat.getIntegerInstance());
		target.setHorizontalAlignment(JTextField.RIGHT);
		target.setValue(null);
		target.setPreferredSize(new Dimension(40, 20));
		if(wahl != 1){
		final JFormattedTextField value = new JFormattedTextField(NumberFormat.getIntegerInstance());
		value.setHorizontalAlignment(JTextField.RIGHT);
		value.setValue(null);
		value.setPreferredSize(new Dimension(40, 20));
			fieldlist.add(new JFormattedTextField[] { source, target ,value});
			f.add(source);
			f.add(target);
			f.add(value);
		}else{
			fieldlist.add(new JFormattedTextField[] { source, target });
		f.add(source);
		f.add(target);
		}

		p.add(f);

	}
}
