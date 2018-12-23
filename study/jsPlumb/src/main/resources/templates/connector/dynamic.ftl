<!DOCTYPE html>
<html>
<head>
<#assign title = "动态连接" />
<#include "/common/head.ftl">
<style type="text/css">
    #diagramContainer {
      padding: 20px;
      width: 80%;
      height: 400px;
      border: 1px solid gray;
    }

    .item {
      position: absolute;
      height: 80px;
      width: 80px;
      border: 1px solid blue;
    }
</style>
</head>
<body>
  <div id="diagramContainer">
    <div id="item_left" class="item"></div>
    <div id="item_right" class="item" style="left:150px;"></div>
  </div>

  <script type="text/JavaScript">
    /* global jsPlumb */
    jsPlumb.ready(function () {
      jsPlumb.setContainer('diagramContainer')

      var common = {
        isSource: true,
        isTarget: true,
        connector: ['Straight'],
        paintStyle: {
          fill: 'white',
          outlineStroke: 'blue',
          strokeWidth: 3
        },
        hoverPaintStyle: {
          outlineStroke: 'lightblue'
        },
        connectorStyle: {
          outlineStroke: 'green',
          strokeWidth: 1
        },
        connectorHoverStyle: {
          strokeWidth: 2
        }
      }

      jsPlumb.addEndpoint('item_left', {
        anchors: ['Right'],
        uuid: "id_left_1"
      }, common)

      jsPlumb.addEndpoint('item_right', {
        anchor: 'Left',
        uuid: "id_right_1"
      }, common)

      jsPlumb.addEndpoint('item_right', {
        anchor: 'Right',
        uuid: "id_right_2"
      }, common)
    });
    
    jsPlumb.draggable('item_right');
    
    /** 无效 */
    jsPlumb.connect({ uuids: ["id_left_1", "id_right_1"] })
  </script>
</body>
</html>