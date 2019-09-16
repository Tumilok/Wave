package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{

    private int radius;

    Color color;
    Handler handler;

    public Ball(int x, int y, ID id, Color color, Handler handler) {
        super(x, y, id);

        width = 12;
        height = 12;
        radius = width / 2;

        velY = -3;

        this.handler = handler;
        this.color = color;
    }

    private void move() {
        x += velX;
        y += velY;

        if (velX < 0) velX = -3;
        else velX = 3;

        if (!Game.isStart) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.id == ID.Player) {
                    if (x > tempObject.getX() && tempObject.getX() + tempObject.getWidth() > x + width)
                        velX = tempObject.getVelX()/5;
                    else
                        velX = tempObject.getVelX();

                    y = tempObject.getY() - height;
                }
            }
        }
    }

    public void tick() {

        move();

        if(y <= 40) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - (width + radius)) velX *= -1;
        if(y >= Game.HEIGHT - height) {
            Game.isStart = false;
            HUD.LIVES--;

            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.id == ID.Player) {
                    x = tempObject.getX() + tempObject.getWidth() / 2 - radius;
                    y = tempObject.getY() - height;
                }
            }
        }

        collision();
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() !=  ID.Ball) {
                if (Game.intersects(tempObject, this)) {

                    if (tempObject.getID() == ID.Brick) {
                        tempObject.health--;
                        if (tempObject.health < 1) {
                            handler.removeObject(tempObject);
                            HUD.score++;
                        }
                    }

                    sideDetection(tempObject);
                }
            }
        }
    }

    public void sideDetection(GameObject tempObject){
        if (x < tempObject.getX()){
            if (y < tempObject.getY()) {
                velY = -Math.abs(velY);
            }else if (y + height > tempObject.getY() + tempObject.getHeight()){
                velY = Math.abs(velY);
            }
            velX = -Math.abs(velX);
        }else if (x + width > tempObject.getX() + tempObject.getWidth()){
            if (y < tempObject.getY()) {
                velY = -Math.abs(velY);
            }else if (y + height > tempObject.getY() + tempObject.getHeight()){
                velY = Math.abs(velY);
            }
            velX = Math.abs(velX);
        }else {
            if (y < tempObject.getY() + tempObject.getHeight() / 2) {
                velY = -Math.abs(velY);
            } else {
                velY = Math.abs(velY);
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    public int getCenterX() {
        return x + radius;
    }

    public int getCenterY() {
        return y + radius;
    }

    public int getRadius() {
        return radius;
    }
}
