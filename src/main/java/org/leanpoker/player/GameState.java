package org.leanpoker.player;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Game;
import org.leanpoker.player.model.PlayerDto;
import org.leanpoker.player.model.Suit;

import java.util.*;

public class GameState extends GameStateBase {

	public final Game game;
	
	public GameState(Game game) {
		this.game = game;
	}

	public boolean isHeadsUp() {
		return false;
		//this.game.players.forEach(p -> p.status);
	}

	public PlayerDto getMyPlayer() {
		return game.players.get(game.in_action);

	}
	public List<Card> getAllCards() {
		List<Card> ret = new ArrayList<>();
		if (game.community_cards != null) {
			ret.addAll(game.community_cards);
		}
		if (getMyPlayer().hole_cards != null) {
			ret.addAll(getMyPlayer().hole_cards);
		}
		return ret;
	}

	public Card getPair() {
		return getPair(getAllCards());

	}

	public boolean havePair() {
		return getPair() != null;
	}

	public Card getPair(List<Card> cards ) {
		//List<Card> cards = getAllCards();
		for (int i = 0; i < cards.size(); i++) {
			for (int j = i + 1; j < cards.size(); j++) {
				if (cards.get(i).rank.equals(cards.get(j).rank)) {
					return cards.get(i);
				}
			}
		}
		return null;
	}




	public boolean haveAce() {
		return 	haveCards(getAllCards(), false, "A");
	}

	public boolean haveKandQInAll() {
		return 	haveCards(getAllCards(), false, "K", "Q");
	}

	public boolean haveKandJSameColor() {
		return 	haveCards(getAllCards(), true, "K", "J");

	}

	public boolean haveKand10SameColor() {
		return 	haveCards(getAllCards(), true, "K", "10");
	}

	public boolean haveKandQinAllSameColor() {
		return 	haveCards(getAllCards(), true, "K", "Q");
	}

	public boolean haveDrill() {
		return maxSameCard(getAllCards()) == 3;
	}

	public boolean havePoker() {
		return maxSameCard(getAllCards()) == 4;
	}

	public boolean have2Pair() {
		Card c = getPair();
		if (c == null) {
			return false;
		}
		List<Card> clist = new ArrayList<>();
		clist.addAll(getAllCards());
		clist.removeIf(c2 -> c2.rank == c.rank);
		return getPair(clist) != null;
	}

	public boolean haveFullHouse() {
		List<Card> clist = new ArrayList<>();
		clist.addAll(getAllCards());
		if (maxSameCard(clist) == 3) {
			String rank = maxSameCardRank(clist);
			clist.removeIf(c -> c.rank.equals(rank));
			return maxSameCard(clist) > 1;
		}
		return false;
	}


	public boolean haveStraight() {
		List<Card> clist = new ArrayList<>();
		clist.addAll(getAllCards());
		clist.sort(Comparator.comparing(c -> c.rank));
		int maxLength = 0;
		int length = 0;
		if (clist.stream().anyMatch(c -> c.rank.equals("A"))) {
			length = 1;
		}
		for (int i = 0; i < CARD_RANKS.length; i++) {
			boolean found = false;

			for(int j = 0; j< clist.size(); j++) {
				if (clist.get(i).rank.equals(CARD_RANKS[i])) {
					found = true;
				}
			}
			if (!found) {
				length = 0;
			} else {
				length++;
				if (length > maxLength) {
					maxLength = length;
				}
			}
		}
		return maxLength >= 5;
	}

}

/*public boolean haveRankInHand(String... rank) {
		List<Card> allcards = getMyPlayer().hole_cards;
		for (int j = 0; j < rank.length; j++) {
			boolean foundIt = false;
			for (int i = 0; i < allcards.size(); i++) {
				foundIt = allcards.get(i).rank.equals(rank.equals(j));
				if (foundIt) break;
			}
			if (!foundIt) return false;
		}
		return true;
	}*/

	/*public boolean haveRankInAllCards(String... rank) {
		List<Card> allcards = getAllCards();
		for (int j = 0; j < rank.length; j++) {
			boolean foundIt = false;
			for (int i = 0; i < allcards.size(); i++) {
				foundIt = allcards.get(i).rank.equals(rank.equals(j));
				if (foundIt) break;
			}
			if (!foundIt) return false;
		}
		return true;
	}*/

/**
 {
 "tournament_id":"550d1d68cd7bd10003000003",     // Id of the current tournament

 "game_id":"550da1cb2d909006e90004b1",           // Id of the current sit'n'go game. You can use this to link a
 // sequence of game states together for logging purposes, or to
 // make sure that the same strategy is played for an entire game

 "round":0,                                      // Index of the current round within a sit'n'go

 "bet_index":0,                                  // Index of the betting opportunity within a round

 "small_blind": 10,                              // The small blind in the current round. The big blind is twice the
 //     small blind

 "current_buy_in": 320,                          // The amount of the largest current bet from any one player

 "pot": 400,                                     // The size of the pot (sum of the player bets)

 "minimum_raise": 240,                           // Minimum raise amount. To raise you have to return at least:
 //     current_buy_in - players[in_action][bet] + minimum_raise

 "dealer": 1,                                    // The index of the player on the dealer button in this round
 //     The first player is (dealer+1)%(players.length)

 "orbits": 7,                                    // Number of orbits completed. (The number of times the dealer
 //     button returned to the same player.)

 "in_action": 1,                                 // The index of your player, in the players array

 "players": [                                    // An array of the players. The order stays the same during the
 {                                           //     entire tournament

 "id": 0,                                // Id of the player (same as the index)

 "name": "Albert",                       // Name specified in the tournament config

 "status": "active",                     // Status of the player:
 //   - active: the player can make bets, and win the current pot
 //   - folded: the player folded, and gave up interest in
 //       the current pot. They can return in the next round.
 //   - out: the player lost all chips, and is out of this sit'n'go

 "version": "Default random player",     // Version identifier returned by the player

 "stack": 1010,                          // Amount of chips still available for the player. (Not including
 //     the chips the player bet in this round.)

 "bet": 320                              // The amount of chips the player put into the pot
 },
 {
 "id": 1,                                // Your own player looks similar, with one extension.
 "name": "Bob",
 "status": "active",
 "version": "Default random player",
 "stack": 1590,
 "bet": 80,
 "hole_cards": [                         // The cards of the player. This is only visible for your own player
 //     except after showdown, when cards revealed are also included.
 {
 "rank": "6",                    // Rank of the card. Possible values are numbers 2-10 and J,Q,K,A
 "suit": "hearts"                // Suit of the card. Possible values are: clubs,spades,hearts,diamonds
 },
 {
 "rank": "K",
 "suit": "spades"
 }
 ]
 },
 {
 "id": 2,
 "name": "Chuck",
 "status": "out",
 "version": "Default random player",
 "stack": 0,
 "bet": 0
 }
 ],
 "community_cards": [                            // Finally the array of community cards.
 {
 "rank": "4",
 "suit": "spades"
 },
 {
 "rank": "A",
 "suit": "hearts"
 },
 {
 "rank": "6",
 "suit": "clubs"
 }
 ]
 */
