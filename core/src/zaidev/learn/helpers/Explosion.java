package zaidev.learn.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import zaidev.learn.entitys.Entity;
import zaidev.learn.managers.Handler;
import zaidev.learn.managers.ID;
import zaidev.learn.screens.GameScreen;

import java.util.ArrayList;

public class Explosion extends Entity {



    private Animate animate;

    public Explosion(int x, int y, GameScreen gameScreen, ID id, Handler handler) {
        super(x, y, gameScreen, id, handler);


        width = 32;
        height = 32;

        animate = new Animate("explosion.png", 0.2f, width , height, 0);


    }


    @Override
    public void update(float dt) {

        animate.update(dt);
        ArrayList<Entity> removeExplosions = new ArrayList<>();
        for(int i = 0; i < handler.getList().size(); i++) {

            if(animate.isRemoved()) {
                removeExplosions.add(this);
            }

            handler.getList().removeAll(removeExplosions);

        }

    }


    @Override
    public void render(SpriteBatch sb) {

        animate.render(sb,true, x,y,width,height);


    }

    @Override
    public void dispose() {
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
