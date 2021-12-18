package main;

import java.awt.event.KeyEvent;

public class MapScene extends Scene {

    public MapScene() {
        System.out.println("MapScene 플레이어 월드 구현");
    }


    private boolean changingScene = false;
    private float timeToChangeScene = 1.0f;

    @Override
    public void init() {

    }
    @Override
    public void update(float dt) {

        System.out.println("" + (1.0f / dt) + "FPS");

        // todo :: Exit input / scene change to previous scene.




        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_ENTER)) {
            changingScene = true;
        }

        if (changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 1.0f;
            Window.get().b -= dt * 5.0f;

        } else if (changingScene) {

        }
    }
}
