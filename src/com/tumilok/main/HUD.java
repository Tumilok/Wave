package com.tumilok.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static int LIVES = 3;
    private int greenValue, redValue;
    public static int score = 0;
    public static int level = 1;

    public void tick() {
        LIVES = Game.clamp(LIVES, 0, 3);
        if (LIVES <= 0) Game.gameState = State.End;

        greenValue = LIVES*50;
        redValue = 250 - LIVES*60;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(Game.WIDTH/2 - 30, 10, 60, 20);
        g.setColor(new Color(redValue, greenValue, 0));
        g.fillRect(Game.WIDTH/2 - 30, 10, LIVES*20, 20);
        g.setColor(Color.white);
        g.drawRect(Game.WIDTH/2 - 30, 10, 60, 20);

        g.drawString("Score: " + score, 15, 25);
        g.drawString("Level: " + level, Game.WIDTH - 70, 25);
    }
}