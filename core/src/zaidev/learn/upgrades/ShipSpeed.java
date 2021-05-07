package zaidev.learn.upgrades;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import zaidev.learn.entitys.Entity;
import zaidev.learn.managers.Handler;
import zaidev.learn.managers.ID;
import zaidev.learn.screens.GameScreen;



/**
 * Description:
 * If ship collides with speed upgrade
 * then ship gains 25 % extra speed
 * for a certain amount of time
 */

public class ShipSpeed extends Entity {






    public ShipSpeed(int x, int y, GameScreen gameScreen, ID id, Handler handler) {
        super(x, y, gameScreen, id, handler);

        texture = new Texture("SpeedAsset.png");

        width = 20;
        height = 20;
        velY = 1f;



    }


    @Override
    public void update(float dt) {

        // fall vertically
        y -= velY;

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
        skin.dispose();

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
