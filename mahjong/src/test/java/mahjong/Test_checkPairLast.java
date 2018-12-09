package mahjong_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Set;

public class Test_checkPairLast {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// null ArrayList
	public void pairLast1() {
		Set test = new Set();
		test.fillSetFromString("");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return false; only threeOfAKind & threeConsecutive path
	public void pairLast2() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C1,C2,C3");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return true; find threeOfAKind first
	public void pairLast3() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,B1,C1");
		assertTrue(test.pairLast());
	}
	
	@Test
	// return false; find threeOfAKind first but no pair at last
	public void pairLast4() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,B1,C2");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return true; find threeOfAKind first and pair last
	public void pairLast5() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,C1,B1");
		assertTrue(test.pairLast());
	}
	
	@Test
	// return false; find threeOfAKind first but no pair at last
	public void pairLast6() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return false; pair first
	public void pairLast7() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,C2");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return false; can't find threeOfAKind & three consecutive first but tempList.size() > i+4
	public void pairLast8() {
		Set test = new Set();
		test.fillSetFromString("B1,B2,B2,B3,B4");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return true; can't find three consecutive first and pair last
	public void pairLast9() {
		Set test = new Set();
		test.fillSetFromString("B1,B2,B3,C1,C1");
		assertTrue(test.pairLast());
	}
	
	@Test
	// return false; can't find threeOfAKind & threeConsecutive first and tempList.size() <= i+3
	public void pairLast10() {
		Set test = new Set();
		test.fillSetFromString("B1,B2,B2");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return false; can't find threeOfAKind & threeConsecutive first and tempList.size() <= i+4
	public void pairLast11() {
		Set test = new Set();
		test.fillSetFromString("B1,B2,B2,B3");
		assertFalse(test.pairLast());
	}
	
}
