package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;

public class Test_isSmallWinds {
	
	@Test
	public void SmallWind1() throws Exception {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C1,C1,C1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallWinds(meldList))check = true;
		}
		assertTrue(check);
	}

	@Test
	public void SmallWind2() throws Exception {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W3,W3,W3,W4,W4,W4,B4,B4,B4");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallWinds(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	public void SmallWind3() throws Exception {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W4,W4,W4,D7,D7,D7");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallWinds(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	public void SmallWind4() throws Exception  {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,G3,G3,G3");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallWinds(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	public void SmallWind5() throws Exception  {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,C3,C3,C3,D4,D4,D3,D3,D3");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallWinds(meldList))check = true;
		}
		assertFalse(check);
	}
}
