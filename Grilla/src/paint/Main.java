package paint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	int x1 = 0;
	int y1 = 1;
	int x2 = 20;
	int y2 = 20; 
	
	public void pintarLineaDDA(Graphics g) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		int steps, k;
		float xIncrement, yIncrement, x = x1, y =y1;
		if (Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);
		} else {
			steps = Math.abs(dy);
			xIncrement = dx / (float) steps;
			yIncrement = dy / (float) steps;
			x = x1;
			y = y1;

			for (k = 0; k <= steps; k++) {
				x += xIncrement;
				y += yIncrement;
				g.fillRect((int) x, (int) y, 20, 20);

			}
		}
	}

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
		
		JButton b2 = new JButton("Línea con Bresenham");
		b2.setBounds(0, 0, 80, 30);
		
		JButton b3 = new JButton("Negro");
		b2.setBounds(0, 0, 80, 30);
		
		JButton b4 = new JButton("Rojo");
		b2.setBounds(0, 0, 80, 30);
		
		JButton b5 = new JButton("Cyan");
		b2.setBounds(0, 0, 80, 30);
		
//		ACTIONLISTENERS PARA LOS BOTONES
		
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		
//		panel.add(b3);
//		panel.add(b4);
//		panel.add(b5);
		panel.add(b1);
		panel.add(b2);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}