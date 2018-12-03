package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;

public class Test_isMixOneSuit {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void mixOneSuit1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,W1,W1,W1,B9,B9");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isMixOneSuit(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// false cases; F -> T
	public void mixOneSuit2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,W1,W1,W1,C4,C5,C6,B9,B9");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isMixOneSuit(meldList))check = true;
		}
		assertTrue(check);
	}
}
