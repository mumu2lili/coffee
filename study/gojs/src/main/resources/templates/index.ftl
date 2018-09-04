<!DOCTYPE html>
<html>
<head>
<#assign title = "目录" />
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
  <ul>
    <li><a href="${basePath}group">组</a></li>
    <li><a href="${basePath}shape">图形</a></li>
    <li><a href="${basePath}flow">流程</a></li>
  </ul>


</body>
</html>