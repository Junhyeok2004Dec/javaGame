package main.player;

public class Player {


    public int level, xp, maxXP, id;

    public String PlayerName;


    public void defaultSettings() {

        level = 1;
        xp = 0;
        maxXP = 10;
        id = -1;
        PlayerName = "[\ti\t] this is Default Player, something 'll broken.";
    }

    public Player playerData(int id, int level, int xp, int maxXP, String name) {
        this.level = level;
        this.xp = xp;
        this.maxXP = maxXP;
        this.PlayerName = name;

        return this;

    }



    public String getPlayerName()
    {
        return getPlayerName();

    }

}
