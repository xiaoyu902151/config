<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%@taglib prefix="relation" uri="http://www.bxsurvey.com/relation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/support/support_add.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/jedate/jedate.css"/>  
	      
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/moment.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/js/plugins/ztree/zTreeStyle/img/zTreeStyle.css" rel="stylesheet" type="text/css" />
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
    <link rel='stylesheet' href="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog/jDialog.css" />
	<script src="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-form.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/pages/support/plan/people.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/pages/support/plan/stand.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/pages/support/plan/plot.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jedate/jedate.js"></script>
	<style type="text/css">
		.ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
	    }
	</style>
	<script type="text/javascript">
        var fileListGrid;
        var fileRows = [];
        var delRows =[];
        var fileUploadDialog;
        //初始化树控件
     	 var zNodesTeam = [
     	 { id: 1, pId: "", name: "泄漏与火灾",checked:false,nocheck:true},
     	 { id: 2, pId: 1, name: "油罐泄漏",checked:false,nocheck:false},
     	 { id: 3, pId: 1, name: "装车台油品泄漏",checked:false,nocheck:false},
     	 { id: 4, pId: 1, name: "机泵泄漏着火",checked:false,nocheck:false},
     	 { id: 5, pId: 1, name: "油船装卸漏油或着火",checked:false,nocheck:false},
     	 { id: 6, pId: "", name: "水体污染",checked:false,nocheck:true},
     	 { id: 7, pId: 6, name: "码头水体污染",checked:false,nocheck:false},
     	 { id: 8, pId: 6, name: "油库罐区水体污染",checked:false,nocheck:false},
     	 { id: 9, pId: 6, name: "装车台水体污染",checked:false,nocheck:false},
     	 { id: 10, pId: "", name: "中毒事故",checked:false,nocheck:true},
     	 { id: 11, pId: 10, name: "急性职业中毒事故",checked:false,nocheck:false}
     	 ];
     	 var eventTypeSetting = {
     			 check: {
    				enable: true,
    				chkboxType: {"Y":"", "N":""}
    			 },
       			 view: {
       				 dblClickExpand: true,
       				 selectedMulti: false
       			 },
       			 data: {
       				 simpleData: {
       					 enable: true
       				 }
       			 },
       			 callback: {
       				 //beforeClick: beforeClick,
       				 onCheck: onCheck
       			 }
       	 };
		$(document).ready(function() {
			
			$("#expiryDate").bind("click", function () {
	    		//时间控件
	    		var start = {
	    				dateCell: '#expiryDate',
	    				format: 'YYYY-MM-DD',
	    				festival:false,
	    				isTime: false,
	    				choosefun: function(datas){
	    				 	$("#organForm").data('formValidation')  
	    	                    .updateStatus('expiryDate', 'NOT_VALIDATED',null)  
	    	                    .validateField('expiryDate'); 
	    					  
	    				},
	    		         clearfun:function(){
	    		         	 $("#organForm").data('formValidation')  
	 	                    .updateStatus('expiryDate', 'NOT_VALIDATED',null)  
	 	                    .validateField('expiryDate'); 
	    		         },
	    		         okfun:function(){
	    		         	 $("#organForm").data('formValidation')  
	  	                    .updateStatus('expiryDate', 'NOT_VALIDATED',null)  
	  	                    .validateField('expiryDate'); 
	    		         }
	    		};
	    		jeDate(start);
	    	});
			$("#filingTime").bind("click", function () {
	    		//时间控件
	    		var start = {
	    				dateCell: '#filingTime',
	    				format: 'YYYY-MM-DD',
	    				festival:false,
	    				isTime: false,
	    				choosefun: function(datas){
	    				 	$("#organForm").data('formValidation')  
	    	                    .updateStatus('filingTime', 'NOT_VALIDATED',null)  
	    	                    .validateField('filingTime'); 
	    					  
	    				},
	    		         clearfun:function(){
	    		         	 $("#organForm").data('formValidation')  
	 	                    .updateStatus('filingTime', 'NOT_VALIDATED',null)  
	 	                    .validateField('filingTime'); 
	    		         },
	    		         okfun:function(){
	    		         	 $("#organForm").data('formValidation')  
	  	                    .updateStatus('filingTime', 'NOT_VALIDATED',null)  
	  	                    .validateField('filingTime'); 
	    		         }
	    		};
	    		jeDate(start);
	    	});
			$("#docReleaseDate").bind("click", function () {
	    		//时间控件
	    		var start = {
	    				dateCell: '#docReleaseDate',
	    				format: 'YYYY-MM-DD',
	    				festival:false,
	    				isTime: false,
	    				choosefun: function(datas){
	    				 	$("#organForm").data('formValidation')  
	    	                    .updateStatus('docReleaseDate', 'NOT_VALIDATED',null)  
	    	                    .validateField('docReleaseDate'); 
	    					  
	    				},
	    		         clearfun:function(){
	    		         	 $("#organForm").data('formValidation')  
	 	                    .updateStatus('docReleaseDate', 'NOT_VALIDATED',null)  
	 	                    .validateField('docReleaseDate'); 
	    		         },
	    		         okfun:function(){
	    		         	 $("#organForm").data('formValidation')  
	  	                    .updateStatus('docReleaseDate', 'NOT_VALIDATED',null)  
	  	                    .validateField('docReleaseDate'); 
	    		         }
	    		};
	    		jeDate(start);
	    	});
			
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
		            planType: {
		                validators: {
		                    notEmpty: {
		                        message: '请选择预案类型！'
		                    }
		                }
		            },
		           planTemplateId: {
		                validators: {
		                    notEmpty: {
		                        message: '请选择预案模板！'
		                    }
		                }
		            },
		            planNum: {
		                validators: {
		                    notEmpty: {
		                        message: '预案编号不能为空！'
		                    },
							stringLength: {
								min: 2,
								max: 10,
								message: '预案编号必须是2~10个字符组成！'
							}
		                }
		            },
		            versionNumber: {
		                validators: {
		                    notEmpty: {
		                        message: '预案版本号不能为空！'
		                    },
							stringLength: {
								min: 2,
								max: 10,
								message: '预案版本号必须是2~10个字符组成！'
							}
		                }
		            },
					planName: {
		                validators: {
		                    notEmpty: {
		                        message: '预案名称不能为空！'
		                    },
		                    stringLength: {
								min: 2,
								max: 25,
								message: '预案名称必须是2~25个字符组成！'
							}
		                }
		            },
		            planLevel: {
		                validators: {
		                    notEmpty: {
		                        message: '请选择预案级别！'
		                    }
		                }
		            },
		            departId: {
		                validators: {
		                    notEmpty: {
		                        message: '请选择所属企业！'
		                    }
		                }
		            },
		            expiryDate: {
		                validators: {
		                    notEmpty: {
		                        message: '有效期不能为空！'
		                    },
		                    date: {
		                    	message: '时间格式不正确！',
		                        format: 'YYYY-MM-DD'
		                    }
		                }
		            },
					filingTime: {
		                validators: {
		                    notEmpty: {
		                        message: '备案时间不能为空！'
		                    },
		                    date: {
		                    	message: '时间格式不正确！',
		                        format: 'YYYY-MM-DD'
		                    }
		                }
		            },
		            docReleaseDate: {
		                validators: {
		                    notEmpty: {
		                        message: '发布时间不能为空！'
		                    },
		                    date: {
		                    	message: '时间格式不正确！',
		                        format: 'YYYY-MM-DD'
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
			//loadData();
			
			var planId = "${plan.id}";
		    initFileList(planId);
		    initOrganList(planId);
		    initStandList(planId);
		    initPlotList(planId);
		    initEventType();
		});
		
		/*加载组织机构列表*/
        function initOrganList(planId){
			peopleListGrid = $('#peopleListTable');
			if(planId==null||planId=='') {
				return;
			}
			var param={};
			$.ajax({
				type:'POST',
				cache:false,
				url:"${pageContext.request.contextPath}/support/plan.do?getOrganByPlanId&planId="+planId,
				data:{},
				dataType:'json',
				success:function(r){
					if(r.success){
					    zPeopleNodes = r.obj.data;
					    var ztree = $.fn.zTree.init($("#treePeople"), peopleSetting, zPeopleNodes);
						if(zPeopleNodes.length > 1){
							 var peopleNodes = ztree.getNodes();
							 selectPeopleNode = peopleNodes[0].children[0];
							 ztree.setting.callback.onClick(null, ztree.setting.treeId, selectPeopleNode);//调用事件
							 ztree.selectNode(selectPeopleNode);//选择点 
						}
					}
				},
				error:function(){
					
				}
			});
        }
        /*加载分级响应标准列表*/
        function initStandList(planId){
			standListGrid = $('#standListTable');
			if(planId==null||planId=='') {
				return;
			}
			var param={};
			$.ajax({
				type:'POST',
				cache:false,
				url:"${pageContext.request.contextPath}/support/plan.do?getStandByPlanId&planId="+planId,
				data:{},
				dataType:'json',
				success:function(r){
					if(r.success){
					    zStandNodes = r.obj.data;
					    var ztree = $.fn.zTree.init($("#treeStand"), standSetting, zStandNodes);
						if(zStandNodes.length > 1){
							 var standNodes = ztree.getNodes();
							 selectStandNode = standNodes[0].children[0];
						     ztree.selectNode(selectStandNode);//选择点 
						     ztree.setting.callback.onClick(null, ztree.setting.treeId, selectStandNode);//调用事件
							 
						}					
					}
				},
				error:function(){
					
				}
			});
        }
     
        /*加载疏散方案图列表*/
        function initPlotList(planId){
			plotListGrid = $('#plotListTable');
			if(planId==null||planId=='') {
				return;
			}
			var param={};
			$.ajax({
				type:'POST',
				cache:false,
				url:"${pageContext.request.contextPath}/support/plan.do?getPlotByPlanId&planId="+planId,
				data:{},
				dataType:'json',
				success:function(r){
					if(r.success){
					    zPlotNodes = initGraphic(r.obj);
					    var ztree = $.fn.zTree.init($("#treePlot"), plotSetting, zPlotNodes);
						if(zPlotNodes.length > 1){
							 var plotNodes = ztree.getNodes();
							 selectPlotNode = plotNodes[0].children[0];
						     ztree.selectNode(selectPlotNode);//选择点 
						     ztree.setting.callback.onClick(null, ztree.setting.treeId, selectPlotNode);//调用事件
							 
						}
					}
				},
				error:function(){
					
				}
			});
        }
        
        function initGraphic(zPlotNodes){
        	parent.parent.support.plot_plan.planGraphics=[];
            var index=0;
            for(var i=0;i<zPlotNodes.length;i++){
                 var ary =zPlotNodes[i].data
                 for(var j=0;j<ary.length;j++){
                 	ary[j].geoIndex = index;
                 	var obj = {id:'',planId:'',geojson:ary[j].plotGeo};
                 	parent.parent.support.plot_plan.planGraphics.push(obj);
                 	index++;
                 }
            }
            return zPlotNodes;
        }
		/*加载附件列表*/
		function initFileList(planId) {
			fileListGrid = $('#fileListTable');
			if(planId==null||planId=='') {
				return;
			}
			var param={};
			$.ajax({
				type:'POST',
				cache:false,
				url:"${pageContext.request.contextPath}/support/plan.do?getAffixByPlanId&planId="+planId,
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
		
		function loadData() {
            fileListGrid = $('#fileListTable');
			$.fn.zTree.init($("#treeStand"), standSetting, zStandNodes);
			standListGrid = $('#standListTable');
			
			$.fn.zTree.init($("#treePlot"), plotSetting, zPlotNodes);
			plotListGrid = $('#plotListTable');

        }; 
		
		function initEventType(){
			var eventType = "${plan.eventType}";
			var arry = eventType.split(",");
			var display = "";
			for (var i=0;i<arry.length;i++){
			    for(var j=0;j<zNodesTeam.length;j++){
			        if(arry[i] == zNodesTeam[j].id){
			        	display += zNodesTeam[j].name + ",";
			        	zNodesTeam[j].checked = true;
			        }
			    }
			}
			if (display.length > 0 ) display = display.substring(0, display.length-1);
			var eventObjDis = $("#eventTypeDisplay");
			eventObjDis.attr("value", display);
			$.fn.zTree.init($("#event_treeDemo"), eventTypeSetting, zNodesTeam);
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
			var zPeopleTree = $.fn.zTree.getZTreeObj("treePeople");
			var peopleNodes = zPeopleTree.getNodes();
			var zStandTree = $.fn.zTree.getZTreeObj("treeStand");
			var standNodes = zStandTree.getNodes();
			var zPlotTree = $.fn.zTree.getZTreeObj("treePlot");
			var plotNodes = zPlotTree.getNodes();
			parent.editData("jDialogFrame","organForm","${pageContext.request.contextPath}/support/plan.do?update",$.toJSON(fileRows),$.toJSON(delRows),$.toJSON(peopleNodes[0].children),$.toJSON(delPeoples),$.toJSON(standNodes[0].children),$.toJSON(plotNodes[0].children),$.toJSON(delStand),$.toJSON(delPlot));
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
			fileUploadDialog = jDialog.iframe("${pageContext.request.contextPath}/pages/support/plan/fileUpload.jsp",{	                
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
		
		//关联事件类型目录树--开始
		function ztree_eventTypeClick(){
       	       	 //显示目录树
	         //$("#event_menuContent").slideDown("fast");
	       	if(/msie/.test(navigator.userAgent.toLowerCase())){//ie
	       	     $("#event_menuContent").css({"margin-left":"155px", "margin-top":"-215px"}).slideDown("fast");           		
	       	}
	       	else if(/firefox/.test(navigator.userAgent.toLowerCase())){//火狐
	          	 $("#event_menuContent").css({"margin-left":"155px", "margin-top":"-250px"}).slideDown("fast");           		
	       	}
	       	else if(/webkit/.test(navigator.userAgent.toLowerCase())){//谷歌
	          	 $("#event_menuContent").css({"margin-left":"155px", "margin-top":"-250px"}).slideDown("fast");          		
	       	}  
	       	else if(/opera/.test(navigator.userAgent.toLowerCase())){//opera
	          	 $("#event_menuContent").css({"margin-left":"155px", "margin-top":"-250px"}).slideDown("fast");           		
	       	}  
	       	else{//其他浏览器
	       	     $("#event_menuContent").css({"margin-left":"155px", "margin-top":"-215px"}).slideDown("fast");        		
	       	} 
			 $("#organDialog").bind("mousedown", onBodyDown);
		};
		
		function onCheck(e, treeId, treeNode) {
 			var zTree = $.fn.zTree.getZTreeObj("event_treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "",
			dis = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].id + ",";
				dis += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (dis.length > 0 ) dis = dis.substring(0, dis.length-1);
			var eventObj = $("#eventType");
			var eventObjDis = $("#eventTypeDisplay");
			eventObj.attr("value", v);
			eventObjDis.attr("value", dis);
       	 }
        function onBodyDown(event){
			 if (!(event.target.id == "eventBtn" || event.target.id == "event_menuContent" || $(event.target).parents("#event_menuContent").length>0)) {
				 hideMenu();
			 }         	 
        };
        function hideMenu(){
			 $("#event_menuContent").fadeOut("fast");
			 $("#j-dialog-body").unbind("mousedown", onBodyDown);          	 
        };
		//关联事件类型目录树--结束	
	</script>
</head>
<body style="overflow:hidden;">

    <div id="organDialog"> 
 	   	<div class="container">
 	   		<ul class="nav nav-tabs" role="tablist" id="myTab">
				<li class="active"><a href="#promation" role="tab" data-toggle="tab">基本信息 <i class="glyphicon"></i></a></li>
				<li><a href="#organization" role="tab" data-toggle="tab">组织机构 <i class="glyphicon"></i></a></li>
				<li><a href="#request" role="tab" data-toggle="tab">分级响应要求 <i class="glyphicon"></i></a></li>
				<li><a href="#project" role="tab" data-toggle="tab">疏散方案图 <i class="glyphicon"></i></a></li>
				<li><a href="#Ftxt" role="tab" data-toggle="tab">附件 <i class="glyphicon"></i></a></li>
			</ul>
 	   		<form id="organForm" method="post" class="form-horizontal" action="target.php">
				<input type="hidden" name="id" value="${plan.id}"/>	
				<div class="tab-content">
					<div class="tab-pane active" id="promation">	
						<div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">预案类型<span>*</span></label>
							<div class="col-xs-4">
								<dict:select defaultValue=""  name="planType" id="planType" value="${plan.planType}" dictName="PLAN_TYPE" cssClass="form-control"/>
							</div>
							<label class="col-xs-2 control-label">预案名称<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" value="${plan.planName}" name="planName" id="planName" />
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">预案编号<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control"   value="${plan.planNum}" name="planNum" id="planNum" />
							</div>
							<label class="col-xs-2 control-label">预案版本号<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" value="${plan.versionNumber}" name="versionNumber" id="versionNumber"  />
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">发布版本号<span>*</span></label>
							<div class="col-xs-4">
								<input type="text" class="form-control" value="${plan.docReleaseNumber}"  name="docReleaseNumber" id="docReleaseNumber" />
							</div>
							<label class="col-xs-2 control-label">预案级别<span>*</span></label>
							<div class="col-xs-4">
								<dict:select defaultValue=""  name="planLevel" id="planLevel" value="${plan.planLevel}"  dictName="PLAN_LEVEL" cssClass="form-control"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">行业类别<span>*</span></label>
							<div class="col-xs-4">
								<dict:select defaultValue="" value="${plan.industry}" name="industry" id="industry" dictName="INDUSTRY" cssClass="form-control"/>
							</div>
							<label class="col-xs-2 control-label">所属企业<span>*</span></label>
							<div class="col-xs-4">
								<relation:select defaultValue="" value="${plan.departId}" saveField="departId" disField="departName" tableName="SysDepart"  name="departId" id="departId"  cssClass="form-control"/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">有效期<span>*</span></label>
							<div class="col-xs-4">
									<input type="text" class="form-control" value="<fmt:formatDate value='${plan.expiryDate}' pattern='yyyy-MM-dd' />" name="expiryDate" id="expiryDate"  style="cursor:pointer; "/>
							</div>
							<label class="col-xs-2 control-label">备案时间<span>*</span></label>
							<div class="col-xs-4">
									<input type="text" class="form-control" value="<fmt:formatDate value='${plan.filingTime}' pattern='yyyy-MM-dd' />" name="filingTime" id="filingTime"  style="cursor:pointer; "/>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">发布时间<span>*</span></label>
							<div class="col-xs-4">
									<input type="text" class="form-control" value="<fmt:formatDate value='${plan.docReleaseDate}' pattern='yyyy-MM-dd' />" name="docReleaseDate" id="docReleaseDate" style="cursor:pointer; "/>
							</div>
							<label class="col-xs-2 control-label">关键字</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" name="keyword" id="keyword" value="${plan.keyword}" />
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">关联事件类型</label>
							<div class="col-xs-9" style="padding-right:0px;">
								<input  type="text" class="form-control" name="eventTypeDisplay" id="eventTypeDisplay"  readonly="readonly"/>
								<input type="hidden" value="${plan.eventType}" name="eventType" id="eventType"  /></label>
							</div>
							<div class="col-xs-1" style="padding-left:0px;">
							    <button type="button"  data-toggle="modal" data-target="#mymodal-data" class="btn btn-default" style="margin-left: 0px;" value="选择" id="eventBtn" onclick="ztree_eventTypeClick()">选择</button>
							</div>
						</div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">适用范围</label>
							<div class="col-xs-10">
								<textarea type="text" class="form-control" name="scope" id="scope" rows="3" data-fv-stringlength data-fv-stringlength-max="500">${plan.scope}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">发放范围</label>
							<div class="col-xs-10">
								<textarea type="text" class="form-control" name="range" id="range" rows="3" data-fv-stringlength data-fv-stringlength-max="500">${plan.range}</textarea>
							</div>
					    </div>
					    <div class="form-group" style="margin-bottom: 5px;">
							<label class="col-xs-2 control-label">预案描述</label>
							<div class="col-xs-10">
								<textarea type="text" class="form-control" name="remark" id="remark" rows="3" data-fv-stringlength data-fv-stringlength-max="500">${plan.remark}</textarea>
							</div>
					    </div>												
					</div>
				
					<div class="tab-pane" id="organization">
						<div class="form-group" style="margin:0 10px;">
							<div class="col-xs-4" style="border:1px solid #e4e4e4; border-right:none;padding-left:0;padding-right:0; height:500px;">
								<h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">机构树</h3>
								<div class="OrgnSide">
									<div id="menuContent" class="menuContent" style="float:left;" >
								        <ul id="treePeople" class="ztree">
								        </ul>
								    </div>
								</div>
							</div>
							<div class="col-xs-8" style="border:1px solid #e4e4e4;height:500px; padding-left:0;padding-right:0;">
								     <h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">人员列表</h3>
								     <div id="peopleToolbar" style="padding:5px 0px 0px 0px;"> 
									    <button id="add" type="button"  class="btn btn-danger" onclick="showAddPeopleView()">
								            <i class="glyphicon glyphicon-plus"></i> 新增
								        </button>
       								  </div>
					                 <table id="peopleListTable"
						               data-toggle="table"
						               data-height="400"
						               data-toolbar="peopleToolbar"
						               data-toolbarAlign="left"
						               >
				            		<thead>
				           			 <tr>
				               			 <th data-field="id" data-visible="false">ID</th>
				               			 <th data-field="name">姓名</th>
				               			 <th data-field="sex" data-formatter="genterPeopleSex">性别</th>
				               			 <th data-field="phone">联系电话</th>
				               			 <th data-field="duty">职责</th>
				               			 <th data-field="operation" data-formatter="genterPeopleOperate">操作</th>
				            		</tr>
				           			 </thead>
				        			</table>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="request">
						<div class="form-group" style="margin:0 10px;">
							<div class="col-xs-4" style="border:1px solid #e4e4e4; border-right:none;padding-left:0;padding-right:0; height:500px;">
								<h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">事件类型</h3>
								<div class="OrgnSide">
									<div id="menuContent" class="menuContent" style="float:left;" >
								        <ul id="treeStand" class="ztree">
								        </ul>
								    </div>
								</div>
							</div>
							<div class="col-xs-8" style="border:1px solid #e4e4e4;height:500px; padding-left:0;padding-right:0;">
								<h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">响应要求</h3>
									<div id="standToolbar" style="padding:5px 0px 0px 0px;"> 
									    <button id="add" type="button"  class="btn btn-danger" onclick="showAddStandView()">
								            <i class="glyphicon glyphicon-plus"></i> 新增
								        </button>
       								  </div>
					                 <table id="standListTable"
						               data-toggle="table"
						               data-height="400"
						               data-toolbar="standToolbar"
						               data-toolbarAlign="left"
						               >
				            		<thead>
				           			 <tr>
				               			 <th data-field="id" data-visible="false">ID</th>
				               			 <th data-field="planType" data-visible="false">事件类型</th>
				               			 <th data-field="planTypeDis" data-visible="false">事件类型</th>
				               			 <th data-field="suppliesType" data-visible="false">物资种类</th>
				               			 <th data-field="suppliesTypeDis">物资种类</th>
				               			 <th data-field="oneLevelStand">I级</th>
				               			 <th data-field="twoLevelStand">II级</th>
				               			 <th data-field="threeLevelStand">II级</th>
				               			 <th data-field="operation" data-formatter="genterStandOperate">操作</th>
				            		</tr>
				           			 </thead>
				        			</table>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="project">
						<div class="form-group" style="margin:0 10px;">
							<div class="col-xs-4" style="border:1px solid #e4e4e4; border-right:none;padding-left:0;padding-right:0; height:500px;">
								<h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">方案列表</h3>
								<div class="OrgnSide">
									<div id="menuContent" class="menuContent" style="float:left;" >
								        <ul id="treePlot" class="ztree">
								        </ul>
								    </div>
								</div>
							</div>
							<div class="col-xs-8" style="border:1px solid #e4e4e4;height:500px; padding-left:0;padding-right:0;">
								<h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">方案详情</h3>
								    <div id="plotToolbar" style="padding:5px 0px 0px 0px;"> 
									    <button id="add" type="button"  class="btn btn-danger" onclick="showAddPlotView()">
								            <i class="glyphicon glyphicon-plus"></i> 新增
								        </button>
       								  </div>
					                 <table id="plotListTable"
						               data-toggle="table"
						               data-height="400"
						               data-toolbar="plotToolbar"
						               data-toolbarAlign="left"
						               >
				            		<thead>
				           			 <tr>
				               			 <th data-field="id" data-visible="false">ID</th>
				               			 <th data-field="plotName">方案名称</th>
				             			 <th data-field="planType" data-visible="false">事故类型</th>
				               			 <th data-field="planTypeDis">事件类型</th>
				               			 <th data-field="geoIndex"  data-visible="false">方案索引</th>
				               			 <th data-field="plotGeo"  data-visible="false">方案内容</th>
				               			 <th data-field="bz">备注</th>
				               			 <th data-field="operation" data-formatter="genterPlotOperate">操作</th>
				            		</tr>
				           			 </thead>
				        			</table>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="Ftxt">
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
 	    <!-- 关联事件类型目录树div-->
		<div id="event_menuContent"  style="display:none;position:absolute;">
			 <ul id="event_treeDemo" class="ztree" style="margin-top:0; width:600px;border:1px solid #CCCCCC;max-height:300px;overflow-x:auto;background:#F3F3F3"></ul>
		</div>
  </div> 
</body>
</html>
