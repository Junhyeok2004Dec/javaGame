package main.scene;

import main.system.util.components.Camera;
import main.system.util.components.SpriteSheet;
import main.system.util.AssetPool;
import org.joml.Vector2f;

public class OptionScene extends Scene {


    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public OptionScene() {
        System.out.println("Program Setting Scene");
    }

    @Override
    public void init() {

        sceneNum = 99;
        load();
        this.camera = new Camera(new Vector2f(0,0));
    }

    private void load() {


        AssetPool.getShader(shaderAddress);

        AssetPool.addSpritesheet(spriteSheetAddress, new SpriteSheet(AssetPool.getTexture(spriteSheetAddress), 16, 16, 28, 0));

    }

    @Override
    public void update(float dt) {

        System.out.println("" + (1.0f / dt) + "FPS");

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
