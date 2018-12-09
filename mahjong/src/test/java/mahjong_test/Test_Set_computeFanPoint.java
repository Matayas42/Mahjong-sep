package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.Game;
import mahjong.Set;

public class Test_Set_computeFanPoint {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	// All Honor Tiles, Max points
	public void computeFanPoint1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G2,G2");
		assertEquals(10,test.computeFanPoints());
	}
	
	@Test
	// Great Dragon, 8 points
	public void computeFanPoint2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,G3,G3,G3,G1,G1,G1,G2,G2,G2,W1,W1");
		assertEquals(8,test.computeFanPoints());
	}
	
	@Test
	// All One Suit, 7 points
	public void computeFanPoint3() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,B7,B7,B7,B9,B9");
		assertEquals(7,test.computeFanPoints());
	}
	
	@Test
	// Common Hand, 1 points
	public void computeFanPoint4() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,B4,B5,B6,C1,C2,C3,C4,C5,C6,G1,G1");
		assertEquals(3,test.computeFanPoints());
	}

	@Test
	// All In Tripletts, 3 points
	public void computeFanPoint5() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,D3,D3,D3,B7,B7,B7,B8,B8");
		assertEquals(3,test.computeFanPoints());
	}
	
	@Test
	// Mix One Suit, 3 points
	public void computeFanPoint6() throws Exception {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B2,B3,B4,B4,B5,B6,W1,W1,W1,B9,B9");
		assertEquals(3,test.computeFanPoints());
	}
	
	@Test
	// Small Dragon, 5 points
	public void computeFanPoint7() throws Exception {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		assertEquals(5,test.computeFanPoints());
	}
	
	@Test
	// Small Winds, Max points
	public void computeFanPoint8() throws Exception {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C1,C1,C1");
		assertEquals(10,test.computeFanPoints());
	}

	@Test
	// Great Winds, Max points
	public void computeFanPoint9() throws Exception {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C1,C1");
		assertEquals(10,test.computeFanPoints());
	}
	
	@Test
	// Seven pairs, 4 points
	public void computeFanPoint10() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D3,D3,D2,D2,D4,D4,C8,C8,C3,C3,B7,B7");
		assertEquals(4,test.computeFanPoints());
	}
	
	@Test
	// All Orphans, Max points
	public void computeFanPoint11() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,B1,B1,B1,C1,C1,C1,C9,C9,C9,G1,G1");
		assertEquals(10,test.computeFanPoints());
	}
}
