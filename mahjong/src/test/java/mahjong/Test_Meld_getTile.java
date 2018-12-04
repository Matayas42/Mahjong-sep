package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Tile;

public class Test_Meld_getTile {

	@Test
	public void getTile1() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Pong",tile);
		Tile res = meld.getTile();
		assertEquals(res, tile);
	}
	
	@Test
	public void getTile2() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Kong",tile);
		Tile res = meld.getTile();
		assertEquals(res, tile);
	}
	
	@Test
	public void getTile3() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Chow",tile);
		Tile res = meld.getTile();
		assertEquals(res, tile);
	}
	
	@Test
	public void getTile4() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Eyes",tile);
		Tile res = meld.getTile();
		assertEquals(res, tile);
	}
	
	@Test
	public void getTile5() throws Exception {
		Tile tile = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		Meld meld = new Meld("Pong",tile);
		Tile res = meld.getTile();
		boolean result = res.equals(tile2);
		assertFalse(result);
	}
	
	@Test
	public void getTile6() throws Exception {
		Tile tile = new Tile('B',1);
		Tile tile2 = new Tile('D',1);
		Meld meld = new Meld("Pong",tile);
		Tile res = meld.getTile();
		boolean result = res.equals(tile2);
		assertFalse(result);
	}
	
	@Test
	public void getTile7() throws Exception {
		Tile tile = new Tile('B',1);
		Tile tile2 = new Tile('W',1);
		Meld meld = new Meld("Pong",tile);
		Tile res = meld.getTile();
		boolean result = res.equals(tile2);
		assertFalse(result);
	}
	
	@Test
	public void getTile8() throws Exception {
		Tile tile = new Tile('B',1);
		Tile tile2 = new Tile('G',1);
		Meld meld = new Meld("Pong",tile);
		Tile res = meld.getTile();
		boolean result = res.equals(tile2);
		assertFalse(result);
	}
	
}
