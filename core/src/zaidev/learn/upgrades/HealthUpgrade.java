package zaidev.learn.upgrades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import zaidev.learn.entitys.Entity;
import zaidev.learn.managers.Handler;
import zaidev.learn.managers.ID;
import zaidev.learn.screens.GameScreen;

public class HealthUpgrade extends Entity {


    public HealthUpgrade(int x, int y, GameScreen gameScreen, ID id, Handler handler) {
        super(x, y, gameScreen, id, handler);

        texture = new Texture("heart.png");

        width = 20;
        height = 20;

        velY = 1f;
    }

    @Override
    public void update(float dt) {
        y -= velY;
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(texture,x,y,width,height);

    }

    @Override
    public void dispose() {

        skin.dispose();
        texture.dispose();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
