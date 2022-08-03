package main.scene;

import assets.RegisterMapData;
import main.system.util.renderer.Renderer;
import main.system.util.components.Camera;
import main.system.util.components.GameObject;

import java.util.ArrayList;
import java.util.List;



public abstract class Scene implements RegisterMapData {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();
    public GameObject activeGameObject = null;


    public static int sceneNum = 1;


    public Scene() {

    }

    public void init() {

    }

    public void start() {


        for (GameObject go : gameObjects) {

            go.start();
            this.renderer.add(go);


        }

        isRunning = true;

    }

    public void addGameObjectToScene(GameObject go) {

        if (!isRunning) {

            gameObjects.add(go);


        }else {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);

        }

    }


    public abstract void update(float dt);

    public Camera camera(){
        return this.camera;
    }

    public Scene getScene() {
        return this;
    }




}
