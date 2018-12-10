package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
