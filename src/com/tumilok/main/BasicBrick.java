package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class BasicBrick extends GameObject{

	public BasicBrick(int x, int y, ID id, Color color) {
		super(x, y, id, color);
		
		width = 48;
		height = 16;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
}
