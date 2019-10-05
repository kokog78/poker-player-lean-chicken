package org.leanpoker.player;

import java.util.List;

import org.leanpoker.player.model.Card;

public class HandEvaluation {

	private final List<Card> hand;
	
	public HandEvaluation(List<Card> hand) {
		this.hand = hand;
	}
	
	public boolean doAllHands() {
		return true;
	}
	
}
