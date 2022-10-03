package main.system.util.world;

import assets.RegisterMapData;
import main.system.util.AssetPool;
import main.system.util.components.GameObject;
import main.system.util.components.SpriteSheet;
import main.system.util.components.Transform;
import main.system.util.renderer.SpriteRenderer;
import org.joml.Vector2f;

import java.util.ArrayList;

import static main.scene.Window.getScene;

public class WorldObject implements RegisterMapData {

	private String map;


	ArrayList<GameObject[][]> objectList = new ArrayList<>();


	private final int totalObjCount = 256;


	private final int blockSize = 16;

	private GameObject[][] object;

	private SpriteSheet sprite;


	// todo GameObject 하나의 Arraylist에 저장할 것.


	public void worldGen() {



		DataManager mapGen = new DataManager();



		AssetPool.getShader(shaderAddress);

		AssetPool.addSpritesheet(spriteSheetAddress, new SpriteSheet(AssetPool.getTexture(spriteSheetAddress),
				blockSize, blockSize, totalObjCount, 0));


		sprite = AssetPool.getSpritesheet(spriteSheetAddress);



		for (int id : mapID) {
			mapGen.Data("src/assets/data/map/" + mapFile(id) + ".block");




			// WorldData 분배



			//worldData 분배 방식

			// a(11) a(12) a(13) .. a(1  width)
			// a(21) ...
			// ..
			// a(height 1) ...      a(height width)

			// for matrix system


			int width = mapGen.getWidth();
			int height = mapGen.getHeight();


			object = new GameObject[width][height];



			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {



					object[j][i] = new GameObject("Block" + j + height * i, new Transform(new Vector2f(blockSize * j, - blockSize * i), new Vector2f(blockSize, blockSize)), 1);

					object[j][i].addComponent(new SpriteRenderer(sprite.getSprite(mapGen.getData(i, j))));

					System.out.println(getScene());
					getScene().addGameObjectToScene(object[j][i]);



				}


			}

			objectList.add(object);


		}




	}


	public void objectListTransform(int index, Transform transform) {


		/*
		무조건 직사각형(선형, 크기 = 가로 x 세로 로 정의되는 2차 선형 배열 데이터)
		 */
		int gObj1, gObj2;
		gObj1 = object.length;
		gObj2 = object[0].length;
		for(GameObject gameobject[][] : objectList) {



		}
	}

	public GameObject getObject(int x, int y)  {
		return this.object[x][y];
	}


	public String mapFile(int id) {

		map = Integer.toString(id);

		return this.map;

	}



}
