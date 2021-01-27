package com.piggy.coffee.tmp;

import java.util.UUID;

import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {
	    long sum = 0;
	    for(int i = 10 - 1; i > 0; i--) {
	    	sum += i;
	    }
	    System.out.println(sum);
	}

	@Test
	public void test2() {

		// math.abs(6*math.pow(2.71828183,expectElem)-113*expectElem+17)<=0.00001
		double d = Math.abs(6 * Math.pow(2.71828183, 0.216366) - 113 * 0.216366 + 17);
		double d2 = Math.abs(6 * Math.pow(2.71828183, 4.377021) - 113 * 4.377021 + 17);
		System.out.println(d);
		System.out.println(d2);
		
		System.out.println(Math.abs(d) < 0.00001);
		System.out.println(Math.abs(d2) < 0.00001);
	}

	@Test
	public void test3() {

		// math.abs(6*math.pow(2.71828183,expectElem)-113*expectElem+17)<=0.00001
		double d = Math.abs(6 * Math.pow(2.718281828459045, 0.216366) - 113 * 0.216366 + 17);
		double d2 = Math.abs(6 * Math.pow(2.718281828459045, 4.377021) - 113 * 4.377021 + 17);
		System.out.println(d);
		System.out.println(d2);
	}

}
