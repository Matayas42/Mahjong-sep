package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_isWinningSet {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// not enough tiles to win
	public void winningSet1() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,C3,C4,C5,C5,C6,C7,W1,W1,W1,C9");
		assertFalse(test.isWinningSet());
	}
	
	@Test
	// check pair last : true
	public void winningSet2() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B9,B9");
		assertTrue(test.isWinningSet());
	}
	
	@Test
	// check pair last : false; check pair first : false
	public void winningSet3() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B7,B9");
		assertFalse(test.isWinningSet());
	}
	
	@Test
	// check pair last : false; check pair first : true
	public void winningSet4() {
		Set test = new Set();
		test.fillSetFromString("C9,C9,D1,D2,D3,D5,D5,D5,D4,D4,D4,W2,W2,W2");
		assertTrue(test.isWinningSet());
	}
	
}
