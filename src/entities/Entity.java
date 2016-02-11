package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private float rotx,roty,rotz;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, float rotx, float roty, float rotz, float scale) {
		super();
		this.model = model;
		this.position = position;
		this.rotx = rotx;
		this.roty = roty;
		this.rotz = rotz;
		this.scale = scale;
	}
	
	public void transformPosition(float tx, float ty, float tz){
		this.position.x += tx;
		this.position.y += ty;
		this.position.z += tz;
	}
	
	public void transformRotation(float tx, float ty, float tz){
		this.rotx += tx;
		this.roty += ty;
		this.rotz += tz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotx() {
		return rotx;
	}

	public void setRotx(float rotx) {
		this.rotx = rotx;
	}

	public float getRoty() {
		return roty;
	}

	public void setRoty(float roty) {
		this.roty = roty;
	}

	public float getRotz() {
		return rotz;
	}

	public void setRotz(float rotz) {
		this.rotz = rotz;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
}
