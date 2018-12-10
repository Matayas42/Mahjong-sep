package mahjong_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.Game;
import mahjong.Set;

public class Test_Set_addRandomTile {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void addRandomTile1() throws Exception {
		Set test = new Set(); 
		test.addRandomTiles(1);
		assertNotNull(test.getTiles());
	}

}
