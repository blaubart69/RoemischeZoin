package at.spi.RoemicalTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.spi.Roem;

public class LoadTests {

	final int LoadTest = 500;
	
	TestSeZoin test;
	
	@Before
	public void setUp() throws Exception {
		test = new TestSeZoin();
	}
	@Test
	public void Load1() {
		for (int i=0; i < LoadTest; i++) {
			test.HinUndHer1();
		}
	}
	@Test
	public void Load3_SpindiOptimiert() {
		for (int i=0; i < LoadTest; i++) {
			test.HinUndHer3();
		}
	}
	@Test
	public void Load4_niceInternetVersionWithStringArray() {
		for (int i=0; i < LoadTest; i++) {
			test.HinUndHer4();
		}
	}
	@Test
	public void Load5_niceInternetVersionWith_ONE_String() {
		for (int i=0; i < LoadTest; i++) {
			test.HinUndHer5();
		}
	}
	@Test
	public void Load6() {
		final StringBuilder sb = new StringBuilder();

		for (int j=0; j < LoadTest; j++) {
			for ( int i=1; i <= 10000; i++) {
				at.spi.Roem.convertTo6_withPassedInStringBuilder(i,sb);
				final int ParsedVal = at.spi.Roem.parse(sb.toString());
				// und jezan miassatns gleich sei
				assertEquals(i, ParsedVal);
			}
		}
	}

}
