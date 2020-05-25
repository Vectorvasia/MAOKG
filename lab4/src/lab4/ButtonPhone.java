package lab4;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.*;

public class ButtonPhone {
	
	public static Cylinder getCylinder(float radius, float height, Color3f emissiveColor) {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Cylinder(radius, height, primflags, getButtonPhoneAppearence(emissiveColor));		
	}
	
	private static Appearance getButtonPhoneAppearence(Color3f emissive) {
		Appearance ap = new Appearance();
		Color3f ambient = new Color3f(0.2f, 0.15f, .15f);
		Color3f diffuse = new Color3f(1.2f, 1.15f, .15f);
		Color3f specular = new Color3f(0.0f, 0.0f, 0.0f);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
		return ap;
	}
}
