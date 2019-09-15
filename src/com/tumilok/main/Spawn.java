package com.tumilok.main;

import java.awt.Color;

public class Spawn {

    Handler handler;

    private boolean isLevel = true;

    public Spawn(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        if (HUD.score >= HUD.level * 46) {
            HUD.level++;
            isLevel = true;
        }

        if (isLevel) {
            int size = handler.object.size();
            for (int i = 0; i < size; i++){
                handler.removeObject(handler.object.get(0));
            }

            if (HUD.level == 1) {
                handler.addObject(new Player(256, 420, ID.Player, Color.white));
                handler.addObject(new Ball(314, 408, ID.Ball, Color.red, handler));

                for (int i = 0; i < 6; i++)
                    for (int j = 0; j < 10; j++)
                        if (10 - j != i + 1 && j != i)
                            if (i != 4 || j != 1 && j != 8)
                                handler.addObject(new BasicBrick(8 + 64 * j, 64 + 26 * i, ID.Brick));
            }
            if (HUD.level == 2) {
                handler.addObject(new Player(256, 420, ID.Player, Color.white));
                handler.addObject(new Ball(314, 408, ID.Ball, Color.red, handler));
                Game.isStart = false;

                for (int i = 0; i < 6; i++)
                    for (int j = 0; j < 10; j++)
                        handler.addObject(new BasicBrick(8 + 64 * j, 64 + 26 * i, ID.Brick));
            }
            isLevel = false;
        }
    }
}