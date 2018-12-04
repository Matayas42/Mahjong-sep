package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Test;

import mahjong.InvalidTileException;
import mahjong.Tile;

public class Test_Tile_getShorthand {

	@Test
	public void shortHand1() throws InvalidTileException {
		Tile t1 = new Tile('G',1);
		String res = "G1";
		assertEquals(res, t1.getShorthand());
	}
	
	@Test
	public void shortHand2() throws InvalidTileException {
		Tile t1 = new Tile('D',1);
		String res = "D1";
		assertEquals(res, t1.getShorthand());
	}
	
	@Test
	public void shortHand3() throws InvalidTileException {
		Tile t1 = new Tile('B',1);
		String res = "B1";
		assertEquals(res, t1.getShorthand());
	}
	
	@Test
	public void shortHand4() throws InvalidTileException {
		Tile t1 = new Tile('C',1);
		String res = "C1";
		assertEquals(res, t1.getShorthand());
	}
	
	@Test
	public void shortHand5() throws InvalidTileException {
		Tile t1 = new Tile('W',1);
		String res = "W1";
		assertEquals(res, t1.getShorthand());
	}
	
}
