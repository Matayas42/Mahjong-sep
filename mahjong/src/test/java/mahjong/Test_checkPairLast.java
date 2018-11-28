package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
	// return true; find threeOfAKind & threeConsecutive first
	public void pairLast3() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,B1,C1");
		assertTrue(test.pairLast());
	}
	
	@Test
	// return false; find threeOfAKind & threeConsecutive first but no pair at last
	public void pairLast4() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,B1,C2");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return false; find threeOfAKind & threeConsecutive first but no pair at last
	public void pairLast5() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,C1,B1");
		assertTrue(test.pairLast());
	}
	
	@Test
	// return false; find threeOfAKind & threeConsecutive first but no pair at last
	public void pairLast6() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2");
		assertFalse(test.pairLast());
	}
	
	@Test
	// return false; find threeOfAKind & threeConsecutive first but no pair at last
	public void pairLast7() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,C1,C2");
		assertFalse(test.pairLast());
	}
	
	
	
}
