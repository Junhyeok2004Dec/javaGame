package main.util.world;

import assets.RegisterMapData;

public class MatrixObject implements RegisterMapData {

    private String map;



    public MatrixObject() {


    }

    public void worldGen() {

        System.out.println("worldgen");



        DataManager mapGen = new DataManager();



        for(int id : mapID)
        {
            mapGen.Data(mapAddress + "/" +  mapFile(id) + ".block");

        }


        // WorldData 분배




        //worldData 분배 방식

        // a(11) a(12) a(13) .. a(1  width)
        // a(21) ...
        // ..
        // a(height 1) ...      a(height width)

        // for matrix system



/*
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {






                object[j][i] = new GameObject("Block" + j + height * i, new Transform(new Vector2f(blockSize * i, blockSize * j), new Vector2f(blockSize * i, blockSize* j)), 1);

                SpriteRenderer objSpriteRenderer = new SpriteRenderer();
                Sprite objSprite = new Sprite();

                objSprite.setTexture(AssetPool.getTexture(spriteSheetAddress));
                objSpriteRenderer.setSprite(objSprite);

                object[j][j].addComponent(objSpriteRenderer);


                getScene().addGameObjectToScene(object[j][i]);


                // imgui Component
                getScene().activeGameObject = object[j][i];
                System.out.println(gson.toJson(objSprite));






            }


        }


*/

    }




    public String mapFile(int id) {

        map = Integer.toString(id);

        return this.map;

    }




}
