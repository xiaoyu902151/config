<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%@taglib prefix="relation" uri="http://www.bxsurvey.com/relation" %>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.css"/>
	      
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/moment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript">
	
	</script>
</head>
<body >

 	   	    <div style="text-align: center;overflow: visible;;"  id="content">
 	   	    <script type="text/javascript">
 	   	        var a='<%=request.getParameter("value")%>';
 	   	        a=$.trim(a);
                document.write(decodeURI(a));
               </script>
             </div>
 	   		
			<div class="modal-footer" style="position: fixed; bottom: 0px;right: 15px; width:100%;">
					<div>
						<button  onclick="parent.closeDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
					</div>
			</div>
</body>
</html>
