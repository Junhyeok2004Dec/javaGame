package main.scene;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import imgui.ImGui;
import main.Input.KeyListener;
import main.components.Sprite;
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

    private GameObject obj1, objtest;


    public MainScene() {

    }

    @Override
    public void init() {


        loadResources();



        sceneNum = 0;
        this.camera = new Camera(new Vector2f(0, 0));

        ObjectManagerTemp();










    }
    private void ObjectManagerTemp() {
        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100),
                new Vector2f(256, 256)), 2);

        SpriteRenderer render1 = new SpriteRenderer();
        render1.setColor(new Vector4f(1.0f, 0.0f, 0.0f, 0.5f));

        obj1.addComponent(render1);
        this.addGameObjectToScene(obj1);
        this.activeGameObject = obj1;

        GameObject obj2 = new GameObject("Object 2",
                new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 3);

        SpriteRenderer obj2SpriteRenderer = new SpriteRenderer();


        Sprite obj2Sprite = new Sprite();
        obj2Sprite.setTexture(AssetPool.getTexture(spriteSheetAddress));
        obj2SpriteRenderer.setSprite(obj2Sprite);
        obj2.addComponent(obj2SpriteRenderer);
        this.addGameObjectToScene(obj2);

        objtest = new GameObject("아싸 가오리", new Transform(new Vector2f(200,200), new Vector2f(128,128)), 1);


        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .generateNonExecutableJson()
                    .create();



            String serialized = gson.toJson(obj1);
            System.out.println(serialized);

            // 역직렬화




            /*
        GameObject obj = gson.fromJson(serialized, GameObject.class);
        System.out.println(obj);

             */
        } catch (JsonIOException e) {
            System.out.println(e + " // JsonIOExcept");
        } catch (Exception e) {

            System.out.println(e + "Unexcepted");
        }
    }



    private void ObjectManager() {





        /*
        int blockSize = 64;
        object1 = new GameObject("main",  new Transform(new Vector2f(blockSize, blockSize), new Vector2f(blockSize, blockSize)), 1);
        SpriteRenderer objSpriteRenderer = new SpriteRenderer();
        objSpriteRenderer.setColor(new Vector4f(1,0,0,1));

        object1.addComponent(objSpriteRenderer);

        this.addGameObjectToScene(object1);
        this.activeGameObject = object1;

        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Component.class, new Deserializer()).create();

        String ser = gson.toJson(object1);
        System.out.println(ser);


         */
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


