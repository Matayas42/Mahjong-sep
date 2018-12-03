package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_checkPairFirst {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// no pair at all, immediately jump to return false
	public void pairFirst1() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3");
		assertFalse(test.pairFirst());
	}
	
	@Test
	// pairs found and run main loop
	public void pairFirst2() {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C2,C2,C1");
		assertTrue(test.pairFirst());
	}
	
	@Test
	// consecutive found and run main loop
	public void pairFirst3() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C4,C4,C3");
		assertTrue(test.pairFirst());
	}
	
	@Test
	// a pair found and does not run 2nd for loop
	public void pairFirst4() {
		Set test = new Set();
		test.fillSetFromString("C1,C1");
		assertTrue(test.pairFirst());
	}
	
}
