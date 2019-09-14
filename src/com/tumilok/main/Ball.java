package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{
	
	private int radius;
	
	Handler handler;
	Player player;

	public Ball(int x, int y, ID id, Handler handler, Player player) {
		super(x, y, id);
		
		width = 12;
		height = 12;
		radius = width / 2;
		
		velX = 3;
		velY = -3;
		
		this.handler = handler;	
		this.player = player;
	}
	
	private void move() {
		x += velX;
		y += velY;
		
		if (!Game.isStart) {			
			if (x > player.getX() && player.getX() + player.getWidth() > x + width)
				velX = -player.getVelX()/4;
			else 
				velX = player.getVelX();
			
			y = player.getY() - height;
		}
	}

	public void tick() {
		
		move();
		
        if(y <= 0) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
        if(y >= Game.HEIGHT - height) {
        	Game.isStart = false;
        	HUD.LIVES--;
        	x = player.getX() + player.getWidth() / 2 - radius;
			y = player.getY() - height;
        }

        collision();
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() !=  ID.Ball) {
                if (Game.intersects(tempObject, this)) {
                    if (tempObject.getID() == ID.BasicBrick)  handler.removeObject(tempObject);
                    
                    if (tempObject.getX() <= x + radius &&
                    		x + radius <= tempObject.getX() + tempObject.getWidth()) velY *= -1;
                    else velX *= -1;
                }
            }
        }
    }

	public void render(Graphics g) {
		g.setColor(Color.red);
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
