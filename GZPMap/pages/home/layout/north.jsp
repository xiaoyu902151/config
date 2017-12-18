<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<link rel='stylesheet' href="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog/jDialog.css" />
	
<%--	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>--%>
   <script src="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/home/layout/north.js"></script>  
  	
   <script>
   	/* 退出登录 */
	function logout()  {
	
	    var dialog = jDialog.confirm("是否退出系统",{
						handler : function(button,dialog) {
							window.location.href = "${pageContext.request.contextPath}/login.do?logout";
							}
						},{
							handler : function(button,dialog) {
								dialog.close();
							}
						});
	}
   </script>
   <div id="header_left"></div>
   <div id="div_login">
	   <a >欢迎光临！</a>
	   <div id="userImg"></div>
	   <c:if test="${user == null}">
	   	<a class="login" href="${pageContext.request.contextPath}/login.do?loginUI">登录</a>
	   </c:if>
	   <c:if test="${user != null}">
	   	<a class="login" onclick="logout()">退出</a>
	    <a class="login" href="#">${user.realName}</a>
	   </c:if>
   </div>
   <div id="menubar" class="menubar">
     <ul id = "nav">
        <c:forEach items="${northModules}" var="module" varStatus="status">
	        <li <c:if test="${module.moduleId==selectModules}"> class="active" </c:if> onclick="location.href='${pageContext.request.contextPath}${module.modulePath}'">
	          <div style="background-position: ${module.moduleBz} 0;"></div>
	          <a>${module.moduleName}</a>
	        </li>
	        <li id="line" class="line"></li>
		</c:forEach>
     </ul>
   </div>

