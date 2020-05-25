package lab2;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Ship {
	private void drawBasis(Graphics2D g2d) {
		g2d.setColor(new Color(128, 64, 0));
		double[][] points = {
				{-300, -10}, {-60, 70}, {140, 70}, {290, 0},
				{165, 170}, {-80, 170}, {-300, -10}
		};
		GeneralPath basis = new GeneralPath();
		basis.moveTo(points[0][0], points[0][1]);
		int length = points.length;
		for(int i = 0; i < length; i++) {
			basis.lineTo(points[i][0], points[i][1]);
		}
		basis.closePath();
		g2d.fill(basis);
	}
	
	private void drawMast(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(0,  -60,  13,  130);
	}
	
	private void drawSail(Graphics2D g2d) {
		GradientPaint gp = new GradientPaint(10, 20, new Color(192, 192, 192), 20, 10, Color.blue, true);
		g2d.setPaint(gp);
		double[][] points = {
				{-90, -130}, {-60, -230}, {130, -230}, {90, -110},
				{130, -20}, {-70, -30}, {-90, -130}
		};
		GeneralPath sail = new GeneralPath();
		sail.moveTo(points[0][0], points[0][1]);
		int length = points.length;
		for(int i = 0; i < length; i++) {
			sail.lineTo(points[i][0], points[i][1]);
		}
		sail.closePath();
		g2d.fill(sail);
	}
	
	void drawShip(Graphics2D g2d) {
		drawBasis(g2d);
		drawMast(g2d);
		drawSail(g2d);
	}
}
