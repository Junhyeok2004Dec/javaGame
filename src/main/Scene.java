package main;

import main.assets.Camera;

public abstract class Scene {

    public Camera camera;

    public Scene() {

    }

    protected abstract void init();

    public abstract void update(float dt);

}
