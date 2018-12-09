package mahjong_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;

public class Test_isAllHonorTiles {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void AllHonorTiles1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllHonorTiles(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void AllHonorTiles2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,C3,C3,C3,D4,D4,D4,B2,B2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllHonorTiles(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// simplest test case
	public void AllHonorTiles3() throws Exception {
		Set test = new Set();
		test.fillSetFromString("W1,W1,G3,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllHonorTiles(meldList))check = true;
		}
		assertFalse(check);
	}
}
