package com.tumilok.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == State.Menu) {

            if (mouseOver(mx, my, 362, 275, 300, 68)) {
                Game.gameState = State.Game;
            } else if (mouseOver(mx, my, 362, 375, 300, 68)) {
                Game.gameState = State.Help;
            } else if (mouseOver(mx, my, 362, 475, 300, 68)) {
                Game.gameState = State.Exit;
            }
        }

        if (Game.gameState == State.Help) {
            if (mouseOver(mx, my, 362, 600, 300, 68)) {
                Game.gameState = State.Menu;
            }
        }

        if (Game.gameState == State.End) {
            if (mouseOver(mx, my, 362, 600, 300, 68)) {
                Game.gameState = State.Menu;
            }
        }
        
        if (Game.gameState == State.Exit) {
            if (mouseOver(mx, my, 340, 350, 150, 50)) {
                System.exit(0);
            }
            else if (mouseOver(mx, my, 534, 350, 150, 50)) {
            	Game.gameState = State.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
    	Font fnt = new Font("arial", 4, 70);
    	Font fnt2 = new Font("arial", 1, 45);
    	Font fnt3 = new Font("arial", 3, 20);
    	Font fnt4 = new Font("arial", 1, 23);
    	
    	g.setColor(Color.white);
    	
        if (Game.gameState == State.Menu) {
            g.setFont(fnt);
            g.drawString("WAVE", 410, 150);

            g.setFont(fnt2);
            g.drawRect(362, 275, 300, 68);
            g.drawString("Play", 465, 325);
            g.drawRect(362, 375, 300, 68);
            g.drawString("Help", 465, 425);
            g.drawRect(362, 475, 300, 68);
            g.drawString("Exit", 465, 525);
        }
        else if (Game.gameState == State.Help) {
            

            g.setFont(fnt);
            g.drawString("HELP", 410, 150);
            g.drawRect(362, 600, 300, 68);

            g.setFont(fnt2);
            g.drawString("Back", 460, 650);

            g.setFont(fnt3);
            g.drawString("Press Space button to start the game.", 100, 300);
            g.drawString("Use WASD or Up, Down, Left, Right buttons to move platform", 100, 325);
        }
        else if (Game.gameState == State.End) {
            g.setFont(fnt2);
            g.drawString("Game Over!", 400, 300);
            g.drawString("Your Score is: " + HUD.score, 340, 350);
            g.drawRect(362, 600, 300, 68);
            g.drawString("Back", 465, 650);
        }
        else if (Game.gameState == State.Exit) { 
            g.setFont(fnt);
            g.drawString("WAVE", 410, 150);
           
        	g.drawRect(312, 250, 400, 200);
            g.setFont(fnt4);
            g.drawString("Do you really want to Exit?", 340, 300);
            g.drawRect(340, 350, 150, 50);
            g.drawString("YES", 385, 385);
            g.drawRect(534, 350, 150, 50);
            g.drawString("NO", 590, 385);
        }
    }
}