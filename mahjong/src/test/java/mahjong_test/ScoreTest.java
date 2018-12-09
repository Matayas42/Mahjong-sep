package mahjong_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mahjong.*;

public class ScoreTest {

	@Test
	public void ScoreTest_commonHand() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6,G1,G1");
		assertEquals(1,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_allInTripletts() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,C2,C2,C2,D3,D3,D3,B7,B7,B7,B8,B8");
		assertEquals(3,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_mixOneSuit() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("C1,C2,C3,C3,C4,C5,C5,C6,C7,W1,W1,W1,C9,C9");
		assertEquals(3,test.computeFanPoints());
	}

	@Test
	public void ScoreTest_allInTriplettsAndMixOneSuit() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,D1,D1,D1,D6,D6,D6,D9,D9,D9,G3,G3,G3");
		assertEquals(6,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_allOneSuit() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("B1,B1,B1,B1,B2,B3,B4,B5,B6,B7,B7,B7,B8,B8");
		assertEquals(7,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_greatDragon() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,G3,G3,G3,G1,G1,G1,G2,G2,G2,W1,W1");
		assertEquals(8,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_smallDragon() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("C1,C1,C1,D2,D2,D2,G1,G1,G1,G2,G2,G2,G3,G3");
		assertEquals(5,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_isSevenPairs() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("C1,C1,D1,D1,D2,D2,B5,B5,B1,B1,B6,B6,C3,C3");
		assertEquals(4,test.computeFanPoints());
	}
	
	@Test
	public void ScoreTest_smallWinds() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,C3,C3,C3");
		assertEquals(10,test.computeFanPoints());
	}

	@Test
	public void ScoreTest_greatWinds() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("W1,W1,W1,W2,W2,W2,W3,W3,W3,W4,W4,W4,C3,C3");
		assertEquals(10,test.computeFanPoints());
	}

	@Test
	public void ScoreTest_allKongs() {
		Game g = new Game(false);
		Set test = new Set();
		test.fillSetFromString("D1,D1,D1,D1,D2,D2,D2,D2,D3,D3,D3,D3,W1,W1");
		assertEquals(10,test.computeFanPoints());
	}
}
