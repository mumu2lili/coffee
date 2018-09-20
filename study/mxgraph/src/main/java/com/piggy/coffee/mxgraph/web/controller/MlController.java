package com.piggy.coffee.mxgraph.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.piggy.coffee.mxgraph.model.XmlFile;

/**
 * 机器学习
 *
 * @author mm
 *
 */
@Controller
@RequestMapping("ml")
public class MlController {

	@RequestMapping(value = "editor", method = RequestMethod.GET)
	public String editor(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "ml/editor";
	}

	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Map<String, Object> save(@RequestBody XmlFile model, HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String xml = URLDecoder.decode(model.getXml(), "UTF-8");

		// 保存文件
		String path = System.getProperty("user.dir") + File.separator + "systemplate" + File.separator
				+ model.getFilename();
		File file = new File(path);
		FileUtils.forceMkdirParent(file);
		FileUtils.writeStringToFile(file, xml, "UTF-8");

		// 解析文件
		parseMxXmlFile(xml);

		Map<String, Object> result = new HashMap<>();
		result.put("message", "ok");
		return result;
	}

	private void parseMxXmlFile(String xml) {
		Document doc = mxXmlUtils.parseXml(xml);
		Element e = doc.getDocumentElement();
		mxGraph graph = new mxGraph();

		mxCodec dec = new mxCodec(e.getOwnerDocument());

		dec.decode(e, graph.getModel());

		mxIGraphModel model = graph.getModel();

		int n = model.getChildCount(model.getRoot());

		for (int i = 0; i < n; i++) {
			mxCell cell = (mxCell) model.getChildAt(model.getRoot(), i);
			System.out.println(cell.getId());
		}

		System.out.println(n);

	}

}
