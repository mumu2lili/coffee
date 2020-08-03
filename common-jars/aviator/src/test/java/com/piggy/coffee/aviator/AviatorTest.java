package com.piggy.coffee.aviator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;

public class AviatorTest {


	@Test
	public void test() {
		int[] a = new int[]{6, 7, 8, 9};
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", a);
 
        System.out.println(AviatorEvaluator.execute("1 + 2 + 3"));
        System.out.println(AviatorEvaluator.execute("a[1] + 100", env));
        System.out.println(AviatorEvaluator.execute("'a[1]=' + a[1]", env));
        //求数组长度
        System.out.println(AviatorEvaluator.execute("count(a)", env));
        //求数组总和
        System.out.println(AviatorEvaluator.execute("reduce(a, +, 0)", env));
        //检测数组每个元素都在 0 <= e < 10 之间。
        System.out.println(AviatorEvaluator.execute("seq.every(a, seq.and(seq.ge(0), seq.lt(10)))", env));
	}

	

}
