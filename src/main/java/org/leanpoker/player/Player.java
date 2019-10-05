package org.leanpoker.player;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

import org.leanpoker.player.model.Game;

public class Player {

    static final String VERSION = "All-in always, jvm8";

    public static int betRequest(JsonElement request) {
        return Integer.MAX_VALUE;
    }

    public static void showdown(JsonElement game) {
    }
    
    private Game toGame(JsonElement request) {
    	return new GsonBuilder().create().fromJson(request, Game.class);
    }

    private int[] getHand(Game game) {
    	
    	
    	return null;
    }
}
