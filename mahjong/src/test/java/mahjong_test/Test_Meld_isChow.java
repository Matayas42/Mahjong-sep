package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Tile;

public class Test_Meld_isChow {

	@Test
	public void chow1() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Chow",tile);
		boolean res = meld.isChow();
		assertTrue(res);
	}
	
	@Test
	public void chow2() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Pong",tile);
		boolean res = meld.isChow();
		assertFalse(res);
	}
	
	@Test
	public void chow3() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Kong",tile);
		boolean res = meld.isChow();
		assertFalse(res);
	}
	
	@Test
	public void chow4() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Eyes",tile);
		boolean res = meld.isChow();
		assertFalse(res);
	}
	
}
