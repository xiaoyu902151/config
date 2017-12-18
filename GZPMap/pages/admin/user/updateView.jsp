<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.bxsurvey.sys.user.model.SysUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="relation" uri="http://www.bxsurvey.com/relation" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%
SysUser user = (SysUser) request.getSession().getAttribute("userBO");//获取用户对象

String userType=user.getUserType().toString();

%>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/jedate/jedate.css"/>    
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/moment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jedate/jedate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			//查询用户类型select
			var userType="<dict:select defaultValue="" value="${sysUser.userType}"  name="userType" id="paramsValue"  dictName="USERTYPE"  />";
			$("#userType").html(userType);
			$("#paramsValue").attr("class","form-control");
			for (var i=0;i<$("#paramsValue").children().length;i++){
				if($($("#paramsValue").children()[i]).val()=='<%=userType%>'){
					$("#paramsValue").children().slice(1,i+1).remove();
				}
			}
			$("#birthday").bind("click", function () {
        		//时间控件
        		var start = {
        				dateCell: '#birthday',
        				format: 'YYYY-MM-DD',
        				festival:false,
        				isTime: false,
        				choosefun: function(datas){
        					$("#organForm").data('formValidation')  
        	                    .updateStatus('birthday', 'NOT_VALIDATED',null)  
        	                    .validateField('birthday');
        					  
        				},
        		         clearfun:function(){
        		        	 $("#organForm").data('formValidation')  
     	                    .updateStatus('birthday', 'NOT_VALIDATED',null)  
     	                    .validateField('birthday');
        		         },
        		         okfun:function(){
        		        	 $("#organForm").data('formValidation')  
      	                    .updateStatus('birthday', 'NOT_VALIDATED',null)  
      	                    .validateField('birthday');
        		         }
        		};
        		jeDate(start);
        		
        		
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
		            loginName: {
		                validators: {
		                    notEmpty: {
		                        message: '登录帐号不能为空！'
		                    },
							stringLength: {
								min: 3,
								max: 30,
								message: '登录帐号必须是3~30个字母或数字组成！'
							},
							regexp: {
								regexp: /^[a-zA-Z0-9_\.]+$/,
								message: '登录帐号必须是字母和数字组成！'
	                        }
		                }
						
		            },
		            userType: {
		                validators: {
		                    notEmpty: {
		                        message: '用户类型不能为空！'
		                    }
		                }
		            },
		            realName: {
		                validators: {
		                    notEmpty: {
		                        message: '真实姓名不能为空！'
		                    },
							stringLength: {
								min: 2,
								max: 30,
								message: '真实姓名必须是2~30个字母组成！'
							}
		                }
		            },
/* 		            birthday: {
		                validators: {
		                    notEmpty: {
		                        message: '生日不能为空！'
		                    },
		                	callback: {
		                        message: '时间格式有误！',
		                        callback: function(value, validator) {
		                            var m = new moment(value, 'YYYY-MM-DD', true);
		                            // Check if the input value follows the MMMM D format
		                            if (!m.isValid()) {
		                                return false;
		                            }
		                            
		                            return true;
		                        }
		                    }
		                }
		            }, */
					regDate: {
		                validators: {
		                    notEmpty: {
		                        message: '注册时间不能为空！'
		                    },
		                    date: {
		                        format: 'YYYY-MM-DD'
		                    }
		                }
		            },
					validity: {
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
			parent.editData("jDialogFrame","organForm","${pageContext.request.contextPath}/admin/user.do?update")
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
<body style="overflow:hidden;">
    <div id="organDialog" class="col-sm-8 col-sm-offset-2"> 
 	   	<div class="container">
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
	 	   	<input type="hidden" name="userId" value="${sysUser.userId}" />
	 	   	<input type="hidden" class="form-control" name="regDate" value="${sysUser.regDate}"  data-date-format="YYYY-MM-DD"/>
			<input type="hidden" class="form-control" name="password" value="${sysUser.password}"/>	
				<div class="form-group">
						<label class="col-xs-2 control-label">登录帐号<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="loginName" value="${sysUser.loginName}"  readonly="readonly"/>
						</div>
						<label class="col-xs-2 control-label">用户类型<span>*</span></label>
						<div class="col-xs-4" id="userType">
						
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">真实姓名<span>*</span></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="realName" value="${sysUser.realName}"/>
						</div>
						<label class="col-xs-2 control-label">出生日期</label>
						<div class="col-xs-4">
								  <input type="text" class="form-control" name = "birthday" id="birthday"  value="<fmt:formatDate value='${sysUser.birthday}' pattern='yyyy-MM-dd' />" style="cursor:pointer; "/>
						</div>
				</div>
				<div class="form-group" >
					<label class="col-xs-2 control-label">所属企业</label>
					<div class="col-xs-10">
						<relation:select defaultValue="" value="${sysUser.departId}" saveField="departId" disField="departName" tableName="SysDepart"  name="departId" id="departId"  cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">手机号码</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="moblie" value="${sysUser.moblie}"/>
						</div>
						<label class="col-xs-2 control-label">用户地址</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="address" value="${sysUser.address}"/>
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">联系电话</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="tel" value="${sysUser.tel}"/>
						</div>
						<label class="col-xs-2 control-label">QQ号码</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="oicq" value="${sysUser.oicq}"/>
						</div>
				</div>
				<div class="form-group">
						<label class="col-xs-2 control-label">电子邮件</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="email" value="${sysUser.email}"/>
						</div>
						<%--<label class="col-xs-2 control-label">注册日期</label>
						<div class="col-xs-4">
							<div class="input-group date form_datetime" id="regDate" data-date-format="YYYY-MM-DD">
								  <input type="text" class="form-control" value="${sysUser.regDate}" disabled="disabled" />
								  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar" disabled="disabled"></span></span>
							</div>
						</div>
				--%>
				<label class="col-xs-2 control-label">有效性<span>*</span></label>
						<div class="col-xs-4">
						    <input name="validity" type="radio" value="true" <c:if test="${sysUser.validity==true}"> checked </c:if> style="width:20px;" />是
							<input name="validity" type="radio" value="false" <c:if test="${sysUser.validity==false}"> checked </c:if> style="width:20px;"/>否
						</div>
				</div>
				<div class="form-group">
						
						<label class="col-xs-2 control-label">备注</label>
						<div class="col-xs-10">
							<input type="text" class="form-control" name="bz" value="${sysUser.bz}"/>
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
