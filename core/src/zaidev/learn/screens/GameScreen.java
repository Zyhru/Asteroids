package zaidev.learn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

import static zaidev.learn.Boot.HEIGHT;
import static zaidev.learn.Boot.WIDTH;


public class GameScreen implements Screen {

    private Boot                game;
    private OrthographicCamera  cam;
    private SpriteBatch         sb;
    private Texture             texture;
    private Sprite              background;
    private Random              r;

    private Handler             handler;
    private UI                  ui;
    private Stage               stage;
    private Label               label;

    private Skin                skin;
    private final FPSLogger logger = new FPSLogger();





    public GameScreen(Boot game) {
        this.game = game;
        sb = new SpriteBatch();
        r = new Random();
        handler = new Handler();


        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH / 2 + 32, HEIGHT / 2 + 32);


        ui = new UI(WIDTH - 4, 10);
        skin = new Skin(Gdx.files.internal("C:\\Users\\Harvey Munoz\\LibGDX-Animation\\core\\assets\\star-soldier-ui.json"));

        stage = new Stage();

        label = new Label("Score " + Ship.score, skin);
        label.setPosition(8, 560);

        stage.addActor(ui);
        stage.addActor(label);

        texture = new Texture("stars_background.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new Sprite(texture);

          handler.add(new Ship(      WIDTH / 2 - 64,50, this, ID.Ship,  handler));
//        handler.add(new ShipSpeed( WIDTH / 2 - 50, 300,  this, ID.SPEED, handler));



        initAsteroids();



    }


    public Boot getGame() { return game; }

    public UI getUI() { return ui; }

    public Stage getStage() { return stage; }


    private void initAsteroids() {


        for(int i = 0; i < 20; i++) {
            handler.add(new Asteroid(r.nextInt(WIDTH / 2  - 16) , i * 100 + HEIGHT - 96, this, ID.ASTEROID, handler));

        }


    }

    @Override
    public void show() {

    }


    public void update(float delta) {

        cam.update();

        handler.update(delta);
        label.setText("Score " + Ship.score);


//        if(Math.random() < 0.002f) {
//            handler.add(new ShipSpeed( r.nextInt(WIDTH / 2  - 16), HEIGHT - 96,  this, ID.SPEED, handler));
//            handler.add(new ScoreUpgrade( r.nextInt(WIDTH / 2  - 16), HEIGHT - 96,  this, ID.SCORE, handler));
//            handler.add(new HealthUpgrade( r.nextInt(WIDTH / 2  - 16), HEIGHT - 96,  this, ID.HEALTH, handler));
//
//        }
//



    }

    @Override
    public void render(float delta) {



        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        sb.begin();

        background.draw(sb);
        background.scroll(0,-0.1f * delta);


        // rendering game objects
        handler.render(sb);

        sb.end();




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
        sb.dispose();
        handler.dispose();
        ui.dispose();
        stage.dispose();
        skin.dispose();

    }
}
