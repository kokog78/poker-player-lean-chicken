package org.leanpoker.player;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Suit;

public class CardsRuleTest {

	@Test
	public void testName() throws Exception {
		List<Card> lc = Arrays.asList(
                new Card("K", Suit.clubs),
                new Card("Q", Suit.clubs),
                new Card("10", Suit.diamonds));
		assertTrue(new CardsRule("KQs").matches(lc));
		assertTrue(new CardsRule("K10o").matches(lc));
		assertFalse(new CardsRule("K10s").matches(lc));
		assertFalse(new CardsRule("810o").matches(lc));
	}
	
	@Test
	public void testName1() throws Exception {
		List<Card> lc = Arrays.asList(
                new Card("K", Suit.spades),
                new Card("2", Suit.clubs));
		assertFalse(new CardsRule("KQs").matches(lc));
		
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("J", rank) <= 0) {
				assertFalse(new CardsRule("A" + rank + "o").matches(lc));
			}
		}

		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("10", rank) <= 0) {
				assertFalse(new CardsRule("A" + rank + "s").matches(lc));
			}
		}
		
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("6", rank) <= 0) {
				System.out.println(rank);
				assertFalse(new CardsRule(rank + rank + "o").matches(lc));
			}
		}
	}
	
	
	@Test
	public void testName2() throws Exception {
		List<Card> lc = Arrays.asList(
                new Card("3", Suit.hearts),
                new Card("10", Suit.hearts));
		assertFalse(new CardsRule("KQs").matches(lc));
		
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("J", rank) <= 0) {
				assertFalse(new CardsRule("A" + rank + "o").matches(lc));
			}
		}

		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("10", rank) <= 0) {
				assertFalse(new CardsRule("A" + rank + "s").matches(lc));
			}
		}
		
		for (String rank : RankOrder.ranks) {
			if (RankOrder.instance.compare("6", rank) <= 0) {
				System.out.println(rank);
				assertFalse(new CardsRule(rank + rank + "o").matches(lc));
			}
		}
	}
	
	
	
	
}
