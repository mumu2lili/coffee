<!DOCTYPE html>
<html>
<head>
<#assign title = "流程编辑" />
<#include "/common/head.ftl">
<script type="text/javascript">
    RESOURCES_PATH="/mxgraph/editors/resources"
    STYLE_PATH="/mxgraph/editors/styles"
    IMAGE_PATH="/mxgraph/editors/images"
</script>
<script type="text/JavaScript" src="${basePath}mxgraph/js/mxClient.js"></script>
<script type="text/javascript">
		function main()
		{
			// Checks if browser is supported
			if (!mxClient.isBrowserSupported())
			{
				// Displays an error message if the browser is
				// not supported.
				mxUtils.error('Browser is not supported!', 200, false);
			}
			else
			{
				// Defines an icon for creating new connections in the connection handler.
				// This will automatically disable the highlighting of the source vertex.
				mxConnectionHandler.prototype.connectImage = new mxImage('${basePath}/mxgraph/editors/images/connector.gif', 16, 16);

				// Creates the div for the toolbar
				var tbContainer = document.createElement('div');
				tbContainer.style.position = 'absolute';
				tbContainer.style.overflow = 'hidden';
				tbContainer.style.padding = '2px';
				tbContainer.style.left = '0px';
				tbContainer.style.top = '0px';
				tbContainer.style.width = '24px';
				tbContainer.style.bottom = '0px';
				
				document.body.appendChild(tbContainer);
			
				// Creates new toolbar without event processing
				var toolbar = new mxToolbar(tbContainer);
				toolbar.enabled = false
				
				// Creates the div for the graph
				var container = document.createElement('div');
				container.style.position = 'absolute';
				container.style.overflow = 'hidden';
				container.style.left = '24px';
				container.style.top = '0px';
				container.style.right = '0px';
				container.style.bottom = '0px';
				container.style.background = 'url("${basePath}/mxgraph/editors/images/grid.gif")';

				document.body.appendChild(container);
				
				// Workaround for Internet Explorer ignoring certain styles
				if (mxClient.IS_QUIRKS)
				{
					document.body.style.overflow = 'hidden';
					new mxDivResizer(tbContainer);
					new mxDivResizer(container);
				}
	
				// Creates the model and the graph inside the container
				// using the fastest rendering available on the browser
				var model = new mxGraphModel();
				var graph = new mxGraph(container, model);

				// Enables new connections in the graph
				graph.setConnectable(true);
				graph.setMultigraph(false);

				// Stops editing on enter or escape keypress
				var keyHandler = new mxKeyHandler(graph);
				var rubberband = new mxRubberband(graph);
				
				var addVertex = function(icon, w, h, style)
				{
					var vertex = new mxCell(null, new mxGeometry(0, 0, w, h), style);
					vertex.setVertex(true);
				
					var img = addToolbarItem(graph, toolbar, vertex, icon);
					img.enabled = true;
					
					graph.getSelectionModel().addListener(mxEvent.CHANGE, function()
					{
						var tmp = graph.isSelectionEmpty();
						mxUtils.setOpacity(img, (tmp) ? 100 : 20);
						img.enabled = tmp;
					});
				};
				
				addVertex('${basePath}/mxgraph/editors/images/rectangle.gif', 100, 40, '');
				addVertex('${basePath}/mxgraph/editors/images/rounded.gif', 100, 40, 'shape=rounded');
				addVertex('${basePath}/mxgraph/editors/images/ellipse.gif', 40, 40, 'shape=ellipse');
				addVertex('${basePath}/mxgraph/editors/images/rhombus.gif', 40, 40, 'shape=rhombus');
				addVertex('${basePath}/mxgraph/editors/images/triangle.gif', 40, 40, 'shape=triangle');
				addVertex('${basePath}/mxgraph/editors/images/cylinder.gif', 40, 40, 'shape=cylinder');
				addVertex('${basePath}/mxgraph/editors/images/actor.gif', 30, 40, 'shape=actor');
			}
		}

		function addToolbarItem(graph, toolbar, prototype, image)
		{
			// Function that is executed when the image is dropped on
			// the graph. The cell argument points to the cell under
			// the mousepointer if there is one.
			var funct = function(graph, evt, cell, x, y)
			{
				graph.stopEditing(false);

				var vertex = graph.getModel().cloneCell(prototype);
				vertex.geometry.x = x;
				vertex.geometry.y = y;
					
				graph.addCell(vertex);
				graph.setSelectionCell(vertex);
			}
			
			// Creates the image which is used as the drag icon (preview)
			var img = toolbar.addMode(null, image, function(evt, cell)
			{
				var pt = this.graph.getPointForEvent(evt);
				funct(graph, evt, cell, pt.x, pt.y);
			});
			
			// Disables dragging if element is disabled. This is a workaround
			// for wrong event order in IE. Following is a dummy listener that
			// is invoked as the last listener in IE.
			mxEvent.addListener(img, 'mousedown', function(evt)
			{
				// do nothing
			});
			
			// This listener is always called first before any other listener
			// in all browsers.
			mxEvent.addListener(img, 'mousedown', function(evt)
			{
				if (img.enabled == false)
				{
					mxEvent.consume(evt);
				}
			});
						
			mxUtils.makeDraggable(img, graph, funct);
			
			return img;
		}
</script>
</head>
<body  onload="main();">
</body>
</html>