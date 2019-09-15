package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class BasicBrick extends GameObject{
	
	private int greenValue;

	public BasicBrick(int x, int y, ID id) {
		super(x, y, id);
		
		width = 48;
		height = 16;
		health = 1;
	}

	public void tick() {
		greenValue = 255 - health * 100;
		
	}

	public void render(Graphics g) {
		g.setColor(new Color(50, greenValue, 100));
		g.fillRect(x, y, width, height);
	}
}
