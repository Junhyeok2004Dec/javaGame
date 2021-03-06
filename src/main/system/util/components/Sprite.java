package main.system.util.components;

import org.joml.Vector2f;

public class Sprite {

    private Texture texture = null;
    private Vector2f[] texCoords =


            {
            new Vector2f(1, 1),
            new Vector2f(1, 0),
            new Vector2f(0, 0),
            new Vector2f(0, 1)

            };


    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTexCoords() {
        return this.texCoords;
    }

    public void setTexCoords(Vector2f[] texCoords) {
        this.texCoords = texCoords;
    }

    public void setTexture(Texture tex) {
        this.texture = tex;
    }
}
