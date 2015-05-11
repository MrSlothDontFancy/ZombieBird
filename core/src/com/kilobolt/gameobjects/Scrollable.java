package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
	//instant variables
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledLeft;
	
	
	public Scrollable(float x, float y, int width, int height, float scrollSpeed)
	{
		position 		= new Vector2(x,y);
		velocity 		= new Vector2(scrollSpeed, 0); //because X is horizontal and Y is vertical
		this.width 		= width;
		this.height		= height;
		isScrolledLeft	= false;
	}
	
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
		
		// If the scrollable object is no longer visible:
		if(position.x + width < 0)
		{
			isScrolledLeft = true;
		}
	}
	
	//Reset : Should Overrride in subclass for more specific behaviour
	public void reset(float newX)
	{
		position.x = newX;
		isScrolledLeft = false;
	}
	
	//getters for instances 
	public boolean isScrolledLeft()
	{
		return isScrolledLeft;
	}
	
	public float getTailX()
	{
		return position.x + width;
	}
	
	public float getX()
	{
		return position.x;
	}
	
	public float getY()
	{
		return position.y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	

}
