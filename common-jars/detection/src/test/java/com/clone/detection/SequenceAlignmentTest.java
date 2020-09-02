package com.clone.detection;

import org.junit.Test;

public class SequenceAlignmentTest {

	@Test
	public void test() {
		String aCode = "a,c,a";
		String aRow = "1,2,3";
		String bCode = "b,c,b";
		String bRow = "1,2,3";
		SequenceAlignment sa = new SequenceAlignment();
		sa.setParameter(aCode, bCode, aRow, bRow);
		sa.alignment();
		sa.getCodeAResult();

		System.out.println(sa.getCodeAResult());
		System.out.println(sa.getCodeBResult());
	}
	
	@Test
	public void test2() {
		String aCode = "a,c,a,c";
		String aRow = "1,2,3,4";
		String bCode = "b,c,b,c";
		String bRow = "1,2,3,4";
		SequenceAlignment sa = new SequenceAlignment();
		sa.setParameter(aCode, bCode, aRow, bRow);
		sa.alignment();
		sa.getCodeAResult();

		System.out.println(sa.getCodeAResult());
		System.out.println(sa.getCodeBResult());
	}
	
	
	@Test
	public void test3() {
		String aCode = "a,c1,c2,c3,c4,a,a,c5";
		String aRow = "1,2,3,4,5,6,7,8";
		String bCode = "b,c1,c2,c3,c4,b,b,c5";
		String bRow = "1,2,3,4,5,6,7,8";
		SequenceAlignment sa = new SequenceAlignment();
		sa.setParameter(aCode, bCode, aRow, bRow);
		sa.alignment();
		sa.getCodeAResult();

		System.out.println(sa.getCodeAResult());
		System.out.println(sa.getCodeBResult());
	}
	
	@Test
	public void test4() {
		String aCode = "a,c1,c2,c3,c4,a,a,c5";
		String aRow = "1,2,3,4,5,6,7,8,9";
		String bCode = "b,c1,c2,c3,c4,b,b,c5";
		String bRow = "1,2,3,4,5,6,7,8,9";
		SequenceAlignment sa = new SequenceAlignment();
		sa.setParameter(aCode, bCode, aRow, bRow);
		sa.alignment();
		sa.getCodeAResult();

		System.out.println(sa.getCodeAResult());
		System.out.println(sa.getCodeBResult());
	}

}
