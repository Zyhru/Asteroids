package zaidev.learn.entitys;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import zaidev.learn.helpers.Animate;
import zaidev.learn.helpers.UI;

import zaidev.learn.managers.Handler;
import zaidev.learn.managers.ID;
import zaidev.learn.screens.GameScreen;


import java.util.ArrayList;
import java.util.Random;

import static zaidev.learn.Boot.HEIGHT;
import static zaidev.learn.Boot.WIDTH;

public class Asteroid extends Entity {





    private Random r;
    private Animate animate;

    public Asteroid(int x, int y, GameScreen gameScreen, ID id, Handler handler) {
        super(x,y,gameScreen, id, handler);

        r = new Random();

        texture = new Texture("asteriod.png");

        height = width = MathUtils.random(40, 80);
        velY = 2.5f;
        


    }

    public boolean isAsteroidRemoved() {

        return y < 0;
    }

    @Override
    public void update(float dt) {

        y -= velY;

        if(x > WIDTH - 96) {
            x = WIDTH - 96;

        }


        ArrayList<Entity> removeEntity = new ArrayList<>();
        for(int i = 0; i < handler.getList().size(); i++) {

            Entity tempObject = handler.getList().get(i);

            if(handler.getList().size() < 25) {
                handler.add(new Asteroid(r.nextInt(WIDTH / 2  - 16) , i * 100 + HEIGHT - 96, gameScreen, ID.ASTEROID, handler));
            }

                if(isAsteroidRemoved()) {
                    removeEntity.add(this);
                    System.out.println("removed asteroid[frame]");

                }

                if(tempObject.getID() == ID.Ship) {

                    if(this.collides(tempObject.getBounds())) {
                        System.out.println("asteroid collides with ship");

//                        int randomScore = r.nextInt(100 - 50) + 50;
//                        System.out.println("Ship has lost " + randomScore + " score.");
//                        Ship.score -= randomScore;

                        // health logic
                        // decrease health when collide w/ asteroid
                        UI ui = gameScreen.getUI();
                        ui.setValue(ui.getValue() - 0.1f);




                        removeEntity.add(this);
                    }

                }

            handler.getList().removeAll(removeEntity);

        }


    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(texture, x, y, width, height);


    }

    @Override
    public void dispose() {

        texture.dispose();


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
