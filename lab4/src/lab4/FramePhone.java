package lab4;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;

public class FramePhone {
	public static Box getBox(float length, float width, float height) {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(length, width, height, primflags, getFramePhoneAppearence());		
	}
	private static Appearance getFramePhoneAppearence() {
		Appearance ap = new Appearance();
		Color3f emissive = new Color3f(0.0f, 0.05f, 0.0f);
		Color3f ambient = new Color3f(.0f, .0f, 1.0f);
		Color3f diffuse = new Color3f(0.2f, 0.15f, .15f);
		Color3f specular = new Color3f(0.0f, 0.8f, 0.0f);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
		return ap;
	}
}
