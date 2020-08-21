package com.piggy.coffee.aviator;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import com.piggy.coffee.aviator.func.ListCmpFunction;

public class AviatorTest {

	@Test
	public void test() {

		System.out.println(AviatorEvaluator.execute("1 * 2 * 3"));

	}

	@Test
	public void testArr() {
		int[] a = new int[] { 6, 7, 8, 9 };
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", a);

		System.out.println(AviatorEvaluator.execute("a[1] + 100", env));
	}

	@Test
	public void testString() {
		int[] a = new int[] { 6, 7, 8, 9 };
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", a);

		System.out.println(AviatorEvaluator.execute("string.endsWith('aab', 2+'1')", env));
	}

	@Test
	public void testString2s() {
		int[] a = new int[] { 6, 7, 8, 9 };
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", a);

		System.out.println(AviatorEvaluator.execute("string.contains('aabcd', 'ab')", env));
	}

	@Test
	public void testSeqFilter() {
		Map<String, Object> env = new HashMap<String, Object>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(20);
		list.add(10);
		env.put("list", list);

		List<?> result = (List<?>) AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
		System.out.println(result);

	}

	@Test
	public void testListCmp() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", "1 2 3");
		env.put("actual", "1.01 2.01 3.01");

		AviatorEvaluator.addFunction(new ListCmpFunction());
		boolean result = (boolean) AviatorEvaluator.execute("list.forEach('math.abs(expect - actual) < 0.1', ' ')",
				env);
		System.out.println(result);

	}

	@Test
	public void testListCmp2() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", "1 2 3");
		env.put("actual", "1.01 2.01 3.01");

		AviatorEvaluator.addFunction(new ListCmpFunction());
		boolean result = (boolean) AviatorEvaluator.execute("list.forEach('math.abs(expect - actual) < 0.1')", env);
		System.out.println(result);

	}

	@Test
	public void testListCmp_Tab() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", "1	2	3");
		env.put("actual", "1.01	2.01	  3.01");

		AviatorEvaluator.addFunction(new ListCmpFunction());
		boolean result = (boolean) AviatorEvaluator.execute("list.forEach('math.abs(expect - actual) < 0.1', '	')",
				env);
		System.out.println(result);

	}

	@Test
	public void testListForEach_line() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect",
				"1" + System.getProperty("line.separator") + "2" + System.getProperty("line.separator") + "3");
		env.put("actual", "1.01" + System.getProperty("line.separator") + "2.01" + System.getProperty("line.separator")
				+ "3.01 " + System.getProperty("line.separator"));

		AviatorEvaluator.addFunction(new ListCmpFunction());
		boolean result = (boolean) AviatorEvaluator.execute("list.forEach('math.abs(expect - actual) < 0.1', '\r\n')",
				env);
		System.out.println(result);

	}

	@Test
	public void testListForEach_line2() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		String s = System.getProperty("line.separator");
		env.put("expect", "1" + s + "2");
		env.put("actual", "0.216366" + s + "4.377021");

		AviatorEvaluator.addFunction(new ListCmpFunction());
		boolean result = (boolean) AviatorEvaluator.execute(
				"list.forEach( ' math.abs(6*math.pow(2.718281828459045,actual)-113*actual+17)<=0.00001', '\r\n' )",
				env);
		System.out.println(result);

	}

	@Test
	public void testListForEach_line3() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", 1);
		env.put("actual", 0.216366);

		AviatorEvaluator.setOption(Options.MATH_CONTEXT, MathContext.DECIMAL64);
		AviatorEvaluator.addFunction(new ListCmpFunction());
		double result = (double) AviatorEvaluator.execute("6*math.pow(2.718281828459045,actual)-113*actual+17",
				env);
		System.out.println(result);

	}
	
	@Test
	public void testListForEach_line4() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", 1);
		env.put("actual", 0.216366);
		env.put("actual", 4.377021);
		//double d2 = Math.abs(6 * Math.pow(2.718281828459045, 4.377021) - 113 * 4.377021 + 17);
		AviatorEvaluator.addFunction(new ListCmpFunction());
		double result = (double) AviatorEvaluator.execute("6*math.pow(2.718281828459045,0.216366)-113*0.216366+17",
				env);
		System.out.println(result);

	}
	
	@Test
	public void testListForEach_line5() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", 1);
		env.put("actual", 0.216366);
		env.put("actual", 4.377021);
		//double d2 = Math.abs(6 * Math.pow(2.718281828459045, 4.377021) - 113 * 4.377021 + 17);
		AviatorEvaluator.addFunction(new ListCmpFunction());
		double result = (double) AviatorEvaluator.execute(" math.abs(6*math.pow(2.718281828459045,0.216366)-113*0.216366+17)",
				env);
		System.out.println(result);

	}
	
	@Test
	public void testListForEach_line6() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("expect", 1);
		env.put("actual", 0.216366);
		env.put("actual", 4.377021);
		//double d2 = Math.abs(6 * Math.pow(2.718281828459045, 4.377021) - 113 * 4.377021 + 17);
		AviatorEvaluator.addFunction(new ListCmpFunction());
		boolean result = (boolean) AviatorEvaluator.execute(" math.abs(6*math.pow(2.718281828459045,0.216366)-113*0.216366+17) <=0.00001",
				env);
		System.out.println(result);

	}
}
