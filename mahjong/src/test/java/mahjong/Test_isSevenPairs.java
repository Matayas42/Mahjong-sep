package mahjong_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;

public class Test_isSevenPairs {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void sevenPairs1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B2,B2,C3,C3,C4,C4,D5,D5,D7,D7,W1,W1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSevenPairs(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void sevenPairs2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B2,B2,C3,C3,C4,C4,D5,D5,D7,D7,W1,W2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSevenPairs(meldList))check = true;
		}
		assertFalse(check);
	}
	
}
