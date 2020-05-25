package lab5;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.vecmath.Vector3f;
import java.awt.event.*;

public class CarAnimation extends KeyAdapter implements ActionListener {

    private static final float DELTA_DISTANCE_Z = 0.04f;
    private static final float DELTA_DISTANCE = 0.02f;

    private Car car;
    private TransformGroup carTransformGroup;
    private Transform3D transform3D = new Transform3D();
    

    private float xLoc = 0;
    private float yLoc = -0.8f;
    private float zLoc = 0;

    private float xAngle = 0;
    private float yAngle = 0;
    private float zAngle = 0;


    private boolean resetXRotation = false;
    private boolean resetYRotation = false;
    private boolean resetZRotation = false;

    private boolean isRotatedPosY = false;
    private boolean isRotatedNegY = false;


    private boolean isPressedW = false;
    private boolean isPressedS = false;
    private boolean isPressedA = false;
    private boolean isPressedD = false;

    CarAnimation(Car car) {
        this.setCar(car);
        
        this.carTransformGroup = car.getCarTransformGroup();
        this.carTransformGroup.getTransform(this.transform3D);
        
        Transform3D temp = new Transform3D();
        
        transform3D.rotX(-Math.PI/2);
        temp.rotZ(Math.PI);
        transform3D.mul(temp);

        

        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void Move() {
        if (isPressedW) {
            zLoc -= DELTA_DISTANCE_Z;
        }

        if (isPressedS) {
            zLoc += DELTA_DISTANCE_Z;
        }

        if (isPressedA) {
            xLoc -= DELTA_DISTANCE;
        }

        if (isPressedD) {
            xLoc += DELTA_DISTANCE;
        }

        transform3D.setTranslation(new Vector3f(xLoc, yLoc, zLoc));

        if (isRotatedPosY) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(degree(-20));
            transform3D.mul(rotation);

            zAngle += degree(-20);

            isRotatedPosY = false;
        }

        if (isRotatedNegY) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(degree(20));
            transform3D.mul(rotation);

            zAngle += degree(20);

            isRotatedNegY = false;
        }

        if (resetYRotation) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(-zAngle);
            transform3D.mul(rotation);

            resetYRotation = false;
            zAngle = 0;
        }

        if (resetZRotation) {
            Transform3D rotation = new Transform3D();
            rotation.rotY(-yAngle);
            transform3D.mul(rotation);

            resetZRotation = false;
            yAngle = 0;
        }

        if (resetXRotation) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(xAngle);
            transform3D.mul(rotation);

            resetXRotation = false;
            xAngle = 0;
        }
        carTransformGroup.setTransform(transform3D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Move();
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyCode()) {
            case 87: // W
                isPressedW = true;
                break;
            case 83: // S
                isPressedS = true;
                break;
            case 65: // A
                if (!isPressedA) {
                    isPressedA = true;
                    isRotatedNegY = true;
                }
                break;
            case 68: // D
                if (!isPressedD) {
                    isPressedD = true;
                    isRotatedPosY = true;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyCode()) {
            case 87: // W
                isPressedW = false;
                break;
            case 83: // S
                isPressedS = false;
                break;
            case 65: // A
                isPressedA = false;
                resetYRotation = true;
                break;
            case 68: // D
                isPressedD = false;
                resetYRotation = true;
                break;
        }
    }

    private float degree(float degrees) {
        return (float) (degrees * Math.PI / 180);
    }

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}