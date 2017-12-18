<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/support/support_add.css"/>
	      
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
		            departName: {
		                validators: {
		                    notEmpty: {
		                        message: '公司名不能为空！'
		                    },
							stringLength: {
								min: 3,
								max: 30,
								message: '公司名必须是3~30个字符组成！'
							}
		                }
						
		            },
		            departContact: {
		                validators: {
		                    notEmpty: {
		                        message: '联系人不能为空！'
		                    },
							stringLength: {
								min: 2,
								max: 30,
								message: '联系人必须是2~30个字符组成！'
							}
		                }
		            },
		            departTel: {
		                validators: {
		                    notEmpty: {
		                        message: '联系电话不能为空！'
		                    },
							stringLength: {
								min: 3,
								max: 30,
								message: '联系电话必须是3~30个数字组成！'
							}
		                }
		            },
					departAddress: {
		                validators: {
		                    notEmpty: {
		                        message: '地址不能为空！'
		                    },
		                    stringLength: {
								min: 3,
								max: 30,
								message: '地址必须是3~30个字符组成！'
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
			parent.editData("jDialogFrame","organForm","${pageContext.request.contextPath}/admin/depart.do?update")
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
		
		function sonff(){ 
			alert(">>this is inner page's js function"); 
		}; 

	</script>
</head>
<body style="overflow: hidden;">

    <div id="organDialog"> 
 	   	<div class="container">
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
 	   			<input type="hidden" name="departId" value="${sysDepart.departId}"/>
 	   			<input type="hidden" class="form-control" name="departValidity" value="true"/>
				<div class="tab-content">	
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">公司名<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departName" value="${sysDepart.departName}"/>
							</div>
							 <label class="col-xs-2 control-label">联系人<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departContact" value="${sysDepart.departContact}"/>
							</div> 
							
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">联系电话<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departTel" value="${sysDepart.departTel}"/>
							</div>
							<label class="col-xs-2 control-label">地址<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departAddress" value="${sysDepart.departAddress}"/>
							</div> 
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">电子邮件</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departEmail" value="${sysDepart.departEmail}"/>
							</div>
							<label class="col-xs-2 control-label">单位类型</label>
							<div class="col-xs-4">
								<dict:select defaultValue="" value="${sysDepart.paramsId}" name="paramsId" id="paramsId" dictName="DEPART" cssClass="form-control"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">机构值</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departValue" value="${sysDepart.departValue}"/>
							</div>
							<label class="col-xs-2 control-label">是否部门</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departType" value="${sysDepart.departType}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">备注</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departBz" value="${sysDepart.departBz}"/>
							</div>
							<label class="col-xs-2 control-label">传真</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departFax" value="${sysDepart.departFax}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">邮政编码</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departZipcode" value="${sysDepart.departZipcode}"/>
							</div>
							<label class="col-xs-2 control-label">营业执照</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departLicence" value="${sysDepart.departLicence}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">企业类型</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departForm" value="${sysDepart.departForm}"/>
							</div>
							<label class="col-xs-2 control-label">登记机关</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departRedistration" value="${sysDepart.departRedistration}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">法人代表</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departRepresentative" value="${sysDepart.departRepresentative}"/>
							</div>
							<label class="col-xs-2 control-label">主管负责人</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departSupervisor" value="${sysDepart.departSupervisor}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">职工人数</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departWorkerNum" value="${sysDepart.departWorkerNum}"/>
							</div>
							<label class="col-xs-2 control-label">技术管理人数</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departTechnicalNum" value="${sysDepart.departTechnicalNum}"/>
							</div>
					</div>
	 				<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">安全管理人数</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departSafetyNum" value="${sysDepart.departSafetyNum}"/>
							</div>
							<label class="col-xs-2 control-label">注册资本</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departRegisteredCapital" value="${sysDepart.departRegisteredCapital}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">经营场所地址</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departBusinessAddress" value="${sysDepart.departBusinessAddress}"/>
							</div>
							<label class="col-xs-2 control-label">经营场所产权</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departBusinessProperty" value="${sysDepart.departBusinessProperty}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">储存设施地址</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departStorageAddress" value="${sysDepart.departStorageAddress}"/>
							</div>
							<label class="col-xs-2 control-label">储存设施产权</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departStorageProperty" value="${sysDepart.departStorageProperty}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">建筑结构</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departBuilding" value="${sysDepart.departBuilding}"/>
							</div>
							<label class="col-xs-2 control-label">储存能力</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departStoragePower" value="${sysDepart.departStoragePower}"/>
							</div>
					</div>
					<div class="form-group" style="margin-bottom: 2px;">
							<label class="col-xs-2 control-label">主要管理制度</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departSystemName" value="${sysDepart.departSystemName}"/>
							</div>
							<label class="col-xs-2 control-label">占地面积</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="departArea" value="${sysDepart.departArea}"/>
							</div>
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
