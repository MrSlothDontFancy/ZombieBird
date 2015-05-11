package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;




public class GameRenderer {
	
	private GameWorld 				myWorld;
	private OrthographicCamera		cam; //shit actually makes the game 2D
	private ShapeRenderer			shapeRenderer; // gift from the frame work to show us shapes
	
	private SpriteBatch 			batcher; // draws images for us using the indices provided . (x,y,width and height, typically)

	//CHANGING CAMERA WIDTH TO 136 anf game height determined  by game screen
	private int midPointY;
	private int gameHeight;
	
	//Game Objects
	private Bird bird;
	private ScrollHandler scroller;
	private Grass 		  frontGrass, backGrass;
	private Pipe		  pipe1,pipe2,pipe3;
	
	//Game Assests
	private TextureRegion bg, grass;
	private Animation birdAnimation;
	private TextureRegion birdMid, birdDown, birdUp;
	private TextureRegion skullUp, skullDown, bar;
	
	public GameRenderer(GameWorld world, int gameHeight,int midPointY) 
	{
		myWorld = world;
		// TODO Auto-generated constructor stub
		
		this.gameHeight = gameHeight;
		this.midPointY  = midPointY;
	
		cam = new OrthographicCamera();
		cam.setToOrtho(true,136,204);
		
		batcher = new SpriteBatch();
		//Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined); //shit, see what we did there? we used cam as an argument
		
		// Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();
		
	}
	


private void initGameObjects()
{
	bird 		= myWorld.getBird();
	scroller	= myWorld.getScroller();
	frontGrass  = scroller.getFrontGrass();
	backGrass	= scroller.getBackGrass();
	pipe1		= scroller.getPipe1();
	pipe2		= scroller.getPipe2();
	pipe3		= scroller.getPipe3();
}

//avoiding a messy constructor
private void initAssets()
{
	bg 				= AssetLoader.bg;
	grass			= AssetLoader.grass;
	birdAnimation	= AssetLoader.birdAnimation;
	birdMid			= AssetLoader.bird;
	birdDown		= AssetLoader.birdDown;
	birdUp			= AssetLoader.birdUp;
	skullUp			= AssetLoader.skullUp;
	skullDown		= AssetLoader.skullDown;
	bar				= AssetLoader.bar;
}

private void drawGrass()
{
	//Draw the grass
	batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),frontGrass.getWidth(),frontGrass.getHeight());
	batcher.draw(grass, backGrass.getX() , backGrass.getY(),backGrass.getWidth(),backGrass.getHeight());
	
}


private void drawSkulls()
{
	//Temporary code! Its a mess
	// We will fix this when we finish Pipe
	batcher.draw(skullUp, pipe1.getX()-1, pipe1.getY()+pipe1.getHeight() - 14, 24, 14);
	batcher.draw(skullDown, pipe1.getX()-1, pipe1.getY()+pipe1.getHeight() + 45,24,14);
	
	batcher.draw(skullUp, pipe2.getX()-1, pipe2.getY()+pipe2.getHeight() - 14,24,14);
	batcher.draw(skullDown, pipe2.getX()-1, pipe2.getY()+pipe2.getHeight() + 14,24,14);
	
	batcher.draw(skullUp, pipe3.getX()-1, pipe3.getY()+pipe3.getHeight() - 14,24,14);
	batcher.draw(skullDown, pipe3.getX()-1, pipe3.getY()+pipe3.getHeight() + 45, 24, 14 );
	
	
}+

private void drawPipes()
{
	batcher.draw(bar, pipe1.getX(), pipe1.getY(),pipe1.getWidth(),pipe1.getHeight());
	batcher.draw(bar, pipe1.getX(), pipe1.getY(),pipe1.getHeight() + 45,
			pipe1.getWidth(),midPointY + 66 - (pipe1.getHeight() + 45));
	
	
	batcher.draw(bar, pipe2.getX(), pipe2.getY(),pipe2.getWidth(),pipe2.getHeight());
	batcher.draw(bar, pipe2.getX(), pipe2.getY(),pipe2.getHeight()+45,
			pipe2.getWidth(),midPointY + 66 - (pipe2.getHeight() + 45));
	
	batcher.draw(bar, pipe3.getX(), pipe3.getY(),pipe3.getWidth(),pipe3.getHeight());
	batcher.draw(bar, pipe3.getX(), pipe3.getY(),pipe3.getHeight()+45,
			pipe3.getWidth(),midPointY + 66 - (pipe3.getHeight() + 45));
}

	public void render(float runTime) // value in parameter to determine which frame the bird animation should display
	{
		//We will move these outside of the loop for performance later
		//Bird bird = myWorld.getBird();
		
		//Fill the entire screen with black , to prevent potential flickering.		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Being Shape renderer 
		shapeRenderer.begin(ShapeType.Filled);
		
		//Draw background color
		shapeRenderer.setColor(55/255.0f,80/255.0f,100/255.0f,1);
		shapeRenderer.rect(0,0,136,midPointY + 60);
		
		//Draw Grass
		shapeRenderer.setColor(111/255.0f,186/ 255.0f, 45 / 255.0f,1);
		shapeRenderer.rect(0,midPointY + 66,136 ,11 );
		
		//Draw Dirt
		shapeRenderer.setColor(147/255.0f,80/255.0f,27/255.0f,1);
		shapeRenderer.rect(0, midPointY + 77, 136 , 52 );
		
		
		//End shapeRenderer
		shapeRenderer.end();
		
		//Begin sprite batch
		batcher.begin();
		//Disable transparency 
		//This is good for performance when drawing images that do not require transparency
		batcher.disableBlending();
		batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136 , 43);
		
		// 1 . Draw Grass
		drawGrass();
		
		// 2. Draw Pipes
		drawPipes();
		batcher.enableBlending();
		
		// 3. Draw skulls (require transparency
		drawSkulls();
		
		
		//The bird needs transparency . so we enable that again.
		batcher.enableBlending();
		
		//Draw bird at its coordinates , Retrieve the Animation object from
		//AssetLoader
		//Pass in the runTime variable to get the current frame.
		//modified to handle rotation properly
		if(bird.shouldntFlap())
		{
			//batcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime),bird.getX(),bird.getY(),bird.getWidth(),bird.getHeight());
			batcher.draw(birdMid, bird.getX(), bird.getY(), bird.getWidth()/ 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
		}
		else
		{
			batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight()/2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
			
		}
		// End SpiteNatch
		batcher.end();
		
	
	
}
	}
