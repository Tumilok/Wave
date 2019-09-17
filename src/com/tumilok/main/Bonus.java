package com.tumilok.main;

import java.awt.*;
import java.util.Random;

public class Bonus extends GameObject {

    private int bonus;

    Handler handler;
    Color color;
    Random rand;

    public Bonus(int x, int y, ID id, Handler handler) {
        super(x, y , id);

        width = 16;
        height = 8;
        velY = 2;
        rand = new Random();
        bonus = rand.nextInt(2);

        this.handler = handler;
    }

    public void tick(){
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);

        collision();
    }

    private void collision() {
        int i = 0;
        while (handler.object.get(i).id != ID.Player) i++;

            GameObject tempObject = handler.object.get(i);
            if (x + width >= tempObject.getX() &&
                    x <= tempObject.getX() + tempObject.getWidth()){
                if (y + height >= tempObject.getY()) {
                    handler.removeObject(this);
                    if (bonus == 0) sizeDecrease(tempObject);
                    else if (bonus == 1) sizeIncrease(tempObject);
                }
            }
    }

    public void sizeDecrease(GameObject tempObject){
        if (tempObject.getWidth() < 64) return;

        handler.addObject(new Player(tempObject.getX() + tempObject.getWidth()/4,
                tempObject.getY(), ID.Player, tempObject.getWidth() / 2, tempObject.getHeight()));
        handler.removeObject(tempObject);
    }

    public void sizeIncrease(GameObject tempObject){
        if (tempObject.getWidth() > 128) return;
        else if (tempObject.getWidth() > 64)
            handler.addObject(new Player(tempObject.getX() - tempObject.getWidth()/2,
                    tempObject.getY(), ID.Player, tempObject.getWidth()*3/2, tempObject.getHeight()));
        else
            handler.addObject(new Player(tempObject.getX() - tempObject.getWidth()/2,
                tempObject.getY(), ID.Player, tempObject.getWidth()*2, tempObject.getHeight()));
        handler.removeObject(tempObject);
    }

    public void render(Graphics g){
        if (bonus == 0) color = Color.blue;
        else if (bonus == 1) color = Color.CYAN;
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}