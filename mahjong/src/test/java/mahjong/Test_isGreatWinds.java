package mahjong;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class Test_isGreatWinds {
	
	class Set_stub_true extends Set{
		public boolean isWinningSet() {
			return true;
		}
	}
	
	class Set_stub_false extends Set{
		public boolean isWinningSet() {
			return false;
		}
	}

	@Test
	public void GreatWind1() throws Exception {
		Game g = new Game(false);
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C1,C1");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isGreatWinds(meldList))check = true;
		}
		assertTrue(check);
	}

	@Test
	public void GreatWind2() throws Exception {
		Game g = new Game(false);
		Set_stub_false test = new Set_stub_false();
		test.fillSetFromString("W1,W1,W1,W2,W2,W3,W3,W3,W4,W4,W4,B4,B4,B4");
		List<List<Meld>> listToTest = test.allPossibleHands(test.getTiles());
		boolean check = false;
		for (List<Meld> meldList : listToTest) {
			if(test.isGreatWinds(meldList))check = true;
		}
		assertFalse(check);
	}
	
}


