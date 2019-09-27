package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class BotPlatform extends GameObject {
	
	private Handler handler;

    public BotPlatform(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);

        this.width = width;
        this.height = height;
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;
        
        int i = 0;
        while(handler.object.get(i).getID() != ID.MenuBall) i++;
        GameObject tempObject = handler.object.get(i);
        if (tempObject.getX() + tempObject.getWidth()/2 < x + width/2) velX = -3;
        else if (tempObject.getX() + tempObject.getWidth()/2 > x + width/2) velX = 3;
        else velX = 0;

        x = Game.clamp(x, 1, Game.WIDTH - width - 1);
        y = Game.clamp(y, 650, Game.HEIGHT - 50);
    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
}