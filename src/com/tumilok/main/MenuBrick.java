package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class MenuBrick extends GameObject{

	private int greenValue;

    Handler handler;

    public MenuBrick(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        width = 48;
        height = 16;
        health = 1;

        this.handler = handler;
    }

    public void tick() {
        greenValue = 253 - health * 50;

        if (health < 1) {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(255, greenValue, 0));
        g.fillRect(x, y, width, height);
        g.setColor(Color.pink);
        g.drawRect(x, y, width, height);
    }
}