<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
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
	$(document).ready(function() {
		init();
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
	            oneLevelStand: {
	                validators: {
	                    notEmpty: {
	                        message: 'I级需求不能为空！'
	                    },
	                    stringLength: {
							max: 6,
							message: 'I级需求不能超过6个数字！'
						},
						regexp: {
							regexp: /^[0-9]+$/,
							message: 'I级需求必须是数字！'
                        }
	                }
	            },
	            twoLevelStand: {
	                validators: {
	                    notEmpty: {
	                        message: 'II级需求不能为空！'
	                    },
	                    stringLength: {
							max: 6,
							message: 'II级需求不能超过6个数字！'
						},
						regexp: {
							regexp: /^[0-9]+$/,
							message: 'II级需求必须是数字！'
                        }
	                }
	            },
	            threeLevelStand: {
	                validators: {
	                    notEmpty: {
	                        message: 'III级需求不能为空！'
	                    },
	                    stringLength: {
							max: 6,
							message: 'III级需求不能超过6个数字！'
						},
						regexp: {
							regexp: /^[0-9]+$/,
							message: 'III级需求必须是数字！'
                        }
	                }
	            },
	        }
	   });
	});
	
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
	
	function init(){
		var form = $("#organForm");
		parent.initAddStandData(form);
	};
	function saveStand() {
		var validator  = $("#organForm").data('formValidation');
		var isValid = isValidForm(validator);
		if(!isValid) return;
		var form = $("#organForm");
		parent.saveStand(form);
	};
</script>
</head>
<body>
	<div id="organDialog">
		<div class="container">
			<form id="organForm" method="post" class="form-horizontal" enctype="multipart/form-data">
				<input type="hidden" class="form-control" name="id" id="id" />
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">事件类型</label>
					<div class="col-xs-8">
						<input type="hidden" class="form-control" name="planType" id="planType" />
						<input type="text" class="form-control" name="planTypeDis" id="planTypeDis" disabled="true"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">物资种类</label>
					<div class="col-xs-8">
						<select class="form-control" name="suppliesType" id="suppliesType" >
							<option value="77">消防水泵</option>
							<option value="78">同步发电机</option>
							<option value="79">消防栓</option>
							<option value="80">消防池</option>
							<option value="81">消防水罐</option>
							<option value="82">消防车</option>
							<option value="83">呼吸器</option>
							<option value="84">应急工具</option>
							<option value="76">灭火器</option>
						</select>
						<%-- <dict:select defaultValue="" value="" name="suppliesType" id="suppliesType" dictName="SUPPLIES_TYPE_DETAIL" cssClass="form-control"/> --%>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">I级需求</label>
					<div class="col-xs-8">
						<input class="form-control" name="oneLevelStand" id="oneLevelStand" value="0" type="text"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">II级需求</label>
					<div class="col-xs-8">
						<input class="form-control" name="twoLevelStand" id="twoLevelStand" value="0" type="text"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">III级需求</label>
					<div class="col-xs-8">
						<input class="form-control" name="threeLevelStand" id="threeLevelStand" value="0" type="text"/>
					</div>
				</div>
			</form>
			<div class="modal-footer Ptfixed">
				<div>
					<button onclick="saveStand()" class=" btn btn-default" name="addTJ"
						value="close">
						<i class="glyphicon glyphicon-plus"></i>保存
					</button>
					<button  onclick="parent.closeStandDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>