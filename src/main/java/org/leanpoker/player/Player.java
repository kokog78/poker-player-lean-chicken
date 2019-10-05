package org.leanpoker.player;

import java.util.List;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Game;
import org.leanpoker.player.model.PlayerDto;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "All-in always, with stack";

    public static int betRequest(JsonElement request) {
    	Game game = toGame(request);
    	HandEvaluation eval = new HandEvaluation(getHand(game));
    	if (eval.doAllHands()) {
    		return getActivePlayer(game).stack;
    	} else {
    		return 0;
    	}
    }

    public static void showdown(JsonElement game) {
    }
    
    private static Game toGame(JsonElement request) {
    	return new GsonBuilder().create().fromJson(request, Game.class);
    }
    
    public static PlayerDto getActivePlayer(Game game) {
    	return game.players.get(game.in_action);
    }

    private static List<Card> getHand(Game game) {
    	return getActivePlayer(game).hole_cards;
    }
    
    
}
