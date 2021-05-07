package zaidev.learn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import zaidev.learn.entitys.Ship;
import zaidev.learn.screens.GameScreen;
import zaidev.learn.screens.RespawnScreen;


public class Boot extends Game {
	public static final int WIDTH = 300;
	public static final int HEIGHT = 600;







	@Override
	public void create () {
		setScreen(new GameScreen(this));


	}




	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();


		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			System.out.println("Exited program.");
			Gdx.app.exit();
		}


		if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			Ship.score = 0;
			setScreen(new GameScreen(this));
		}



	}
	
	@Override
	public void dispose () {
		System.out.println("Dispose");
		super.dispose();


	}
}
