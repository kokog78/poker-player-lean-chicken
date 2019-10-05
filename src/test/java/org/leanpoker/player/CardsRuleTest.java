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
	
	
	
}
