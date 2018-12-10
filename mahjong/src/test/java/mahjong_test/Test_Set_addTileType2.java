package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.Game;
import mahjong.InvalidTileException;
import mahjong.Set;
import mahjong.Tile;

public class Test_Set_addTileType2 {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void addTileType2_1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		boolean check = test.addTile('C',5,false);
		assertTrue(check);
	}

}
