package com.tumilok.main;

import java.awt.*;
import java.util.Random;

public class Bonus extends GameObject {

    private int bonus;

    Handler handler;
    Color color;
    Random rand;
    ID idOfObject;

    public Bonus(int x, int y, ID id, Handler handler) {
        super(x, y , id);

        width = 16;
        height = 8;
        velY = 2;
        rand = new Random();
        bonus = rand.nextInt(6);

        this.handler = handler;

        if (bonus == 0) { color = Color.blue; idOfObject = ID.Player; }
        else if (bonus == 1) { color = Color.CYAN; idOfObject = ID.Player; }
        else if (bonus == 2) { color = Color.GRAY; idOfObject = ID.Ball; }
        else if (bonus == 3) { color = Color.green; idOfObject = ID.Ball; }
        else if (bonus == 4) { color = Color.magenta; idOfObject = ID.Ball; }
        else if (bonus == 5) { color = Color.orange; idOfObject = ID.Ball; }
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
            if (y + height >= tempObject.getY() && y <= tempObject.getY() + tempObject.getHeight()) {
                handler.removeObject(this);
                activateBonus();
            }
        }
    }

    private void activateBonus(){
        int i = 0;
        while (handler.object.get(i).id != idOfObject) i++;
        GameObject tempObject = handler.object.get(i);

        if (bonus == 0) playerSizeDecrease(tempObject);
        else if (bonus == 1) playerSizeIncrease(tempObject);
        else if (bonus == 2) ballSpeedIncrease(tempObject);
        else if (bonus == 3) ballSpeedDecrease(tempObject);
        else if (bonus == 4) ballSizeDecrease(tempObject);
        else if (bonus == 5) ballSizeIncrease(tempObject);
    }

    private void playerSizeDecrease(GameObject tempObject){
        if (tempObject.getWidth() < 64) return;

        handler.addObject(new Player(tempObject.getX() + tempObject.getWidth()/4,
                tempObject.getY(), ID.Player, tempObject.getWidth() / 2, tempObject.getHeight()));
        handler.removeObject(tempObject);
    }

    private void playerSizeIncrease(GameObject tempObject){
        if (tempObject.getWidth() > 128) return;
        else if (tempObject.getWidth() > 64)
            handler.addObject(new Player(tempObject.getX() - tempObject.getWidth()/2,
                    tempObject.getY(), ID.Player, tempObject.getWidth()*3/2, tempObject.getHeight()));
        else
            handler.addObject(new Player(tempObject.getX() - tempObject.getWidth()/2,
                    tempObject.getY(), ID.Player, tempObject.getWidth()*2, tempObject.getHeight()));
        handler.removeObject(tempObject);
    }

    private void ballSpeedIncrease(GameObject tempObject) {
        if (Math.abs(tempObject.getVelY()) > 5) return;

        tempObject.setVelY(tempObject.getVelY() * 3/2);
        tempObject.setVelX(tempObject.getVelX() * 3/2);
    }

    private void ballSpeedDecrease(GameObject tempObject) {
        if (Math.abs(tempObject.getVelY()) < 3) return;

        tempObject.setVelY(tempObject.getVelY() * 2 / 3);
        tempObject.setVelX(tempObject.getVelX() * 2 / 3);
    }

   private void ballSizeDecrease(GameObject tempObject) {
        if (tempObject.getWidth() < 12) return;
        Ball ball = new Ball(tempObject.getX(), tempObject.getY(), ID.Ball,
                tempObject.getWidth()/2, tempObject.getHeight()/2, handler);
        ball.setVelX(tempObject.getVelX());
        ball.setVelY(tempObject.getVelY());

        handler.removeObject(tempObject);
        handler.addObject(ball);

   }

   private void ballSizeIncrease(GameObject tempObject) {
        if (tempObject.getWidth() > 12) return;
        Ball ball = new Ball(tempObject.getX(), tempObject.getY(), ID.Ball,
                tempObject.getWidth()*2, tempObject.getHeight()*2, handler);
        ball.setVelX(tempObject.getVelX());
        ball.setVelY(tempObject.getVelY());

        handler.removeObject(tempObject);
        handler.addObject(ball);
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}