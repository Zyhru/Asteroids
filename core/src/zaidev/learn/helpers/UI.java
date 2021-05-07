package zaidev.learn.helpers;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class UI extends ProgressBar{

    public UI(int width, int height) {

        super(0f,1f,0.01f,false, new ProgressBarStyle());
        healthBar(width,height);


    }

    /**
     * @param width of health bar
     * @param height of health bar
     */

    public void healthBar(int width, int height) {

        getStyle().background = getColoredDrawable(width, height, Color.BLACK);
        getStyle().knob = getColoredDrawable(0, height, Color.GREEN);
        getStyle().knobBefore = getColoredDrawable(width, height, Color.GREEN);


        setWidth(width);
        setHeight(height);

        setAnimateDuration(0.0f);
        setValue(1f);

        setAnimateDuration(0.25f);
        setPosition(0,0);


    }


    public boolean isAlive() {
        return getValue() > 0;
    }


    /**
     *
     * @param width width of image
     * @param height height of image
     * @param color color of image
     * @return drawable
     */

    public Drawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        return drawable;
    }





    public void dispose() {


    }




}
