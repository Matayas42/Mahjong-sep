package mahjong_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mahjong.*;

public class Test_Set_print {

	@Before
	public void setupBeforeClass() throws Exception {
		Game g = new Game(false);
	}

	//assert can't be use since it is void function no return value, only check its coverage
	@Test
	// simplest test case
	public void print1() throws Exception {
		Set test = new Set();
		test.fillSetFromString("C2,C3");
		test.print();
	}
	
	@Test
	// simplest test case
	public void print2() throws Exception {
		Set test = new Set();
		test.fillSetFromString("");
		test.print();
	}
	

}
