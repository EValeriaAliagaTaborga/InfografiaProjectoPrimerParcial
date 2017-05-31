package paint;

import java.awt.Graphics;

public class DDA {

	public void pintarLineaDDA(int x1, int y1, int x2, int y2, Graphics g) {
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
}
