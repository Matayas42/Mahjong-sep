package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;
import mahjong.Tile;

public class Test_canPong {
	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void pong1() throws Exception {
		Set test = new Set();
		Tile tile = new Tile('B',1);
		test.fillSetFromString("B1,B1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.canPong(tile))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void pong2() throws Exception {
		Set test = new Set();
		Tile tile = new Tile('B',2);
		test.fillSetFromString("B1,B1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.canPong(tile))check = true;
		}
		assertFalse(check);
	}
	
}
