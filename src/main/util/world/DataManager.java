package main.util.world;

import main.scene.Scene;
import main.util.renderer.FontRenderer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {




    private int width, height;
    private  int xPos, yPos;
    ArrayList<ArrayList<Integer>> layout = new ArrayList<>();
    private String data;
    private String[] dataSplit;

    public int getWidth() {
        return
                this.width;
    }

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
       return this.yPos;

    }

    public int getHeight() {
        return this.height;
    }

    // World GameObject Data
    public void Data(String filepath) {


        // Log log = new Log();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {


            while ((data = bufferedReader.readLine()) != null) {


                ArrayList<Integer> row = new ArrayList<>();


                dataSplit = data.trim().split("\\s");

                xPos = Integer.parseInt(dataSplit[0]);
                yPos = Integer.parseInt(dataSplit[1]);

                for (String str : dataSplit) {

                    if (!str.isEmpty()) {
                        int id = Integer.parseInt(str) + 2;
                        row.add(id);
                    }
                }

                layout.add(row);

                this.width = layout.get(0).size();
                this.height = layout.size() -1 ;
                //exit
                if (data.isEmpty()) {
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

    public void loadResources(String shader, String image, int width, int height, int size) {


    }


    public Scene Scene() {
        return this.Scene();
    }
}
