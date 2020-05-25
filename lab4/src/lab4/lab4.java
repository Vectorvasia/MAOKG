package lab4;

import javax.swing.*;
import javax.vecmath.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.*;

public class lab4 implements ActionListener  {

	private TransformGroup phoneTransformGroup;
	private Transform3D phoneTransform3D = new Transform3D();
	private Timer timer;
	private float angle = 0;
	
	public static void main(String[] args) {
		new lab4();
	}
	
	public lab4() {
		timer = new Timer(50, this);
		timer.start();
		BranchGroup scene = createSceneGraph();
		SimpleUniverse u = new SimpleUniverse();
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(scene);
		}

	private BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();
		phoneTransformGroup = new TransformGroup();
		phoneTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		buildPhone();
		objRoot.addChild(phoneTransformGroup);
		
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
		Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color,
		light1Direction);
		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);
		
		
		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		objRoot.addChild(ambientLightNode);
		return objRoot;
	}

	private void buildPhone() {
		TransformGroup tgFrame = new TransformGroup();
		Transform3D transformFrame = new Transform3D();
		Box boxFrame = FramePhone.getBox(0.22f, 0.4f, 0.03f);
		Vector3f vectorFrame = new Vector3f(.0f, .0f, .0f);
		transformFrame.setTranslation(vectorFrame);
		tgFrame.setTransform(transformFrame);
		tgFrame.addChild(boxFrame);
		phoneTransformGroup.addChild(tgFrame);
		
		TransformGroup tgScreen = new TransformGroup();
		Transform3D transformScreen = new Transform3D();
		Box boxScreen = ScreenPhone.getBox(0.17f, 0.2f, 0.001f, "");
		Vector3f vectorScreen = new Vector3f(.0f, 0.18f, 0.03f);
		transformScreen.setTranslation(vectorScreen);
		tgScreen.setTransform(transformScreen);
		tgScreen.addChild(boxScreen);
		phoneTransformGroup.addChild(tgScreen);
		
		createButton(0.15f, -0.11f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(.0f, -0.11f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(-0.15f, -0.11f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		
		createButton(0.15f, -0.19f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(.0f, -0.19f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(-0.15f, -0.19f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		
		createButton(0.15f, -0.27f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(.0f, -0.27f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(-0.15f, -0.27f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		
		createButton(0.15f, -0.35f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(.0f, -0.35f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		createButton(-0.15f, -0.35f, 0.02f, 0.05f, 0.05f, new Color3f(.0f, .0f, .0f));
		
	}

	private void createButton(float x, float y, float z, float radius, float height, Color3f emissiveColor) {
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		Cylinder cylinder = ButtonPhone.getCylinder(radius, height, emissiveColor);
		Vector3f vector = new Vector3f(x, y, z);
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(cylinder);
		phoneTransformGroup.addChild(tg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		phoneTransform3D.rotY(angle);
		phoneTransformGroup.setTransform(phoneTransform3D);
		angle += 0.035;
		
	}
}
