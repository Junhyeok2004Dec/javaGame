package main.world;

import main.GameObject;
import main.Scene;
import main.Transform;
import main.assets.components.SpriteRenderer;
import main.assets.components.SpriteSheet;
import main.util.AssetPool;
import org.joml.Vector2f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorldGen {



    private int totalObjCount = 256;


    private int blockSize = 32;



    private SpriteSheet sprite;
    protected GameObject[] objects = new GameObject[totalObjCount];



    public WorldGen() {
    }

    public void worldGen(int width, int height, int objectShift) {

        AssetPool.getShader("src/main/assets/default.glsl");

        AssetPool.addSpritesheet("src/main/assets/images/font.png", new SpriteSheet(AssetPool.getTexture("src/main/assets/images/font.png"),
                blockSize, blockSize, totalObjCount, 0));


        sprite = AssetPool.getSpritesheet("src/main/assets/images/font.png");


        //worldData 분배 방식

        // a(11) a(12) a(13) .. a(1  width)
        // a(21) ...
        // ..
        // a(height 1) ...      a(height width)

        // for matrix system


        // 주의사항 :: height 와 width 의 순서는 각각 i와 j로 매칭됩니다.
        // 가로를 j로 설정하였습니다. 세로는 j
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                objects[j + height * i] = new GameObject("MapBlock" + j + height * i,

                        new Transform(new Vector2f(blockSize * i, blockSize * j),
                                new Vector2f(blockSize, blockSize)), 1
                );

                objects[j + height * i].addComponent(new SpriteRenderer(sprite.getSprite(j + height * i))); // getSprite( '여기 안에 입력받은 data 넣을 것' )
                scene.addGameObjectToScene(objects[j + height * i]);


                System.out.println("object\t" + (j + height * i) + "is Created");


            }
        }


    }



}
