<!DOCTYPE html>
<html>
<head>
<#assign title = "拖动" />
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
      jsPlumb.connect({
        source: 'item_left',
        target: 'item_right',
        endpoint: 'Rectangle',
        overlays: [ ['Arrow', { width: 12, length: 12, location: 0.95 }] ]
      })

      jsPlumb.draggable('item_left')
      jsPlumb.draggable('item_right')
    })
  </script>
</body>
</html>