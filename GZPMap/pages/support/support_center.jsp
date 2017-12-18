<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>广州港务局应急管理平台一期</title>
    <link href="${pageContext.request.contextPath}/content/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/bacise.css" rel="stylesheet" type="text/css" /> 
    
    <script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/support/support_center.js"></script>
     
   <script>
	    var dojoConfig = {
		    paths: { Extension: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/Extension",
		             ExtensionDraw: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/plotDraw"
		     }
		};
    </script>
 	<script src="${pageContext.request.contextPath}/js/map/interfacesmap/drawExtension/tween.js"></script>     
 	<script src='${pageContext.request.contextPath}/js/map/interfacesmap/utils.js' type='text/javascript'></script>
 	<link rel='stylesheet' href='${pageContext.request.contextPath}/js/arcgisapi/3.16/esri/css/esri.css' />
 	<link rel='stylesheet' href='${pageContext.request.contextPath}/content/css/map/mapcss.css' />
 	<script src='${pageContext.request.contextPath}/js/arcgisapi/3.16/init.js' type='text/javascript'></script>
 	<script src='${pageContext.request.contextPath}/js/map/interfacesmap/mapconfig.js' type='text/javascript'></script>
 	<script src='${pageContext.request.contextPath}/js/map/interfacesmap/map.js' type='text/javascript'></script>
 	<link href="${pageContext.request.contextPath}/content/css/map/map-plot.css" rel="stylesheet" type="text/css" />
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.plot.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/support/plot_plan.js"></script>      
  
     <style>
     	.title{ width:88px;}
     	.title ul li{ width:88px;}
     	.title ul li img{ margin:9px 24px 6px 24px;}
     	.title .titsh{width:86px;}
     	.title ul li:hover{width:86px;}
     	
     </style>
     
       <!--内容区 开始-->
    <div class="center">
        <!--内容区 左侧-->
        <div class="title">
            <ul>
              <c:forEach items="${sysModuleList}" var="module" varStatus="status">
              		 <li <c:if test="${status.first==true}"> class="titsh" </c:if> >
	                    <a  href="#${module.moduleId}div" role="tab" data-toggle="tab">
	                        <img src="${pageContext.request.contextPath}${module.moduleIcon}" />
	                        <span>${module.moduleName}</span>
	                    </a>
                	</li> 
                <div class="line_bg"></div>
              </c:forEach>
            </ul>
        </div>

        <div class="side">
        	 <div class="tab-content">
       		 	   <c:forEach items="${sysModuleList}" var="module" varStatus="status">
			            <div class="tab-pane <c:if test="${status.first==true}"> active </c:if>" id="${module.moduleId}div" >
				            <div  style="width: 90% ;height:100%;overflow: hidden;position:absolute; top:10px; bottom:0px;">
				      		   <!--<jsp:include page="${module.modulePath}"></jsp:include>-->
				      		   <iframe src = "${pageContext.request.contextPath}/${module.modulePath}" width="100%" height="100%" frameborder="0" allowTransparency="true" ></iframe>
				    		</div> 
			    		</div>
		    		</c:forEach>
    		</div>
        </div> 
   </div>