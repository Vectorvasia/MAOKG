package car;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

@SuppressWarnings("serial")
class Car extends JFrame {

    private Canvas3D canvas;
    private SimpleUniverse universe;
    private BranchGroup root;

    private TransformGroup road = new TransformGroup();
    private TransformGroup car;

    private Map<String, Shape3D> shapeMap;

    Car() throws IOException {

        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);


        addImageBackground();
        addLightToUniverse();

        ChangeViewAngle();

        car = getModelGroup();
        road.addChild(car);

        root.addChild(road);


        CarAnimation carAnimation = new CarAnimation(this);
        canvas.addKeyListener(carAnimation);

        root.compile();
        universe.addBranchGraph(root);
    }

    private void configureWindow() {
        setTitle("Car Animation");
        setSize(1000, 622);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        canvas.setFocusable(true);
        add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
        OrbitBehavior ob = new OrbitBehavior(canvas);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE));
        universe.getViewingPlatform().setViewPlatformBehavior(ob);
    }

    private void addImageBackground() {
        TextureLoader t = new TextureLoader("assets/road.jpg", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
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

    @SuppressWarnings("unchecked")
	private TransformGroup getModelGroup() throws IOException {

        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene carScene = null;
        try
        {
        	carScene = f.load("assets/sls_amg.obj");
        }
        catch (Exception e)
        {
            System.out.println("File loading failed:" + e);
        }

//        Transform3D scaling = new Transform3D();
//        scaling.setScale(1.0/6);
//        Transform3D tfCar = new Transform3D();
//        Transform3D y = new Transform3D();
//        y.rotY(-Math.PI/3);
//        tfCar.rotX(-Math.PI/2);
//        y.mul(y, tfCar);
//        tfCar.mul(y, scaling);
//        TransformGroup tgCar = new TransformGroup(tfCar);
        TransformGroup sceneGroup = new TransformGroup();


        Hashtable carNamedObjects = carScene.getNamedObjects();
        Enumeration enumer = carNamedObjects.keys();
        String name;
        while (enumer.hasMoreElements())
        {
            name = (String) enumer.nextElement();
            System.out.println("Name: "+name);
        }

        Appearance BlueApp = new Appearance();
        setToMyDefaultAppearance(BlueApp,new Color3f(0.1f,0.1f,0.2f));
        Appearance WhiteApp = new Appearance();
        setToMyDefaultAppearance(WhiteApp,new Color3f(0.9f,0.9f,0.9f));
        Appearance BlackApp = new Appearance();
        setToMyDefaultAppearance(BlackApp,new Color3f(0.0f,0.0f,0.0f));
        Appearance RedApp = new Appearance();
        setToMyDefaultAppearance(RedApp,new Color3f(1.0f,0.0f,0.0f));
        Appearance BApp = new Appearance();
        setToMyDefaultAppearance(BApp,new Color3f(0.7f,1.0f,1.0f));
        Appearance YApp = new Appearance();
        setToMyDefaultAppearance(YApp,new Color3f(1.0f,1.0f,0.5f));
        
        Shape3D wheel1_pivot = (Shape3D) carNamedObjects.get("wheel1_pivot");
        wheel1_pivot.setAppearance(BlackApp);
        Shape3D wheel02_pivot = (Shape3D) carNamedObjects.get("wheel02_pivot");
        wheel02_pivot.setAppearance(BlackApp);
        Shape3D wheel03_pivot = (Shape3D) carNamedObjects.get("wheel03_pivot");
        wheel03_pivot.setAppearance(BlackApp);
        Shape3D wheel04_pivot = (Shape3D) carNamedObjects.get("wheel04_pivot");
        wheel04_pivot.setAppearance(BlackApp);
        
        Shape3D glass = (Shape3D) carNamedObjects.get("glass");
        glass.setAppearance(BlackApp);
        Shape3D rearlight_glasses = (Shape3D) carNamedObjects.get("rearlight_glasses");
        rearlight_glasses.setAppearance(RedApp);
        Shape3D hl_glasses = (Shape3D) carNamedObjects.get("hl_glasses");
        hl_glasses.setAppearance(RedApp);
        Shape3D door_rf_pivot = (Shape3D) carNamedObjects.get("door_rf_pivot");
        door_rf_pivot.setAppearance(BlueApp);
        Shape3D exhaust = (Shape3D) carNamedObjects.get("exhaust");
        exhaust.setAppearance(BlackApp);
        Shape3D tailings = (Shape3D) carNamedObjects.get("tailings");
        tailings.setAppearance(RedApp);
        Shape3D boot = (Shape3D) carNamedObjects.get("boot");
        boot.setAppearance(BlueApp);
        Shape3D defrost = (Shape3D) carNamedObjects.get("defrost");
        defrost.setAppearance(BApp);
        Shape3D glass_interior_glass = (Shape3D) carNamedObjects.get("glass_interior_glass");
		glass_interior_glass.setAppearance(WhiteApp);
        Shape3D gauge = (Shape3D) carNamedObjects.get("gauge");
        gauge.setAppearance(WhiteApp);
        Shape3D headlings = (Shape3D) carNamedObjects.get("headlings");
        headlings.setAppearance(YApp);
        Shape3D door_lf_pivot = (Shape3D) carNamedObjects.get("door_lf_pivot");
        door_lf_pivot.setAppearance(BlueApp);
        Shape3D chassis = (Shape3D) carNamedObjects.get("chassis");
        chassis.setAppearance(BlueApp);
        Shape3D windscreen = (Shape3D) carNamedObjects.get("windscreen");
        windscreen.setAppearance(BApp);
        Shape3D bump_rear = (Shape3D) carNamedObjects.get("bump_rear");
        bump_rear.setAppearance(BlueApp);
        Shape3D defaultCar = (Shape3D) carNamedObjects.get("default");
        defaultCar.setAppearance(WhiteApp);
        Shape3D engine = (Shape3D) carNamedObjects.get("engine");
        engine.setAppearance(BlueApp);
        Shape3D bump_front = (Shape3D) carNamedObjects.get("bump_front");
        bump_front.setAppearance(BlueApp);

        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        TransformGroup transformGroup = new TransformGroup();
        transformGroup.addChild(glass.cloneTree());
        transformGroup.addChild(rearlight_glasses.cloneTree());
        transformGroup.addChild(hl_glasses.cloneTree());
        transformGroup.addChild(door_rf_pivot.cloneTree());
        transformGroup.addChild(exhaust.cloneTree());
        transformGroup.addChild(tailings.cloneTree());
        transformGroup.addChild(boot.cloneTree());
        transformGroup.addChild(defrost.cloneTree());
        transformGroup.addChild(glass_interior_glass.cloneTree());
        transformGroup.addChild(gauge.cloneTree());
        transformGroup.addChild(headlings.cloneTree());
        transformGroup.addChild(door_lf_pivot.cloneTree());
        transformGroup.addChild(chassis.cloneTree());
        transformGroup.addChild(windscreen.cloneTree());
        transformGroup.addChild(bump_rear.cloneTree());
        transformGroup.addChild(defaultCar.cloneTree());
        transformGroup.addChild(engine.cloneTree());
        transformGroup.addChild(bump_front.cloneTree());
        
        TransformGroup wheel1 = new TransformGroup();
        TransformGroup wheel2 = new TransformGroup();
        TransformGroup wheel3 = new TransformGroup();
        TransformGroup wheel4 = new TransformGroup();
        
        
        wheel1.addChild(wheel1_pivot.cloneTree());
        wheel2.addChild(wheel02_pivot.cloneTree());
        wheel3.addChild(wheel03_pivot.cloneTree());
        wheel4.addChild(wheel04_pivot.cloneTree());
        
        
        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        
        int timeStart = 500;
        int timeRotationHour = 600;

        Transform3D W1RotationAxis = new Transform3D();
        Transform3D W1RotationAxis1 = new Transform3D();
        W1RotationAxis1.rotX(Math.PI/4);
        W1RotationAxis.rotZ(Math.PI/2);
        W1RotationAxis.mul(W1RotationAxis1);
        Alpha W1RotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, timeStart, 0,
                timeRotationHour, 0, 0, timeRotationHour, 0, 0);
        RotationInterpolator w1Rotation = new RotationInterpolator(W1RotationAlpha, wheel1,
        		W1RotationAxis, - (float)Math.PI / 36, 0.0f);
        w1Rotation.setSchedulingBounds(bounds);
        
        Transform3D W4RotationAxis = new Transform3D();
        Transform3D W4RotationAxis1 = new Transform3D();
        W4RotationAxis1.rotX(Math.PI/4);
        W4RotationAxis.rotZ(Math.PI/2);
        W4RotationAxis.mul(W4RotationAxis1);
        RotationInterpolator w4Rotation = new RotationInterpolator(W1RotationAlpha, wheel4,
        		W4RotationAxis, - (float) Math.PI / 36, 0.0f);
        w4Rotation.setSchedulingBounds(bounds);
        
        Transform3D W2RotationAxis = new Transform3D();
        W2RotationAxis.rotZ(Math.PI/2);
        RotationInterpolator w2Rotation = new RotationInterpolator(W1RotationAlpha, wheel2,
        		W2RotationAxis, (float)Math.PI / 36, 0.0f);
        w2Rotation.setSchedulingBounds(bounds);
        
        Transform3D W3RotationAxis = new Transform3D();
        W3RotationAxis.rotZ(Math.PI/2);
        RotationInterpolator w3Rotation = new RotationInterpolator(W1RotationAlpha, wheel3,
        		W3RotationAxis, (float) Math.PI / 36, 0.0f);
        w3Rotation.setSchedulingBounds(bounds);
        
        wheel1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheel1.addChild(w1Rotation);
        wheel4.addChild(w4Rotation);
        wheel2.addChild(w2Rotation);
        wheel3.addChild(w3Rotation);
         
        sceneGroup.addChild(transformGroup);
        sceneGroup.addChild(wheel1);
        sceneGroup.addChild(wheel2);
        sceneGroup.addChild(wheel3);
        sceneGroup.addChild(wheel4);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return sceneGroup;
    }
    
    public static void setToMyDefaultAppearance(Appearance app, Color3f col) {
    	app.setMaterial(new Material(col,col,col,col,150.0f));
    }

    private void printModelElementsList(Map<String, Shape3D> shapeMap) {
        for (String name : shapeMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }

    private void ChangeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();      
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);
    }

    public static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(location));
    }

    public TransformGroup getCarTransformGroup() {
        return car;
    }
}

