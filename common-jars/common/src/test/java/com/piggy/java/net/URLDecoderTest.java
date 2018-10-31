package com.piggy.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Assert;
import org.junit.Test;

public class URLDecoderTest {

	@Test
	public void test() throws UnsupportedEncodingException {
		String src = "<mxGraphModel dx=\"1042\" dy=\"604\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><object label=\"&lt;ul style='padding-left:0px;text-align: left;'&gt;&lt;li style='list-style:none'&gt;类型：边界模块1&lt;/li&gt;&lt;li style='list-style:none'&gt;工具：工具2&lt;/li&gt;&lt;/ul&gt;\" componentType=\"InerComponent1\" toolChoose=\"Vectorize\" id=\"2\"><mxCell style=\"rounded=0;whiteSpace=wrap;html=1;componentType=singleTool;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"20\" y=\"20\" width=\"120\" height=\"60\" as=\"geometry\"/></mxCell></object><mxCell id=\"3\" value=\"模块\" style=\"swimlane;componentType=module;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"20\" y=\"100\" width=\"200\" height=\"200\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
		System.out.println(src);

		String xml = URLEncoder.encode(src, "UTF-8");
		System.out.println(xml);

		String xml2 = URLDecoder.decode(xml, "UTF-8");
		System.out.println(xml2);

		boolean r = src.equals(xml2);
		Assert.assertTrue(r);

	}

	@Test
	public void test_plus() throws UnsupportedEncodingException {
		String src = "<mxGraphModel dx=\"1042\" dy=\"604\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><object label=\"&lt;ul style='padding-left:0px;text-align: left;'&gt;&lt;li style='list-style:none'&gt;类型：边界模块1&lt;/li&gt;&lt;li style='list-style:none'&gt;工具：工具2&lt;/li&gt;&lt;/ul&gt;\" componentType=\"InerComponent1\" toolChoose=\"Vectorize\" id=\"2\"><mxCell style=\"rounded=0;whiteSpace=wrap;html=1;componentType=singleTool;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"20\" y=\"20\" width=\"120\" height=\"60\" as=\"geometry\"/></mxCell></object><mxCell id=\"3\" value=\"模块\" style=\"swimlane;componentType=module;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"20\" y=\"100\" width=\"200\" height=\"200\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
		System.out.println(src);

		String xml = URLEncoder.encode(src, "UTF-8");
		System.out.println(xml);

		xml = xml.replaceAll("\\+", "%20");
		System.out.println(xml);

		String xml2 = URLDecoder.decode(xml, "UTF-8");
		System.out.println(xml2);

		boolean r = src.equals(xml2);
		Assert.assertTrue(r);

	}

	@Test
	public void test3() throws UnsupportedEncodingException {
		String src = "a b+";

		String xml = URLEncoder.encode(src, "UTF-8");
		System.out.println(xml);

		xml = xml.replaceAll("\\+", "%20");
		System.out.println(xml);

		String xml2 = URLDecoder.decode(xml, "UTF-8");
		System.out.println(xml2);

		boolean r = src.equals(xml2);
		Assert.assertTrue(r);

	}

}
