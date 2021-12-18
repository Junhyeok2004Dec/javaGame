package main;

public abstract class Scene {

    public Scene() {

    }

    protected abstract void init();

    public abstract void update(float dt);

}
