package at.spi.RoemicalTest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.Test;

public class TestSeZoin {

	
	final static Map<String,Integer> TestNumbers = new HashMap<>();
	
	static {
		TestNumbers.put("I", 		1);
		TestNumbers.put("II", 		2);
		TestNumbers.put("V", 		5);
		TestNumbers.put("IV", 		4);
		TestNumbers.put("IX", 		9);
		TestNumbers.put("CMXL", 	940);
		TestNumbers.put("M", 		1000);
		TestNumbers.put("CM", 		900);
		TestNumbers.put("CLI", 		151);
		TestNumbers.put("XCIX", 	99);
		TestNumbers.put("MMXIV",	2014);
		TestNumbers.put("XXVIII",	28);
		TestNumbers.put("CCC",		300);
		TestNumbers.put("DXXXVII",	537);
	}
	@Test
	public void testConvertTo1() {
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			assertEquals(e.getKey(), at.spi.Roem.convertTo1_SpindisErsterAnsatz(e.getValue()));
		}
	}
	@Test
	public void testConvertTo2() {
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			assertEquals(e.getKey(), at.spi.Roem.convertTo2(e.getValue()));
		}
	}
	@Test
	public void testConvertTo3() {
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			assertEquals(e.getKey(), at.spi.Roem.convertTo3_SpindiOptimiert(e.getValue()));
		}
	}
	@Test
	public void testConvertTo4() {
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			assertEquals(e.getKey(), at.spi.Roem.convertTo4_niceWithStringArray(e.getValue()));
		}
	}
	@Test
	public void testConvertTo5() {
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			assertEquals(e.getKey(), at.spi.Roem.convertTo5(e.getValue()));
		}
	}
	@Test
	public void testConvertTo6() {
		StringBuilder sb = new StringBuilder();
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			at.spi.Roem.convertTo6_withPassedInStringBuilder(e.getValue(),sb);
			assertEquals(e.getKey(), sb.toString());
		}
	}
	@Test
	public void parse1() {
		for ( Entry<String,Integer> e : TestNumbers.entrySet()) {
			assertEquals(e.getValue(), new Integer(at.spi.Roem.parse(e.getKey())) );
		}
	}
	@Test 
	public void HinUndHer1() {
		for ( int i=1; i <= 10000; i++) {
			final String RoemZahl = at.spi.Roem.convertTo1_SpindisErsterAnsatz(i);
			final int ParsedVal = at.spi.Roem.parse(RoemZahl);
			// und jezan miassatns gleich sei
			assertEquals(i, ParsedVal);
		}
	}
	@Test 
	public void HinUndHer2() {
		for ( int i=1; i <= 10000; i++) {
			final String RoemZahl = at.spi.Roem.convertTo2(i);
			final int ParsedVal = at.spi.Roem.parse(RoemZahl);
			// und jezan miassatns gleich sei
			assertEquals(i, ParsedVal);
		}
	}
	@Test 
	public void HinUndHer3() {
		for ( int i=1; i <= 10000; i++) {
			final String RoemZahl = at.spi.Roem.convertTo3_SpindiOptimiert(i);
			final int ParsedVal = at.spi.Roem.parse(RoemZahl);
			// und jezan miassatns gleich sei
			assertEquals(i, ParsedVal);
		}
	}
	@Test 
	public void HinUndHer4() {
		for ( int i=1; i <= 10000; i++) {
			final String RoemZahl = at.spi.Roem.convertTo4_niceWithStringArray(i);
			final int ParsedVal = at.spi.Roem.parse(RoemZahl);
			// und jezan miassatns gleich sei
			assertEquals(i, ParsedVal);
		}
	}
	@Test 
	public void HinUndHer5() {
		for ( int i=1; i <= 10000; i++) {
			final String RoemZahl = at.spi.Roem.convertTo5(i);
			final int ParsedVal = at.spi.Roem.parse(RoemZahl);
			// und jezan miassatns gleich sei
			assertEquals(i, ParsedVal);
		}
	}
	@Test 
	public void HinUndHer6() {
		final StringBuilder sb = new StringBuilder();
		for ( int i=1; i <= 10000; i++) {
			at.spi.Roem.convertTo6_withPassedInStringBuilder(i,sb);
			final int ParsedVal = at.spi.Roem.parse(sb.toString());
			// und jezan miassatns gleich sei
			assertEquals(i, ParsedVal);
		}
	}
	@Test 
	public void testRomValues() {
		assertEquals(1000, 	at.spi.Roem.getRomValue('M'));
		assertEquals(500, 	at.spi.Roem.getRomValue('D'));
		assertEquals(100, 	at.spi.Roem.getRomValue('C'));
		assertEquals(50, 	at.spi.Roem.getRomValue('L'));
		assertEquals(10, 	at.spi.Roem.getRomValue('X'));
		assertEquals(5, 	at.spi.Roem.getRomValue('V'));
		assertEquals(1, 	at.spi.Roem.getRomValue('I'));
	}
	@Test
	public void getHighDigit() {
		assertEquals(0, at.spi.Roem.getHighDigit(0));
		assertEquals(1, at.spi.Roem.getHighDigit(1));
		assertEquals(2, at.spi.Roem.getHighDigit(20));
		assertEquals(3, at.spi.Roem.getHighDigit(3033));
		assertEquals(4, at.spi.Roem.getHighDigit(41230));
		assertEquals(5, at.spi.Roem.getHighDigit(505289));
		assertEquals(6, at.spi.Roem.getHighDigit(6003040));
		assertEquals(7, at.spi.Roem.getHighDigit(70004320));
		assertEquals(8, at.spi.Roem.getHighDigit(800043201));
		
		Random randGen = new Random();
		
		for (int i=0; i<100; i++) {
			int intValue = randGen.nextInt(Integer.MAX_VALUE);
			int firstDigit  = Integer.parseInt( Integer.toString(intValue).substring(0,1) );
			
			assertEquals(firstDigit, at.spi.Roem.getHighDigit(intValue));
		}
	}
	@Test
	public void Compare3toInternetz() {
		for ( int i=1; i <= 10000; i++) {
			// und jezan miassatns gleich sei
			assertEquals( at.spi.Roem.convertTo3_SpindiOptimiert(i) , at.spi.Roem.convertTo4_niceWithStringArray(i));
		}
	}
}
