package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_Tile_isDragon {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void dragon1() {
		Set test = new Set();
		test.fillSetFromString("G1");
		Tile t1 = test.getTileAt(0);
		assertTrue(t1.isDragon());
	}
	
	@Test
	// simply false
	public void dragon2() {
		Set test = new Set();
		test.fillSetFromString("D1");
		Tile t2 = test.getTileAt(0);
		assertFalse(t2.isDragon());
	}
	
}
