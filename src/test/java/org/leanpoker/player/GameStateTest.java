package org.leanpoker.player;

import org.junit.Test;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Suit;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameStateTest {

    @Test
    public void testHaveCards() {
        GameStateBase gs = new GameStateBase();
        List<Card> lc = Arrays.asList(
                new Card("A", Suit.clubs),
                new Card("Q", Suit.clubs),
                new Card("10", Suit.diamonds));
        assertTrue(gs.haveCards(lc, true, "A", "Q"));
        assertFalse(gs.haveCards(lc, true, "A", "10"));
        assertTrue(gs.haveCards(lc, false, "A", "10"));
        assertTrue(gs.haveCards(lc, false, "A", "Q", "10"));

        assertFalse(gs.haveCards(lc, false, "A", "Q", "K"));
        assertFalse(gs.haveCards(lc, false, "K"));
        assertFalse(gs.haveCards(lc, true, "A", "Q", "K"));

       /* lc = Arrays.asList(
                new Card("A", Suit.clubs),
                new Card("Q", Suit.diamonds),
                new Card("10", Suit.clubs));
        assertFalse(gs.haveCards(lc, true, "A", "Q"));
*/

    }
}