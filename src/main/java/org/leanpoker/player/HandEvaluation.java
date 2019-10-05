package org.leanpoker.player;

import java.util.List;

import org.leanpoker.player.model.Card;

public class HandEvaluation {

	private final List<Card> hand;
	private final List<Card> community_cards;
	
	public HandEvaluation(List<Card> hand, List<Card> community_cards) {
		this.hand = hand;
		this.community_cards = community_cards;
	}
	
	public boolean doAllHands() {
		return true;
	}
	
}
