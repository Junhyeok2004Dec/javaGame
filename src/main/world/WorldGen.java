package main.world;

import main.GameObject;
import main.Scene;
import main.Transform;
import main.assets.components.SpriteRenderer;
import main.assets.components.SpriteSheet;
import main.util.AssetPool;
import org.joml.Vector2f;

public class WorldGen{


    private int totalObjCount = 128;




    private byte[] tiles;
    private int width,height;


    protected SpriteSheet sprite;
    protected GameObject[] objects = new GameObject[totalObjCount];




    Scene scene = new Scene() {
        @Override
        public void update(float dt) {

        }
    };

    public void worldGen(int width, int height) {
        this.width = width;
        this.height = height;

        AssetPool.addSpritesheet("src/main/assets/images/asciiA.bmp", new SpriteSheet(AssetPool.getTexture("src/main/assets/images/ascii.bmp"),
                32,32,totalObjCount,0));

        for (int i = 0; i < totalObjCount; i++) {

            objects[i] = new GameObject("MapBlockID" + i,

                    new Transform(new Vector2f(32*i, 32* Math.abs(i / 32)),
                            new Vector2f(width, height)), 1);


            objects[i].addComponent(new SpriteRenderer(sprite.getSprite(i)));
            scene.addGameObjectToScene(objects[i]);
        }

    }


}
