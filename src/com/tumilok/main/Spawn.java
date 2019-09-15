package com.tumilok.main;

public class Spawn {
	
	Handler handler;
	
	public Spawn(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {		
		if (HUD.score >= HUD.level * 10) {
			HUD.level++;
		}
	}
}
