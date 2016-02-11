package renderEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import toolbox.Maths;

public class Renderer {
	
	public static final float FOV = 70;
	public static final float nearPlane = 0.1f;
	public static final float farPlane = 1000;
	
	private Matrix4f projectionMatrix;
	
	public Renderer(StaticShader shader){
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0,0,0,1);
	}
	
	public void render(Entity entity, StaticShader shader){
		TexturedModel tmodel = entity.getModel();
		RawModel model = tmodel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotx(), entity.getRoty(), entity.getRotz(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tmodel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yscale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f)))*aspectRatio);
		float xscale = yscale/aspectRatio;
		float frustum_length = farPlane - nearPlane;
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = xscale;
		projectionMatrix.m11 = yscale;
		projectionMatrix.m22 = -((farPlane + nearPlane)/frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2*nearPlane*farPlane)/frustum_length);
		projectionMatrix.m33 = 0;
	}
	
	
}