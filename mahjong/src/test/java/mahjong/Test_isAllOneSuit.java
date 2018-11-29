package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
	
public class Test_isAllOneSuit {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	class Set_stub_true extends Set{
		public boolean isWinningSet() {
			return true;
		}
	}
	
	class Set_stub_false extends Set{
		public boolean isWinningSet() {
			return false;
		}
	}
	
	@Test
	// simplest test case
	public void AllOneSuit1() throws Exception {
		Set_stub_true test = new Set_stub_true();
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
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("C1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B9,B9");
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
		Set_stub_false test = new Set_stub_false();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B8,B9,B7,B7");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllOneSuit(meldList))check = true;
		}
		assertTrue(check);
	}
}
