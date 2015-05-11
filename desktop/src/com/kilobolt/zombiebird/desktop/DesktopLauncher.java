package com.kilobolt.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kilobolt.zombiebird.ZBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie Bird";//added code
		config.width = 272; //added code
		config.height = 408;//added code
		new LwjglApplication(new ZBGame(), config);
		
	}
}
