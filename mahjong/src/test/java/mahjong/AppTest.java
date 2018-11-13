package mahjong;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void testApp() {

        Set test = new Set();
        test.fillSetFromString("D1,D2,G1,D3,D4,D5,D6,C1,C2,C3,G1,C4,C5,C6");
        
        assertTrue(test.isWinningSet());
    }
}
