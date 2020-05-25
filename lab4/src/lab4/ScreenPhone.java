package lab4;

import java.awt.Container;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

public class ScreenPhone {
	
	public static Box getBox(float length, float width, float height, String picture) {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(length, width, height, primflags, getScreenPhoneAppearence(picture));		
	}
	
	private static Appearance getScreenPhoneAppearence(String picture) {
		Appearance ap = new Appearance();
		Color3f emissive = new Color3f(0.7f, 1.0f, 1.0f);
		Color3f ambient = new Color3f(0.2f, 0.5f, 0.15f);
		Color3f diffuse = new Color3f(0.2f, 0.15f, .15f);
		Color3f specular = new Color3f(0.0f, 0.8f, 0.0f);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
		
		if (picture != "") {
			TextureLoader loader = new TextureLoader(picture, "LUMINANCE", new Container());
			Texture texture = loader.getTexture();
			texture.setBoundaryModeS(Texture.WRAP);
			texture.setBoundaryModeT(Texture.WRAP);
			texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
			TextureAttributes texAttr = new TextureAttributes();
			texAttr.setTextureMode(TextureAttributes.MODULATE);
			ap.setTexture(texture);
			ap.setTextureAttributes(texAttr);
		}
		
		return ap;
	}
}
