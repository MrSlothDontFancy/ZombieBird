package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;



public class GameWorld {
	private Bird 		  bird;
	private ScrollHandler scroller;
	private Grass 		  frontGrass, backGrass;
	private Pipe		  pipe1,pipe2,pipe3;
	

	public GameWorld(int midPointY)
	{
		bird 		= new Bird(33, midPointY-5,17,12);
		scroller	= new ScrollHandler(midPointY + 66);
	}
	
	
	public void update(float delta)
	{
		bird.update(delta);
		scroller.update(delta);
	}
	
	public Bird getBird()
	{
		return bird;
	}
	
	public ScrollHandler getScroller()
	{
		return scroller;
	}
	
	
}
