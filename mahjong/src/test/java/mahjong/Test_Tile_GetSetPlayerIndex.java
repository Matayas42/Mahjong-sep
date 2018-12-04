package mahjong_test;

import static org.junit.Assert.*;

import mahjong.InvalidTileException;
import mahjong.Tile;
import org.junit.Test;

public class Test_Tile_GetSetPlayerIndex {

	@Test
	public void playerIndex1() throws InvalidTileException {
		Tile tile = new Tile('B',1);
		int res = tile.getPlayerIndex();
		assertEquals(res, -1);
	}
	
	@Test
	public void playerIndex2() throws InvalidTileException {
		Tile tile = new Tile('B',1);
		tile.setPlayerIndex(3);
		int res = tile.getPlayerIndex();
		assertEquals(res, 3);
	}
	
}
