package zaidev.learn.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import zaidev.learn.Boot;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		config.width = Boot.WIDTH;
		config.height = Boot.HEIGHT;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;


		System.out.println("Foreground FPS: " + config.foregroundFPS);
		System.out.println("Background FPS: " + config.backgroundFPS);

		new LwjglApplication(new Boot(), config);
	}
}
