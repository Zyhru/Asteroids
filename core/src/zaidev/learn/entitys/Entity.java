package zaidev.learn.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import zaidev.learn.helpers.Animate;
import zaidev.learn.screens.GameScreen;
import zaidev.learn.managers.Handler;
import zaidev.learn.managers.ID;

public abstract class Entity {

    protected int x,y;
    protected float velX, velY;
    protected Texture texture;
    protected GameScreen gameScreen;
    protected int width, height;
    protected ID id;
    protected Handler handler;



    public Entity(int x, int y, GameScreen gameScreen, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.gameScreen = gameScreen;
        this.id = id;
        this.handler = handler;


    }


    public boolean collides(Rectangle object) {
        return object.overlaps(getBounds());
    }


    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public abstract Rectangle getBounds();




    public ID getID() {
        return id;
    }


    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelX() {
        return velX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }






}
