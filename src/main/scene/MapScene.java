package main.scene;

public class MapScene extends Scene {

    public MapScene() {
        System.out.println("MapScene 플레이어 월드 구현");
    }


    private float timeToChangeScene = 1.0f;

    @Override
    public void init() {


    }




    @Override
    public void update(float dt) {

        System.out.println("" + (1.0f / dt) + "FPS");


        if (timeToChangeScene > 0) {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 1.0f;
            Window.get().b -= dt * 5.0f;

        }

    }
}
