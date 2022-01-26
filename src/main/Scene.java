package main;

import main.assets.Renderer;
import main.world.WorldGen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// todo :: fetch the Scene and worldmap
// main.world.World



public abstract class Scene {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public static int sceneNum = 1;


    int obj = 28;



    private String[] dataSplit;




    public Scene() {

    }

    public void init() {

    }

    public void start() {


        for (GameObject go : gameObjects) {

            go.start();
            this.renderer.add(go);


        }

        isRunning = true;

    }

    public void addGameObjectToScene(GameObject go) {

        if (!isRunning) {

            gameObjects.add(go);


        }else {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);

        }

    }


    public abstract void update(float dt);

    public Camera camera(){
        return this.camera;
    }

    public void mapData(String filepath) {

        WorldGen worldMap = new WorldGen();

        try {



            String data = new String(Files.readAllBytes(Paths.get(filepath)));
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

                worldMap.worldGen(mapWidth, mapHeight, obj);






            }










        } catch (IOException e) {



            System.out.println("월드 데이터가 손상/손실되었습니다. 맵데이터 재설치가 요구됩니다. \n 임의의 변형은 오류를 가져올 수 있습니다.");


            e.printStackTrace();
        }

    }



}
