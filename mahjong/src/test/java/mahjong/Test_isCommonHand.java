package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Test_isCommonHand {
	
	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void commonHand1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,B4,B5,B6,C1,C2,C3,C4,C5,C6,G1,G1");
		assertTrue(test.isCommonHand());
	}
	
	@Test
	// not common hand
	public void commonHand2() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D2,B4,B5,B6,C1,C2,C3,C4,C5,C6,G1,G1");
		assertFalse(test.isCommonHand());
	}
	
	@Test
	// confusing cases T -> F
	public void commonHand3() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,B4,B5,B6,D1,D2,D3,C4,C5,C6,G1,G1");
		assertTrue(test.isCommonHand());
	}
	
	@Test
	// 13 tiles
	public void commonHand4() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,B4,B5,B6,D1,D2,D3,C4,C5,C6,G1");
		assertFalse(test.isCommonHand());
	}
	
	@Test
	// invalid type A1
	public void commonHand5() {
		Set test = new Set();
		test.fillSetFromString("A1,C2,C3,B4,B5,B6,D1,D2,D3,C4,C5,C6,G1,G1");
		assertFalse(test.isCommonHand());
	}
	
	@Test
	// invalid number D10
	public void commonHand6() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,B4,B5,B6,D10,D2,D3,C4,C5,C6,G1,G1");
		assertFalse(test.isCommonHand());
	}
	
	@Test
	// too many tile C3
	public void commonHand7() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,C1,C2,C3,C1,C2,C3,C1,C2,C3,C3,C4"
				+ "");
		assertFalse(test.isCommonHand());
	}
}