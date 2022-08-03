package main.system.util.components;

import org.joml.Vector2f;

public class Collision {


    float dx , dy;

    public void calcDis(GameObject gameObject, GameObject collisionObject) {
        this.dx = Math.abs(gameObject.transform.getXpos() - collisionObject.transform.getXpos());
        this.dy = Math.abs(gameObject.transform.getYpos() - collisionObject.transform.getYpos());

    }

    public Vector2f getDistanceObj2f(GameObject obj1, GameObject obj2) {
        calcDis(obj1, obj2);
        return new Vector2f(this.dx, this.dy);
    }
/*
    public boolean isCollision(GameObject obj1, GameObject obj2)
    {
        if(this.dx) {

        }else {

            return false
        }
    }
*/


}
