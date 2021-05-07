package zaidev.learn.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zaidev.learn.entitys.Entity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Handler {
    
    private List<Entity> handler;



    public Handler() {
        handler = new LinkedList<>();


    }



    public Entity get(int i) {
        return handler.get(i);
    }


    public List<Entity> getList() {
        return handler;
    }


    public void add(Entity entity) {
        handler.add(entity);

    }

    public void remove(Entity entity) {
        handler.remove(entity);
    }




    public void update(float dt) {
        for(int i = 0; i < handler.size(); i++) {

            Entity tempObject = handler.get(i);


            tempObject.update(dt);



        }
    }


    public void render(SpriteBatch sb) {


        for(int i = 0; i < handler.size(); i++) {

            Entity tempObject = handler.get(i);

            tempObject.render(sb);


        }

    }


    public void dispose() {

        for(int i = 0; i < handler.size(); i++) {

            Entity tempObject = handler.get(i);
            tempObject.dispose();

            System.out.println("Disposing");

        }


    }


}
