<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style type="text/css">
	.tree{border:1px solid #617775;height:250px;margin-top:10px;overflow-x:auto;overflow-y:scroll;margin-bottom:5px;}
	</style>
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

	<script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/js/plugins/ztree/zTreeStyle/img/zTreeStyle.css" rel="stylesheet" type="text/css" />
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
 	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.form_datetime').datetimepicker({
				format: "YYYY-MM-DD"
			});
		    $('#organForm').formValidation({
				err: {
		            container: 'tooltip'
		        },
		        message: '输入的值格式有误！',
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		            roleName: {
		                validators: {
		                    notEmpty: {
		                        message: '角色名称不能为空！'
		                    },
							stringLength: {
								min: 2,
								max: 30,
								message: '角色名称必须是2~30个字符！'
							}
		                }
		            },
					roleValidity: {
						validators: {
							notEmpty: {
								message: '必须选择有效性！'
							}
						}
					}
		        }
		    });
		    roleId = "${sysRole.roleId}";
		    initResourceTree(roleId);
		});

		/*加载资源树*/
		function initResourceTree(roleId) {
			if(roleId==null||roleId=='') {
				return;
			}
			var setting = {
				check: {enable: true,chkStyle: "checkbox",chkboxType: { "Y" : "ps", "N" : "ps" }},
				data : {simpleData:{enable : true,idKey : 'id',pIdKey : 'pid',rootPid : '0'}}
			};
			var param={};
			$.ajax({
				type:'POST',
				cache:false,
				url:"${pageContext.request.contextPath}/admin/role.do?getRoleResourceList&roleid="+roleId,
				data:param,
				dataType:'json',
				success:function(r){
					if(r.success){
						var nodes = r.obj.data;
						$.fn.zTree.init($("#tree2"), setting, nodes);	//渲染树
					}else{
						//$.messager.alert('提示', r.msg, 'error');
					}
				},
				error:function(){
					//$.messager.alert('提示', '操作错误！', 'error');
				}
			});
		}    

		function btnReset(){
			$("input").val('');
		   var validator  = $("#organForm").data('formValidation');
		   validator.resetForm()
		};
		function btnClick(){
		    var validator  = $("#organForm").data('formValidation');
			var isValid = isValidForm(validator);
			if(!isValid) return;
						//获取树各个节点ID值
			var treeObj = $.fn.zTree.getZTreeObj("tree2");
			var nodes = treeObj.getCheckedNodes();
			var arr = new Array();
			if (nodes.length>0) {  
			    $(nodes).each(function(i){
					var json={ressId:nodes[i].id};
				    arr.push(json);
				});
			}
			var data = $.toJSON(arr);
			parent.editRoleData("jDialogFrame","organForm","${pageContext.request.contextPath}/admin/role.do?update",data);
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
		

	</script>
</head>
<body style="overflow:hidden;">
    <div id="organDialog" class="col-sm-8 col-sm-offset-2"> 
 	   	<div class="container">
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
 		   	    <input type="hidden" id="roleId" class="form-control" name="roleId"   value="${sysRole.roleId}"/>
				<div class="form-group">
						<label class="col-xs-2 control-label">角色名称<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control"  name="roleName"  value="${sysRole.roleName}"/>
						</div>
						<label class="col-xs-2 control-label">角色分类<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="paramsId" value="${sysRole.paramsId}" />
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">有效性<span>*</span></label>
						<div class="col-xs-4">
						    <input name="roleValidity" type="radio" value="true" <c:if test="${sysRole.roleValidity==true}"> checked </c:if> style="width:20px;" />是
							<input name="roleValidity" type="radio" value="false" <c:if test="${sysRole.roleValidity==false}"> checked </c:if> style="width:20px;"/>否
						</div>
						<label class="col-xs-2 control-label">备注</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="roleBz" value="${sysRole.roleBz}" />
						</div>
				</div>
				<input type="hidden" name="rolePid" >
				<div class="form-group">
					<div style="width:100%" class="tree"><ul id="tree2" class="ztree"></ul></div>
				</div>
			</form> 
			<div class="modal-footer" style="position: fixed; bottom: 0px;right: 15px; width:100%;">
					<div>
						<button  onclick="btnClick()" class="btn btn-primary" name="submit"><i class="glyphicon glyphicon-ok"></i>确定</button>
						<button  onclick="btnReset()" class=" btn  btn-danger" name="reset"><i class="glyphicon glyphicon-trash"></i>清空</button>
						<button  onclick="parent.closeDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
					</div>
			</div>
 	    </div> 
  </div>

</body>
</html>
