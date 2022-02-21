package main.system.player;

public class Player implements DefaultEntity {


    public int level, xp, maxXP, id;

    public String PlayerName;


    public void defaultSettings() {

        this.level = 1;
        this.xp = 0;
        this.maxXP = levelCalc(level);
        this.id = -1;
        this.PlayerName = "[\ti\t] this is Default Player, something 'll broken.";
    }

    public Player Player(int id, int level, int xp, String name) {
        this.id = id;
        this.level = level;
        this.xp = xp;
        this.maxXP = levelCalc(level);
        this.PlayerName = name;

        return this;



    }

    public String getName() {
        return this.PlayerName;
    }


    public int getID() {
        return this.id;
    }

    public int getLevel() {
        return this.level;
    }

    public int getXp() {
        return this.xp;
    }

    public int getMaxXP() {
        return this.maxXP;
    }

    public int levelCalc(int level) {

        return (int) ( Math.pow(10,level) + Math.pow(2,level-80) + (level * (level + 30)));

    }


}
