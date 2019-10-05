package org.leanpoker.player;

import org.leanpoker.player.model.Game;
import org.leanpoker.player.model.PlayerDto;

public class HandEvaluation {

	private final GameState state;
	
	public HandEvaluation(GameState state) {
		this.state = state;
	}
	
	public int getBet() {
		if (doAllHands()) {
    		return getActivePlayer(state.game).stack;
    	} else {
    		return 0;
    	}
	}
	
	public boolean doAllHands() {
		return true;
	}
	
	public PlayerDto getActivePlayer(Game game) {
    	return game.players.get(game.in_action);
    }
	
}
