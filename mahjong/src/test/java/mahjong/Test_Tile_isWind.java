package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.*;

public class Test_Tile_isWind {
	
	@Test
	public void wind1() throws InvalidTileException {
		Tile t1 = new Tile('W',1);
		assertTrue(t1.isWind());
	}
	
	@Test
	public void wind2() throws InvalidTileException {
		Tile t2 = new Tile('C',1);
		assertFalse(t2.isWind());
	}
	
}
