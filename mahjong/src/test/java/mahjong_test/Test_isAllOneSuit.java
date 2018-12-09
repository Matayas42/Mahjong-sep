package mahjong_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;
	
public class Test_isAllOneSuit {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void AllOneSuit1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B9,B9");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOneSuit(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void AllOneSuit2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,C2,C3,C4,C4,C5,C6,C7,C7,C7,D9,D9");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOneSuit(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// simplest test case
	public void AllOneSuit3() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B8,B9,B7,B7");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOneSuit(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void AllOneSuit4() throws Exception {
		Set test = new Set();
		test.fillSetFromString("");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
				if(test.isAllOneSuit(meldList))check = true;
		}
		assertFalse(check);
	}
}
