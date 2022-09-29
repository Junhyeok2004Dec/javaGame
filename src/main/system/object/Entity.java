package main.system.object;

import main.system.util.components.GameObject;

import java.util.ArrayList;

public class Entity implements DefaultEntity{




    private int id,hp,atk,def;
    private String name = "";
    ArrayList<Entity> entities = new ArrayList<>();


    ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void Entity(int id, int hp, int atk, int def, String name) {

        this.id = id;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.name = name;

    }


    public void createEntity(Entity entity) {





    }





    public void defaultSettings() {


        id = -2;
        hp = 576000;
        def = 10;
        name = "err";



    }

}
