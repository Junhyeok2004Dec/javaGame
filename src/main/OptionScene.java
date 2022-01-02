package main;

import main.Input.KeyListener;

import java.awt.event.KeyEvent;

public class OptionScene extends Scene{


    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public OptionScene() {
        System.out.println("Program Setting Scene");
    }

    @Override
    public void init() {

    }


    @Override
    public void update(float dt) {

        System.out.println("" + (1.0f / dt) + "FPS");

        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_ENTER)){
            changingScene = true;
        }

        if (changingScene && timeToChangeScene > 0)
        {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 1.0f;
            Window.get().b -= dt * 5.0f;

        } else if (changingScene)
        {

        }
    }
}
