package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbhelpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	
	private float runTime = 0; //extra variable which helps keep track of how long the game has been running
	
	public GameScreen()
	{
		float screenWidth  = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth    = 136;
		float gameHeight   = screenHeight / (screenWidth / gameWidth);
		
		int midPointY = (int) (gameHeight / 2);
		
		world    = new GameWorld(midPointY);
		renderer = new GameRenderer(world,(int) gameHeight, midPointY); 
		
		Gdx.input.setInputProcessor(new InputHandler(world.getBird()));
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "show called");
		
	}

	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.app.log("GameScreen)", (1/delta)+ "");
		
		runTime += delta; //incrementing runtime by delta
		
		world.update(delta); //GameWorld updates
		renderer.render(delta); //GameRemderer renders
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen","pause called");
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen","resume called");
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
