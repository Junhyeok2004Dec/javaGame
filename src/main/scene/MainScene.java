package main.scene;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import main.Input.KeyListener;
import main.components.SpriteSheet;
import main.util.AssetPool;
import main.util.object.Camera;
import main.util.object.GameObject;
import main.util.world.MatrixObject;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MainScene extends Scene  {

    MatrixObject objectCreate = new MatrixObject();




    private int totalObj = 28;


    private SpriteSheet sprite;

    public MainScene() {


    }

    @Override
    public void init() {


        loadResources();

        System.out.println("mainscene");

        sceneNum = 0;
        this.camera = new Camera(new Vector2f(0, 0));



        //Gson Component


        Gson gson = new GsonBuilder().setPrettyPrinting()
                .create();


        System.out.println(gson.toJson("Hello?"));








    }

    private void loadResources() {


        AssetPool.getShader(shaderAddress);

        AssetPool.addSpritesheet(spriteSheetAddress,
                new SpriteSheet(AssetPool.getTexture(spriteSheetAddress),
                        16, 16, 28, 0));



        objectCreate.worldGen();


        // ResourceManager 참고




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

    @Override
    public void imgui() {
        ImGui.begin("MapScene window");
        ImGui.text("text");
        ImGui.end();


    }


}


