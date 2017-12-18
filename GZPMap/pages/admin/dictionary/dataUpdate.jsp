<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
 	<script src="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-form.js"></script>

<script type="text/javascript">
	function saveData(){
			var validator  = $("#organForm").data('formValidation');
			var isValid = isValidForm(validator);
			if(!isValid) return;
			parent.editData("jDialogFrame","organForm","${pageContext.request.contextPath}/sysParams.do?update");
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
		
	$(document).ready(function(){
		$('#organForm').formValidation({
				err: {
		            container: 'tooltip'
		        },
		        excluded: [':disabled'],
		        message: '输入的值格式有误！',
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		            paramsName: {
		                validators: {
		                    notEmpty: {
		                        message: '参数名称不能为空！'
		                    },
		                    stringLength: {
								min: 1,
								max: 10,
								message: '参数名称必须是1~10个字符组成！'
							}
		                }
		            },
		            paramsBz: {
		                validators: {
							stringLength: {
								max: 122,
								message: '备注最多为122个字符组成！'
							}
		                }
		            },
		            paramsIndex: {
		                validators: {
		                    notEmpty: {
		                        message: '排序不能为空！'
		                    },
		                    stringLength: {
								min: 1,
								max: 4,
								message: '参数名称必须是1~4个字符组成！'
							},
							regexp: {
								regexp: /^[0-9]+$/,
								message: '排序必须由数字组成！'
	                        }
		                }
		            },
		            paramsValue: {
		                validators: {
							stringLength: {
								max: 16,
								message: '参数值最多为16个字符组成！'
							}
		                }
		            }
		        }
		 });
	});
</script>
</head>
<body>
	<div id="organDialog">
		<div class="container">
			<form id="organForm" method="post" class="form-horizontal" enctype="multipart/form-data">
				<input type="text" style="display:none" class="form-control" name="paramsType" id="paramsType" value="${sysParamsTest.paramsType}"/>
				<input type="text" style="display:none" class="form-control" name="pid" id="pid" value="${sysParamsTest.pid}"/>
				<input type="text" style="display:none" class="form-control" name="paramsId" id="paramsId" value="${sysParamsTest.paramsId}"/>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">参数名称<span>*</span></label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="paramsName" id="paramsName" value="${sysParamsTest.paramsName}"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">参数值</label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="paramsValue" id="paramsValue" value="${sysParamsTest.paramsValue}"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">备注</label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="paramsBz" id="paramsBz" value="${sysParamsTest.paramsBz}"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">排序<span>*</span></label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="paramsIndex" id="paramsIndex" value="${sysParamsTest.paramsIndex}"/>
					</div>
				</div>
			</form>
			<div class="modal-footer Ptfixed">
				<div>
					<button onclick="saveData()" class=" btn btn-default" name="save"
						value="save">
						<i class="glyphicon glyphicon-plus"></i>保存
					</button>
					<button  onclick="parent.closeDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>