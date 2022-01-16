package main;

import main.Input.KeyListener;
import main.assets.components.SpriteRenderer;
import main.assets.components.SpriteSheet;
import main.util.AssetPool;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene {




    private int totalObj = 28;


    private SpriteSheet sprite;

    public MainScene() {


    }

    @Override
    public void init() {

        sceneNum = 0;


        loadResources();

        this.camera = new Camera(new Vector2f(0, 0));


        sprite = AssetPool.getSpritesheet("src/main/assets/images/spritesheet.png");

        GameObject[] objects = new GameObject[totalObj];






        for(int i = 0; i < totalObj; i++) {

            objects[i] = new GameObject("AutoObject" + i,

                    new Transform(new Vector2f(16*i, 16*i),
                            new Vector2f(16 , 16))
                    , 1

                    );
            objects[i].addComponent(new SpriteRenderer(sprite.getSprite(i)));
            this.addGameObjectToScene(objects[i]);


        }



    }

    private void loadResources() {
        AssetPool.getShader("src/main/assets/default.glsl");

        AssetPool.addSpritesheet("src/main/assets/images/spritesheet.png",
                new SpriteSheet(AssetPool.getTexture("src/main/assets/images/spritesheet.png"),
                        16, 16, 28, 0));

    }


    //sprite animatione

    private int spriteIndex = 0;
    private float spriteAnimationTime = 0.01f;
    private float spriteAnimationTimeLeft = 0.0f;

    @Override
    public void update(float dt) {



        spriteAnimationTimeLeft -= dt;


        if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 1000f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 1000f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_UP)) {
            camera.position.y += 1000f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 1000f * dt;
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();


    }



}


