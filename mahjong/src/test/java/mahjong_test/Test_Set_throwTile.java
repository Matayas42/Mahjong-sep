package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.Game;
import mahjong.Set;
import mahjong.Tile;

public class Test_Set_throwTile {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void throwTile1() throws Exception {
		Set test = new Set(); 
		test.fillSetFromString("B1,B1,B1,C4,C5,C6,D2,D3,D4,W1,W1,W1,W1,B9,B9");
		List<Tile> subList = test.getTiles();
		assertEquals(subList.get(1),test.throwTile(1));
	}
	
	@Test
	public void throwTile2() throws Exception {
		Set test = new Set(); 
		test.fillSetFromString("");
		assertNull(test.throwTile(1));
	}

}
