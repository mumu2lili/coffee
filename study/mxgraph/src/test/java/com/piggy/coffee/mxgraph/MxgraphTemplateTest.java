package com.piggy.coffee.mxgraph;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

public class MxgraphTemplateTest {

	@Test
	public void testDemo1() throws IOException {
		String path = System.getProperty("user.dir") + File.separator + "src/test/resources/systemplate"
				+ File.separator + "demo1.xml";
		File file = new File(path);
		String xml = FileUtils.readFileToString(file, "utf8");
		parseMxXmlFile(xml);
	}

	private void parseMxXmlFile(String xml) {
		Document doc = mxXmlUtils.parseXml(xml);
		Element e = doc.getDocumentElement();
		mxGraph graph = new mxGraph();

		mxCodec dec = new mxCodec(e.getOwnerDocument());

		dec.decode(e, graph.getModel());

		mxIGraphModel model = graph.getModel();

		mxCell root = (mxCell) model.getRoot();

		parseCell(model, root);

	}

	private void parseCell(mxIGraphModel model, mxCell parent) {
		System.out.println(parent.getId());

		int n = model.getChildCount(parent);
		for (int i = 0; i < n; i++) {
			mxCell child = (mxCell) model.getChildAt(parent, i);
			parseCell(model, child);
		}

	}

}
