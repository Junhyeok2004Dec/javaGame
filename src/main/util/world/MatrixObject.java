package main.util.world;

import assets.RegisterMapData;
import main.util.components.SpriteSheet;
import main.util.renderer.SpriteRenderer;
import main.util.AssetPool;
import main.util.object.GameObject;
import main.util.object.Transform;
import org.joml.Vector2f;

import static main.scene.Window.getScene;

public class MatrixObject implements RegisterMapData {

	private String map;


	// 2차원 배열 설정하였음. object에는 2개의 인자가 있으며, f:R^2 -> R인 스칼라 함수임. 무조건 하나로 매핑됨.


	private final int totalObjCount = 28;


	private final int blockSize = 16;


	private SpriteSheet sprite;




	public void worldGen() {

		System.out.println("worldgen");

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


			GameObject[][] object = new GameObject[width][height];



			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {



					object[j][i] = new GameObject("Block" + j + height * i, new Transform(new Vector2f(blockSize * mapGen.getyPos(), blockSize * mapGen.getxPos()), new Vector2f(blockSize, blockSize)), 1);

					object[j][i].addComponent(new SpriteRenderer(sprite.getSprite(mapGen.getData(i, j))));

					System.out.println(getScene());
					getScene().addGameObjectToScene(object[j][i]);



				}


			}
		}




	}


	public String mapFile(int id) {

		map = Integer.toString(id);

		return this.map;

	}



}
