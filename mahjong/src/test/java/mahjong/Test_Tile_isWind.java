package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_Tile_isWind {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void wind1() {
		Set test = new Set();
		test.fillSetFromString("W1");
		Tile t1 = test.getTileAt(0);
		assertTrue(t1.isWind());
	}
	
	@Test
	// simply false
	public void wind2() {
		Set test = new Set();
		test.fillSetFromString("C1");
		Tile t2 = test.getTileAt(0);
		assertFalse(t2.isWind());
	}
	
}
