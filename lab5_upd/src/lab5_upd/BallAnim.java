package lab5_upd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Vector3f;


public class BallAnim extends KeyAdapter implements ActionListener {
	
    private static final float DELTA_DISTANCE = 0.0000001f;

	private Ball ball;
    private TransformGroup ballTransformGroup;
    private Transform3D transform3D = new Transform3D();
    

    private float xLoc = 0;
    private float yLoc = 0;
    private float zLoc = 0;

    private boolean isPressedA = false;
    private boolean isPressedD = false;
    private boolean isPressedSpace = false;
    private int count = 1;

    BallAnim(Ball ball) {
        this.setBall(ball);
        
        this.ballTransformGroup = ball.getBallTransformGroup();
        this.ballTransformGroup.getTransform(this.transform3D);
        
             
        Timer timer = new Timer(50, this);
        timer.start();
    }

    private void setBall(Ball ball) {
		this.ball = ball;		
	}

	private void Move() {
        if (isPressedA) {
            xLoc -= 0.01f;
        }

        if (isPressedD) {
            xLoc += 0.01f;
        }
        if (isPressedSpace) {
        	if(count < 3) count++;
        	else count = 1;
        	
        	while(yLoc < 0.06f) {
        		yLoc += DELTA_DISTANCE;
                xLoc -= DELTA_DISTANCE/2;
                transform3D.setTranslation(new Vector3f(xLoc, yLoc, zLoc));
                ballTransformGroup.setTransform(transform3D);
        	} 	
        }
        yLoc = -0.045f * count;
        transform3D.setTranslation(new Vector3f(xLoc, yLoc, zLoc));
        ballTransformGroup.setTransform(transform3D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Move();
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyCode()) {
            case 65: // A
                if (!isPressedA) {
                    isPressedA = true;
                }
                break;
            case 68: // D
                if (!isPressedD) {
                    isPressedD = true;
                }
                break;
            case 32: // space
                if (!isPressedSpace) {
                    isPressedSpace = true;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyCode()) {
            case 65: // A
                isPressedA = false;

                break;
            case 68: // D
                isPressedD = false;
                break;
            case 32: // space
                isPressedSpace = false;
                break;
        }
    }

	public Ball getBall() {
		return ball;
	}
}
