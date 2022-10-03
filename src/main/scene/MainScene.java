package main.scene;

import assets.RegisterMapData;
import main.system.util.AssetPool;
import main.system.util.Input.KeyListener;
import main.system.util.components.SpriteSheet;
import main.system.util.components.Camera;
import main.system.util.components.GameObject;
import main.system.util.world.WorldObject;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene implements RegisterMapData {

	WorldObject worldObject = new WorldObject();





	@Override
	public void init() {


		loadResources();

		System.out.println("mainscene");

		sceneNum = 0;
		this.camera = new Camera(new Vector2f(0, 0));

		loadResources();



	}


	private void loadResources() {


		AssetPool.getShader(shaderAddress);

		AssetPool.addSpritesheet(spriteSheetAddress,
				new SpriteSheet(AssetPool.getTexture(spriteSheetAddress),
						16, 16, 28, 0));



		worldObject.worldGen();


		// ResourceManager 참고


	}



	//sprite animatione

	@Override
	public void update(float dt) {



		if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
			camera.position.x += 1000f * dt;
		} else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
			camera.position.x -= 1000f * dt;
		} else if (KeyListener.isKeyPressed(GLFW_KEY_UP)) {
			camera.position.y += 1000f * dt;
		} else if (KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
			camera.position.y -= 1000f * dt;
		} else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT_BRACKET)) {
			camera.adjustProjection();
			camera.enLargement(0.97f);
		} else if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT_BRACKET)) {
			camera.adjustProjection();
			camera.enLargement(1.02f);
		}
 		for (GameObject go : this.gameObjects) {
			go.update(dt);
		}


		this.renderer.render();


	}



}


