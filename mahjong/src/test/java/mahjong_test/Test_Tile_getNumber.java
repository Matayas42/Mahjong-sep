package mahjong_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mahjong.InvalidTileException;
import mahjong.Tile;

public class Test_Tile_getNumber {
	
	@Test
	public void number1() throws InvalidTileException {
		Tile tile = new Tile('B',1);
		int res = tile.getNumber();
		assertEquals(res, 1);
	}
	
	@Test
	public void number2() throws InvalidTileException {
		Tile tile = new Tile('C',2);
		int res = tile.getNumber();
		assertEquals(res, 2);
	}
	
	@Test
	public void number3() throws InvalidTileException {
		Tile tile = new Tile('D',3);
		int res = tile.getNumber();
		assertEquals(res, 3);
	}
	
	@Test
	public void number4() throws InvalidTileException {
		Tile tile = new Tile('W',4);
		int res = tile.getNumber();
		assertEquals(res, 4);
	}
	
	@Test
	public void number5() throws InvalidTileException {
		Tile tile = new Tile('G',3);
		int res = tile.getNumber();
		assertEquals(res, 3);
	}
}
