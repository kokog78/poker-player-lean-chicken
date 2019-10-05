package org.leanpoker.player;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStateBase {

    public static final String[] CARD_RANKS = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    protected Map<String, List<Card>> cardsMap(List<Card> cards) {
        Map<String, List<Card>> rankMap = new HashMap<>();

        cards.forEach(c -> {
            if (!rankMap.containsKey(c.rank)) {
                rankMap.put(c.rank, new ArrayList<Card>());
            }
            rankMap.get(c.rank).add(c);
        });
        return rankMap;
    }

    public boolean haveCards(List<Card> cards, boolean sameColor, String... rank) {
        Map<String, List<Card>> rankMap = cardsMap(cards);
        boolean clubs = true,spades = true,hearts = true,diamonds = true;
        for(int i = 0; i < rank.length; i++) {
            if (rankMap.get(rank[i]) == null || rankMap.get(rank[i]).size() == 0) {
                return false;
            } else {
                clubs = clubs && rankMap.get(rank[i]).stream().anyMatch(c -> c.suit.equals(Suit.clubs));
                spades = spades && rankMap.get(rank[i]).stream().anyMatch(c -> c.suit.equals(Suit.spades));
                hearts = hearts && rankMap.get(rank[i]).stream().anyMatch(c -> c.suit.equals(Suit.hearts));
                diamonds = diamonds && rankMap.get(rank[i]).stream().anyMatch(c -> c.suit.equals(Suit.diamonds));
            }
        }
        if (sameColor) {
            return clubs || spades || hearts || diamonds;
        } else {
            return true;
        }
    }

    public int maxSameCard(List<Card> cards) {
        Map<String, List<Card>> rankMap = cardsMap(cards);
        int max = 0;
        for (String rank : CARD_RANKS) {
            if (rankMap.containsKey(rank)) {
                int size = rankMap.get(rank).size();
                if (max < size) {
                    max = size;
                }
            }
        }
        return max;
    }




}
