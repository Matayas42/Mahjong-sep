package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.*;

public class Test_Tile_getType {
	
	@Test
	public void type1() throws InvalidTileException {
		Tile tile = new Tile('B',1);
		String res = tile.getType();
		assertEquals(res, "Bamboo");
	}
	
	@Test
	public void type2() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		String res = tile.getType();
		assertEquals(res, "Character");
	}
	
	@Test
	public void type3() throws InvalidTileException {
		Tile tile = new Tile('D',1);
		String res = tile.getType();
		assertEquals(res, "Dot");
	}
	
	@Test
	public void type4() throws InvalidTileException {
		Tile tile = new Tile('W',1);
		String res = tile.getType();
		assertEquals(res, "Wind");
	}
	
	@Test
	public void type5() throws InvalidTileException {
		Tile tile = new Tile('G',1);
		String res = tile.getType();
		assertEquals(res, "Dragon");
	}
	
}
