package main;

import main.assets.Shader;
import main.assets.Texture;
import main.assets.components.SpriteRenderer;
import main.util.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class MainScene extends Scene {


    private String vertexShaderSource = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}\n";
    private String fragmentShaderSource = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    color = fColor;\n" +
            "}\n";

    private int vertexID, fragmentID, shaderProgram;

    private float[] vertexArray = {
            // position               // color                  // UV Coordinates
            100f, 0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1, 1, // Bottom right 0
            0f, 100f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0, 0, // Top left     1
            100f, 100f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1, 0, // Top right    2
            0f, 0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0, 1  // Bottom left  3
    };

    // IMPORTANT: Must be in counter-clockwise order
    private int[] elementArray = {
            /*
                    x        x
                    x        x
             */
            2, 1, 0, // Top right triangle
            0, 1, 3 // bottom left triangle
    };


    private int vaoID, vboID, eboID;


    private Shader defaultShader;
    private Texture texture;

    GameObject testObj;

    private boolean firstTime = false;

    public MainScene() {


    }

    @Override
    public void init(){

        this.camera = new Camera(new Vector2f());

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset *2);
        float totalHeight = (float)(300 - yOffset *2);
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight / 100.0f;

        for (int x=0; x<100; x++) {
            for (int y=0; y < 100; y++) {

                float xPos = xOffset + (x * sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject go = new GameObject("Obj" + x + "" + y, new Transform(new Vector2f(xPos, yPos),
                        new Vector2f(sizeX, sizeY)));
                go.addComponent(new SpriteRenderer(new Vector4f(xPos/totalWidth, yPos/totalHeight,1,1)));
                this.addGameObjectToScene(go);

            }
        }


        loadResources();


}

    private void loadResources() {
        AssetPool.getShader("src/main/assets/default.glsl");

    }

    @Override
    public void update(float dt) {


        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();


    }

}


