package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test_isCommonHand {
	
	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void commonHand1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,B4,B5,B6,C1,C2,C3,C4,C5,C6,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// not common hand
	public void commonHand2()  throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D2,B4,B5,B6,C1,C2,C3,C4,C5,C6,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// confusing cases T -> F
	public void commonHand3()  throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,B4,B5,B6,D1,D2,D3,C4,C5,C6,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// 13 tiles
	public void commonHand4() throws Exception  {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,B4,B5,B6,D1,D2,D3,C4,C5,C6,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// invalid type A1
	public void commonHand5() throws Exception  {
		Set test = new Set();
		test.fillSetFromString("A1,C2,C3,B4,B5,B6,D1,D2,D3,C4,C5,C6,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// invalid number D10
	public void commonHand6()  throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,B4,B5,B6,D2,D2,D3,C4,C5,C6,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// too many tile C3
	public void commonHand7()  throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,C1,C2,C3,C1,C2,C3,C1,C2,C3,C3,C4"
				+ "");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isCommonHand(meldList))check = true;
		}
		assertFalse(check);
	}
}