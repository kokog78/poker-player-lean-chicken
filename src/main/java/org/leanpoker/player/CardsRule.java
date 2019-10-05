package org.leanpoker.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Suit;

public class CardsRule {

	private String rank1;
	private String rank2;
	private boolean sameSuit;
	
	public CardsRule(String rule) {
		rank1 = rule.substring(0, 1);
		rank2 = rule.substring(1, 2);
		sameSuit = rule.charAt(2) == 's';
	}
	
	public boolean matches(List<Card> cards) {
		Set<Suit> rank1Suites = new HashSet<>();
		Set<Suit> rank2Suites = new HashSet<>();
		for (Card card : cards) {
			if (card.rank.equals(rank1)) {
				rank1Suites.add(card.suit);
			}
			if (card.rank.equals(rank2)) {
				rank2Suites.add(card.suit);
			}
		}
		if (sameSuit) {
			for (Suit suit : rank1Suites) {
				if (rank2Suites.contains(suit)) {
					return true;
				}
			}
		} else {
			return !rank1Suites.isEmpty() && !rank2Suites.isEmpty();
		}
		return false;
	}
}
