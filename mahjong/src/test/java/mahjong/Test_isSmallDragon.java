package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test_isSmallDragon {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}
	
	@Test
	// simply true
	public void smallDragon1() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallDragon(meldList))check = true;
		}
		assertTrue(check);
	}
	
	@Test
	// pair not in G
	public void smallDragon2() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,W1,W1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallDragon(meldList))check = true;
		}
		assertFalse(check);
	}
	
	@Test
	// only 1 dragon mold
	public void smallDragon3() throws Exception{
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,B1,B1,B1,G2,G2,G2,G1,G1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isSmallDragon(meldList))check = true;
		}
		assertFalse(check);
	}
	
}
