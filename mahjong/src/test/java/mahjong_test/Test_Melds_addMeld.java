package mahjong_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import mahjong.Meld;
import mahjong.Melds;
import mahjong.Tile;

public class Test_Melds_addMeld {

	@Test
	public void addMelds1() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		Tile tile3 = new Tile('D',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Meld meld1 = new Meld("Pong",tile1);
		Meld meld2 = new Meld("Kong",tile2);
		Meld meld3 = new Meld("Chow",tile3);
		List<Meld> meldList = Arrays.asList(meld1, meld2);
		
		Melds melds = new Melds(meldList, tileList);
		melds.addMeld(meld3);
		ArrayList<Meld> res = new ArrayList<Meld>();
		res.add(meld1);
		res.add(meld2);
		res.add(meld3);
		assertEquals(res, melds.getMelds());
	}
	
	@Test
	public void addMelds2() throws Exception {
		Tile tile1 = new Tile('B',1);
		Tile tile2 = new Tile('C',1);
		Tile tile3 = new Tile('D',1);
		Tile tile4 = new Tile('W',1);
		List<Tile> tileList = Arrays.asList(tile1, tile2);
		
		Meld meld1 = new Meld("Pong",tile1);
		Meld meld2 = new Meld("Kong",tile2);
		Meld meld3 = new Meld("Chow",tile3);
		Meld meld4 = new Meld("Eyes",tile4);
		List<Meld> meldList = Arrays.asList(meld1, meld2);
		
		Melds melds = new Melds(meldList, tileList);
		melds.addMeld(meld4);
		ArrayList<Meld> res = new ArrayList<Meld>();
		res.add(meld1);
		res.add(meld2);
		res.add(meld3);
		boolean result = res.equals(melds.getMelds());
		assertFalse(result);
	}
	
}
