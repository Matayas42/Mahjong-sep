package mahjong_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Tile;

public class Test_Meld_toString {

	@Test
	public void string1() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Pong",tile);
		String res = meld.toString();
		assertEquals(res, "Pong Bamboo1");
	}
	
	@Test
	public void string2() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Kong",tile);
		String res = meld.toString();
		assertEquals(res, "Kong Bamboo1");
	}
	
	@Test
	public void string3() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Chow",tile);
		String res = meld.toString();
		assertEquals(res, "Chow Bamboo1");
	}
	
	@Test
	public void string4() throws Exception {
		Tile tile = new Tile('B',1);
		Meld meld = new Meld("Eyes",tile);
		String res = meld.toString();
		assertEquals(res, "Eyes Bamboo1");
	}
	
}
