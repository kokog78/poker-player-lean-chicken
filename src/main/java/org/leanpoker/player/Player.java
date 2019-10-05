package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "All-in always, jvm8";

    public static int betRequest(JsonElement request) {
        return Integer.MAX_VALUE;
    }

    public static void showdown(JsonElement game) {
    }

    private int[] getHand(JsonElement request) {
    	
    	int in_action = request.getAsJsonObject().get("in_action").getAsInt();
    	return null;
    }
}
