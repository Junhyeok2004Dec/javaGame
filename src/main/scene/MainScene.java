package main.scene;

import assets.RegisterMapData;
import main.system.util.AssetPool;
import main.system.util.Input.KeyListener;
import main.system.util.components.*;
import main.system.util.renderer.SpriteRenderer;
import main.system.util.world.WorldObject;
import org.joml.Vector2f;
import org.lwjgl.vulkan.VkRayTracingShaderGroupCreateInfoNV;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene implements RegisterMapData {

	WorldObject worldObject = new WorldObject();

	private GameObject object = null;





	@Override
	public void init() {

		Texture tex = new Texture();

		loadResources();

		System.out.println("mainscene");

		sceneNum = 0;
		this.camera = new Camera(new Vector2f(0, 0));

		loadResources();

		loadObject(new GameObject("testObject", new Transform(new Vector2f(1.2f,1.2f), new Vector2f(1.5f,1.5f)), 1), tex.init( "src/assets/image/Spongebob.png"));



	}


	private void loadResources() {


		AssetPool.getShader(shaderAddress);

		AssetPool.addSpritesheet(spriteSheetAddress,
				new SpriteSheet(AssetPool.getTexture(spriteSheetAddress),
						16, 16, 256, 0));



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
		} else if (KeyListener.isKeyPressed(GLFW_KEY_R)) {
			this.object.transform.position.set(new Vector2f(1f,0f));
		} else if (KeyListener.isKeyPressed(GLFW_KEY_F)) {
			this.object.transform.position.set(new Vector2f(0f,1f));
		}







		/**
		 GameObject 관련 로드 및 업데이트
		 float dt 간 업데이트 구성요함.

		 2022 10 05

		 */
 		for (GameObject go : this.gameObjects) {
			go.update(dt);
		}


		this.renderer.render();


	}


	public void loadObject(GameObject gameObject, Texture spriteAddress) {

		SpriteSheet sprite = null;
		this.object = gameObject;

		this.object.addComponent(new SpriteRenderer(sprite.getSprite(spriteAddress)));
		this.addGameObjectToScene(this.object);


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


