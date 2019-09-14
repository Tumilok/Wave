package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public Player(int x, int y, ID id, Color color) {
		super(x, y, id, color);
		
		width = 128;
		height = 12;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - width);
		y = Game.clamp(y, 350, 420);
	}

	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
