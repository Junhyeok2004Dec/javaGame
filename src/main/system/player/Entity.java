package main.system.player;

public class Entity implements DefaultEntity{


    private boolean isAggersive;
    private int id,hp,atk,def;
    private String name = "";

    public Entity Entity(int id, int hp, int atk, int def, boolean isAggersive, String name) {

        this.isAggersive = isAggersive;
        this.id = id;
        this.hp = hp;
        this.atk = atk;
        this.def = def;

        this.name = name;

        return this;


    }





    public void defaultSettings() {


        isAggersive = false;
        id = -2;
        hp = 576000;
        def = 10;
        name = "err";






    }

}
