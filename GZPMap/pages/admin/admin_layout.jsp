<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>广州港务局应急管理平台一期</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
     
    <link href="${pageContext.request.contextPath}/content/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/site.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/map/map-index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/admin/admin_layout.js"></script>
    
  </head>
  
  <body id="indexLayout"  style="width: 100% ;height: 100% ;overflow: hidden;border: 0px;" scroll="no" >
    <div id="header" style="height:90px;width:100%;overflow: hidden;position:absolute; top:0;">
        <jsp:include page="/pages/home/layout/north.jsp"></jsp:include>
	    <!-- 整体布局 -上 -->
    </div>
    <div  id="main-container" style="width: 100% ;height:auto;overflow: hidden;position:absolute; top:90px; bottom:0px;">
        <jsp:include page="/pages/admin/admin_center.jsp"></jsp:include>
    </div>
  </body>
</html>