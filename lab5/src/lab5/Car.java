package lab5;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;

import java.io.FileReader;
import java.io.IOException;
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
        Transform3D transform3D = new Transform3D();
        car.getTransform(transform3D);
        System.out.println("File loading failed:" + car.toString());
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
        Scene scene = getSceneFromFile("assets/sls_amg.obj");
        shapeMap = scene.getNamedObjects();

        printModelElementsList(shapeMap);

        TransformGroup group = new TransformGroup();

        for (String shapeName: shapeMap.keySet()) {
            Shape3D shape = shapeMap.get(shapeName);

            scene.getSceneGroup().removeChild(shape);
            group.addChild(shape);
        }

        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        return group;
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
