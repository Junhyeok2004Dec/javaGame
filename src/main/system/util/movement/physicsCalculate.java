package main.system.util.movement;

import org.joml.Vector2f;

public class physicsCalculate {

	private Vector2f pos2f = new Vector2f(0,0);
	private Vector2f speed2f = new Vector2f(0,0);
	private Vector2f acceleration2f = new Vector2f(0,0);





	public Vector2f position2f(Vector2f pos, Vector2f speed, Vector2f acceleration) {

		acceleration.add(acceleration2f);
		speed.add(speed2f, acceleration);
		pos.add(pos2f, speed);


		return pos;
	}

	public Vector2f JumpCalc(Vector2f pos) {

		this.acceleration2f = new Vector2f(0,-9.8f);

		return this.acceleration2f;
		//개발중


	}





}
