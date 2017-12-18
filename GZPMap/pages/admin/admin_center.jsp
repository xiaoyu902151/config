<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>广州港务局应急管理平台一期</title>
    <link href="${pageContext.request.contextPath}/content/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/bacise.css" rel="stylesheet" type="text/css" /> 
    
    <script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/admin/admin_center.js"></script>

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
				           <!--  <div class="top_001">
				                <img src="${pageContext.request.contextPath}/content/images/map/index/home.png" class="icon_img_01" />
				                <span style="height: 35px; width: 350px;">当前位置：后台管理&nbsp;>&nbsp;<label id="selModel" >${module.moduleName}</label></span>
				            </div>
				            -->
				            <div  style="width: 90% ;height:100%;overflow: hidden;position:absolute; top:10px; bottom:0px;">
				      		   <!--<jsp:include page="${module.modulePath}"></jsp:include>-->
				      		   <iframe src = "${pageContext.request.contextPath}/${module.modulePath}" width="100%" height="100%" frameborder="0" allowTransparency="true" ></iframe>
				    		</div> 
			    		</div>
		    		</c:forEach>
    		</div>
        </div> 
   </div>
   