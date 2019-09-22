package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{

    private int radius;

    Handler handler;

    public Ball(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);

        this.width = width;
        this.height = height;
        this.handler = handler;

        radius = width / 2;
        velY = -3;
    }

    public void tick() {

        move();
        collision();

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
    }

    private void move() {
        x += velX;
        y += velY;

        if (velX < 0) velX = -Math.abs(velY);
        else velX = Math.abs(velY);

        if (!Game.isStart) {
            int i = 0;
            while (handler.object.get(i).id != ID.Player) i++;
            GameObject tempObject = handler.object.get(i);

            velX = tempObject.getVelX()/2;

            x = Game.clamp(x, tempObject.getX(), tempObject.getX() + tempObject.getWidth() - width);
            y = tempObject.getY() - height;
        }
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() ==  ID.Player || tempObject.getID() == ID.Brick) {
                if (intersects(tempObject)) {
                    if (tempObject.getID() == ID.Brick)
                        tempObject.health--;

                    sideDetection(tempObject);
                }
            }
        }
    }

    private boolean intersects(GameObject rect)
    {
        int DeltaX = x + radius - Math.max(rect.getX(),
                Math.min(x + radius, rect.getX() + rect.getWidth()));
        int DeltaY = y + radius - Math.max(rect.getY(),
                Math.min(y + radius, rect.getY() + rect.getHeight()));

        return (DeltaX * DeltaX + DeltaY * DeltaY) < (radius * radius);
    }

    private void sideDetection(GameObject tempObject){
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
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
    }
}