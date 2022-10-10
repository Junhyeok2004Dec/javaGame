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

	// world
	ArrayList<GameObject[][]> objectList = new ArrayList<>();


	private final int totalObjCount = 256;


	private final int blockSize = 16;

	private GameObject[][] object;

	private SpriteSheet sprite;


	/**
	 *
	 * Using this Command for "Worldgen()", Load DataManager, and getAddress involves that mapFileAddress, and data.
	 *
	 */
	public void worldGen() {


		AssetPool.getShader(shaderAddress);

		DataManager mapGen = new DataManager();





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



					object[j][i] = new GameObject("Block" + j + height * i, new Transform(new Vector2f(blockSize * j, - blockSize * i), new Vector2f(blockSize, blockSize)), 0);
					object[j][i].addComponent(new SpriteRenderer(sprite.getSprite(mapGen.getData(i, j))));
					getScene().addGameObjectToScene(object[j][i]);


				}


			}

			objectList.add(object);


		}




	}


	public GameObject getObject(int x, int y)  {
		return this.object[x][y];
	}

	public GameObject[][] getObject() {
		return this.object;
	}

	public ArrayList<GameObject[][]> getObjectList() {
		return objectList;
	}

	public String mapFile(int id) {

		map = Integer.toString(id);

		return this.map;

	}



}
