package lab2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class lab2 extends JPanel implements ActionListener {
	private double angle = 0;

	private double scale = 1;
	private double delta = 0.01;
	 
	private static int maxWidth;
	private static int maxHeight;
	
	public lab2() {
		Timer timer =  new Timer(10, this);
		timer.start();
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
        		RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
		
		g2d.setBackground(new Color(255, 255, 128));
        g2d.clearRect(0, 0, maxWidth + 1, maxHeight + 1);
        
        drawFrame(g2d);
		g2d.translate(maxWidth/2, maxHeight/2);
		
		g2d.rotate(angle);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float) scale));

		Ship ship = new Ship();
		ship.drawShip(g2d);
		
	}

	private void drawFrame(Graphics2D g2d) {
		g2d.setColor(Color.green);
        BasicStroke bs2 = new BasicStroke(16, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER);
        g2d.setStroke(bs2);
        g2d.drawRect(50, 15, maxWidth - 100, maxHeight - 30);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("LAB 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new lab2());
        frame.setVisible(true);
        
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (scale < 0.01) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        scale += delta;
        angle += 0.01;

        repaint();
	}
}
