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
	        	plotName: {
	                validators: {
	                    notEmpty: {
	                        message: '方案名称不能为空！'
	                    },
	                    stringLength: {
							max: 22,
							message: '方案名称不能超过22个字符！'
						}
	                }
	            },
	            bz: {
	                validators: {
	                    stringLength: {
							max: 122,
							message: '备注不能超过122个字符！'
						}
	                }
	            },
	            plotGeo: {
	                validators: {
	                    notEmpty: {
	                        message: '方案不能为空！'
	                    }
	                }
	            }
	        }
	   });
	});
	function init(){
		var form = $("#organForm");
		parent.initAddPlotData(form);
	}
	function savePlot() {
		var geoIndex = $("#geoIndex").val();
		$("#plotGeo").val(parent.parent.parent.support.plot_plan.getGraphicJsonByIndex(geoIndex));
		var validator  = $("#organForm").data('formValidation');
		validator.resetForm();
		var isValid = isValidForm(validator);
		if(!isValid) return;
		var form = $("#organForm");
		parent.savePlot(form);
	}
	function showGraphicsWin(){
		var geoIndex = $("#geoIndex").val();
		parent.parent.parent.support.plot_plan.plotMap(geoIndex);
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
</script>
</head>
<body>
	<div id="organDialog">
		<div class="container">
			<form id="organForm" method="post" class="form-horizontal" enctype="multipart/form-data">
				<input type="hidden" class="form-control" name="id" id="id" />
				<input type="hidden" class="form-control" name="geoIndex" id="geoIndex" />
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">方案名称</label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="plotName" id="plotName" />
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">事件类型</label>
					<div class="col-xs-8">
						<input type="hidden" class="form-control" name="planType" id="planType" />
						<input type="text" class="form-control" name="planTypeDis" id="planTypeDis" disabled="true"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">备注</label>
					<div class="col-xs-8">
						<input class="form-control" name="bz" id="bz"/>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
					<label class="col-xs-3 control-label">方案</label>
					<div class="col-xs-8">
						<input type="hidden" class="form-control" name="plotGeo" id="plotGeo"/>
						<button type="button" class="btn btn-default" onclick="showGraphicsWin()">绘制</button>
					</div>
				</div>
			</form>
			<div class="modal-footer Ptfixed">
				<div>
					<button onclick="savePlot()" class=" btn btn-default" name="addTJ"
						value="close">
						<i class="glyphicon glyphicon-plus"></i>保存
					</button>
					<button  onclick="parent.closePlotDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>