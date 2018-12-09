package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;
import mahjong.Tile;

public class Test_canChow {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simplest test case
	public void chow1() throws Exception {
		Set test = new Set();
		Tile tile = new Tile('C',1);
		test.fillSetFromString("C2,C3");
		boolean check = test.canChow(tile);
		assertTrue(check);
	}
	
	@Test
	// simplest test case
	public void chow2() throws Exception {
		Set test = new Set();
		Tile tile = new Tile('C',9);
		test.fillSetFromString("C2,C3");
		boolean check = test.canChow(tile);
		assertFalse(check);
	}

}
