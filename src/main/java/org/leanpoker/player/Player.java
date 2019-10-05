package org.leanpoker.player;

import org.leanpoker.player.model.Game;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "All-in always, with stack";

    public static int betRequest(JsonElement request) {
    	Game game = toGame(request);
        return getActivePlayer(game).stack;
    }

    public static void showdown(JsonElement game) {
    }
    
    private static Game toGame(JsonElement request) {
    	return new GsonBuilder().create().fromJson(request, Game.class);
    }
    
    public static org.leanpoker.player.model.Player getActivePlayer(Game game) {
    	return game.players.get(game.in_action);
    }

    private static int[] getHand(Game game) {
    	
    	
    	return null;
    }
}
