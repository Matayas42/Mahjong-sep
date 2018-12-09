package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;

public class Test_isGreatDragon {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void greatDragon1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,G3,G3,G3,G1,G1,G1,G2,G2,G2,W1,W1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isGreatDragon(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// ONLY 2 dragon molds
	public void greatDragon2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isGreatDragon(meldList))check = true;
		}
		assertFalse(check);
	}
	
}
