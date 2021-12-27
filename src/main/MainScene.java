package main;

import main.assets.components.SpriteRenderer;
import main.assets.components.SpriteSheet;
import main.util.AssetPool;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene {
    public MainScene() {


    }

    @Override
    public void init() {


        loadResources();

        this.camera = new Camera(new Vector2f(-250, 0));


        SpriteSheet sprite = AssetPool.getSpritesheet("src/main/assets/images/spritesheet.png");


        GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        obj1.addComponent(new SpriteRenderer(sprite.getSprite(0)));
        this.addGameObjectToScene(obj1);

    }

    private void loadResources() {
        AssetPool.getShader("src/main/assets/default.glsl");

        AssetPool.addSpritesheet("src/main/assets/images/spritesheet.png",
                new SpriteSheet(AssetPool.getTexture("src/main/assets/images/spritesheet.png"),
                        16, 16, 26, 0));

    }

    @Override
    public void update(float dt) {

        if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 100f * dt;
        }
        if (KeyListener.isKeyPressed(GLFW_KEY_UP)) {
            camera.position.y += 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 100f * dt;
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();


    }

}


