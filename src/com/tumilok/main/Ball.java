package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{
	
	private int radius;
	
	Handler handler;

	public Ball(int x, int y, ID id, Color color, Handler handler) {
		super(x, y, id, color);
		
		width = 12;
		height = 12;
		radius = width / 2;
		
		velY = -3;
		
		this.handler = handler;
	}
	
	private void move() {
		x += velX;
		y += velY;
		
		if (!Game.isStart) {			
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if (tempObject.id == ID.Player) {
					if (x > tempObject.getX() && tempObject.getX() + tempObject.getWidth() > x + width)
						velX = -tempObject.getVelX()/4;
					else 
						velX = tempObject.getVelX();
					
					y = tempObject.getY() - height;
				}
			}
		}
	}

	public void tick() {
		
		move();
		
        if(y <= 0) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
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
        
        //handler.addObject(new Trail(x, y, ID.Trail, color, width/2, height/2, 0.01f, handler));
    }

	public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() !=  ID.Ball) {
                if (Game.intersects(tempObject, this)) {
                    if (tempObject.getID() == ID.BasicBrick)  handler.removeObject(tempObject);
                    if (tempObject.getX() <= x + radius && x + radius <= tempObject.getX() + tempObject.getWidth()){
                        velY *= -1;
                    }
                    else velX *= -1;
                }
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
