package main.world;

import main.GameObject;
import main.Scene;
import main.assets.components.FontRenderer;
import main.assets.components.SpriteSheet;
import main.player.Player;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {


    private int window = Scene.sceneNum;

    private int objCount = 256;

    private int count;
    private String data;


    private SpriteSheet sprite;
    protected GameObject[] objects = new GameObject[objCount];





    private String[] dataSplit;
    private int obj = 22;



    MatrixObject gen = new MatrixObject();







    // Player Data
    public void Data(String filepath, Player player)
    {
        try {

            data = new String(Files.readAllBytes(Paths.get(filepath)));
            dataSplit("\\s+");





        } catch (Exception e) {

        }
    }

    // World GameObject Data
    public void Data(String filepath, String shader, String texture) {

        try {



            data = new String(Files.readAllBytes(Paths.get(filepath)));
            dataSplit = data.split("\\s+");


            int mapWidth = Integer.parseInt(dataSplit[0]);
            int mapHeight = Integer.parseInt(dataSplit[1]);








            int index = data.indexOf("#start");

            int eol = data.indexOf(("#end"), index);

            System.out.println(mapHeight);
            System.out.println(mapWidth);
            System.out.println(obj);
            System.out.println(dataSplit.length);

            for (int i =0; i < dataSplit.length; i++) {

                gen.worldGen(mapWidth, mapHeight, shader, texture);






            }










        } catch (IOException e) {



            System.out.println("월드 데이터가 손상/손실되었습니다. 맵데이터 재설치가 요구됩니다. \n 임의의 변형은 오류를 가져올 수 있습니다.");
            e.printStackTrace();
        }

    }

    public void loadResources(FontRenderer font, String text, Color color) {

    }
    public void loadResources(String shader, String image, int width, int height, int size)
    {




    }


    public Scene Scene() {
        return this.Scene();
    }
}
