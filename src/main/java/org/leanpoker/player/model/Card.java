package org.leanpoker.player.model;

public class Card {
    public String rank;
    public Suit suit;

    public Card(String rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card() {

    }
}
