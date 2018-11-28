package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_isThreeConsecutive {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// return true
	public void consecutive1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertTrue(check);
	}
	
	@Test
	// return false
	public void consecutive2() {
		Set test = new Set();
		test.fillSetFromString("B1,D2,D3");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void consecutive3() {
		Set test = new Set();
		test.fillSetFromString("B1,B2,C3");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void consecutive4() {
		Set test = new Set();
		test.fillSetFromString("B2,B2,B3");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void consecutive5() {
		Set test = new Set();
		test.fillSetFromString("B1,B2,B2");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertFalse(check);
	}
	
	@Test
	// return false
	public void consecutive6() {
		Set test = new Set();
		test.fillSetFromString("D1,D2");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertFalse(check);
	}
	
}
