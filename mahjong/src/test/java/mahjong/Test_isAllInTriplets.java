package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test_isAllInTriplets {
	
	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void AllInTripplets1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,D3,D3,D3,B7,B7,B7,B8,B8");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// mix one suit + all in triplets
	public void AllInTripplets2() throws Exception{
		Set test = new Set();
		test.fillSetFromString("W1,W1,D1,D1,D1,D6,D6,D6,D9,D9,D9,G3,G3,G3");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// all on suit + all in triplets
	public void AllInTripplets3()throws Exception {
		Set test = new Set();
		test.fillSetFromString("B3,B3,B3,B4,B4,B4,B7,B7,B7,B8,B8,B8,B9,B9");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// not all in triplets
	public void AllInTripplets4() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,C5,D4,D4,D4,D9,D9,W1,W1,W1,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// not all in triplets
	public void AllInTripplets5() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,C5,D4,D4,D5,D9,D9,W1,W1,W1,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// 13 tiles
	public void AllInTripplets6()throws Exception {
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D9,D9,D9,W1,W1,W1,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// invalid type A5
	public void AllInTripplets7() throws Exception{
		Set test = new Set();
		test.fillSetFromString("A5,C5,C5,D4,D4,D4,D9,D9,D9,W1,W1,W1,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// invalid number D10
	public void AllInTripplets8() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D9,D9,D10,W1,W1,W1,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// too many tile D4
	public void AllInTripplets9() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D4,D4,D4,W1,W1,W1,G2,G2");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isAllInTripletts(meldList))check = true;
		}
		assertFalse(check);
	}
}
