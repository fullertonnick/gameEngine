package engineTester;



import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import texture.ModelTexture;

public class MainGameLoop {
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer Renderer = new Renderer(shader);
		
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		TexturedModel tmodel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
		Entity entity = new Entity(tmodel, new Vector3f(0,0,-50),0,0,0,1);
		
		Camera camera = new Camera();
		
		//Main Game Loop
		while(!Display.isCloseRequested()){
			entity.transformRotation(0, 0.01f, 0);
			camera.move();
			Renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			Renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}
}
