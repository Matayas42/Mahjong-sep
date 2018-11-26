package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;
	
public class Test_isAllOneSuit {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	class Set_stub_true extends Set{
		public boolean isWinningSet() {
			return true;
		}
	}
	
	class Set_stub_false extends Set{
		public boolean isWinningSet() {
			return false;
		}
	}
	
	@Test
	// simplest test case
	public void AllOneSuit1() {
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B9,B9");
		assertTrue(test.isAllOneSuit());
	}
	
	@Test
	// simplest test case
	public void AllOneSuit2() {
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("C1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B9,B9");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	// simplest test case
	public void AllOneSuit3() {
		Set_stub_false test = new Set_stub_false();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B8,B9");
		assertFalse(test.isAllOneSuit());
	}
}
