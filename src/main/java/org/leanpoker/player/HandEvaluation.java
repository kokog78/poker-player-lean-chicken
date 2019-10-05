package org.leanpoker.player;

import org.leanpoker.player.model.Game;
import org.leanpoker.player.model.PlayerDto;

public class HandEvaluation {

	private final GameState state;
	
	public HandEvaluation(GameState state) {
		this.state = state;
	}
	
	public int getBet() {
		if (doAllIn()) {
    		return getAllInValue();
    	} else {
    		return 0;
    	}
	}
	
	private boolean doAllIn() {
		return isPair() || isAX() || isKQs() || isKJs() || isKTs() || isKQo();
	}
	
	private int getAllInValue() {
		return getActivePlayer(state.game).stack;
	}
	
	private PlayerDto getActivePlayer(Game game) {
    	return game.players.get(game.in_action);
    }
	
	private boolean isPair() {
		return state.havePair();
	}
	
	private boolean isAX() {
		return false;
	}
	
	private boolean isKQs() {
		return false;
	}
	
	private boolean isKJs() {
		return false;
	}
	
	private boolean isKTs() {
		return false;
	}
	
	private boolean isKQo() {
		return false;
	}
	
}
