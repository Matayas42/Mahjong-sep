package mahjong;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import mahjong.*;

public class SetTest {

	
	@Test
    public void consecutive1() {
        Game g = new Game(false);
    	Set test = new Set();
        test.fillSetFromString("D1,D2,D3");
        boolean check = test.isThreeConsecutive(test.getTiles());
        assertTrue(check);
    }
	
	//common hand
	@Test
    public void commonHand1() {
        Game g = new Game(false);
    	Set test = new Set();
        test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6,G1,G1");
        assertTrue(test.isCommonHand());
    }
	
	//all in Trippletts
	@Test
    public void AllINTripplets1() {
        Game g = new Game(false);
    	Set test = new Set();
        test.fillSetFromString("D1,D1,D1,D3,D3,D3,C9,C9,C9,C2,C2,C2,G1,G1");
        assertTrue(test.isAllInTripletts());
    }
	
	
	//removePair
    @Test
    public void removePair1() {
        Game g = new Game(false);
    	Set test = new Set();
        test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6,G1,G1");
        List<Tile> l = Set.removePair(test.getTiles());
        test.fillSetFromString("D1,D2,D3,D4,D5,D6,C1,C2,C3,C4,C5,C6");
        boolean check = l.equals(test.getTiles()); 
        assertTrue(check);
    }
    
}
