package main;

import main.assets.Renderer;

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

        try {

            String data = new String(Files.readAllBytes(Paths.get(filepath)));
            dataSplit = data.split("\\s+");

            int index = data.indexOf("#start");
            int eol = data.indexOf(("#end"), index);

            String outputData = data.substring(index,eol);


            for (int i =0; i < dataSplit.length; i++) {

                // the Worldmap Tile data will loaded by file,
                // WorldGen.java is perform that functions.
                // TODO :: WorldGen.java check 2022 01 17 02:00


            }











            // 예시 //
            // #start{id :: 124}
            // <여기부터 인식>





        } catch (IOException e) {



            System.out.println("월드 데이터가 손상/손실되었습니다. 프로그램 재설치가 요구됩니다.");
            e.printStackTrace();
        }

    }



}
