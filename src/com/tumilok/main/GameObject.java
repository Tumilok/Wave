package com.tumilok.main;

import java.awt.Graphics;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected int width, height;
    protected int health;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getID() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelY() {
        return velY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
