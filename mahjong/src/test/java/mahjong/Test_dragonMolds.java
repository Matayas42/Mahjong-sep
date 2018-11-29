package mahjong;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Test_dragonMolds {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// null after removing pair
	public void dragonMolds1() {
		Set test = new Set();
		test.fillSetFromString("C1,C1");
		int res = test.tryDragonMolds();
		assertEquals(res, -1);
	}
	
	@Test
	// 3 four of a kind in G; run 3 loops ONLY --> -1
	public void dragonMolds21() {
		Set test = new Set();
		test.fillSetFromString("G1,G1,G1,G1,G2,G2,G2,G2,G3,G3,G3,G3,W1,W1");
		int res = test.tryDragonMolds();
		assertEquals(res, -1);
	}
	
	@Test
	// 2 three of a kind in G
	public void dragonMolds3() {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		int res = test.tryDragonMolds();
		assertEquals(res, 2);
	}
	
	@Test
	// three consecutive is not considered!
	public void dragonMolds31() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		int res = test.tryDragonMolds();
		assertEquals(res, -1);
	}
	
	@Test
	// all three of a kind
	public void dragonMolds4() {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,B1,B1,B1,B2,B2,B2,G3,G3");
		int res = test.tryDragonMolds();
		assertEquals(res, 0);
	}
	
	
	@Test
	// all three consecutive
	public void dragonMolds5() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,D2,D3,D4,B1,B2,B3,B4,B5,B6,G3,G3");
		int res = test.tryDragonMolds();
		assertEquals(res, -1);
	}
	
	@Test
	// not enough molds
	public void dragonMolds6() {
		Set test = new Set();
		test.fillSetFromString("G1,G1,G1,G1,G2,G2,G2,B2,B3,B4,B5,B6,G3,G3");
		int res = test.tryDragonMolds();
		assertEquals(res, -1);
	}
}
