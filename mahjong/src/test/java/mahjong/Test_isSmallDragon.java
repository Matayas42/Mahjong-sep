package mahjong_test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.*;

public class Test_isSmallDragon {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void smallDragon1() {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		boolean res = test.trySmallDragon();
		assertTrue(res);
	}
	
	@Test
	// pair not in G
	public void smallDragon2() {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,W1,W1");
		boolean res = test.trySmallDragon();
		assertFalse(res);
	}
	
	@Test
	// only 1 dragon mold
	public void smallDragon3() {
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,B1,B1,B1,G2,G2,G2,G1,G1");
		boolean res = test.trySmallDragon();
		assertFalse(res);
	}
	
}
