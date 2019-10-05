package org.leanpoker.player;

import org.leanpoker.player.model.Game;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "All-in always, with stack";

    public static int betRequest(JsonElement request) {
    	Game game = toGame(request);
    	HandEvaluation eval = new HandEvaluation(new GameState(game));
    	return eval.getBet();
    }

    public static void showdown(JsonElement game) {
    }
    
    private static Game toGame(JsonElement request) {
    	return new GsonBuilder().create().fromJson(request, Game.class);
    }
    
    
}
