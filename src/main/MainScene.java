package main;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

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
        //pos                       //color
    0.5f, -0.5f, 0.0f,          1.0f, 0.0f, 0.0f, 1.0f, // right bottom 0
    -0.5f, 0.5f, 0.0f,           0.0f, 1.0f, 0.0f, 1.0f, // left top 1
    0.5f, 0.5f, 0.0f,           0.0f, 0.0f, 1.0f, 1.0f, // right top 2
    -0.5f, -0.5f, 0.0f,         1.0f, 1.0f, 0.0f, 1.0f, // left bottom 3

    };


    private int[] elementArray = {
            /*
                1      2



                3      0
             */
            2,1,0, // topRight
            0,1,3 // btmLeft



    };


    private int vaoID, vboID, eboID;

    public MainScene() {
        System.out.println("MainWindow");

    }

    @Override
    public void init() {

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(vertexID, vertexShaderSource);
        glCompileShader(vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }





        //load & compile the vertex Shader //
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        // Send the data of the shader info to GPU
        glShaderSource(fragmentID, fragmentShaderSource);
        glCompileShader(fragmentID);

        //Err check at compile
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);

            System.out.println("ERR :: 'defaultShader.glsl'\n\t");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }


        // Link Shader
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        //check err when link

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);

            System.out.println("ERR : 'default.glsl'\n\f");
            System.out.println(glGetProgramInfoLog(fragmentID, len));

        }



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
        glBufferData(GL_ARRAY_BUFFER,vertexBuffer,GL_STATIC_DRAW);

        // Indices & Upload

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add vertex pointers

        int positionsSize = 3;
        int colorSize = 4;
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionsSize + colorSize) * floatSizeBytes;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * floatSizeBytes);
        glEnableVertexAttribArray(1);

    }

    @Override
    public void update(float dt) {

        System.out.println("" + (1.0f / dt) + "FPS");
        //shaderProgram
        glUseProgram(shaderProgram);

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
        glUseProgram(0);
    }

}


