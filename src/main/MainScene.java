package main;

import main.assets.components.SpriteRenderer;
import main.assets.components.SpriteSheet;
import main.util.AssetPool;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene {


    private GameObject obj1, obj2, obj3, obj4;
    private SpriteSheet sprite;

    public MainScene() {


    }

    @Override
    public void init() {


        loadResources();

        this.camera = new Camera(new Vector2f(0, 0));


        sprite = AssetPool.getSpritesheet("src/main/assets/images/spritesheet.png");


        obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)), 6);
        obj1.addComponent(new SpriteRenderer(sprite.getSprite(0)));


        obj2 = new GameObject("Object 2", new Transform(new Vector2f(-180, 180), new Vector2f(256, 256)), 0);
        obj2.addComponent(new SpriteRenderer(sprite.getSprite(11)));


        obj3 = new GameObject("Blend O1", new Transform(new Vector2f(-560, 320), new Vector2f(256, 256)), 1);
        obj3.addComponent(new SpriteRenderer(sprite.getSprite(25)));

        obj4 = new GameObject("Blend O2", new Transform(new Vector2f(-560, 500), new Vector2f(256, 256)), 2);
        obj4.addComponent(new SpriteRenderer(sprite.getSprite(26)));




        // Add the Component(Staged) at the Scene
        this.addGameObjectToScene(obj1);
        this.addGameObjectToScene(obj2);
        this.addGameObjectToScene(obj3);
        this.addGameObjectToScene(obj4);

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

        if (spriteAnimationTimeLeft <= 0) {
            spriteAnimationTimeLeft = spriteAnimationTime;
            spriteIndex++;
            if (spriteIndex > 27) {
                spriteIndex = 0;
            }
            obj1.getComponent(SpriteRenderer.class).setSprite(sprite.getSprite(spriteIndex)
            );


        }
        if (KeyListener.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.position.x += 1000f * dt;


        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.position.x -= 1000f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_UP)) {
            camera.position.y += 1000f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.position.y -= 1000f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_A)) {
            obj1.transform.position.x -= 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_D)) {
            obj1.transform.position.x += 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_W)) {
            obj1.transform.position.y += 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_S)) {
            obj1.transform.position.y -= 100f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
            obj3.transform.position.y += 444f * dt;
        } else if (KeyListener.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            obj4.transform.position.x -= 444f * dt;
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();


    }

}


