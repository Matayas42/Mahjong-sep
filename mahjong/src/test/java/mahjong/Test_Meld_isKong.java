package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Tile;

public class Test_Meld_isKong {

	@Test
	public void kong1() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Kong",tile);
		boolean res = meld.isKong();
		assertTrue(res);
	}
	
	@Test
	public void kong2() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Pong",tile);
		boolean res = meld.isKong();
		assertFalse(res);
	}
	
	@Test
	public void kong3() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Chow",tile);
		boolean res = meld.isKong();
		assertFalse(res);
	}
	
	@Test
	public void kong4() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Eyes",tile);
		boolean res = meld.isKong();
		assertFalse(res);
	}
	
}
