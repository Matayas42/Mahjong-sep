package mahjong_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_isAllOrphans {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void allOrphans1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,B1,B1,B1,C1,C1,C1,C9,C9,C9,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOrphans(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// B2 -> False
	public void allOrphans2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,B2,B2,B2,C1,C1,C1,C9,C9,C9,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOrphans(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// Have Kong 
	public void allOrphans3() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,B1,B1,B1,B1,C1,C1,C1,C9,C9,C9,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOrphans(meldList))check = true;
		}
		assertTrue(check);
	}
	
	// if Kong happens, melds != 4 
	
}
