<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		            chName: {
		                validators: {
		                    notEmpty: {
		                        message: '中文名不能为空！'
		                    },
							stringLength: {
								min: 1,
								max: 50,
								message: '中文名必须是2~25个字符组成！'
							}
		                }
		            },
		            hwbh: {
		                validators: {
		                    notEmpty: {
		                        message: '危险货物编号不能为空！'
		                    },
							stringLength: {
								min: 1,
								max: 50,
								message: '危险货物编号必须是2~20个字符组成！'
							},
		                }
		            },
		            enName: {
		                validators: {
		                    notEmpty: {
		                        message: '英文名不能为空！'
		                    },
							stringLength: {
								min: 2,
								max: 20,
								message: '英文名必须是2~20个字符组成！'
							},
		                }
		            },
		            unbh: {
		                validators: {
		                    notEmpty: {
		                        message: 'UN编号不能为空！'
		                    },
							stringLength: {
								min: 0,
								max: 20,
								message: 'UN编号必须是2~20个字符组成！'
							},
		                }
		            },
		            fzs: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 25,
								message: '分子式的长度必须小于25个字符！'
							},
		                }
		            },
		            fzl: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 25,
								message: '分子量的长度必须小于25个字符！'
							},
		                }
		            },
		            cash: {
		                validators: {
		                    notEmpty: {
		                        message: 'CAS号不能为空！'
		                    },
							stringLength: {
								min: 0,
								max: 20,
								message: 'CAS号的长度必须小于20个字符！'
							},
		                }
		            },
		            wgyxz: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 50,
								message: '外观与性状的长度必须小于50个字符！'
							},
		                }
		            },
		            rd: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 20,
								message: '熔点必须是2~20个字符组成！'
							}
		                }
		            },
		            xdmd: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 10,
								message: '相对密度的长度必须小于10个字符！'
							}
		                }
		            },
		             fd: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 10,
								message: '沸点的长度必须小于10个字符！'
							}
		                }
		            },
		            bhzqy: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 10,
								message: '饱和蒸气压的长度必须小于10个字符！'
							}
		                }
		            },
		            rjx: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 50,
								message: '溶解性的长度必须小于50个字符！'
							}
		                }
		            },
		            qrtj: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 50,
								message: '侵入途径的长度必须小于50个字符！'
							}
		                }
		            },
		            rsx: {
		                validators: {
		                    notEmpty: {
		                        message: '请选择燃烧性！'
		                    }
		                }
		            },
		             rsfjw: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 50,
								message: '燃烧分解物的长度必须小于50个字符！'
							}
		                }
		            },
		            sd: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 5,
								message: '闪点的长度必须小于5个字符！'
							}
		                }
		            },
		            bzsx: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 5,
								message: '爆炸上限的长度必须小于5个字符！'
							}
		                }
		            },
		            yrwd: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 10,
								message: '引燃温度的长度必须小于10个字符！'
							},
							regexp: {
								regexp: /^(-)?[1-9]*[0-9]*$/,
								message: '引燃温度必须是数字！'
	                        }
		                }
		            },
		             bzxx: {
		                validators: {
		                    stringLength: {
								min: 0,
								max: 5,
								message: '爆炸下限的长度必须小于5个字符！'
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
		    
		    var knowlageId = "${dangerKnowlage.id}";
		    initFileList(knowlageId);
		});

    
    	/*加载附件列表*/
		function initFileList(knowlageId) {
			    fileListGrid = $('#fileListTable');
			/*	fileListGrid = fileListGrid.bootstrapTable('load', fileRows);*/
			if(knowlageId==null||knowlageId=='') {
				return;
			}
			var param={};
			$.ajax({
				type:'POST',
				cache:false,
				url:"${pageContext.request.contextPath}/support/degKnowlage.do?getAffixByKnowlageId&knowlageId="+knowlageId,
				data:{},
				dataType:'json',
				success:function(r){
					if(r.success){
					    fileRows = r.obj;
					    fileListGrid = fileListGrid.bootstrapTable('load', fileRows);
					}
				},
				error:function(){
					
				}
			});
		}   

		function btnReset(){
			$("input").val('');
			$("textarea").html('');
			$("select > option:first").attr("selected", true);
			var validator  = $("#organForm").data('formValidation');
			validator.resetForm();
			$('.glyphicon').removeClass('glyphicon-ok glyphicon-remove');
		};
		function btnClick(){
		    var validator  = $("#organForm").data('formValidation');
			var isValid = isValidForm(validator);
			if(!isValid) return;
			parent.editData("jDialogFrame","organForm","${pageContext.request.contextPath}/support/degKnowlage.do?update",$.toJSON(fileRows),$.toJSON(delRows));
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
			fileUploadDialog = jDialog.iframe("${pageContext.request.contextPath}/pages/support/degknowlage/fileUpload.jsp",{	                
			    title: '上传文件',
                width: 500,
                height:300,
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
							fileRows.push({
								id: null,
				                name: obj.name,
				                path: obj.path,
				                fileAfter:obj.fileAfter,
				                uploadTime:'--',
				                validity:'0'
							});
							fileListGrid.bootstrapTable('load', fileRows);
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
 	   	<div class="container">
 	   		<ul class="nav nav-tabs" role="tablist" id="myTab">
	            <li class="active"><a href="#message" data-toggle="tab">基本信息 <i class="glyphicon"></i></a></li>
	            <li><a href="#danger" data-toggle="tab">危险性 <i class="glyphicon"></i></a></li>
				<li><a href="#way" data-toggle="tab">保护措施 <i class="glyphicon"></i></a></li>
				<li><a href="#intervention" data-toggle="tab">处置 <i class="glyphicon"></i></a></li>
				<li><a href="#access" data-toggle="tab">附件 <i class="glyphicon"></i></a></li>
			</ul> 
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
	 	   		<input type="hidden" name="id" value="${dangerKnowlage.id}" />
				<div class="tab-content">
					<div class="tab-pane active" id="message">	
						<div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">中文名<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="chName" id="chName" value="${dangerKnowlage.chName}" />
							</div>
							<label class="col-xs-2 control-label">危险货物编号<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="hwbh" id="hwbh" value="${dangerKnowlage.hwbh}" />
							</div>
					    </div>	
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">英文名<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="enName" id="enName"  value="${dangerKnowlage.enName}"  />
							</div>
							<label class="col-xs-2 control-label">UN编号<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="unbh" id="unbh" value="${dangerKnowlage.unbh}" />
							</div>
					    </div>	
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">分子式</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="fzs" id="fzs"  value="${dangerKnowlage.fzs}" />
							</div>
							<label class="col-xs-2 control-label">分子量</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="fzl" id="fzl" value="${dangerKnowlage.fzl}"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">CAS号<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="cash" id="cash"  value="${dangerKnowlage.cash}"/>
							</div>
							<label class="col-xs-2 control-label">外观与性状</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="wgyxz" id="wgyxz" value="${dangerKnowlage.wgyxz}"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">熔点（℃）</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="rd" id="rd"  value="${dangerKnowlage.rd}"/>
							</div>
							<label class="col-xs-2 control-label">相对密度</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="xdmd" id="xdmd" value="${dangerKnowlage.xdmd}"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">沸点（℃）</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="fd" id="fd"  value="${dangerKnowlage.fd}"/>
							</div>
							<label class="col-xs-2 control-label">饱和蒸气压</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="bhzqy" id="bhzqy" value="${dangerKnowlage.bhzqy}"/>
							</div>
					    </div>	
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">溶解性</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="rjx" id="rjx" value="${dangerKnowlage.rjx}" />
							</div>
							<label class="col-xs-2 control-label">侵入途径</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="qrtj" id="qrtj" value="${dangerKnowlage.qrtj}"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">毒性</label>
							<div class="col-xs-10">
								<textarea class="form-control" name="dx"  id="dx" rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.dx}</textarea>
							</div>
					    </div>	
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">健康危害</label>
							<div class="col-xs-10">
							    <textarea class="form-control" name="jkwh"  id="jkwh"  rows="3" data-fv-stringlength data-fv-stringlength-max="500"  data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.jkwh}</textarea>
							</div>
					    </div>	
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">急救方法</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="jjff"  id="jjff" rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.jjff}</textarea>
							</div>
					    </div>	
					</div>
					 <div class="tab-pane" id="danger">
					    <div class="form-group" style="margin-bottom: 5px;">
					    	<label class="col-xs-2 control-label">燃烧性</label>
							<div class="col-xs-4">
								<dict:select defaultValue="" value="${dangerKnowlage.rsx}" name="rsx" id="rsx" dictName="RSX" cssClass="form-control"/>
								<%-- <select class="form-control" name="rsx" id="rsx">
									<option value="" <c:if test="${dangerKnowlage.rsx==null}"> selected = "selected" </c:if>>--请选择--</option>
						            <option value="1" <c:if test="${dangerKnowlage.rsx==1}"> selected = "selected" </c:if>>易燃</option>
						            <option value="2" <c:if test="${dangerKnowlage.rsx==2}"> selected = "selected" </c:if>>不易燃</option>
						        </select> --%>
							</div>
							<label class="col-xs-2 control-label">燃烧分解物</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="rsfjw" id="rsfjw"  value="${dangerKnowlage.rsfjw}"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">闪点(℃)</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="sd" id="sd"   value="${dangerKnowlage.sd}"/>
							</div>
							<label class="col-xs-2 control-label">爆炸上限(v%)</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="bzsx" id="bzsx" value="${dangerKnowlage.bzsx}" />
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">引燃温度(℃)</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="yrwd" id="yrwd" value="${dangerKnowlage.yrwd}"  />
							</div>
							<label class="col-xs-2 control-label">爆炸下限(v%)</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="bzxx" id="bzxx" value="${dangerKnowlage.bzxx}"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">危险特性</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="wxtx"  id="wxtx"  rows="5" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.wxtx}</textarea>
							</div>
					    </div>	
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">储运条件</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="cytj"  id="cytj"  rows="5" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.cytj}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">泄漏处理</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="xlcl"  id="xlcl" rows="6" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.xlcl}</textarea>
							</div>
					    </div>
					</div>
					<div class="tab-pane" id="way">
						<div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">个体防护</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="gtfh"  id="gtfh"  rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.gtfh}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">操作处理方法</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="czclff"  id="czclff"  rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.czclff}</textarea>
							</div>
					    </div>
					      <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">运输注意事项</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="yszysx"  id="yszysx" rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.yszysx}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">储存注意事项</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="cczysx"  id="cczysx"  rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字" >${dangerKnowlage.cczysx}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">泄漏防护</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="xlfh"  id="xlfh" rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.xlfh}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">火灾防护</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="hzfh"  id="hzfh" rows="3" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.hzfh}</textarea>
							</div>
					    </div>
					</div>
					<div class="tab-pane" id="intervention">
						<div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">灭火措施</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="mhcs"  id="mhcs" rows="11"  data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.mhcs}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">急救措施</label>
							<div class="col-xs-10">
							     <textarea class="form-control" name="jjcs"  id="jjcs" rows="11" data-fv-stringlength data-fv-stringlength-max="500" data-fv-stringlength-message="长度不能超过500个文字">${dangerKnowlage.jjcs}</textarea>
							</div>
					    </div>
					</div>
					<div class="tab-pane" id="access">
					 <div class="form-group">
						<button id="addTJ" type="button" class="col-xs-2 btn btn-info" onclick="addFile()">
							<i class="glyphicon glyphicon-plus"></i>上传文件
						</button>
	                 </div>
	                 <div class="form-group">
	                 <table id="fileListTable"
		               data-toggle="table"
		               data-height="400">
            		<thead>
           			 <tr>
               			 <th data-field="id" data-visible="false">ID</th>
               			 <th data-field="name">文件名</th>
               			 <th data-field="path">存放路径</th>
               			 <th data-field="fileAfter">文件后缀</th>
               			 <th data-field="uploadTime">上传时间</th>
               			 <th data-field="validity" data-visible="false">删除标记</th>
               			 <th data-field="operation" data-formatter="genterOperate">操作</th>
            		</tr>
           			 </thead>
        			</table>
	                 </div>	
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
