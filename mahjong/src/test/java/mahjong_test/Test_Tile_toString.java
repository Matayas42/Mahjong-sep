package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.InvalidTileException;
import mahjong.Tile;

public class Test_Tile_toString {
	
	@Test
	public void string1() throws InvalidTileException {
		Tile tile = new Tile('B',1);
		String res = tile.toString();
		assertEquals(res, "Bamboo1");
	}
	
	@Test
	public void string2() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		String res = tile.toString();
		assertEquals(res, "Character1");
	}
	
	@Test
	public void string3() throws InvalidTileException {
		Tile tile = new Tile('D',1);
		String res = tile.toString();
		assertEquals(res, "Dot1");
	}
	
	@Test
	public void string4() throws InvalidTileException {
		Tile tile = new Tile('W',1);
		String res = tile.toString();
		assertEquals(res, "Wind1");
	}
	
	@Test
	public void string5() throws InvalidTileException {
		Tile tile = new Tile('G',1);
		String res = tile.toString();
		assertEquals(res, "Dragon1");
	}
	
}
