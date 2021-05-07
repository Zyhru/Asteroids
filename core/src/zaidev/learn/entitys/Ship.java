package zaidev.learn.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import zaidev.learn.Boot;
import zaidev.learn.helpers.Animate;
import zaidev.learn.helpers.UI;
import zaidev.learn.managers.Handler;
import zaidev.learn.screens.GameScreen;
import zaidev.learn.managers.ID;
import zaidev.learn.screens.RespawnScreen;
import zaidev.learn.upgrades.HealthUpgrade;
import zaidev.learn.upgrades.ScoreUpgrade;
import zaidev.learn.upgrades.ShipSpeed;

import java.util.ArrayList;
import java.util.Random;

import static zaidev.learn.Boot.HEIGHT;
import static zaidev.learn.Boot.WIDTH;


public class Ship extends Entity {

    private float timer;
    private boolean collided;
    private int bulletTimer = 40;


    public static int score;
    public static final int SHIP_WIDTH = 32;
    public static final int SHIP_HEIGHT = 32;


    private final Random r;
    private final Boot game = gameScreen.getGame();
    private final UI ui = gameScreen.getUI();


    private Animate animate;




    public Ship(int x, int y, GameScreen gameScreen, ID id, Handler handler) {

        super(x, y, gameScreen, id, handler);
        r = new Random();

        width = SHIP_WIDTH;
        height = SHIP_HEIGHT;
        velX = 300;


        animate = new Animate("ship.png", 0.5f, 17, 32, 0);


    }

    private void handleInput(float delta) {

        // left movement
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= velX * delta;

        }

        // right movement
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            x += velX * delta;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            System.out.println("pressed space");



            // delay for shooting bullets
            if(bulletTimer < 0) {

                handler.add(new Bullet(x + 23, y, gameScreen, ID.BULLET, handler));
                handler.add(new Bullet(x, y, gameScreen, ID.BULLET, handler));

                bulletTimer = 40;
                }

        }

    }

    private void frameCollision() {
        if (x < 0) {
            x = 0;
        }

        if (x > Boot.WIDTH / 2  -  4 ) {
            x = Boot.WIDTH / 2  -  4;
        }
    }



    private void randomPowerUp() {
        int randomUpgrade = r.nextInt(3);

        System.out.println("Random Upgrade " + randomUpgrade);

            if(randomUpgrade == 0) {
                handler.add(new ShipSpeed( r.nextInt(WIDTH / 2  - 16), HEIGHT - 96, gameScreen, ID.SPEED, handler));
                Gdx.app.debug("Random Upgrade", "Spawned Speed Upgrade");
            } else if(randomUpgrade == 1) {
                handler.add(new ScoreUpgrade( r.nextInt(WIDTH / 2  - 16), HEIGHT - 96, gameScreen, ID.SCORE, handler));
                Gdx.app.debug("Random Upgrade", "Spawned Speed Upgrade");
            } else {
                handler.add(new HealthUpgrade( r.nextInt(WIDTH / 2  - 16), HEIGHT - 96, gameScreen, ID.HEALTH, handler));
                Gdx.app.debug("Random Upgrade", "Spawned Speed Upgrade");

            }
    }


    public int getX() {
        return x;
    }

    @Override
    public void update(float dt) {

        bulletTimer--;
        score++;
        handleInput(dt);
        frameCollision();

        if(!ui.isAlive()) {
            game.setScreen(new RespawnScreen(game));
        }


        // add an upgrade every 500 points
        if(score % 500 == 0) {
            System.out.println("spawning a powerup");
            randomPowerUp();
        }


        timer += dt;
        if(collided) {
            if(timer > 10) {
                setVelX(300);
                collided = false;
                System.out.println("Upgrade is no longer in effect.");
                fadeText(this, "Speed reverted back to normal");

            }
        }

        ArrayList<Entity> garbageList = new ArrayList<>();
        for(int i = 0; i < handler.getList().size(); i++) {

            if(Math.random() < 0.0001f) {
                handler.add(new ShipSpeed( 100, i * 100 + HEIGHT - 96,  gameScreen, ID.SPEED, handler));
                handler.add(new ScoreUpgrade( r.nextInt(WIDTH / 2  - 16), i * 100 + HEIGHT - 96,  gameScreen, ID.SCORE, handler));
                handler.add(new HealthUpgrade( r.nextInt(WIDTH / 2  - 16), i * 100 + HEIGHT - 96,  gameScreen, ID.HEALTH, handler));

            }

            Entity temp = handler.getList().get(i);

            if(temp.getID() == ID.SPEED) {
                if(this.collides(temp.getBounds())) {
                    System.out.println("Ship collided with speed upgrade");
                    garbageList.add(temp);

                    collided = true;
                    timer = 0;

                    System.out.println("Upgrade has commenced.");
                    setVelX(700);

                    // text fade
                    fadeText(temp,"25% extra speed");

                }

            }

            if(temp.getID() == ID.SCORE) {
                if(this.collides(temp.getBounds())) {
                    System.out.println("Ship collided => score upgrade");

                    int randomScore = r.nextInt(500 - 100) + 100;
                    System.out.println("Score " + randomScore);
                    Ship.score += randomScore;

                    fadeText(temp,randomScore + " extra score");

                    garbageList.add(temp);
                }
            }

            if(temp.getID() == ID.HEALTH) {
                if(this.collides(temp.getBounds())) {

                    UI ui = gameScreen.getUI();
                    ui.setValue(ui.getValue() + 0.1f);

                    fadeText(temp, 10 + " extra health");

                    garbageList.add(temp);

                }
            }


            handler.getList().removeAll(garbageList);

        }

    }


    /**
     *
     * @param entity entity position
     * @param text message of upgrade
     */
    public void fadeText(Entity entity, String text) {

        Label label = new Label(text, skin);
        label.setPosition(entity.getX(), entity.getY() + 64);
        label.setWrap(true);
        label.setFontScale(0.7f);
        label.addAction(Actions.moveBy(0,10,3));
        label.addAction(Actions.fadeOut(2f));
        gameScreen.getStage().addActor(label);

    }


    @Override
    public void render(SpriteBatch sb) {

           animate.render(sb,true,x,y,width,height);



    }


    @Override
    public void dispose() {
        texture.dispose();
        skin.dispose();

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }


}
