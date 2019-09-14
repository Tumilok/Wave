package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{
	
	private int radius;
	
	Handler handler;

	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		width = 8;
		height = 8;
		radius = width / 2;
		
		this.handler = handler;
		
		velX = 3;
		velY = -3;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 8) velX *= -1;
		
		collision();
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID()!=  ID.Ball) {
				if (Game.intersects(tempObject, this)) {
					if (x + radius < tempObject.getX() + tempObject.getWidth()/2) {
						if (y + radius < tempObject.getY() ||
								y + radius < tempObject.getY() + tempObject.getHeight()) velY *= -1;
						else velX *= -1;
					}
					else {
						if (y + radius < tempObject.getY() ||
								y + radius < tempObject.getY() + tempObject.getHeight()) velY *= -1;
						else velX *= -1;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 8, 8);
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
