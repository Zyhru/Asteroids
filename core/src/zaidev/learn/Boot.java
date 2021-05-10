package zaidev.learn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import zaidev.learn.entitys.Ship;
import zaidev.learn.screens.GameScreen;
import zaidev.learn.screens.RespawnScreen;


public class Boot extends Game {


	public static final int WIDTH = 300;
	public static final int HEIGHT = 600;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Skin skin;








	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH / 2 + 32, HEIGHT / 2 + 32);

		skin = new Skin((Gdx.files.internal("C:\\Users\\Harvey Munoz\\libgdx-workspace\\Asteroids\\core\\assets\\star-soldier-ui.json")));
		setScreen(new GameScreen(this));


	}


	public Skin getSkin() {
		return skin;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public SpriteBatch getBatch() {
		return batch;
	}


	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
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
		batch.dispose();
		skin.dispose();
		super.dispose();


	}
}
