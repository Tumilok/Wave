package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

    public Player(int x, int y, ID id, int width, int height) {
        super(x, y, id);

        this.width = width;
        this.height = height;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 1, Game.WIDTH - width - 1);
        y = Game.clamp(y, 650, Game.HEIGHT - 50);
    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
}