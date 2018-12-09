package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.*;

public class Test_Tile_isHonorTile {
	
	@Test
	public void honorTile1() throws InvalidTileException {
		Tile tile = new Tile('W',1);
		assertTrue(tile.isHonorTile());
	}
	
	@Test
	public void honorTile2() throws InvalidTileException {
		Tile tile = new Tile('G',1);
		assertTrue(tile.isHonorTile());
	}
	
	@Test
	public void honorTile3() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		assertFalse(tile.isHonorTile());
	}
	
}
