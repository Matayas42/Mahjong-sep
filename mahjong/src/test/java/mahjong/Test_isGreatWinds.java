package mahjong;

import static org.junit.Assert.*;

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
	public void GreatWind1() {
		Game g = new Game(false);
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C1,C1");
		assertTrue(test.isGreatWinds());
	}

	@Test
	public void GreatWind2() {
		Game g = new Game(false);
		Set_stub_false test = new Set_stub_false();
		test.fillSetFromString("W1,W1,W1,W2,W2,W3,W3,W3,W4,W4,W4,B4,B4,B4");
		assertFalse(test.isGreatWinds());
	}
	
}


