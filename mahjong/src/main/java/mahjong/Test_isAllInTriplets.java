package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_isAllInTriplets {
	
	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void AllInTripplets1() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,D3,D3,D3,B7,B7,B7,B8,B8");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	// mix one suit + all in triplets
	public void AllInTripplets2() {
		Set test = new Set();
		test.fillSetFromString("W1,W1,D1,D1,D1,D6,D6,D6,D9,D9,D9,G3,G3,G3");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	// all on suit + all in triplets
	public void AllInTripplets3() {
		Set test = new Set();
		test.fillSetFromString("B3,B3,B3,B4,B4,B4,B7,B7,B7,B8,B8,B8,B9,B9");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	// not all in triplets
	public void AllInTripplets4() {
		Set test = new Set();
		test.fillSetFromString("C2,C5,C5,D4,D4,D4,D9,D9,D9,W1,W1,W1,G2,G2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	// 13 tiles
	public void AllInTripplets5() {
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D9,D9,D9,W1,W1,W1,G2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	// invalid type A5
	public void AllInTripplets6() {
		Set test = new Set();
		test.fillSetFromString("A5,C5,C5,D4,D4,D4,D9,D9,D9,W1,W1,W1,G2,G2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	// invalid number D10
	public void AllInTripplets7() {
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D9,D9,D10,W1,W1,W1,G2,G2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	// too many tile D4
	public void AllInTripplets8() {
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D4,D4,D4,W1,W1,W1,G2,G2");
		assertFalse(test.isAllInTripletts());
	}
}
