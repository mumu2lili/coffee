
/**
 * 单选组件.
 */
var SingleToolEditDataDialog = function(ui, cell)
{
	var div = document.createElement('div');
	var graph = ui.editor.graph;
	
	var value = graph.getModel().getValue(cell);
	
	// Converts the value to an XML node
	if (!mxUtils.isNode(value))
	{
		var doc = mxUtils.createXmlDocument();
		var obj = doc.createElement('object');
		obj.setAttribute('label', value || '');
		value = obj;
	}

	// Creates the dialog contents
	var form = new mxForm('properties');
	form.table.style.width = '100%';

	var attrs = value.attributes;
	var names = [];
	var texts = [];
	var count = 0;

	var id = EditDataDialog.getDisplayIdForCell(ui, cell);
	
	var temp = [];
	var isLayer = graph.getModel().getParent(cell) == graph.getModel().getRoot();

	for (var i = 0; i < attrs.length; i++)
	{
		if ((isLayer || attrs[i].nodeName != 'label') && attrs[i].nodeName != 'placeholders')
		{
			temp.push({name: attrs[i].nodeName, value: attrs[i].nodeValue});
		}
	}
	
	// Sorts by name
	temp.sort(function(a, b)
	{
	    if (a.name < b.name)
	    {
	    		return -1;
	    }
	    else if (a.name > b.name)
	    {
	    		return 1;
	    }
	    else
	    {
	    		return 0;
	    }
	});

	if (id != null)
	{	
		var text = document.createElement('input');
		text.style.width = '220px';
		text.style.textAlign = 'center';
		text.setAttribute('type', 'text');
		text.setAttribute('readOnly', 'true');
		text.setAttribute('value', id);
		
		form.addField(mxResources.get('id') + ':', text);
	}
	
	// 业务数据界面
	var generateUi = function(name, resData)
	{
			try
			{
					// Checks if the name is valid
					var clone = value.cloneNode(false);
					clone.setAttribute(name, '');

					names.push(name);
					var text = form.addCombo(nameLabel[name] + ':', false);
					$(text).attr("name", name);
					for(var i = 0; i < resData.datas.length; i++) {
						var data = resData.datas[i];
						form.addOption(text, data.key, data.value, i == 0);
					}
					if(resData.defaultVal) {
						$(text).find("option:selected").attr("selected",false);
						$(text).find("option[value='" + resData.defaultVal + "']").attr("selected",true);
					}
					text.style.width = '100%';
					texts.push(text);
					text.focus();
			}
			catch (e)
			{
				mxUtils.alert(e);
			}
	};
	var nameLabel = {
			"componentType": "组件类型",
			"toolChoose": "工具选择"
	}
	var resData = {
			"datas": [
				{
					"key": "边界模块1",
					"value": "InerComponent1"
				}, {
					"key": "边界模块2",
					"value": "InerComponent2"
				}
			],
			"defaultVal": "InerComponent1"
	};
	generateUi("componentType", resData);
	resData = {
			"datas": [
				{
					"key": "工具1",
					"value": "Classify_Report"
				}, {
					"key": "工具2",
					"value": "Vectorize"
				}, {
					"key": "工具3",
					"value": "Ansj_WordSeg"
				}
			],
			"defaultVal": "Vectorize"
	};
	generateUi("toolChoose", resData);
	
	// 恢复数据
	var setData = function(index, name, value)
	{
		names[index] = name;
		var selectField = $("select[name='" + name + "']", form.getTable()).get(0);
		$(selectField).find("option:selected").attr("selected",false);
		$(selectField).find("option[value='" + value + "']").attr("selected",true);
		texts[index] = selectField;
		
	};
	for (var i = 0; i < temp.length; i++)
	{
		setData(count, temp[i].name, temp[i].value);
		count++;
	}
	
	var top = document.createElement('div');
	top.style.cssText = 'position:absolute;left:30px;right:30px;overflow-y:auto;top:30px;bottom:80px;';
	top.appendChild(form.table);

	var newProp = document.createElement('div');
	newProp.style.whiteSpace = 'nowrap';
	newProp.style.marginTop = '6px';

	top.appendChild(newProp);
	div.appendChild(top);
	
	this.init = function()
	{
		if (texts.length > 0)
		{
			texts[0].focus();
		}
	};
	
	var cancelBtn = mxUtils.button(mxResources.get('cancel'), function()
	{
		ui.hideDialog.apply(ui, arguments);
	});
	
	cancelBtn.className = 'geBtn';
	
	var applyBtn = mxUtils.button(mxResources.get('apply'), function()
	{
		try
		{
			ui.hideDialog.apply(ui, arguments);
			
			// Clones and updates the value
			value = value.cloneNode(true);
			var removeLabel = false;
			
			for (var i = 0; i < names.length; i++)
			{
				if (texts[i] == null)
				{
					value.removeAttribute(names[i]);
				}
				else
				{
					value.setAttribute(names[i], texts[i].value);
					removeLabel = removeLabel || (names[i] == 'placeholder' &&
						value.getAttribute('placeholders') == '1');
				}
			}
			
			// Removes label if placeholder is assigned
			if (removeLabel)
			{
				value.removeAttribute('label');
			}
			
			// Updates the value of the cell (undoable)
			graph.getModel().setValue(cell, value);
		}
		catch (e)
		{
			mxUtils.alert(e);
		}
	});
	applyBtn.className = 'geBtn gePrimaryBtn';
	
	var buttons = document.createElement('div');
	buttons.style.cssText = 'position:absolute;left:30px;right:30px;text-align:right;bottom:30px;height:40px;'
	
	if (ui.editor.graph.getModel().isVertex(cell) || ui.editor.graph.getModel().isEdge(cell))
	{
		var replace = document.createElement('span');
		replace.style.marginRight = '10px';
		var input = document.createElement('input');
		input.setAttribute('type', 'checkbox');
		input.style.marginRight = '6px';
		
		if (value.getAttribute('placeholders') == '1')
		{
			input.setAttribute('checked', 'checked');
			input.defaultChecked = true;
		}
	
		mxEvent.addListener(input, 'click', function()
		{
			if (value.getAttribute('placeholders') == '1')
			{
				value.removeAttribute('placeholders');
			}
			else
			{
				value.setAttribute('placeholders', '1');
			}
		});
		
		replace.appendChild(input);
		mxUtils.write(replace, mxResources.get('placeholders'));
		
		if (EditDataDialog.placeholderHelpLink != null)
		{
			var link = document.createElement('a');
			link.setAttribute('href', EditDataDialog.placeholderHelpLink);
			link.setAttribute('title', mxResources.get('help'));
			link.setAttribute('target', '_blank');
			link.style.marginLeft = '10px';
			link.style.cursor = 'help';
			
			var icon = document.createElement('img');
			icon.setAttribute('border', '0');
			icon.setAttribute('valign', 'middle');
			icon.style.marginTop = (mxClient.IS_IE11) ? '0px' : '-4px';
			icon.setAttribute('src', Editor.helpImage);
			link.appendChild(icon);
			
			replace.appendChild(link);
		}
		
		buttons.appendChild(replace);
	}
	
	if (ui.editor.cancelFirst)
	{
		buttons.appendChild(cancelBtn);
		buttons.appendChild(applyBtn);
	}
	else
	{
		buttons.appendChild(applyBtn);
		buttons.appendChild(cancelBtn);
	}

	div.appendChild(buttons);
	this.container = div;
};



