package main;

import main.assets.components.SpriteRenderer;
import main.util.AssetPool;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene {
    public MainScene() {


    }

    @Override
    public void init(){

        this.camera = new Camera(new Vector2f(-250, 0));

        GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        obj1.addComponent(new SpriteRenderer(AssetPool.getTexture("src/main/assets/image/1.png")));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("SpongeBob!", new Transform(new Vector2f(155, 256), new Vector2f(256, 320)));
        obj2.addComponent(new SpriteRenderer(AssetPool.getTexture("src/main/assets/image/Spongebob.png")));
        this.addGameObjectToScene(obj2);
        loadResources();


}

    private void loadResources() {
        AssetPool.getShader("src/main/assets/default.glsl");

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


