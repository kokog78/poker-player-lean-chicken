package org.leanpoker.player;

import java.util.List;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Game;
import org.leanpoker.player.model.PlayerDto;

public class HandEvaluation {

	private final GameState state;
	
	public HandEvaluation(GameState state) {
		this.state = state;
	}
	
	public int getBet() {
		if (doAllIn()) {
			return getMinRaise();
    	} else if (!wasRaised()) {
    		int minimum = getMinimalValue();
    		if (minimum <= getStack()) {
    			return minimum;
    		}
    	}
		return 0;
	}
	
	private boolean doAllIn() {
		return isPair() || isAX() || isKQ() || isKJs() || isKTs();
	}
	
	private int getAllInValue() {
		return getActivePlayer(state.game).stack;
	}
	
	private int getStack() {
		return getActivePlayer(state.game).stack;
	}
	
	private boolean wasRaised() {
		return state.game.current_buy_in > getBigBlindValue();
	}
	
	private int getMinRaise() {
		return state.game.current_buy_in * 2;
	}
	
	private int getEffectiveStackSize() {
		int stack = getStack();
		int result = 0;
		for (PlayerDto player : state.game.players) {
			if (player.stack >= stack) {
				result = stack;
				break;
			} else if (player.stack > result) {
				result = player.stack;
			}
		}
		return result / getBigBlindValue();
	}
	
	private boolean cardsMatch(String ... rules) {
		List<Card> cards = state.getAllCards();
		for (String rule : rules) {
			CardsRule cr = new CardsRule(rule);
			if (!cr.matches(cards)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isPreFlop() {
		return state.getAllCards().size() == 2;
	}
	
	private int getBigBlindValue() {
		return state.game.small_blind * 2;
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
	
	private boolean isKJs() {
		return state.haveKandJSameColor();
	}
	
	private boolean isKTs() {
		return state.haveKand10SameColor();
	}
	
	private boolean isKQ() {
		return state.haveKandQInAll();
	}
	
}
