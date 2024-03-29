package main.system.util.renderer;

import main.system.util.components.Sprite;
import main.system.util.components.Texture;
import main.system.util.components.Component;
import main.system.util.components.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {


    private Vector4f color = new Vector4f(1,1,1,1);
    private Sprite sprite = new Sprite();


    //image Transform
    private transient Transform lastTransform;
    private transient boolean isDirty = false;

    private Texture texture;


    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite();
        this.isDirty = true;
    }

    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
        this.isDirty = true;
    }

    public SpriteRenderer(Texture texture) {
        this.texture = texture;
        this.color = new Vector4f(1, 0.7f, 1, 1);
    }

    @Override
    public void start() {

        this.lastTransform = gameObject.transform.copy();
    }


    @Override
    public void update(float dt) {


        if (!this.lastTransform.equals(this.gameObject.transform))
        {
            this.gameObject.transform.copy(this.lastTransform);
            isDirty = true;
        }

    }

    public Vector4f getColor() {

        return this.color;
    }

    public Texture getTexture() {
        return sprite.getTexture();

    }

    public Vector2f[] getTexCoords() {

        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.isDirty = true;

    }

    public void setColor(Vector4f sprite) {
        if (!this.color.equals(color )) {
            this.isDirty = true;
            this.color.set(color);
        }
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public boolean setClean() {
        return this.isDirty = false;
    }


}