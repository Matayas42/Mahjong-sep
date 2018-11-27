package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_isPair {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// return true
	public void isPair1() {
		Set test = new Set();
		test.fillSetFromString("D1,D1");
		boolean check = Set.isPair(test.getTiles());
		assertTrue(check);
	}
	
	@Test
	// return false
	public void isPair2() {
		Set test = new Set();
		test.fillSetFromString("D1,D2");
		boolean check = Set.isPair(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void isPair3() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1");
		boolean check = Set.isPair(test.getTiles());
		assertFalse(check);
	}
	
}
