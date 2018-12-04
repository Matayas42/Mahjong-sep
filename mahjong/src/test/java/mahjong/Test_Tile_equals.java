package mahjong_test;

import org.junit.Test;

import static org.junit.Assert.*;

import mahjong.InvalidTileException;
import mahjong.Tile;

public class Test_Tile_equals {
	
	@Test
	public void equals1() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		boolean res = tile.equals(new Tile('C',1));
		assertTrue(res);
	}
	
	@Test
	public void equals2() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		boolean res = tile.equals(new Tile('B',1));
		assertFalse(res);
	}
	
	@Test
	public void equals3() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		boolean res = tile.equals(new Tile('C',2));
		assertFalse(res);
	}
}
