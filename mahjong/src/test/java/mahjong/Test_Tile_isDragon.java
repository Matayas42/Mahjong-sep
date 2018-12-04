package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.*;

public class Test_Tile_isDragon {

	
	@Test
	public void dragon1() throws InvalidTileException {
		Tile tile = new Tile('G',1);
		assertTrue(tile.isDragon());
	}
	
	@Test
	public void dragon2() throws InvalidTileException {
		Tile tile = new Tile('C',1);
		assertFalse(tile.isDragon());
	}
	
}
