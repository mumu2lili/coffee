package com.piggy.coffee.aviator.func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorJavaType;
import com.googlecode.aviator.runtime.type.AviatorObject;

/**
 * filter(seq,predicate) to filter seq by predicate
 *
 * @author dennis
 *
 */
public class ListCmpFunction extends AbstractFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AviatorObject call(final Map<String, Object> env, final AviatorObject arg1) {

		final AviatorObject arg2 = new AviatorJavaType("separator");
		env.put("separator", " ");
		
		final AviatorObject arg3 = new AviatorJavaType("expect");
		final AviatorObject arg4 = new AviatorJavaType("actual");

		return call(env, arg1, arg2, arg3, arg4);
	}
	
	@Override
	public AviatorObject call(final Map<String, Object> env, final AviatorObject arg1, final AviatorObject arg2) {

		final AviatorObject arg3 = new AviatorJavaType("expect");
		final AviatorObject arg4 = new AviatorJavaType("actual");

		return call(env, arg1, arg2, arg3, arg4);
	}

	@Override
	public AviatorObject call(final Map<String, Object> env, final AviatorObject arg1, final AviatorObject arg2,
			final AviatorObject arg3, final AviatorObject arg4) {

		String exp = (String) arg1.getValue(env);

		String separator = (String) arg2.getValue(env);
		String expect = (String) arg3.getValue(env);
		String actual = (String) arg4.getValue(env);

		List<Double> eList = cvtToList(expect, separator);
		List<Double> aList = cvtToList(actual, separator);
		if (eList.size() != aList.size()) {

		}

		Expression expression = AviatorEvaluator.compile(exp, true);
		Map<String, Object> p = new HashMap<>();
		for (int i = 0; i < eList.size(); i++) {
			Double e = eList.get(i);
			Double a = aList.get(i);
			p.put("expect", e);
			p.put("actual", a);

			// TODO 对结果类型进行判断
			Boolean r = (Boolean) expression.execute(p);
			if(!r) {
				return AviatorBoolean.FALSE;
			}
		}

		return AviatorBoolean.TRUE;
	}

	private List<Double> cvtToList(String str, String separator) {
		List<Double> list = new ArrayList<>();
		String[] arr = str.split(separator);
		for (String s : arr) {
			list.add(Double.parseDouble(s));
		}

		return list;

	}

	@Override
	public String getName() {
		return "list.forEach";
	}

}
