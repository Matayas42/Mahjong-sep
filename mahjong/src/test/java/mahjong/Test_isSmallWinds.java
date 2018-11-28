package mahjong;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Test_isSmallWinds {

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
	public void SmallWind1() {
		Game g = new Game(false);
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C1,C1,C1");
		assertTrue(test.isSmallWinds());
	}

	@Test
	public void SmallWind2() {
		Game g = new Game(false);
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W3,W3,W3,W4,W4,W4,B4,B4,B4");
		assertTrue(test.isSmallWinds());
	}
	
	@Test
	public void SmallWind3() {
		Game g = new Game(false);
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W4,W4,W4,D7,D7,D7");
		assertTrue(test.isSmallWinds());
	}
	
	@Test
	public void SmallWind4() {
		Game g = new Game(false);
		Set_stub_true test = new Set_stub_true();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,G3,G3,G3");
		assertTrue(test.isSmallWinds());
	}
}
