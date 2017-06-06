package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

public class Grilla extends JComponent implements MouseInputListener {
	Point point = null;

	Main controller;

	Dimension preferredSize = new Dimension(500, 500);

	Color gridColor;
	
	Color pencil;

	boolean[][] clon = new boolean[2000][2000];

	public Grilla(Main controller) {
		this.controller = controller;

		addMouseListener(this);
		addMouseMotionListener(this);
		// setBackground(Color.WHITE);
		// setOpaque(true);
		setVisible(true);

	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public Color getPencil() {
		return pencil;
	}

	public void setPencil(Color pencil) {
		this.pencil = pencil;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// COLOR DEL BACKGROUND SI ESTA OPACO
		// if (isOpaque()) {
		// g.setColor(getBackground());
		// g.fillRect(0, 0, getWidth(), getHeight());
		//
		// }

		// COLOR DE GRID
		g.setColor(Color.GRAY);
		dibujarGrid(g, 20);

		// SI HAY UN PUNTO SE PINTA UN CUADRADO.
		if (point != null) {
			g.setColor(Color.BLACK);
			int coordeX = (point.x) / 20;
			int coordeY = (point.y) / 20;
			g.fillRect(coordeX * 20, coordeY * 20, 20, 20);

			// DESPU�S DE PINTAR EL NUEVO CUADRADO SE GUARDA Y SE VUELVEN A
			// PINTAR TODOS
			
			//CLON DE BOOLEANS PARA GRILLA
			int x = point.x;
			int y = point.y;
			clon[x][y] = true;
			for (int i = 0; i < 2000; ++i) {
				for (int j = 0; j < 2000; j++) {
					if (clon[i][j] == true) {
						int a = i / 20;
						int b = j / 20;
						g.fillRect(a*20, b*20, 20, 20);
					}
				}
			}
		}
	}

	// DIBUJAR GRID 24x24
	public void dibujarGrid(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = getWidth() - insets.right;
		int lastY = getHeight() - insets.bottom;

		// LINEAS VERTICALES
		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		// LINEAS HORIZONTALES.
		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}
	}

	// PARA DETECTAR EL CLICK DEL MOUSE
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (point == null) {
			point = new Point(x, y);
		} else {
			point.x = x;
			point.y = y;
		}
		controller.updatePuntoClick(point);
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		controller.updateUbicacionCursor(e.getX(), e.getY());
	}

	public void mouseExited(MouseEvent e) {
		controller.resetLabel();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}
}
