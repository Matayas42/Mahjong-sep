package mahjong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test_removePair {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// remove pair from beginning
	public void removePair1() {
		Set test = new Set();
		test.fillSetFromString("D1,D1,D2");
		List<Tile> list = Set.removePair(test.getTiles());
		test.fillSetFromString("D2");
		boolean check = list.equals(test.getTiles());
		assertTrue(check);
	}
	
	@Test
	// remove pair from last
	public void removePair2() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D2");
		List<Tile> list = Set.removePair(test.getTiles());
		test.fillSetFromString("D1");
		boolean check = list.equals(test.getTiles());
		assertTrue(check);
	}
	
	@Test
	// no remove pair
	public void removePair3() {
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3");
		List<Tile> list = Set.removePair(test.getTiles());
		assertEquals(list, null);
	}
	
}
