<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/support/support_add.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.css"/>
	      
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/moment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
 	    
    <link rel='stylesheet' href="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog/jDialog.css" />
	<script src="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-form.js"></script>
	<script type="text/javascript">
        var fileListGrid;
        var fileRows = [];
        var delRows =[];
        
        var fileUploadDialog;
		$(document).ready(function() {
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
		            lawName: {
		                validators: {
		                    notEmpty: {
		                        message: '法律名不能为空！'
		                    }
		                }
		            }
		        }
		    })
		    .on('err.form.fv', function(e) {
		        var $form  = $(e.target),
		        fv = $form.data('formValidation'),
		        $invalidFields = fv.getInvalidFields();
	            for (var i = 0; i < $invalidFields.length; i++) {
	                var $field    = $invalidFields.eq(i),
	                    autoFocus = fv.isOptionEnabled($field.attr('data-fv-field'), 'autoFocus');
	                if (autoFocus) {
	                    var $tabPane = $field.parents('.tab-pane'), tabId;
	                    if ($tabPane && (tabId = $tabPane.attr('id'))) {
	                        $('a[href="#' + tabId + '"][data-toggle="tab"]').tab('show');
	                    }
	                    break;
	                }
	            }
		     })
		     .on('status.field.fv', function(e, data) {
	            var validator = data.fv,
	                $tabPane = data.element.parents('.tab-pane'),
	                tabId = $tabPane.attr('id');
	            
	            if (tabId) {
	                var $icon = $('a[href="#' + tabId + '"][data-toggle="tab"]').parent().find('i');
	                if (data.status == validator.STATUS_INVALID) {
	                    $icon.removeClass('glyphicon-ok').addClass('glyphicon-remove');
	                } else if (data.status == validator.STATUS_VALID) {
	                    $icon.removeClass('glyphicon-ok glyphicon-remove');

	                    var isValidTab = validator.isValidContainer($tabPane);
	                    if (isValidTab !== null) {
	                        $icon.addClass(isValidTab ? 'glyphicon-ok' : 'glyphicon-remove');
	                    }
	                }
	            }
		    });
	            
			    fileListGrid = $('#fileListTable');
				fileListGrid = fileListGrid.bootstrapTable('load', fileRows);
			});

		function btnReset(){
			$("input").val('');
			$("textarea").html('');
			$("select > option:first").attr("selected", true);
		    var validator  = $("#organForm").data('formValidation');
		    validator.resetForm();
		    $('.glyphicon').removeClass('glyphicon-ok glyphicon-remove');
		};
		function btnClick(){
			var fj = $("#fj").val();
		    var validator  = $("#organForm").data('formValidation');
			var isValid = isValidForm(validator);
			if(!isValid) return;
			parent.editData("jDialogFrame","organForm","${pageContext.request.contextPath}/support/law.do?update&fj="+fj);
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
		
		function addFile(){
			fileUploadDialog = jDialog.iframe("${pageContext.request.contextPath}/pages/support/law/lawFileUpload.jsp",{	                
			    title: '上传文件',
                width: 500,
                height:250,
                modal: true, // 非模态，即不显示遮罩层
                iframeId:"jDialogFrame"///对话框iframe的ID
	        })
		};
		function uploadFile(frmName,formName,AjaxUrl) {
		    var objForm = $("#"+frmName).contents().find("#"+formName);
		    var options = {
		    	url: AjaxUrl,
		    	success: function (data) {
		    	try {
                   	    var d = $.parseJSON(data);
						if (d.success) {
							fileUploadDialog.close();
							var obj = d.obj;
							$("#fj").val(obj.path+"/"+obj.name+"."+obj.fileAfter);
						}
					} catch (e) {
							var dialog = jDialog.message('数据上传失败',{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
					} 
		    	}
		    }
		    objForm.ajaxSubmit(options);
		};
		
		function genterOperate(value,row,index){
		     var html = "<button type='button' onclick='downLoad("+index+")' class=' btn ' name='reset'><i class='glyphicon glyphicon glyphicon-download-alt'></i>下载</button>";
		     	 html = html +"<button type='button' onclick='deleteRow("+index+")' class=' btn  btn-danger' name='reset'><i class='glyphicon glyphicon-remove'></i>删除</button>";
		     return  html;
		};
		
		function closeDialog(){
            fileUploadDialog.close();
        };
        
		function downLoad(index){
		    var row = fileRows[index];
            window.open("${pageContext.request.contextPath}/file.do?downloadFile&path="+row.path+"&fileName="+encodeURI(encodeURI(row.name))+"&fileAfter="+row.fileAfter);  
		};
		function deleteRow(index){
		    var row = fileRows[index];
		    if(row.id){
		    	delRows.push(row);
		    	fileRows.splice(index,1);
		    }else{
		    	fileRows.splice(index,1);
		    }
		    fileListGrid.bootstrapTable('load', fileRows);
		};
	</script>
</head>
<body style="overflow:hidden;">
    <div id="organDialog"> 
 	   	<div class="container" style="margin-top:10px;">
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
				<input type="hidden" name="id" value="${law.id}"/>
					<div class="form-group">
						<label class="col-xs-2 control-label">法律名称<span>*</span></label>
						<div class="col-xs-10">
							<input type="text" class="form-control" value="${law.lawName}" name="lawName" id="lawName"  />
						</div>
				    </div>
				    <div class="form-group">
						<label style="padding-left: 44px" class="col-xs-2 control-label">类型</label>
						<div class="col-xs-10">
							<dict:select defaultValue="" value="${law.sysParams.paramsId}" name="sysParams.paramsId" id="type" dictName="LAW_TYPE" cssClass="form-control"/>
						</div>
				    </div>		
				    <div class="form-group">
						<label style="padding-left: 44px" class="col-xs-2 control-label">描述</label>
						<div class="col-xs-10">
						     <textarea class="form-control" name="descrition"  id="descrition" rows="3" data-fv-stringlength data-fv-stringlength-max="500" >${law.descrition}</textarea>
						</div>
				    </div>
				    <div class="form-group">
				   		<label style="padding-left: 44px" class="col-xs-2 control-label">附件</label>
				   		<div class="col-xs-8" style="padding-right:0px;">
								<input style="" type="text" class="form-control" name="fj" id="fj" disabled="disabled" value="${law.fj}"/>
						</div>
						<div class="col-xs-2" style="padding-left:0px;">
						    <button type="button"  data-toggle="modal" data-target="#mymodal-data" class="btn btn-default" style="margin-left: 0px;" value="上传" id="eventBtn" onclick="addFile()">上传</button>
						</div>

	                 </div>		
			</form> 
			<div class="modal-footer Ptfixed">
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
