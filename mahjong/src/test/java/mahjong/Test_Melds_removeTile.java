package mahjong_test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import mahjong.Melds;
import mahjong.Tile;

public class Test_Melds_removeTile {

	@Test
	public void removeTile1() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Melds melds = new Melds(tileList);
		melds.removeTile();
		ArrayList<Tile> res = new ArrayList<Tile>();
		res.add(tile2);
		assertEquals(res, melds.getRestTiles());
	}
	
	@Test
	public void removeTile2() throws Exception {
		ArrayList<Tile> tileList = new ArrayList<>();
		
		Melds melds = new Melds(tileList);
		
		assertEquals(null, melds.removeTile());
	}
	
}
