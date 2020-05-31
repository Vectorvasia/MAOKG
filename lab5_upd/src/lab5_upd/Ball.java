package lab5_upd;

import java.awt.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
	
@SuppressWarnings("serial")
class Ball extends JFrame{
	private Canvas3D canvas;
	private SimpleUniverse universe;
	private BranchGroup root;
	
	private TransformGroup bg = new TransformGroup();
	private TransformGroup ball;
	private TransformGroup steps = new TransformGroup();
	
	Ball() throws IOException {

        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);


        addImageBackground();
        addLightToUniverse();

        ChangeViewAngle();

        ball = getModelGroup();
        Transform3D transform3D = new Transform3D();
        ball.getTransform(transform3D);
        System.out.println("File loading failed:" + ball.toString());
        bg.addChild(ball);
        buildSteps();
        bg.addChild(steps);
        root.addChild(bg);
        
        BallAnim ballAnim = new BallAnim(this);
        canvas.addKeyListener(ballAnim);


        root.compile();
        universe.addBranchGraph(root);
    }

	private TransformGroup getModelGroup() throws IOException {
		ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene ballScene = null;
        try
        {
        	ballScene = f.load("assets/Pokeball_obj.obj");
        }
        catch (Exception e)
        {
            System.out.println("File loading failed:" + e);
        }
        
        TransformGroup sceneGroup = new TransformGroup();
        @SuppressWarnings("rawtypes")
		Hashtable ballNamedObjects = ballScene.getNamedObjects();
        @SuppressWarnings("rawtypes")
		Enumeration enumer = ballNamedObjects.keys();
        String name;
        while (enumer.hasMoreElements())
        {
            name = (String) enumer.nextElement();
            System.out.println("Name: "+name);
        }
        
        Shape3D pokeball = (Shape3D) ballNamedObjects.get("pokeball");
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.addChild(pokeball.cloneTree());
        
        sceneGroup.addChild(transformGroup);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return sceneGroup;
	}

	private void ChangeViewAngle() {
		ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();      
        vpTranslation.setTranslation(new Vector3f(0, 0, 1));
        vpGroup.setTransform(vpTranslation);
	}

	private void addLightToUniverse() {
		BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(1000);

        DirectionalLight directionalLight = new DirectionalLight(
                new Color3f(new Color(255, 255, 255)),
                new Vector3f(0, -0.5f, -0.5f));
        directionalLight.setInfluencingBounds(bounds);

        AmbientLight ambientLight = new AmbientLight(
                new Color3f(new Color(255, 255, 245)));
        ambientLight.setInfluencingBounds(bounds);

        root.addChild(directionalLight);
        root.addChild(ambientLight);
	}

	private void addImageBackground() {
		TextureLoader t = new TextureLoader("assets/bg.jpg", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
	}

	private void configureUniverse() {
		universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
	}

	private void configureCanvas() {
		canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        canvas.setFocusable(true);
        add(canvas, BorderLayout.CENTER);
	}

	private void configureWindow() {
		setTitle("lab5");
        setSize(1000, 622);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public TransformGroup getBallTransformGroup() {
        return ball;
    }	
	
	private void buildSteps() {
		TransformGroup tgStep1 = new TransformGroup();
		Transform3D transformStep1 = new Transform3D();
		Box boxStep1 = Step.getBox(0.07f, 0.0625f, 0.03f);
		Vector3f vectorStep1 = new Vector3f(.0f, -0.1075f, .0f);
		transformStep1.setTranslation(vectorStep1);
		tgStep1.setTransform(transformStep1);
		tgStep1.addChild(boxStep1);
		steps.addChild(tgStep1);
		
		TransformGroup tgStep2 = new TransformGroup();
		Transform3D transformStep2 = new Transform3D();
		Box boxStep2 = Step.getBox(0.07f, 0.04f, 0.03f);
		Vector3f vectorStep2 = new Vector3f(-0.12f, -0.13f, .0f);
		transformStep2.setTranslation(vectorStep2);
		tgStep2.setTransform(transformStep2);
		tgStep2.addChild(boxStep2);
		steps.addChild(tgStep2);
		
		TransformGroup tgStep3 = new TransformGroup();
		Transform3D transformStep3 = new Transform3D();
		Box boxStep3 = Step.getBox(0.07f, 0.0175f, 0.03f);
		Vector3f vectorStep3 = new Vector3f(-0.24f, -0.1525f, .0f);
		transformStep3.setTranslation(vectorStep3);
		tgStep3.setTransform(transformStep3);
		tgStep3.addChild(boxStep3);
		steps.addChild(tgStep3);
	}
	
	public static void main(String[] args) {
		try {
            Ball window = new Ball();
            window.setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
	}
}
