package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test_isAllHonorTiles {

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
	public void AllHonorTiles1() throws Exception {
		Set_stub_true test = new Set_stub_true();
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
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllHonorTiles(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void AllHonorTiles3() throws Exception {
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("B1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllHonorTiles(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// simplest test case
	public void AllHonorTiles4() throws Exception {
		Set_stub_false test = new Set_stub_false();
		test.fillSetFromString("W1,W1,W2,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllHonorTiles(meldList))check = true;
		}
		assertFalse(check);
	}
}
