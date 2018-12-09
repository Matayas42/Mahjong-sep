package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SetTest {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void getTiles1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		assertNotNull(test.getTiles());
	}
	
	@Test
	public void setAndGetPlayerIndex1() {
		Set test = new Set();
		test.setPlayerIndex(1);
		assertEquals(1,test.getPlayerIndex());
	}
	
	@Test
	public void setAndIsHouse1() {
		Set test = new Set();
		test.setHouse();
		assertTrue(test.isHouse());
	}
	
	@Test
	public void getNumberOfTiles1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		assertEquals(3,test.getNumberOfTiles());
	}
	
	@Test
	public void getTileAt1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		assertNotNull(test.getTileAt(1));
	}
	
	@Test
	public void consecutive1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		boolean check = Set.isThreeConsecutive(test.getTiles());
		assertTrue(check);
	}

	// common hand
	@Test
	public void commonHand1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6,G1,G1");
		assertTrue(test.isCommonHand(null));
	}

	//All in Trippletts
	@Test
	public void AllInTripplets1() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,D3,D3,D3,B7,B7,B7,B8,B8");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets2() {
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D9,D9,D9,G1,G1,G1,G2,G2");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets3() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,D6,D6,D6,D9,D9,D9,G3,G3,G3,W1,W1");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets4() {
		Set test = new Set();
		test.fillSetFromString("B3,B3,B3,B4,B4,B4,B7,B7,B7,B8,B8,B8,B9,B9");
		assertTrue(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets5() {
		Set test = new Set();
		test.fillSetFromString("C2,C5,C5,D4,D4,D4,D9,D9,D9,G1,G1,G1,W2,W2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets6() {
		Set test = new Set();
		test.fillSetFromString("C5,C5,C5,D4,D4,D4,D9,D9,D9,G1,G1,G1,G2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets7() {
		Set test = new Set();
		test.fillSetFromString("A5, C5, C5, D4, D4, D4, D9, D9, D9, F1, F1, F1, F2, F2");
		assertFalse(test.isAllInTripletts());
	}
	
	@Test
	public void AllInTripplets8() {
		Set test = new Set();
		test.fillSetFromString("C5, C5, C5, D4, D4, D4, D9, D9, D10, F1, F1, F1, F2, F2");
		assertFalse(test.isAllInTripletts());
	}

	// Mix One Suit
	@Test
	public void mixOneSuit1() {
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,C3,C4,C5,C5,C6,C7,W1,W1,W1,C9,C9");
		assertTrue(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit2() {
		Set test = new Set();
		test.fillSetFromString("C1, C2, C3, C5, C5, C5, C6, C7, C8, G1, G1, G1, C9, C9");
		assertTrue(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit3() {
		Set test = new Set();
		test.fillSetFromString("D3, D2, D1, D5, D5, D5, D4, D4, D4, W2, W2, W2, C9, C9");
		assertTrue(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit4() {
		Set test = new Set();
		test.fillSetFromString("B3, B3, B3, B1, B1, B1, B2, B2, B2, G3, G3, G3, B4, B4");
		assertTrue(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit5() {
		Set test = new Set();
		test.fillSetFromString("C1, C2, C3, C3, C4, C5, C5, C6, C7, W1, W1, W1, C9");
		assertFalse(test.isMixOneSuit());
	}
	@Test
	public void mixOneSuit6() {
		Set test = new Set();
		test.fillSetFromString("A1, C2, C3, C3, C4, C5, C5, C6, C7, G1, G1, G1, C9, C9");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit7() {
		Set test = new Set();
		test.fillSetFromString("C10, C2, C3, C3, C4, C5, C5, C6, C7, W1, W1, W1, C9, C9");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit8() {
		Set test = new Set();
		test.fillSetFromString("C1, C2, C3, C2, C3, C4, C5, C6, C7, W1, W1, W1, C9, C9");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit9() {
		Set test = new Set();
		test.fillSetFromString("C1, C1, C3, C5, C5, C5, C6, C7, C8, F1, F1, F1, C9, C9");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit10() {
		Set test = new Set();
		test.fillSetFromString("C1, C1, C1, C1, C1, C2, C5, C5, C5, F1, F1, F1, C9, C9");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit11() {
		Set test = new Set();
		test.fillSetFromString("D4, D3, D2, D5, D5, D5, D6, D7, D8, F1, F1, F2, D9, D9");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit12() {
		Set test = new Set();
		test.fillSetFromString("D4, D3, D2, D5, D5, D5, D6, D7, D8, F1, F1, F2, D9, D8");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit13() {
		Set test = new Set();
		test.fillSetFromString("C5, C4, C3, C1, C1, C1, C8, C6, C7, F3, F3, F3, C4, C4");
		assertFalse(test.isMixOneSuit());
	}
	
	@Test
	public void mixOneSuit14() {
		Set test = new Set();
		test.fillSetFromString("D1, D2, D4, D7, C2, C4, C9, B1, B4, B5, F1, F5, F4, F2");
		assertFalse(test.isMixOneSuit());
	}

	
	//All one Suit
	@Test
	public void AllOneSuit1() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8,B8");
		assertTrue(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit2() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B4,B6,B7,B7,B7,B8,B8");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit3() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B1,B3,B4,B5,B6,B7,B7,B7,B8,B8");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit4() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8,B9");
		assertTrue(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit5() {
		Set test = new Set();
		test.fillSetFromString("B4,B4,B6,B1,B1,B1,B1,B2,B3,B7,B7,B7,B8,B8");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit6() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit7() {
		Set test = new Set();
		test.fillSetFromString("A1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8,B8");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit8() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8,B10");
		assertFalse(test.isAllOneSuit());
	}
	
	@Test
	public void AllOneSuit9() {
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8,D8");
		assertFalse(test.isAllOneSuit());
	}
	
	//All Honor Tiles
	@Test
	public void AllHonorTiles1() {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,G1,G1");
		assertTrue(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles2() {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,G2,G2,G2,G3,G3,G3,G1,G1,G1,W2,W2");
		assertTrue(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles3() {
		Set test = new Set();
		test.fillSetFromString("G1,G1,G1,G1,G1,G2,G3,G3,G3,W1,W1,W1,W3,W3");
		assertFalse(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles4() {
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W3,W3,W2,W2,W2,W4,W4,W4,G2,G2,G2");
		assertFalse(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles5() {
		Set test = new Set();
		test.fillSetFromString("G1,G1,G2,G2,G2,G2,G3,G3,G3,W3,W3,W3,W4,W4");
		assertFalse(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles6() {
		Set test = new Set();
		test.fillSetFromString("G1,G1, G1, G2, G2, G2, G3, G3, G3, G4, G4, G4, G7");
		assertFalse(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles7() {
		Set test = new Set();
		test.fillSetFromString("A1, G1, G1, G2, G2, G2, G3, G3, G3, G4, G4, G4, G7, G7");
		assertFalse(test.isAllHonorTiles());
	}
	
	@Test
	public void AllHonorTiles8() {
		Set test = new Set();
		test.fillSetFromString("G1, G1, G1, G2, G2, G2, G3, G3, G3, G4, G4, G4, G7, G8");
		assertTrue(test.isAllHonorTiles());
	}
	
	// removePair
	@Test
	public void removePair1() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6,G1,G1");
		List<Tile> l = Set.removePair(test.getTiles());
		test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6");
		boolean check = l.equals(test.getTiles());
		assertTrue(check);
	}

}
