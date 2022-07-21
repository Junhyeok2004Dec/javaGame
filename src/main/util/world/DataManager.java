package main.util.world;

import main.scene.Scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {




    private int width, height;
    ArrayList<ArrayList<Integer>> layout = new ArrayList<>();
    private String data;
    private String[] dataSplit;

    public int getWidth() {
        return
                this.width;
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

                for (String str : dataSplit) {

                    if (!str.isEmpty()) {
                        int id = Integer.parseInt(str);
                        row.add(id);
                    }
                }

                layout.add(row);

                this.width = layout.get(0).size();
                this.height = layout.size();
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



    public Scene Scene() {
        return this.Scene();
    }
}
