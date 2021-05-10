package zaidev.learn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import zaidev.learn.Boot;
import zaidev.learn.entitys.Ship;

public class RespawnScreen implements Screen {

    private Boot game;
    private Stage stage;

    public RespawnScreen(Boot game) {
        this.game = game;
        stage = new Stage();

        Label label = new Label("Thanks for playing!\n\n\nScore: " + Ship.score + "\n\nPress R to play \nagain!", game.getSkin());
        label.setWrap(false);
        label.setPosition(Boot.WIDTH / 2 - 145, Boot.HEIGHT / 2);

        stage.addActor(label);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        stage.dispose();


    }
}
