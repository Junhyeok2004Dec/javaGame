package main.scene;

import assets.RegisterMapData;
import main.system.util.AssetPool;
import main.system.util.Input.KeyListener;
import main.system.util.components.SpriteSheet;
import main.system.util.components.Camera;
import main.system.util.components.GameObject;
import main.system.util.world.WorldObject;
import org.joml.Vector2f;

import java.util.ArrayList;

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



	//sprite animate


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
		} else if (KeyListener.isKeyPressed(GLFW_KEY_I)) {
			objectListTransform(0, new Vector2f(1f,1f), new Vector2f(0,0));
		} else if (KeyListener.isKeyPressed(GLFW_KEY_J)) {
			objectListTransform(0, new Vector2f(-1f,-1f), new Vector2f(0,0));

		}
 		for (GameObject go : this.gameObjects) {
			go.update(dt);
		}


		this.renderer.render();


	}


	/**
	 *  @param index objectList's list number, one List element has two-dimension array. Zero(0) is first.
	 * @param position change each gameObj position (themselves)
	 * @param scale change each gameObj scale (themselves)
	 */
	public void objectListTransform(int index, Vector2f position, Vector2f scale) {



		int gObj1,gObj2;

		gObj1 = worldObject.getObject().length;
		gObj2 = worldObject.getObject()[0].length;


		ArrayList<GameObject[][]> objectList = worldObject.getObjectList();
		/*
		무조건 직사각형(선형, 크기 = 가로 x 세로 로 정의되는 2차 선형 배열 데이터)
		 */


		GameObject[][] gameObjects = objectList.get(index);


		//GameObject 순서도 정립 필요.
		for(int i = 0; i < gObj1; i++) {
			for (int j = 0; j < gObj2;  j++) {

				gameObjects[i][j].transform.position.add(position);
				gameObjects[i][j].transform.scale.add(scale);

			}
		}


	}

}


