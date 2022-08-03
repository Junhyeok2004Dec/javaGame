package main.util.object;

import main.util.AssetPool;
import main.util.components.GameObject;
import main.util.components.Transform;
import org.joml.Vector2f;

import java.util.ArrayList;

public class Block {




    /*

    블럭 구성 요소

    블럭 id, name, transform(위치,크기)
    => 블럭 Component
    투과가능성(플레이어)
    투명도(블럭의 alpha channel 값)



    블럭의 고유 사항
    1. 맵에 직접적으로 작용하는가?
    2. 설치/파괴가 가능한가?


     */

    private int id;
    private String name;
    private Transform transform;

    ArrayList<GameObject> blockList = new ArrayList<>();



    public Block block() {
        return this;
    }

    public void createBlock(Transform transform) {
        this.blockList.add(new GameObject("Block " + id , transform, 1));
    }


    public void createBlock(float width, float height, float xpos, float ypos, int zindex) {


        this.blockList.add(new GameObject("Block" + this.blockList.size() , new Transform(
                new Vector2f(width, height) , new Vector2f(xpos, ypos)
        ), zindex));

        this.blockList.get(this.id);
    }

//TODO :: 블럭리스트 arraylist에도 매칭하라.


}
