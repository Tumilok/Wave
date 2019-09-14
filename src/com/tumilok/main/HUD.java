package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int LIVES = 3;
	
	public void tick() {
		LIVES = Game.clamp(LIVES, 0, 3);
		if (LIVES <= 0) System.exit(1);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH/2 - 30, 10, 60, 20);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH/2 - 30, 10, LIVES*20, 20);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2 - 30, 10, 60, 20);
	}
}
