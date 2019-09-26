package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BasicBrick extends GameObject{

    private int greenValue;

    Handler handler;
    Random rand;

    public BasicBrick(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        width = 48;
        height = 16;
        health = 1;

        rand = new Random();

        this.handler = handler;
    }

    public void tick() {
        greenValue = 253 - health * 50;

        if (health < 1) {
            if (rand.nextInt(3) == 1)
                handler.addObject(new Bonus(x, y, ID.Bonus, handler));
            handler.removeObject(this);
            HUD.score++;
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(255, greenValue, 0));
        g.fillRect(x, y, width, height);
        g.setColor(Color.pink);
        g.drawRect(x, y, width, height);
    }
}