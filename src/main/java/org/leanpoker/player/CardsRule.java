package org.leanpoker.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Suit;

public class CardsRule {

	private String rank1;
	private String rank2;
	private boolean sameSuit;
	
	private String PATTERN = "(2|3|4|5|6|7|8|9|10|J|Q|K|A)(2|3|4|5|6|7|8|9|10|J|Q|K|A)(.)";
	
	public CardsRule(String rule) {
		Matcher m = Pattern.compile(PATTERN).matcher(rule);
		if (m.find()) {
			rank1 = m.group(1);
			rank2 = m.group(2);
			sameSuit = m.group(3).equals("s");
		}
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
