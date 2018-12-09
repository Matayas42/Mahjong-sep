package mahjong_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mahjong.Tile;

public class Test_Tile_getNumberRange {
	
	@Test
	public void range1() {
		int res = Tile.getNumberRange('D');
		assertEquals(res, 9);
	}
	
	@Test
	public void range2() {
		int res = Tile.getNumberRange('B');
		assertEquals(res, 9);
	}
	
	@Test
	public void range3() {
		int res = Tile.getNumberRange('C');
		assertEquals(res, 9);
	}
	
	@Test
	public void range4() {
		int res = Tile.getNumberRange('W');
		assertEquals(res, 4);
	}
	
	@Test
	public void range5() {
		int res = Tile.getNumberRange('G');
		assertEquals(res, 3);
	}
	
	@Test
	public void range6() {
		int res = Tile.getNumberRange('A');
		assertEquals(res, -1);
	}
	
}
