package org.leanpoker.player;

import static org.junit.Assert.*;

import org.junit.Test;

public class RankOrderTest {

	@Test
	public void test_1() throws Exception {
		assertTrue(RankOrder.instance.compare("6", "5") > 0);
	}
	
	@Test
	public void test_2() throws Exception {
		assertTrue(RankOrder.instance.compare("6", "J") < 0);
	}
	
	@Test
	public void test_3() throws Exception {
		assertTrue(RankOrder.instance.compare("6", "6") == 0);
	}
	
}
