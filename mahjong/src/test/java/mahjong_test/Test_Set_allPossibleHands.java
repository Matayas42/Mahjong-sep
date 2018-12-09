package mahjong_test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mahjong.Game;
import mahjong.Meld;
import mahjong.Set;

public class Test_Set_allPossibleHands {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	@Test
	public void allPossibleHands1() throws Exception {
		Set test = new Set(); 
		test.fillSetFromString("B1,B1,B1,C4,C5,C6,D2,D3,D4,W1,W1,W1,W1,B9,B9");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		assertNotNull(listToTest);
	}
}
