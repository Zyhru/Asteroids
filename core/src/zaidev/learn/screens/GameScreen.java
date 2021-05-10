package zaidev.learn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import zaidev.learn.Boot;
import zaidev.learn.entitys.Asteroid;
import zaidev.learn.entitys.Ship;
import zaidev.learn.helpers.UI;
import zaidev.learn.managers.ID;
import zaidev.learn.managers.Handler;

import java.util.Random;

import static zaidev.learn.Boot.*;


public class GameScreen implements Screen {

    private Boot                game;
    private Texture             texture;
    private Sprite              background;
    private Random              r;

    private Handler             handler;
    private UI                  ui;
    private Stage               stage;
    private Label               label;
    private Music               music;






    public GameScreen(Boot game) {
        this.game = game;
        r = new Random();
        handler = new Handler();
        stage = new Stage();

        ui = new UI(WIDTH - 4, 10);
        label = new Label("Score " + Ship.score, game.getSkin());
        label.setPosition(8, 560);

        stage.addActor(ui);
        stage.addActor(label);

        texture = new Texture("stars_background.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new Sprite(texture);

        handler.add(new Ship(WIDTH / 2 - 64,50, this, ID.Ship,  handler));

        initAsteroids();
        initMusic();


    }


    public Boot getGame() { return game; }

    public UI getUI() { return ui; }

    public Stage getStage() { return stage; }


    private void initMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("SLOWER-TEMPO2019-12-09_-_Retro_Forest_-_David_Fesliyan.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();


    }

    private void initAsteroids() {


        for(int i = 0; i < 20; i++) {
            handler.add(new Asteroid(r.nextInt(WIDTH / 2  - 16) , i * 100 + HEIGHT - 96, this, ID.ASTEROID, handler));

        }


    }

    @Override
    public void show() {

    }


    public void update(float delta) {

        handler.update(delta);
        label.setText("Score " + Ship.score);


    }

    @Override
    public void render(float delta) {


        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        game.getBatch().begin();



        background.draw(game.getBatch());
        background.scroll(0,-0.1f * delta);


        // rendering game objects
        handler.render(game.getBatch());

        game.getBatch().end();


        // render health bar
        stage.draw();
        stage.act();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        texture.dispose();
        handler.dispose();
        ui.dispose();
        stage.dispose();
        music.dispose();

    }
}
