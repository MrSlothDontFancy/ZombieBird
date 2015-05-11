package com.kilobolt.gameobjects;

import java.util.Random;

;

public class Pipe extends Scrollable{

	private Random r;
	
	public Pipe(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		// TODO Auto-generated constructor stub
		r = new Random();
	}
	
	@Override
	public void reset(float newX)
	{
		//Call the method in the superclass (Scrollable)
		super.reset(newX);
		//change the height to a random number
		height = r.nextInt(90) + 15;
	}
	
	

}
