<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
<script type="text/javascript">
	function savePeople() {
		var validator  = $("#organForm").data('formValidation');
		var isValid = isValidForm(validator);
		if(!isValid) return;
		var form = $("#organForm");
		parent.savePeople(form);
	}
	
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
	        	name: {
	                validators: {
	                    notEmpty: {
	                        message: '姓名不能为空！'
	                    },
	                    stringLength: {
							min: 1,
							max: 10,
							message: '姓名必须是1~10个字符组成！'
						}
	                }
	            },
	            phone: {
	                validators: {
	                    notEmpty: {
	                        message: '联系电话不能为空！'
	                    },
	                    stringLength: {
							min: 4,
							max: 18,
							message: '联系电话必须是4~18个数字或-组成！'
						},
						regexp: {
							regexp: /^[0-9\-]+$/,
							message: '联系电话必须由数字或-组成！'
                        }
	                }
	            },
	            duty: {
	            	validators: {
	                    notEmpty: {
	                        message: '职责不能为空！'
	                    },
	                    stringLength: {
							min: 1,
							max: 50,
							message: '职责必须是1~50个数字组成！'
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
				<input type="text" style="display:none" class="form-control" name="id" id="id" />
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">姓名<span>*</span></label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="name" id="name" />
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">性别<span>*</span></label>
					<div class="col-xs-8">
						<select class="form-control" name="sex" id="sex">
				            <option value="1">男</option>
				            <option value="2">女</option>
						</select>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">联系电话<span>*</span></label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="phone" id="phone" />
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;text-align:right;">
					<label class="col-xs-3 control-label">职责<span>*</span></label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="duty" id="duty" />
					</div>
				</div>
			</form>
			<div class="modal-footer Ptfixed">
				<div>
					<button onclick="savePeople()" class=" btn btn-default" name="addTJ"
						value="close">
						<i class="glyphicon glyphicon-plus"></i>保存
					</button>
					<button  onclick="parent.closePeopleDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>