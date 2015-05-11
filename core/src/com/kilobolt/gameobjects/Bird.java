package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Bird {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float rotation; // For handling bird rotation
	private int   width;
	private int   height;
	
	public Bird(float x, float y, int width , int height)
	{
		
		this.width      = width;
		this.height     = height;
		
		position     	= new Vector2(x,y);
		velocity     	= new Vector2(0,0);
		acceleration    = new Vector2(0,460);
		
	}

	
	


	public void update(float delta) //will be called when Game World Updates
	{
		velocity.add(acceleration.cpy().scl(delta));
		
		if(velocity.y > 200)
		{
			velocity.y = 200;
		}
		
		position.add(velocity.cpy().scl(delta));
		
		//Rotate counter clockwise
		if(velocity.y < 0)
		{
			rotation -= 600 * delta;
			
			if(rotation < -20)
			{
				rotation =-20;
			}
		}
		
		// Rotate clockwise
		if(isFalling())
		{
			rotation += 480 * delta;
			if(rotation > 90)
			{
				rotation = 90;
			}
		}
		
		
		
		
	}
	
	public void onClick() //will be called when the screen is tapped
	{
		velocity.y = -140;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public float getRotation() {
		return rotation;
	}

	public float getY() {
		// TODO Auto-generated method stub
		return position.y;
	}



	public float getX() {
		// TODO Auto-generated method stub
		return position.x;
	}
	
	public boolean isFalling()
	{
		return velocity.y > 110;
	}

	public boolean shouldntFlap()
	{
		return velocity.y > 70;
		
	}




	



	}


	

