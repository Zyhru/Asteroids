package zaidev.learn.entitys;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import zaidev.learn.Boot;
import zaidev.learn.helpers.Explosion;
import zaidev.learn.managers.Handler;
import zaidev.learn.screens.GameScreen;
import zaidev.learn.managers.ID;

import java.util.ArrayList;
import java.util.Random;



public class Bullet extends Entity {


    public static final int BULLET_WIDTH = 5;
    public static final int BULLET_HEIGHT = 5;
    private Random r;
    private Sound sound;



    public Bullet(int x, int y, GameScreen gameScreen, ID id, Handler handler) {
        super(x, y, gameScreen, id, handler);
        texture = new Texture("bullet.png");

        r = new Random();


        width = BULLET_WIDTH;
        height = BULLET_HEIGHT;

        velY = 100;


        sound = Gdx.audio.newSound(Gdx.files.internal("bangMedium.wav"));

    }

    @Override
    public void update(float dt) {

        y += velY * dt;

        ArrayList<Entity> list = new ArrayList<>();
        for(int i = 0; i < handler.getList().size(); i++) {

            Entity tempObject = handler.getList().get(i);

            if(isRemoved()) {
                list.add(this);
                System.out.println("removed bullet[frame]");
            }


            if(tempObject.getID() == ID.ASTEROID) {
                if(this.collides(tempObject.getBounds())) {


                   handler.add(new Explosion(x,y,gameScreen, ID.Explosion, handler));

                    System.out.println("removed asteroid[bullet]");
                    System.out.println("removed bullet[asteroid]");

                    int randomScore = r.nextInt(100 - 50) + 50;

                    System.out.println("Ship has gained " + randomScore + " score.");
                    Ship.score += randomScore;


                    sound.play(0.5f);




                    list.add(tempObject);
                    list.add(this);
                }

            }

            handler.getList().removeAll(list);

        }

    }

    public boolean isRemoved() { return y > Boot.HEIGHT / 2 - 4; }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(texture,x, y, width,height);
    }


    @Override
    public void dispose() {
        texture.dispose();
        sound.dispose();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
