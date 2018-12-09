package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.Tile;

public class Test_Tile_getValueIndex {
	
	@Test
	// B1 = 9
	public void value1() throws Exception {
		Tile tile = new Tile('B',1);
		int res = tile.getValueIndex();
		assertEquals(res, 9);
	}
	
	@Test
	// C2 = 19
	public void value2() throws Exception {
		Tile tile = new Tile('C',2);
		int res = tile.getValueIndex();
		assertEquals(res, 19);
	}
	
	@Test
	// D3 = 2
	public void value3() throws Exception {
		Tile tile = new Tile('D',3);
		int res = tile.getValueIndex();
		assertEquals(res, 2);
	}
	
	@Test
	// W1 = 27
	public void value4() throws Exception {
		Tile tile = new Tile('W',1);
		int res = tile.getValueIndex();
		assertEquals(res, 27);
	}
	
	@Test
	// G2 = 37
	public void value5() throws Exception {
		Tile tile = new Tile('G',2);
		int res = tile.getValueIndex();
		assertEquals(res, 37);
	}
	
}
