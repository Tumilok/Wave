package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		width = 128;
		height = 12;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - width);
		y = Game.clamp(y, 350, 430);
	}

	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}

}
