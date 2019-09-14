package com.tumilok.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1622234583316783243L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	public boolean running = false;
	public static boolean isStart = false;
	
	private Handler handler;
	private HUD hud;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Let's Roll!", this);
		
		hud = new HUD();
		
		Player player = new Player(256, 420, ID.Player);
		Ball ball = new Ball(314, 408, ID.Ball, handler, player);
		
		handler.addObject(player);
		handler.addObject(ball);
		for (int i = 0; i < 7; i+=2) {
			for (int j = i; j < 17 - i; j+=2) {
				handler.addObject(new BasicBrick(32 + 34 * j, 64 + 32 * i, ID.BasicBrick));
			}
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) render();
			frames++;
			 
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var <= min) return var = min;
		else if (var >= max) return var = max;
		else return var;
	}
	
	public static boolean intersects(GameObject rect, Ball circle)
	{
		int DeltaX = circle.getCenterX() - Math.max(rect.getX(),
				Math.min(circle.getCenterX(), rect.getX() + rect.getWidth()));
		int DeltaY = circle.getCenterY() - Math.max(rect.getY(),
				Math.min(circle.getCenterY(), rect.getY() + rect.getHeight()));
		
		return (DeltaX * DeltaX + DeltaY * DeltaY) < (circle.getRadius() * circle.getRadius());
	}	
	
	public static void main(String[] args) {
		new Game();
	}
}
