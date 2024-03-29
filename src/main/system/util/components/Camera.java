package main.system.util.components;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;


    float multiplier = 1.0f;
    float multiplierX = 1.0f;
    float multiplierY = 1.0f;


    public Vector2f position;

    public Camera(Vector2f position) {
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }



    /*
    *
    * Resize Camera. 테스트 모드
    * **/
    public void adjustProjection() {
        projectionMatrix.identity();
        projectionMatrix.ortho(16.0f * multiplier, 32.0f * 16.0f * multiplier, 16.0f*multiplier, 32.0f * 7.0f * multiplier, 16.0f*multiplier, 100.0f * multiplier);
    }

    // TODO 확대하는 게 좀 이상함. 중앙으로 확대할 수 있도록 만들어라. :D
    //테스트
    public void enLargement(float multiplier) {
        this.multiplier *= multiplier;
    }

    public Matrix4f getViewMatrix() {
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -5.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
        this.viewMatrix.identity();
        viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.0f),
                cameraFront.add(position.x, position.y, 0.0f),
                cameraUp);

        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return this.projectionMatrix;
    }
}