package main.assets;

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    //set shader id
    private int shaderProgramID;
    private boolean isUsed = false;


    private String vertexSource;
    private String fragmentSource;
    private String filepath;

    public Shader(String filepath) {

        this.filepath = filepath;
        try {


            //default.glsl 분석 코드
            String source = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] splitString;
            splitString = source.split("(#type)( )+([a-zA-Z]+)");


            // First pattern 찾기 // #type 이후 'pattern' 찾기
            int index = source.indexOf("#type") + 6; // default.glsl text split
            int eol = source.indexOf(("\r\n"), index);
            String firstPattern = source.substring(index, eol).trim();


            // Second pattern // #type 이후 'pattern'
            index = source.indexOf("#type", eol) + 6;
            eol = source.indexOf("\r\n", index);
            String secondPattern = source.substring(index, eol).trim();


            if (firstPattern.equals("vertex")) {
                vertexSource = splitString[1];
            } else if (firstPattern.equals("fragment")) {
                fragmentSource = splitString[1];
            } else {
                throw new IOException("Token is unexpected" + firstPattern);
            }


            if (secondPattern.equals("vertex")) {
                vertexSource = splitString[2];
            } else if (secondPattern.equals("fragment")) {
                fragmentSource = splitString[2];
            } else {
                throw new IOException("Token is unexpected" + secondPattern);
            }


        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Error :: Unable open file for shader  " + filepath + "";

        }


        System.out.println(vertexSource);
        System.out.println(fragmentSource);

    }


    public void compile() {


        int vertexID, fragmentID;

       /*


        ====================================

        compile and link shaders

        ====================================

         */
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(vertexID, vertexSource);
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
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        //Err check at compile
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);

            System.out.println("ERR :: '" + filepath + "'\n\t Linking of shaders is failed // FRAGMENT");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }


        // =============
        //  Link shader
        // =============
        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);

        //check err when link

        success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);

            System.out.println("ERR : '" + filepath + "'\n\f Linking of shaders is failed // LINKING");

            System.out.println(glGetProgramInfoLog(fragmentID, len));

        }


    }

    public void use() {
        if (!isUsed) {
            glUseProgram(shaderProgramID);
            isUsed = true;
        }
    }

    public void detach() {
        glUseProgram(0);
        isUsed = false;

    }


    public void uploadMat4f(String varName, Matrix4f matrix4f) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        matrix4f.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }
    public void uploadMat3f(String varName, Matrix3f matrix3f) {

        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        matrix3f.get(matBuffer);
        glUniformMatrix3fv(varLocation, false, matBuffer);

    }
    public void uploadVec4f(String varName, Vector4f vector) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        glUniform4f(varLocation, vector.x, vector.y, vector.z, vector.w);
    }

    public void uploadVec3f(String varName, Vector3f vector) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        glUniform3f(varLocation, vector.x, vector.y, vector.z);
    }
    public void uploadVec2f(String varName, Vector2f vector) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        glUniform2f(varLocation, vector.x, vector.y);
    }


    public void uploadFloat(String varName, float val) {

        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        glUniform1f(varLocation, val);
    }

    public void uploadInt(String varName, int val) {

        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();

        glUniform1i(varLocation, val);
    }


}
