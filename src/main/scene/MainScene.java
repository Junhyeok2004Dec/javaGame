package main.scene;

import assets.RegisterMapData;
import main.util.AssetPool;
import main.util.Input.KeyListener;
import main.util.components.SpriteSheet;
import main.util.object.Camera;
import main.util.object.GameObject;
import main.util.world.MatrixObject;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene implements RegisterMapData {

	MatrixObject matrixObject = new MatrixObject();





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



		matrixObject.worldGen();


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
		} else if (KeyListener.isKeyPressed(GLFW_KEY_T)) {
			matrixObject.getObject(0,0).transform.position.x += 500f * dt;
		} else if (KeyListener.isKeyPressed(GLFW_KEY_Y)) {
			matrixObject.getObject(0, 0).transform.position.x -= 500f * dt;
		}
 		for (GameObject go : this.gameObjects) {
			go.update(dt);
		}


		this.renderer.render();


	}



}


