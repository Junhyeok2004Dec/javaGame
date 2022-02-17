package main.world;

import main.GameObject;
import main.Scene;
import main.Transform;
import main.assets.components.SpriteRenderer;
import main.assets.components.SpriteSheet;
import main.util.AssetPool;
import org.joml.Vector2f;

import java.util.ArrayList;

public class MatrixObject implements RegisterMapData {

    private String map;




    // 2차원 배열 설정하였음. object에는 2개의 인자가 있으며, f:R^2 -> R인 스칼라 함수임. 무조건 하나로 매핑됨.

    ArrayList<ArrayList<GameObject>> object = new ArrayList<>();


    private int totalObjCount = 28;


    private int blockSize = 32;



    private SpriteSheet sprite;



    public MatrixObject() {


    }

    public void worldGen() {

        System.out.println("worldgen");

        DataManager mapGen = new DataManager();





        AssetPool.getShader("src/main/assets/default.glsl");

        AssetPool.addSpritesheet("src/main/assets/images/spritesheet.png", new SpriteSheet(AssetPool.getTexture("src/main/assets/images/spritesheet.png"),
                blockSize, blockSize, totalObjCount, 0));


        sprite = AssetPool.getSpritesheet("src/main/assets/images/spritesheet.png");



        for(int id : mapID)
        {
            mapGen.Data("src/main/data/map/" + mapFile(id) + ".block");

        }


        // WorldData 분배




        //worldData 분배 방식

        // a(11) a(12) a(13) .. a(1  width)
        // a(21) ...
        // ..
        // a(height 1) ...      a(height width)

        // for matrix system


        int width = mapGen.getWidth();
        int height = mapGen.getHeight();


        object.add(null);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                object.get(i).add(j ,new GameObject("Block" + j + height * i, new Transform(new Vector2f(blockSize * i, blockSize * j), new Vector2f(blockSize, blockSize)), 1));


                object.get(i).get(j).addComponent(new SpriteRenderer(sprite.getSprite(mapGen.getData(j,i) /* i행 j열 */ )));


                getScene().addGameObjectToScene(object.get(j).get(i));




            }


        }




    }
    public Scene getScene() {
        return this.getScene();
    }

    public String mapFile(int id) {

        map = Integer.toString(id);

        return this.map;

    }




}
