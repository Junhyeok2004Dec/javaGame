package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();


    public Scene() {

    }

    public void init() {

    }

    public void start() {


        for (GameObject go : gameObjects) {

            go.start();


        }

    }

    public void addGameObjectToScene(GameObject go) {

        if (!isRunning) {

            gameObjects.add(go);


        }else {
            gameObjects.add(go);
            go.start();

            isRunning = true;
        }

    }


    public abstract void update(float dt);

    public Camera camera(){
        return this.camera;
    }

}
