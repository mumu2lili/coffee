package com.piggy.coffee.aviator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;
import com.piggy.coffee.aviator.func.ListCmpFunction;

public class AviatorTest {

	@Test
	public void test() {

		System.out.println(AviatorEvaluator.execute("1 + 2 + 3"));

	}

	@Test
	public void testArr() {
		int[] a = new int[] { 6, 7, 8, 9 };
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", a);

		System.out.println(AviatorEvaluator.execute("a[1] + 100", env));
	}
	
	@Test
	public void testSeqFilter() {
		Map<String, Object> env = new HashMap<String, Object>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(20);
        list.add(10);
        env.put("list", list);
     
    
        List<?> result = (List<?>)AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
        System.out.println(result); 
    
	}
	
	@Test
	public void testListCmp() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
        env.put("expect", "1 2 3");
        env.put("actual", "1.01 2.01 3.01");
    
        AviatorEvaluator.addFunction(new ListCmpFunction());
        boolean result = (boolean)AviatorEvaluator.execute("list.cmp('math.abs(expect - actual) < 0.1', ' ')", env);
        System.out.println(result); 
    
	}
	
	@Test
	public void testListCmp2() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
        env.put("expect", "1 2 3");
        env.put("actual", "1.01 2.01 3.01");
    
        AviatorEvaluator.addFunction(new ListCmpFunction());
        boolean result = (boolean)AviatorEvaluator.execute("list.cmp('math.abs(expect - actual) < 0.1')", env);
        System.out.println(result); 
    
	}
	
	@Test
	public void testListCmp_Tab() throws IllegalAccessException, NoSuchMethodException {
		Map<String, Object> env = new HashMap<String, Object>();
        env.put("expect", "1	2	3");
        env.put("actual", "1.01	2.01	3.01");
    
        AviatorEvaluator.addFunction(new ListCmpFunction());
        boolean result = (boolean)AviatorEvaluator.execute("list.cmp('math.abs(expect - actual) < 0.1', '	')", env);
        System.out.println(result); 
    
	}

}
