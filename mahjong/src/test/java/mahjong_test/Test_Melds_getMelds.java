package mahjong_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Melds;
import mahjong.Tile;

public class Test_Melds_getMelds {

	@Test
	public void getMelds1() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Meld meld1 = new Meld("Pong",tile1);
		Meld meld2 = new Meld("Kong",tile2);
		List<Meld> meldList = Arrays.asList(meld1, meld2);
		
		Melds melds = new Melds(meldList, tileList);
		ArrayList<Meld> res = new ArrayList<Meld>();
		res.add(meld1);
		res.add(meld2);
		assertEquals(res, melds.getMelds());
	}
	
	@Test
	public void getMelds2() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Meld meld1 = new Meld("Pong",tile1);
		Meld meld2 = new Meld("Kong",tile2);
		List<Meld> meldList = Arrays.asList(meld1, meld2);
		
		Melds melds = new Melds(meldList, tileList);
		ArrayList<Meld> res = new ArrayList<Meld>();
		res.add(meld2);
		res.add(meld1);
		boolean result = res.equals(melds.getMelds());
		assertFalse(result);
	}
	
}
