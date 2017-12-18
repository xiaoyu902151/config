<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
    <link href="${pageContext.request.contextPath}/content/css/site.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/map/map-index.css" rel="stylesheet" type="text/css" /> 
    <link href="${pageContext.request.contextPath}/content/css/map/map-plot.css" rel="stylesheet" type="text/css" /> 
    <link href="${pageContext.request.contextPath}/content/css/map/map-monitor.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/ems/ems-resources.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/danger/danger-index.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/ems/ems-event.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet"
			href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css" />
			<link rel="stylesheet"
			href="${pageContext.request.contextPath}/content/css/default/default.icon.css" />
	<link
		href="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/css/jquery.treetable.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/css/jquery.treetable.theme.default.css"
		rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/content/css/ems/ems-index.css"
			rel="stylesheet" type="text/css" />
			<link
			href="${pageContext.request.contextPath}/js/plugins/video/css/video-js.min.css"
			rel="stylesheet" type="text/css" />

	<script src="${pageContext.request.contextPath}/js/map/interfacesmap/drawExtension/tween.js"></script>  
    <script>
	    var dojoConfig = {
		    paths: { Extension: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/Extension",
		             ExtensionDraw: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/plotDraw"
		     }
		};
    </script>
<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>--%>
    <!-- 引入 echarts.js -->
    <script src="js/plugins/echarts/echarts.min.js"></script>	    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.publicJS.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.print.js"></script>  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.plot.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.location.js"></script>            
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.splitScreen.js"></script>            
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.map2dTool.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.mapControl.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.layer.js"></script>       
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.main.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource_material.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource_team.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource_expert.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/onlinemonitor/monitor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/dangersource/dangersource.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/pub/pub.js"></script>                     
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/home/layout/center.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/alarm/alarm_event.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/jquery.treetable.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>

	<div class="main-content">
      <section>
        <div class="allleft" id="sidebar">
<%--			<div id="nav_bar" class="nav_bar">--%>
<%--				<div id ="region1" title="应急资源" class="nav_but nav_sel" onclick="home.center.navItemClick(this,'','应急资源')">--%>
<%--					<div class="nav_but_ml"></div>--%>
<%--					<span>应急资源</span>--%>
<%--				</div>--%>
<%--				<div class="line_bg"></div>--%>
<%--				<div id ="region2" title="快速定位" class="nav_but" onclick="home.center.navItemClick(this,'','快速定位')">--%>
<%--					<div class="nav_but_ss"></div>--%>
<%--					<span>快速定位</span>--%>
<%--				</div>--%>
<%--				<div class="line_bg"></div>--%>
<%--				<div id ="region3" title="知识库" class="nav_but" onclick="home.center.navItemClick(this,'','知识库')">--%>
<%--					<div class="nav_but_ss"></div>--%>
<%--					<span>知识库</span>--%>
<%--				</div>--%>
<%--				<div class="line_bg"></div>--%>
<%--			</div>   --%>
<%--			<div id="nav_Content" class="nav_Content">--%>
<%--			    <div class="nav_Item_Content"><a onclick="home.north.showMessage();">应急资源</a></div>--%>
<%--			</div>        --%>
        </div>
        <div class="allright" id="content">
          <div class="allrttop" id="tool_container"></div>
          <div id="centerPanel">
        </div>
        <div id="mapSpread"></div>
      </section>
     </div>