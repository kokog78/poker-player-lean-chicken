package org.leanpoker.player.model;

import java.util.List;

public class Game {

	public Integer in_action;
	public List<PlayerDto> players;
	public String tournament_id;
	public String game_id;
	public Integer round;
	public Integer bet_index;
	public Integer small_blind;
	public Integer orbits;
	public Integer dealer;
	public Card[] community_cards;
	public Integer current_buy_in;
	public Integer pot;
}
