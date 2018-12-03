package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_Tile_isHonorTile {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void honorTile1() {
		Set test = new Set();
		test.fillSetFromString("W1");
		Tile t1 = test.getTileAt(0);
		assertTrue(t1.isHonorTile());
	}
	
	@Test
	// simply true
	public void honorTile2() {
		Set test = new Set();
		test.fillSetFromString("G1");
		Tile t2 = test.getTileAt(0);
		assertTrue(t2.isHonorTile());
	}
	
	@Test
	// simply false
	public void honorTile3() {
		Set test = new Set();
		test.fillSetFromString("C1");
		Tile t3 = test.getTileAt(0);
		assertFalse(t3.isHonorTile());
	}
	
}
