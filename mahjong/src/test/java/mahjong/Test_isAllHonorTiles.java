package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;
import mahjong.Test_isAllOneSuit.Set_stub_false;
import mahjong.Test_isAllOneSuit.Set_stub_true;

public class Test_isAllHonorTiles {

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
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G1,G1");
		assertTrue(test.isAllOneSuit());
	}
	
	@Test
	// simplest test case
	public void AllOneSuit1_1() {
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,Gragon1,Gragon1");
		assertTrue(test.isAllOneSuit());
	}
	
	@Test
	// simplest test case
	public void AllOneSuit2() {
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("B1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	// simplest test case
	public void AllOneSuit3() {
		Set_stub_false test = new Set_stub_false();
		test.fillSetFromString("W1,W1,W2,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		assertFalse(test.isAllOneSuit());
	}
}
