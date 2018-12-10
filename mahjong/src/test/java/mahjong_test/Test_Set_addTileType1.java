package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.Game;
import mahjong.InvalidTileException;
import mahjong.Set;
import mahjong.Tile;

public class Test_Set_addTileType1 {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void addTileType1_1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		Tile tile = new Tile('C',5);
		boolean check = test.addTile(tile,false,false);
		assertTrue(check);
	}
	
	@Test
	public void addTileType1_2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D6,D7,D8");
		Tile tile = new Tile('C',5);
		boolean check = test.addTile(tile,false,false);
		assertTrue(check);
	}
	
	@Test
	public void addTileType1_3() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		Tile tile = new Tile('C',1);
		boolean check = test.addTile(tile,true,false);
		assertTrue(check);
	}
	
	@Test
	public void addTileType1_4() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		Tile tile = new Tile('C',1);
		boolean check = test.addTile(tile,true,false);
		assertTrue(check);
	}
	
}
