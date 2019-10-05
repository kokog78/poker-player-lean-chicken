package org.leanpoker.player;

import org.leanpoker.player.model.Game;
import org.leanpoker.player.model.PlayerDto;

public class HandEvaluation {

	private final GameState state;
	
	public HandEvaluation(GameState state) {
		this.state = state;
	}
	
	public int getBet() {
		if (isPreFlop()) {
			if (isFirstIn()) {
				return getMinRaise();
			} else {
				if (isATsPlus() || isAJPlus() || isKQs()) {
					return getMinRaise();
				} else if (weFaceRaiseMaxInBB(2.5)) {
					return getMinRaise();
				} else {
					return 0;
				}
			}
		} else {
			if (isFlop()) {
				if (weFacingNoBet()) {
					return getBigBlindValue();
				} if (weFaceRaiseMaxInBB(3)) {
					return getMinRaise();
				}
			}
			if (is2PairsOrBetter()) {
				return getAllInValue();
			}
		}

		if (doAllIn()) {
			return getMinRaise();
    	} else if (!wasRaised()) {
    		int minimum = getBigBlindValue() * 2;
    		if (minimum <= getStack()) {
    			return minimum;
    		}
    	}
		return 0;
	}
	
	private boolean is66Plus() {
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("6", rank) <= 0) {
				if (hasCards(rank + rank + "o")) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isATsPlus() {
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("10", rank) <= 0) {
				if (hasCards("A" + rank + "s")) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isAJPlus() {
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("J", rank) <= 0) {
				if (hasCards("A" + rank + "o")) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean is2PairsOrBetter() {
		return state.havePoker() || state.haveFullHouse() || state.haveStraight() || state.haveDrill() || state.have2Pair();
	}
	
	private boolean doAllIn() {
		return isPair() || isAX() || isKQ() || isKJs() || isKTs();
	}
	
	private int getAllInValue() {
		return getActivePlayer(state.game).stack;
	}
	
	private int getPot() {
		return state.game.pot;
	}
	
	private boolean weFacingNoBet() {
		return state.game.current_buy_in.equals(0);
	}
	
	private int getStack() {
		return getActivePlayer(state.game).stack;
	}
	
	private boolean wasRaised() {
		return state.game.current_buy_in > getBigBlindValue();
	}
	
	private boolean isFirstIn() {
		return isPreFlop() && state.game.current_buy_in.equals(getBigBlindValue());
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
	
	private boolean hasCards(boolean sameColor, String... ranks) {
		return state.haveCards(state.getAllCards(), sameColor, ranks);
	}
	
	private boolean hasCards(String rule) {
		return new CardsRule(rule).matches(state.getAllCards());
	}
	
	private boolean isPreFlop() {
		return state.getAllCards().size() == 2;
	}
	
	private boolean isFlop() {
		return state.getAllCards().size() == 5;
	}
	
	private int getBigBlindValue() {
		return state.game.small_blind * 2;
	}
	
	private boolean weFaceRaiseMaxInBB(double bb) {
		return state.game.current_buy_in <= getBigBlindValue() * bb;
	}
	
	private int getMinRaise() {
		return state.game.current_buy_in * 2;
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
	
	private boolean isKQs() {
		return hasCards("KQs");
	}
}
