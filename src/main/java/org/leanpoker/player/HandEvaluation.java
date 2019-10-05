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
    	} else if (!wasRaised()) {
    		int minimum = getMinimalValue();
    		if (minimum <= getStack()) {
    			return minimum;
    		}
    	}
		return 0;
	}
	
	private boolean doAllIn() {
		return isPair() || isAX() || isKQs() || isKJs() || isKTs() || isKQo();
	}
	
	private int getAllInValue() {
		return getActivePlayer(state.game).stack;
	}
	
	private int getStack() {
		return getActivePlayer(state.game).stack;
	}
	
	private boolean wasRaised() {
		return state.game.current_buy_in > state.game.small_blind * 2;
	}
	
	private int getMinimalValue() {
		return state.game.small_blind * 4;
	}
	
	private PlayerDto getActivePlayer(Game game) {
    	return game.players.get(game.in_action);
    }
	
	private boolean isPair() {
		return state.havePair();
	}
	
	private boolean isAX() {
		return state.haveAce();
	}
	
	private boolean isKQs() {
		return false;
	}
	
	private boolean isKJs() {
		return state.haveKandJSameColor();
	}
	
	private boolean isKTs() {
		return state.haveKand10SameColor();
	}
	
	private boolean isKQo() {
		return state.haveKandQInAll();
	}
	
}
