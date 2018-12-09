package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Tile;

public class Test_Meld_isPong {

	@Test
	public void pong1() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Pong",tile);
		boolean res = meld.isPong();
		assertTrue(res);
	}
	
	@Test
	public void pong2() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Chow",tile);
		boolean res = meld.isPong();
		assertFalse(res);
	}
	
	@Test
	public void pong3() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Kong",tile);
		boolean res = meld.isPong();
		assertFalse(res);
	}
	
	@Test
	public void pong4() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Eyes",tile);
		boolean res = meld.isPong();
		assertFalse(res);
	}
	
}
