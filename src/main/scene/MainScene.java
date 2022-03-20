package main.scene;

import imgui.ImGui;
import main.Input.KeyListener;
import main.components.SpriteSheet;
import main.renderer.SpriteRenderer;
import main.util.AssetPool;
import main.util.object.Camera;
import main.util.object.GameObject;
import main.util.object.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene  {

    private GameObject object1;
    private SpriteSheet sprites;

    public MainScene() {

    }

    @Override
    public void init() {


        loadResources();

        System.out.println("mainscene");

        sceneNum = 0;
        this.camera = new Camera(new Vector2f(0, 0));

        ObjectManagerTemp();










    }

    private void ObjectManagerTemp() {




        int blockSize = 64;
        object1 = new GameObject("main",  new Transform(new Vector2f(blockSize, blockSize), new Vector2f(blockSize, blockSize)), 1);
        SpriteRenderer objSpriteRenderer = new SpriteRenderer();
        objSpriteRenderer.setColor(new Vector4f(1,0,0,1));

        object1.addComponent(objSpriteRenderer);

        Window.getScene().addGameObjectToScene(object1);
        Window.getScene().activeGameObject = object1;


    }

    private void loadResources() {


        AssetPool.getShader(shaderAddress);

        AssetPool.addSpritesheet(spriteSheetAddress,
                new SpriteSheet(AssetPool.getTexture(spriteSheetAddress),
                        16, 16, 28, 0));






        // ResourceManager 참고






    }


    //sprite animatione

    @Override
    public void update(float dt) {





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

    @Override
    public void imgui() {
        ImGui.begin("MapScene window");
        ImGui.text("text");
        ImGui.end();


    }


}


