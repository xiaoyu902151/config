<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

		function btnReset(){

		};
		function btnClick(){
			var len =$("#right option").length;
			if(len > 0){
				var arr = new Array();
				$("#right option").each(function(){
					var param = {
						userId:$("#userId").val(),
						//userId:40,
						roleId:$(this).val()
					};
					arr.push(param);
				});
				parent.saveRoles("${pageContext.request.contextPath}/admin/user.do?saveRoles",arr);
			}
			
		};
		
		function getValidator(objForm){
		   return  objForm.data('formValidation');
		};
		function isValidForm(validator){
			var isValid = false;

			if ($.isEmptyObject(validator.options.fields)) {
                isValid = true;
                return isValid;
            }

            for (var field in validator.options.fields) {
                validator.validateField(field);
            }

            var invaFields = validator.getInvalidFields();
			if(invaFields.length == 0)
				isValid = true;
		    return isValid;
		};
		
		function addOnclick(){
		/* 将可选角色添加到已选角色 */
		    var $left = $("#left option:selected");
			if($left.length>0){
				$left.each(function(){
					var v = $(this).val();
					var t = $(this).text();
					var flag = true;
					$("#right option").each(function(){
						if($(this).val()== v){
							flag =  false;
						}
					});
					if(flag){
						$("#right").append('<option value="'+v+'">'+t+'</option>');
						$(this).remove();
					}else{
						$(this).remove();
					}
				}); 
			}
		}; 
		function removeOnclick(){ 
			var $right = $("#right option:selected");
			if($right.length>0){
				$right.each(function(){
					var v = $(this).val();
					var t = $(this).text();
					var flag = true;
					$("#left option").each(function(){
						if($(this).val()== v){
							flag =  false;
						}
					});
					if(flag){
						$("#left").append('<option value="'+v+'">'+t+'</option>');
						$(this).remove();
					}else{
						$(this).remove();
					}
				}); 
			}
		};
		
		
	</script>
</head>
<body style="overflow:hidden;">
<div id="organDialog" class="col-sm-10 col-sm-offset-1">
	<div class="form-horizontal">
		<form class="form-group" >
			<input type="hidden" id ="userId" name="userId" value="${userId}"/>
		    <div class="col-xs-5">
                <label class="control-label">可选角色:</label>
                <div >
                    <select id="left" name="left" class="form-control"  multiple="multiple" style="height: 200px;">
                         <c:forEach items="${roleList}" var="sysRole" varStatus="status">
                             <option value="${sysRole.roleId}">${sysRole.roleName}</option>
                         </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-2" style="top:50px;">
             	<button type="button" onclick="addOnclick()" class="btn btn-info btn-xs" name="add" value="增加" style="margin-bottom:10px;"><i class="glyphicon glyphicon-plus-sign"></i>增加</button>
			   	<button type="button" onclick="removeOnclick()" class="btn btn-danger btn-xs" name="remove" value="删除"><i class="glyphicon glyphicon-minus-sign"></i>删除</button>
            </div>
            <div class="col-xs-5">
                <label class="control-label">已选角色:</label>
                <div >
                    <select id="right" class="form-control" name="right" multiple="multiple" style="height: 200px;">
                         <c:forEach items="${userRoleList}" var="sysRole" varStatus="status">
                             <option value="${sysRole.roleId}">${sysRole.roleName}</option>
                         </c:forEach>                    
                    </select>
                </div>
             </div>
		</div>
	</form>
	<div class="modal-footer" style="position: fixed; bottom: 0px;right: 15px; width:100%;">
		<div>
			<button  onclick="btnClick()" class="btn btn-primary" name="submit"><i class="glyphicon glyphicon-ok"></i>确定</button>
			<button  onclick="parent.closeDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
		</div>
	</div>
	</div>
</div> 
</body>
</html>
