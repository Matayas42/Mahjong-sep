package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_Tile_getType {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void type1() {
		Set test = new Set();
		test.fillSetFromString("B1");
		String res = test.getTileAt(0).getType();
		assertEquals(res, "Bamboo");
	}
	
	@Test
	// simply true
	public void type2() {
		Set test = new Set();
		test.fillSetFromString("C1");
		String res = test.getTileAt(0).getType();
		assertEquals(res, "Character");
	}
	
	@Test
	// simply true
	public void type3() {
		Set test = new Set();
		test.fillSetFromString("D1");
		String res = test.getTileAt(0).getType();
		assertEquals(res, "Dot");
	}
	
	@Test
	// simply true
	public void type4() {
		Set test = new Set();
		test.fillSetFromString("W1");
		String res = test.getTileAt(0).getType();
		assertEquals(res, "Wind");
	}
	
	@Test
	// simply true
	public void type5() {
		Set test = new Set();
		test.fillSetFromString("G1");
		String res = test.getTileAt(0).getType();
		assertEquals(res, "Dragon");
	}
	
}
