package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Test_isFourOfAKind {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// return true
	public void fourOfAKind1() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,D1");
		boolean check = Set.isFourOfAKind(test.getTiles());
		assertTrue(check);
	}
	
	@Test
	// return false
	public void fourOfAKind2() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1");
		boolean check = Set.isFourOfAKind(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void fourOfAKind3() {
		Set test = new Set();
		test.fillSetFromString("D2,D1,D1,D1");
		boolean check = Set.isFourOfAKind(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void fourOfAKind4() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D1,D1");
		boolean check = Set.isFourOfAKind(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void fourOfAKind5() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D2,D1");
		boolean check = Set.isFourOfAKind(test.getTiles());
		assertFalse(check);
	}
	
}
