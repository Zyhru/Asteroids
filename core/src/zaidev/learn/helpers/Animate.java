package zaidev.learn.helpers;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animate {

    private Animation animation = null;
    private float stateTime;
    private boolean remove;


    public Animate(String file, float frameDuration, int width, int height, int region) {

        if(animation == null) {
            animation = new Animation(frameDuration, TextureRegion.split(new Texture(file), width, height)[region]);
        }

        stateTime = 0f;


    }

    public Animation getAnimation() {
        return animation;
    }


    public void update(float dt)  {

        stateTime += dt;

        if(animation.isAnimationFinished(stateTime)) {
            remove = true;
        }


    }

    public boolean isRemoved() {
        return remove;
    }



    public void render(SpriteBatch sb, boolean looping, int x, int y, int width, int height) {


        sb.draw((TextureRegion) animation.getKeyFrame(stateTime, looping), x,y, width, height);



    }


}
