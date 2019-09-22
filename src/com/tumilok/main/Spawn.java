package com.tumilok.main;

public class Spawn {

    Handler handler;

    private boolean isLevel = true;

    public Spawn(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        if (HUD.score == HUD.level * 68) {
            HUD.level++;
            isLevel = true;
        }

        if (isLevel) {
            int size = handler.object.size();
            for (int i = 0; i < size; i++) {
                handler.removeObject(handler.object.get(0));
            }

            if (HUD.level == 1) level1();
            else if (HUD.level == 2) level2();
        }
    }

    private void level1 () {
        handler.addObject(new Player(Game.WIDTH / 2 - 64, Game.HEIGHT - 50, ID.Player, 128, 12));
        handler.addObject(new Ball(Game.WIDTH / 2 - 6, Game.HEIGHT - 62, ID.Ball, 12, 12, handler));
        Game.isStart = false;
        isLevel = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                switch (i) {
                    case 0:
                        if (j == 0 || j == 1 || j == 18 || j == 19)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 1:
                        if (j == 0 || j == 19 || j == 6 || j == 7 || j == 12 || j == 13)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 2:
                        if (j == 3 || j == 7 || j == 8 || j == 11 || j == 12 || j == 16)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 3:
                        if (j == 3 || j == 7 || j == 8 || j == 9 || j == 10 || j == 11 || j == 12 || j == 16)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 4:
                        if (j == 2 || j == 4 || j == 8 || j == 11 || j == 15 || j == 17)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 5:
                        if (j == 2 || j == 4 || j == 8 || j == 11 || j == 15 || j == 17)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 6:
                        if (j == 3 || j == 7 || j == 8 || j == 9 || j == 10 || j == 11 || j == 12 || j == 16)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 7:
                        if (j == 3 || j == 7 || j == 8 || j == 11 || j == 12 || j == 16)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 8:
                        if (j == 0 || j == 19 || j == 6 || j == 7 || j == 12 || j == 13)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                    case 9:
                        if (j == 0 || j == 1 || j == 18 || j == 19)
                            handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                        break;
                }
                if (i == 4 || i == 5) {
                    if (j == 3 || j == 9 || j == 10 || j == 16)
                        handler.addObject(new EasyBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                }
            }
        }

    }

    private void level2(){
        handler.addObject(new Player(Game.WIDTH/2 - 64, Game.HEIGHT - 50, ID.Player, 128, 12));
        handler.addObject(new Ball(Game.WIDTH/2 - 6, Game.HEIGHT - 62, ID.Ball, 12, 12, handler));
        Game.isStart = false;
        isLevel = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 3 - i; j < 17 + i; j++) {
                if (j % 2 == 0)
                    handler.addObject(new BasicBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));
                else
                    handler.addObject(new EasyBrick(3 + 51 * j, 100 + 19 * i, ID.Brick, handler));

            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = i; j < 20 - i; j++) {
                if (i % 2 == 0)
                    handler.addObject(new BasicBrick(3 + 51 * j, 176 + 19 * i, ID.Brick, handler));
                else
                    handler.addObject(new EasyBrick(3 + 51 * j, 176 + 19 * i, ID.Brick, handler));
            }
        }
    }
}