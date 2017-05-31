package paint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

public class Main {
	private JLabel label;

	private Point puntoClick, puntoCurso;

	private void buildUI(Container container) {
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		Grilla coordinateArea = new Grilla(this);
		container.add(coordinateArea);

		label = new JLabel();
		resetLabel();
		container.add(label);

		coordinateArea.setAlignmentX(Component.LEFT_ALIGNMENT);
	}

	public void updateUbicacionCursor(int x, int y) {
		if (x < 0 || y < 0) {
			puntoCurso = null;
			updateLabel();
			return;
		}

		if (puntoCurso == null) {
			puntoCurso = new Point();
		}

		puntoCurso.x = x;
		puntoCurso.y = y;
		updateLabel();
	}

	public void updatePuntoClick(Point p) {
		puntoClick = p;
		updateLabel();
	}

	public void resetLabel() {
		puntoCurso = null;
		updateLabel();
	}

//	LABEL PARA INDICAR LAS POSICIONES DE LOS PUNTOS EN LA GRILLA
	public void updateLabel() {
		String text = "";

		if ((puntoClick == null) && (puntoCurso == null)) {
			text = "Mueva el Mouse dentro de la grilla para encontrar un punto";
		} else {

			if (puntoClick != null) {
				text += "Último punto (" + (puntoClick.x) / 20 + ", " + (puntoClick.y) / 20 + "). ";
			}

			if (puntoCurso != null) {
				text += "Punto actual (" + (puntoCurso.x) / 20 + ", " + (puntoCurso.y) / 20 + "). ";
			}
		}

		label.setText(text);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Main controller = new Main();
		controller.buildUI(frame.getContentPane());
		
//		PARA CREAR EL PANEL DE BOTONES
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBounds(0, 0, 200, 200);
		
//		PARA CREAR BOTONES Y SUS CARACTERISTICAS
		JButton b1 = new JButton("Línea con DDA");
		b1.setBounds(0,0, 80, 30);
		b1.setBackground(Color.yellow);
		JButton b2 = new JButton("Línea con Bresenham");
		b2.setBounds(0, 0, 80, 30);
		b2.setBackground(Color.green);
		
//		ACTIONLISTENERS PARA LOS BOTONES
		
//		b1.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				DDA dda;
//				dda.pintarLineaDDA(x1, y1, x2, y2, g);
//			}
//		});
		
//		b2.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				Bresenham bre;
//				bre.line(x, y, x2, y2, g);
//			}
//		});
		
		panel.add(b1);
		panel.add(b2);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}