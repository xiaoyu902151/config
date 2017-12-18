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
		$(document).ready(function() {
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
		           
		            oldPasswork: {
		                validators: {
		                    notEmpty: {
		                        message: '密码不能为空！'
		                    },
							stringLength: {
								min: 3,
								max: 30,
								message: '密码必须是3~30个字母组成！'
							}
		                }
		            },
		            newPasswork: {
		                validators: {
		                    notEmpty: {
		                        message: '密码不能为空！'
		                    },
							stringLength: {
								min: 3,
								max: 30,
								message: '密码必须是3~30个字母组成！'
							}
		                }
		            },
		            new2Passwork: {
		                validators: {
		                    notEmpty: {
		                        message: '密码不能为空！'
		                    },
							stringLength: {
								min: 3,
								max: 30,
								message: '密码必须是3~30个字母组成！'
							},
                     identical: {
                        field: 'newPasswork',
                         message: '两次输入密码不一致'
                                }
		                }
		            }
		        }
		    });
		});

    

		function btnReset(){
			$("input").val('');
			$("select > option:first").attr("selected", true);
		   var validator  = $("#organForm").data('formValidation');
		   validator.resetForm()
		};
		function btnClick(){
		    var validator  = $("#organForm").data('formValidation');
			var isValid = isValidForm(validator);
			if(!isValid) return;
			parent.addData("jDialogFrame","organForm","${pageContext.request.contextPath}/admin/user.do?resetPasswork")
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
 	   		 <input type="hidden" name="userId"  value="<%=request.getParameter("userId")%>"/>
				<div class="form-group">
						<label class="col-xs-2 control-label">原密码<span>*</span></label>
						<div class="col-xs-10">
							<input type="password" class="form-control" name="oldPasswork" />
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">新密码<span>*</span></label>
						<div class="col-xs-10">
							<input type="password" class="form-control" name="newPasswork" />
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">重复密码<span>*</span></label>
						<div class="col-xs-10">
							<input type="password" class="form-control" name="new2Passwork" />
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
