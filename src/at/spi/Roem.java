package at.spi;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Roem {
	
	
	//
	// second attempt
	//
	final static int[]  vals = new int[] { 1000, 500, 100, 50, 10, 5, 1};
	final static String symbols = "MDCLXVI";
	//
	// convert 4
	//
	final static int[] 		v4 = new int[] { 1000,900,500,400,100,90,50,40,10,9,5,4,1};
	final static String[] 	r4 = new String[] { "M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	//
	// convert 5
	//
	final static String 	r5 = "M_CM_D_CD_C_XC_L_XL_X_IX_V_IV_I_";
	final static int[]		x5 = new int[] { 0, 2, 5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30 };
	//
	// first attempt
	//
	public final static List<Entry<Integer,String>> list = new ArrayList<>(); 
	static {
		list.add(new AbstractMap.SimpleEntry<Integer,String>(1000, "M"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>( 500, "D"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>( 100, "C"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(  50, "L"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(  10, "X"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(   5, "V"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(   1, "I"));
	}

	
	public static String convertTo1_SpindisErsterAnsatz(int number) {
		if ( number <= 0) {
			return  "gibt's ned bei de Röma";
		}
		StringBuilder z = new StringBuilder();
		int i=0;
		while ( number > 0 ) {
			Entry<Integer,String> e = list.get(i);
			
			if ( number < e.getKey() ) {
				i++;
			}
			else {
				if ( i == 0 ) {	// > 1000
					z.append( e.getValue() );
					number -= e.getKey();
				}
				else
				{
					// < 1000
					int highDigit = getHighDigit(number);
					if 		( highDigit == 9 ) {
						z.append( list.get(i+1).getValue() ).append( list.get(i-1).getValue() );
						number -=  9 * list.get(i+1).getKey();
					}
					else if ( highDigit == 4 ) {
						z.append( e.getValue() ).append( list.get(i-1).getValue() );
						number -= 4 * e.getKey();
					}
					else {
						z.append( e.getValue() );
						number -= e.getKey();
					}
				}
			}
		}
		return z.toString();
	}
	public static String convertTo2(int number) {
		if ( number <= 0) {
			return  "gibt's ned bei de Röma";
		}
		String z = "";
		int i=0;
		while ( number > 0 ) {
			Entry<Integer,String> e = list.get(i);
			
			if ( number < e.getKey() ) {
				i++;
			}
			else {
				if ( i > 0 )
				{
					// < 1000
					int highDigit = getHighDigit(number);
					if 		( highDigit == 9 ) {
						z 		+=     list.get(i+1).getValue() + list.get(i-1).getValue();
						number -=  9 * list.get(i+1).getKey();
						continue;
					}
					else if ( highDigit == 4 ) {
						z += e.getValue() + list.get(i-1).getValue();
						number -= 4 * e.getKey();
						continue;
					}
				}
				z += e.getValue();
				number -= e.getKey();
			}
		}
		return z;
	}
	public static String convertTo3_SpindiOptimiert(int number) {
		if ( number <= 0) {
			return  "gibt's ned bei de Röma";
		}
		StringBuilder z = new StringBuilder();
		int i=0;
		while ( number > 0 ) {
			if ( number < vals[i] ) {
				i++;
			}
			else {
				if ( i > 0 )
				{
					// < 1000
					int highDigit = getHighDigit(number);
					int idx = -1;
					if 			( highDigit == 9) idx = i + 1;
					else if 	( highDigit == 4) idx = i;
					
					if ( idx != -1) {
						z.append(symbols.charAt(idx)).append(symbols.charAt(i-1));
						number -= highDigit * vals[idx];
						continue;
					}
				}
				z.append(symbols.charAt(i));
				number -= vals[i];
			}
		}
		return z.toString();
	}
	public static String convertTo4_niceWithStringArray(int number) {
		
		int idx = 0;
		StringBuilder z = new StringBuilder();
		
		while ( number > 0 ) {
			while ( number >= v4[idx] ) {
				number -= v4[idx];
				z.append(r4[idx]);
			}
			idx++;
		}
		return z.toString();
	}
	public static String convertTo5(int number) {
		
		int idx = 0;
		StringBuilder z = new StringBuilder();
		
		while ( number > 0 ) {
			while ( number >= v4[idx] ) {
				number -= v4[idx];
				
				int i2 = x5[idx];
				
				z.append( r5.charAt(i2) );
				char x = r5.charAt(i2+1);
				if ( x != '_') {
					z.append(x);
				}
			}
			idx++;
		}
		return z.toString();
	}
	public static void convertTo6_withPassedInStringBuilder(int number, final StringBuilder z) {
		
		int idx = 0;
		z.setLength(0);
		
		while ( number > 0 ) {
			while ( number >= v4[idx] ) {
				number -= v4[idx];
				
				int i2 = x5[idx];
				
				z.append( r5.charAt(i2) );
				char x = r5.charAt(i2+1);
				if ( x != '_') {
					z.append(x);
				}
			}
			idx++;
		}
	}
	public static int parse(final String Rom) {
		int z = 0;

		int i=0;
		while ( i < Rom.length() ) {
			
			int currVal = getRomValue(Rom.charAt(i));
			
			if ( i+1 < Rom.length() ) {
				int nextVal = getRomValue(Rom.charAt(i+1));
				if ( nextVal > currVal ) {
					currVal = nextVal - currVal;
					i++;
				}
			}
			z += currVal;
			i++;
		}
		
		return z;
	}
	public static int getRomValue(char RoemChar) {
		return vals[ symbols.indexOf(RoemChar) ];
	}
	public static int getHighDigit(int i) {
		
		if (i >= 100000000) i /= 100000000;
		if (i >= 10000) i /= 10000;
		if (i >= 100) i /= 100;
		if (i >= 10) i /= 10;
		
		return i;
	}
}
