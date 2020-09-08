package engineTester;


import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Shaders.StaticShader;
import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TextureModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRender;
import renderEngine.OBJLoader;
import terrains.Terrain;
import renderEngine.EntityRenderer;
import textures.ModelTexture;

public class MainGame {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("fox", loader);	
		TextureModel texturedModel = new TextureModel(model, new ModelTexture(loader.loadTexture("texture")));
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDampner(10);
		texture.setReflectivity(1);
		
		Camera camera = new Camera();
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-300),0,0,0,1);
		Light light = new Light(new Vector3f(3000,2000,2000), new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		
		
		MasterRender render = new MasterRender();
		while(!Display.isCloseRequested()) {
			camera.move();
			render.processTerrain(terrain);
			render.processTerrain(terrain2);
			render.processEntity(entity);
			render.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		
		render.cleanUp();
		loader.clean();
		DisplayManager.destroyDisplay();

	}

}
