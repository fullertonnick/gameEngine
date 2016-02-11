package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import toolbox.Maths;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	
	private int location_TransformationMatrix;
	private int location_ProjectionMatrix;
	private int location_ViewMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		location_TransformationMatrix = super.getUniformLocation("transformationMatrix");
		location_ProjectionMatrix = super.getUniformLocation("projectionMatrix");
		location_ViewMatrix = super.getUniformLocation("viewMatrix");
		
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_TransformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_ProjectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f matrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_ViewMatrix, matrix);
	}
	
	

}
