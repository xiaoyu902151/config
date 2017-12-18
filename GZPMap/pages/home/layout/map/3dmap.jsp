<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>港口三维管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>    
    <script src="js/map/cesiumjs/CesiumViewer.js"></script>
    <style>
      @import url(js/map/cesiumjs/CesiumUnminified/Widgets/widgets.css);
      #cesiumContainer {
          width: 100%; height: 100%;
      }
   </style>

  </head>
  
  <body>
  <div id="cesiumContainer"></div>
  <script>
            //            
            var obj = new CesiumViewer('cesiumContainer',{imageryViewModels:MapConfig.imageryViewModels,terrainViewModels:[]});
            var viewer = obj.cesiumViewer;
            //叠加2.5D图
            //obj.addArcGisMapServerLayer(MapConfig.Map25DUrl);											
		    //叠加地形图
		    obj.addTerrainLayer(MapConfig.terrainMapUrl);
			//隐藏logo以及地图服务版权信息等等
			obj.hideMapLogo();
			//设置地图底图容器标题以及字体大小
            obj.setBaseLayerPickerTitle("底图切换","13pt");
            //绘制面
            var geos = [    111.068859, 21.470867, 111.069917, 21.471584,
 							111.070815, 21.472186, 111.071062, 21.472106,
 							111.07118, 21.471002, 111.071359, 21.469847,
 							111.07042, 21.469786, 111.069689, 21.470289,
 							111.068859, 21.470867 ];
            var polygon = obj.drawPolygon("罐区",geos);
			//viewer.zoomTo(wyoming);
			polygon.description = '\<img\width="50%"\style="float:left; margin: 0 1em 1em 0;"\src="//cesiumjs.org/images/2015/02-02/Flag_of_Wyoming.svg"/>\<p>\Wyoming is a state in the mountain region of the Western \United States.\</p>\<p>\Wyoming is the 10th most extensive, but the least populous \and the second least densely populated of the 50 United \States. The western two thirds of the state is covered mostly \with the mountain ranges and rangelands in the foothills of \the eastern Rocky Mountains, while the eastern third of the \state is high elevation prairie known as the High Plains. \Cheyenne is the capital and the most populous city in Wyoming, \with a population estimate of 62,448 in 2013.\</p>\<p>\Source: \<a style="color: WHITE"\target="_blank"\href="http://en.wikipedia.org/wiki/Wyoming">Wikpedia</a>\</p>';


			viewer.camera.flyTo({//初始化跳转某个地方
				destination : Cesium.Cartesian3.fromDegrees(111.08055, 21.47023,
						1800.0)
			});
			//加载3dModel	
			//viewer.entities.removeAll();
			var position = Cesium.Cartesian3.fromDegrees(111.08055, 21.47023);
			var models = [ {
				name : "测试3D模型",
				position : position,
				uri : "js/map/cesiumjs/MMModel/CS/YG.gltf"
			}, {
				name : "测试3D模型",
				position : position,
				uri : "js/map/cesiumjs/MMModel/CS/GX.gltf"
			} ];
			//obj.add3DModels(models);

		</script>
  </body>
</html>
