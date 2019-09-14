package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class BasicBrick extends GameObject{

	public BasicBrick(int x, int y, ID id) {
		super(x, y, id);
		
		width = 48;
		height = 16;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
}
