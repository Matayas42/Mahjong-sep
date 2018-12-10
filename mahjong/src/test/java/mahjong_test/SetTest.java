package mahjong_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SetTest {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void getTiles1() throws InvalidTileException {
		Set test = new Set();
		Tile tile1 = new Tile('C', 1);
		Tile tile2 = new Tile('C', 2);
		Tile tile3 = new Tile('C', 3);
		List<Tile> tileList = new ArrayList<Tile>();
		tileList.add(0,tile1);
		tileList.add(1,tile2);
		tileList.add(2,tile3);
		test.fillSetFromString("C1,C2,C3");
		assertEquals(tileList,test.getTiles());
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
	public void getTileAt1() throws InvalidTileException {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		Tile tile1 = new Tile('D', 1);
		assertEquals(tile1,test.getTileAt(0));
	}
}
