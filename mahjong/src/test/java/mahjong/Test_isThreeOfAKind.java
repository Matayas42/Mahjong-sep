package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Test_isThreeOfAKind {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// return true
	public void threeOfAKind1() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1");
		boolean check = Set.isThreeOfAKind(test.getTiles());
		assertTrue(check);
	}
	
	@Test
	// return false
	public void threeOfAKind2() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D2");
		boolean check = Set.isThreeOfAKind(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void threeOfAKind3() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D2");
		boolean check = Set.isThreeOfAKind(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void threeOfAKind4() {
		Set test = new Set();
		test.fillSetFromString("D2,D2");
		boolean check = Set.isThreeOfAKind(test.getTiles());
		assertFalse(check);
	}
	
}
