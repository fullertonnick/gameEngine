package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	public static final int Width = 1280;
	public static final int Height = 720;
	public static final int FPS_Cap = 120;
	
	
	public static void createDisplay(){
		
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(Width, Height));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("3D Java Game");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, Width, Height);
		
	}
	
	public static void updateDisplay(){
		
		Display.sync(FPS_Cap);
		Display.update();
		
	}
	
	public static void closeDisplay(){
		
		Display.destroy();
		
	}
}
