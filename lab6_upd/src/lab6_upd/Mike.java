package lab6_upd;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;

import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;

import java.util.Enumeration;
import java.util.Hashtable;

@SuppressWarnings("serial")
public class Mike extends JFrame
{
    public Canvas3D myCanvas3D;

    public Mike() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);

        simpUniv.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(simpUniv);

        addLight(simpUniv);

        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("Lab 6");
        setSize(700, 700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new Mike();
    }

    public void createSceneGraph(SimpleUniverse su)
    {

        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene mikeScene = null;
        try
        {
            mikeScene = f.load("assets/mike.obj");
        }
        catch (Exception e)
        {
            System.out.println("File loading failed:" + e);
        }

        Transform3D scaling = new Transform3D();
        scaling.setScale(1.0/6);
        Transform3D tfMike = new Transform3D();
        tfMike.rotX(Math.PI/3);
        tfMike.mul(scaling);
        TransformGroup tgMike = new TransformGroup(tfMike);
        TransformGroup sceneGroup = new TransformGroup();


        @SuppressWarnings("rawtypes")
		Hashtable mikeNamedObjects = mikeScene.getNamedObjects();
        @SuppressWarnings("rawtypes")
		Enumeration enumer = mikeNamedObjects.keys();
        String name;
        while (enumer.hasMoreElements())
        {
            name = (String) enumer.nextElement();
            System.out.println("Name: "+name);
        }

        Shape3D left_leg = (Shape3D) mikeNamedObjects.get("left_leg");
        Shape3D right_leg = (Shape3D) mikeNamedObjects.get("right_leg");
        Shape3D left_hand = (Shape3D) mikeNamedObjects.get("left_hand");
        Shape3D right_hand = (Shape3D) mikeNamedObjects.get("right_hand");
        Shape3D monstr = (Shape3D) mikeNamedObjects.get("monstr");

        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        TransformGroup transformGroup = new TransformGroup();
        transformGroup.addChild(monstr.cloneTree());


        TransformGroup leftLegGr = new TransformGroup();
        TransformGroup rightLegGr = new TransformGroup();
        TransformGroup leftHandGr = new TransformGroup();
        TransformGroup rightHandGr = new TransformGroup();
        leftLegGr.addChild(left_leg.cloneTree());
        rightLegGr.addChild(right_leg.cloneTree());
        leftHandGr.addChild(left_hand.cloneTree());
        rightHandGr.addChild(right_hand.cloneTree());


        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        BranchGroup theScene = new BranchGroup();
        
        Transform3D tCrawl = new Transform3D();
        tCrawl.rotY(-Math.PI/2);

        long crawlTime = 10000;
        Alpha crawlAlpha = new Alpha(1,
        Alpha.INCREASING_ENABLE,
        0,
        0, crawlTime,0,0,0,0,0);

        float crawlDistance = 9.0f;
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha,
        sceneGroup,tCrawl, -9.0f, crawlDistance);

        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
        posICrawl.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);


        int timeStart = 500;
        int timeRotationHour = 600;

        Transform3D leftLegRotationAxis = new Transform3D();
        leftLegRotationAxis.rotZ(Math.PI / 2);
        Alpha leftLegRotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, timeStart, 0,
                timeRotationHour, 0, 0, timeRotationHour, 0, 0);
        RotationInterpolator leftLegRotation = new RotationInterpolator(leftLegRotationAlpha, leftLegGr,
                leftLegRotationAxis, (float) Math.PI / 6, 0.0f);
        leftLegRotation.setSchedulingBounds(bounds);


        Transform3D rightHandRotationAxis = new Transform3D();
        rightHandRotationAxis.rotZ(Math.PI / 2);
        RotationInterpolator rightHandRotation = new RotationInterpolator(leftLegRotationAlpha, rightHandGr,
                rightHandRotationAxis, (float) Math.PI / 6, 0.0f);
        rightHandRotation.setSchedulingBounds(bounds);


        Transform3D rightLegRotationAxis = new Transform3D();
        rightLegRotationAxis.rotZ(Math.PI / 2);
        Alpha rightLegRotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, 0, 0,
                timeRotationHour, 0, 0, timeRotationHour, 0, 0);
        RotationInterpolator rightLegRotation = new RotationInterpolator(rightLegRotationAlpha, rightLegGr,
                rightLegRotationAxis, (float) Math.PI / 4, 0.0f);
        rightLegRotation.setSchedulingBounds(bounds);

        Transform3D leftHandRotationAxis = new Transform3D();
        leftHandRotationAxis.rotZ(Math.PI / 2);
        RotationInterpolator leftHandRotation = new RotationInterpolator(rightLegRotationAlpha, leftHandGr,
                leftHandRotationAxis, (float) Math.PI / 4, 0.0f);
        leftHandRotation.setSchedulingBounds(bounds);


        leftLegGr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rightLegGr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leftHandGr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rightHandGr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leftLegGr.addChild(leftLegRotation);
        rightLegGr.addChild(rightLegRotation);
        leftHandGr.addChild(leftHandRotation);
        rightHandGr.addChild(rightHandRotation);

        sceneGroup.addChild(transformGroup);
        sceneGroup.addChild(leftLegGr);
        sceneGroup.addChild(rightLegGr);
        sceneGroup.addChild(leftHandGr);
        sceneGroup.addChild(rightHandGr);
        tgMike.addChild(sceneGroup);
        theScene.addChild(tgMike);

        Background bg = new Background(new Color3f(0.5f,0.5f,0.5f));

        bg.setApplicationBounds(bounds);
        theScene.addChild(bg);
        theScene.compile();

        su.addBranchGraph(theScene);
    }


    public void addLight(SimpleUniverse su)
    {
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(0.5f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
}
