package mahjong_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Set;


public class Test_Set_fillSetFromString {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void fillSetFromString1() {
		Set test = new Set();
		boolean check = test.fillSetFromString("D1,D2,D3");
		assertTrue(check);
	}
	
	@Test
	public void fillSetFromString2() {
		Set test = new Set();
		boolean check = test.fillSetFromString("D1.D2,D3");
		assertFalse(check);
	}

	@Test
	public void fillSetFromString3() {
		Set test = new Set();
		boolean check = test.fillSetFromString("");
		assertFalse(check);
	}
}
