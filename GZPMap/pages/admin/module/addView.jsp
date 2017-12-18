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
		            moduleId: {
		                validators: {
		                    notEmpty: {
		                        message: '资源ID不能为空！'
		                    },
							stringLength: {
								min: 1,
								max: 10,
								message: '资源ID必须是1~10个数字！'
							},
							regexp: {
								regexp: /^[0-9_\.]+$/,
								message: '资源类型必须是数字！'
	                        }
		                }
						
		            },moduleName: {
		                validators: {
		                    notEmpty: {
		                        message: '资源名称不能为空！'
		                    },
							stringLength: {
								min: 1,
								max: 15,
								message: '资源名称必须是1~15个字符！'
							}
		                }
						
		            },
		            moduleType: {
		                validators: {
		                    notEmpty: {
		                        message: '资源类型不能为空！'
		                    },
							stringLength: {
								min: 1,
								max: 3,
								message: '资源类型是1~3个数字组成！'
							},
							regexp: {
								regexp: /^[0-9_\.]+$/,
								message: '资源类型必须是数字！'
	                        }
		                }
		            },
		            modulePid: {
		                validators: {
		                    notEmpty: {
		                        message: '上级菜单ID不能为空！'
		                    },
							stringLength: {
								min: 1,
								max: 10,
								message: '上级菜单ID必须是1~6个数字组成！'
							},
							regexp: {
								regexp: /^[0-9_\.]+$/,
								message: '上级菜单ID必须是数字！'
	                        }
		                }
		            },
					modulePath: {
		                validators: {
		                    notEmpty: {
		                        message: '资源地址不能为空！'
		                    }
		                }
		            },
					moduleIcon: {
		                validators: {
		                    notEmpty: {
		                        message: '资源图标不能为空！'
		                    }
		                }
		            },
					moduleValidity: {
						validators: {
							notEmpty: {
								message: '必须选择有效性！'
							}
						}
					}
		        }
		    });
		});

    

		function btnReset(){
			$("input").val('');
		   var validator  = $("#organForm").data('formValidation');
		   validator.resetForm()
		};
		function btnClick(){
		    var validator  = $("#organForm").data('formValidation');
			var isValid = isValidForm(validator);
			if(!isValid) return;
			parent.addData("jDialogFrame","organForm","${pageContext.request.contextPath}/admin/module.do?save")
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
 	   	    <input type="hidden" class="form-control" name="moduleValidity" value="true"   />
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
				<div class="form-group">
						<label class="col-xs-2 control-label">资源ID<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moduleId"  />
						</div>
						<label class="col-xs-2 control-label">资源名称<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moduleName"  />
						</div>

				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">资源类型<span>*</span></label>
						<div class="col-xs-4">
							<select class="form-control" name="moduleType">
								<option value="1">子系统</option>
								<option value="2">顶部菜单</option>
								<option value="3">左侧菜单</option>
								<option value="4">地图图层</option>
								<option value="5">地图工具条</option>
								<option value="6">功能按钮</option>
							</select>
<!-- 							<input type="text" class="form-control" name="moduleType" /> -->
						</div>
						<label class="col-xs-2 control-label">上级菜单ID<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="modulePid" />
						</div>

				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">资源地址<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="modulePath" />
						</div>
						<label class="col-xs-2 control-label">资源图标</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moduleIcon" />
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">资源顺序</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moduleIndex" />
						</div>
						<label class="col-xs-2 control-label">资源值</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moduleValue" />
						</div>

				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">有效性<span>*</span></label>
						<div class="col-xs-4">
						    <input type="radio" value="true"  checked disabled="disabled" style="width:20px;" />是
							<input type="radio" value="false" disabled="disabled" style="width:20px;"/>否
						</div>
						<label class="col-xs-2 control-label">备注</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moduleBz" />
						</div>
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
