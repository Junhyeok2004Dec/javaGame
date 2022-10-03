


package main.system.util.components;

import java.util.ArrayList;
import java.util.List;

public class GameObject {


    private String name;

    private List<Component> components;
    public Transform transform;
    private int zIndex;
    private int groupID;




    public GameObject(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = new Transform();
        this.zIndex = 0;
        this.groupID=0;
    }

    public Transform getTransform() {
        return transform;
    }

    // getTransform().getXpos -> get component x pos
    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;
        this.groupID = 0;
    }

    public GameObject(String name, Transform transform, int zIndex, int groupID) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;
        this.groupID = groupID;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting component.";
                }
            }
        }

        return null;
    }





    @Override
    public String toString() {
        return "GameObject{" +
                "name='" + name + '\'' +
                ", components=" + components +
                ", transform=" + transform +
                ", zIndex=" + zIndex +
                ", groupID" + groupID +
                '}';
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for (int i=0; i < components.size(); i++) {
            Component c = components.get(i);
            if (componentClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c) {
        c.gameObject = this;
        this.components.add(c);

    }

    public void update(float dt) {
        for (int i=0; i < components.size(); i++) {
            components.get(i).update(dt);
        }
    }

    public void start() {
        for (int i=0; i < components.size(); i++) {
            components.get(i).start();
        }
    }

    public int zIndex() {
        return this.zIndex;
    }





    }

