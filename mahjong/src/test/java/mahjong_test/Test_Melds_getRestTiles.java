package mahjong_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import mahjong.Melds;
import mahjong.Tile;

public class Test_Melds_getRestTiles {

	@Test
	public void getRestTiles1() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Melds melds = new Melds(tileList);
		ArrayList<Tile> res = new ArrayList<Tile>();
		res.add(tile1);
		res.add(tile2);
		assertEquals(res, melds.getRestTiles());
	}
	
	@Test
	public void getRestTiles2() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Melds melds = new Melds(tileList);
		ArrayList<Tile> res = new ArrayList<Tile>();
		res.add(tile2);
		res.add(tile1);
		boolean result = res.equals(melds.getRestTiles());
		assertFalse(result);
	}
	
}
