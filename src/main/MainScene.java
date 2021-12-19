package main;

import main.assets.Camera;
import main.assets.Shader;
import main.util.Time;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

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
            //pos                       //color                     //UV
            100.5f, -22.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1, 0,    // right bottom 0
            -100.5f, 22.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1, 1,    // left top 1
            100.5f, 22.5f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1, 1,    // right top 2
            -100.5f, -22.5f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0, 0     // left bottom 3

    };


    private int[] elementArray = {
            /*
                1      2



                3      0
             */
            2, 1, 0, // topRight
            0, 1, 3 // btmLeft


    };


    private int vaoID, vboID, eboID;


    private Shader defaultShader;

    public MainScene() {


    }

    @Override
    public void init() {


        this.camera = new Camera(new Vector2f());

        defaultShader = new Shader("src/main/assets/default.glsl");
        defaultShader.compile();
        /*

        ============================================================
        ||  Generate VAO VBO EBO buffer objs, and send to GPU     ||
        ============================================================
         */
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // float buffer of vertices //
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();


        // float VBO upload -> vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Indices & Upload

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add vertex pointers

        int positionsSize = 3;
        int colorSize = 4;
        int uvSize = 2;

        int vertexSizeBytes = (positionsSize + colorSize + uvSize) * Float.BYTES;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * Float.BYTES);
        glEnableVertexAttribArray(1);


        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionsSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);

    }

    @Override
    public void update(float dt) {


        camera.position.x -= dt * 22.0f;
        camera.position.y -= dt * 16.2f;


        defaultShader.use();
        defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4f("uView", camera.getViewMatrix());
        defaultShader.uploadFloat("uTime", Time.getTime());


        // VAO bind
        glBindVertexArray(vaoID);

        //vertex attribute
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        //unbind ALL
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);


        defaultShader.detach();
    }

}


