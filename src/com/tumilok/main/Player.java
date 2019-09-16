package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	
	Color color;

	public Player(int x, int y, ID id, Color color) {
		super(x, y, id);
		
		width = 128;
		height = 12;
		this.color = color;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - width);
		y = Game.clamp(y, 650, Game.HEIGHT - 50);
	}

	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
