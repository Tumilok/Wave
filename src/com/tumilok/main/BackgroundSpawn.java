package com.tumilok.main;

public class BackgroundSpawn {
	Handler handler;

    private boolean reset = true;

    public BackgroundSpawn(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
    	reset = true;
    	int i = 0;
    	while (i < handler.object.size()) {
    		if (handler.object.get(i).getID() == ID.MenuBrick) reset = false;
    		i++;
    	}
    	
        if (reset) {
            int size = handler.object.size();
            for (i = 0; i < size; i++) {
                handler.removeObject(handler.object.get(0));
            }
            
            handler.addObject(new BotPlatform(Game.WIDTH/2 - 64, Game.HEIGHT - 50, ID.BotPlatform, 128, 12, handler));
            handler.addObject(new MenuBall(Game.WIDTH/2 - 6, Game.HEIGHT - 62, ID.MenuBall, 12, 12, handler));
            reset = false;

            for (i = 0; i < 8; i++) {
                for (int j = i; j < 20 - i; j++) {
                    handler.addObject(new MenuBrick(3 + 51 * j, 100 + 19 * i, ID.MenuBrick, handler));
                }
            }
        }
    }
}
