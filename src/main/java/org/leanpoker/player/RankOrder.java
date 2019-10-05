package org.leanpoker.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankOrder implements Comparator<String> {

	public static List<String> ranks = new ArrayList<String>();
	
	public static RankOrder instance = new RankOrder();

	public RankOrder() {
		ranks.add("2");
		ranks.add("3");
		ranks.add("4");
		ranks.add("5");
		ranks.add("6");
		ranks.add("7");
		ranks.add("8");
		ranks.add("9");
		ranks.add("10");
		ranks.add("J");
		ranks.add("Q");
		ranks.add("K");
		ranks.add("A");
	}
	
	@Override
	public int compare(String o1, String o2) {
		return ranks.indexOf(o1) - ranks.indexOf(o2);
	}
	
	
	
}
