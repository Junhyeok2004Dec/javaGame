package main.world;

import main.Scene;
import main.assets.components.FontRenderer;
import main.player.Player;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataManager {


    /*
     hex value, 자리수 상관 없음
     Version 0.01
    */





    private String data;




    int width, height;


    private String[] dataSplit;





    ArrayList<ArrayList<Integer>> layout = new ArrayList<>();




    // Player Data
    public void playerData(String filepath)
    {
        int id;
        int level;
        int xp;
        String name;

        try {



            data = new String(Files.readAllBytes(Paths.get(filepath)));
            dataSplit = data.split("\\s+");



            // PlayerData


            /*
            id
            level
            xp
            Name
             */
            id = Integer.parseInt(dataSplit[0]);
            level = Integer.parseInt(dataSplit[1]);
            xp = Integer.parseInt(dataSplit[2]);

            name = dataSplit[3];

            Player player = new Player();

            player.id = id;
            player.level = level;
            player.xp = xp;
            player.PlayerName = name;













        } catch (Exception e) {

        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // World GameObject Data
    public void Data(String filepath) {




        // Log log = new Log();




        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {


            while((data = bufferedReader.readLine()) != null) {



                ArrayList<Integer> row = new ArrayList<>();

                dataSplit = data.trim().split("\\s");

                for(String str : dataSplit)
                {

                    if (!str.isEmpty())
                    {
                        int id = Integer.parseInt(str);
                        row.add(id);
                    }
                }

                layout.add(row);

                this.width = layout.get(0).size();
                this.height = layout.size();
                //exit
                if (data.isEmpty())
                {
                    continue;
                }


            }










        } catch (IOException e) {



            System.out.println("월드 데이터가 손상/손실되었습니다. 맵데이터 재설치가 요구됩니다. \n 임의의 변형은 오류를 가져올 수 있습니다.");
            e.printStackTrace();
        }

    }

    public int getData(int row, int column) {

        return this.layout.get(row).get(column);
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
