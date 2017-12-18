<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%@taglib prefix="relation" uri="http://www.bxsurvey.com/relation" %>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/support/support_add.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.css"/>
	     
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/language/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/js/plugins/ztree/zTreeStyle/img/zTreeStyle.css" rel="stylesheet" type="text/css" />
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
    <link rel='stylesheet' href="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog/jDialog.css" />
	<script src="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-form.js"></script>
	<style type="text/css">
		.ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
	    }
	    .pos{width:100%; height:100%;position:absolute; top:380px;left:0px;}
	</style>
	<script type="text/javascript">
	
		var selectDataNode = null;
		var dataListGrid;
		var objDialog;
		
		var html =" <div id='dicContent' name='dicContent'>"
			+"<input type='hidden' name='id' id='id'/>"
		    +"<div class='form-group' style='margin:0 10px;'>"
		    +"<label class='col-xs-4 control-label' style='margin-top:8px;'>参数名称</label>"
		    +"<div class='col-xs-8'>"
		    +"<input type='text' class='form-control'  name='paramsName' id='paramsName' />"
		    +"</div>"
		    +"<label class='col-xs-4 control-label' style='margin-top:20px;'>参数类型</label>"
		    +"<div class='col-xs-8' style='margin-top:15px;'>"
		    +"<input type='text' class='form-control'  name='paramsType' id='paramsType' />"
		    +"</div>"
		    +"</div>"
		    +"</div>";
		
		var dataSetting = {
		    data: {
		        simpleData: {
		            enable: true,
		            pIdKey : 'pid'
		        }
		    },
		    callback: {
		        onClick: onDataTreeClick,  //每次点击节点触发事件
		        beforeEditName: beforeDataEditName,//修改节点之前触发事件,返回false取消默认编辑行为
		        beforeRemove: beforeDataRemove //删除节点之前触发事件,返回false取消默认删除行为
		        
		    },
		    view: {
		        dblClickExpand: false,   //取消默认双击展开父节点的功能
		        txtSelectedEnable: false, //是否允许选中节点的文字		    
		        expandSpeed: "",   //设置为 "" 时，不显示动画效果
	 	        addHoverDom: addDataHoverDom,   //设置鼠标移到节点上，在后面显示一个按钮
		        removeHoverDom: removeDataHoverDom,   //用于当鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
		        selectedMulti: false,  //设置是否允许同时选中多个节点
		        showTitle: false //鼠标放在节点上不显示提示,如果 setting.view.showTitle = true & setting.data.key.title = ''，zTree 会自动使用 setting.data.key.name 指定的节点名称当做 title
		        
		    },
		    edit: {
		    	enable: true,
		    	showRemoveBtn : setRemoveBtn, //设置是否显示删除按钮
		    	showRenameBtn : setRenameBtn,  //设置是否显示编辑名称按钮
		    	removeTitle : "删除",
				renameTitle : "修改",
		    }
		};
		
		function setRemoveBtn(treeId, treeNode) {
			if(treeNode.pid == null)
			    return false;
			return true;
		}
		
		function setRenameBtn(treeId, treeNode) {
			if(treeNode.pid == null)
			    return false;
			return true;
		}
		
		

		function addDataHoverDom(treeId, treeNode) {
		    var sObj = $("#" + treeNode.tId + "_span");
		    //editNameFlag=true表示节点处于编辑名称状态,$("#addBtn_" + treeNode.tId).length > 0表示鼠标在节点上不停移动时,永远只有一个增加按钮
		    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
		    if(treeNode.pid != null) return;
		    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		        + "' title='添加新节点' onfocus='this.blur();' ></span>";
		    sObj.after(addStr);
		    var btn = $("#addBtn_" + treeNode.tId);
		    if (btn) btn.bind("click", function () {
		    	
		    	 dailog=jDialog.dialog({
		       	 title: '添加数据字典',
		       	 width: 300,
		       	 height:160,                    
		       	 modal: true, // 非模态，即不显示遮罩层
		       	 top:130,
		       	 left:50,                    
		       	 content:html,
		       	 buttons:[{
		                       type : 'normal',    // normal 或者 highlight
		                       text : '确定',      // 按钮的显示文本
		                       handler : function(button,dialog){ // 按钮点击事件
		                       	   var paramsName = $("#paramsName").val();
		                       	   var paramsType = $("#paramsType").val();
		                    	   if(paramsName == ""||paramsType == ""){
		                    		   alert("不能添加空值！");
		                    		   return;
		                    	   };
		                    	   $.ajax({
										type:'POST',
										url:"${pageContext.request.contextPath}/sysParams.do?saveParamsType",
										data:"name="+paramsName+"&paramsType="+paramsType,
										dataType:'json',
										success:function(r){
											if(r.success){
												dialog.close();
											    jDialog.message('数据添加成功',{
														autoClose : 1000,    // 3s后自动关闭
														padding : '30px',    // 设置内部padding
														modal: false         // 非模态，即不显示遮罩层
												});
												window.location.reload();
											}else{
												dialog.close();
												jDialog.message('数据添加失败',{
														autoClose : 1000,    // 3s后自动关闭
														padding : '30px',    // 设置内部padding
														modal: false         // 非模态，即不显示遮罩层
												});
											}
										}
									});
		                    	   
		                       }
		                 },{
		                     type : 'normal',    // normal 或者 highlight
		                     text : '关闭',      // 按钮的显示文本
		                     handler : function(button,dialog){ // 按钮点击事件
		                    	 dialog.close();
		                     }
		                 }]
		        	});
		    });
		};
		function removeDataHoverDom(treeId, treeNode) {
		    $("#addBtn_" + treeNode.tId).unbind().remove();
		};
		//删除
		function beforeDataRemove(treeId, treeNode) {
		    	var dialog = jDialog.confirm("您要删除当前所选项目？",{
					handler : function(button,dialog) {
						dialog.close();
						$.ajax({
							url : "${pageContext.request.contextPath}/sysParams.do?deleteParamsType",
							data : "id="+treeNode.id,
							cache : false,
							dataType : "json",
							success : function(data) {
								if(data.success){
									var dialog = jDialog.message('删除成功！',{
										autoClose : 1000,    // 3s后自动关闭
										padding : '30px',    // 设置内部padding
										modal: false         // 非模态，即不显示遮罩层
							        });
							        window.location.reload();
								}else{
									var dialog = jDialog.message('删除失败！',{
										autoClose : 1000,    // 3s后自动关闭
										padding : '30px',    // 设置内部padding
										modal: false         // 非模态，即不显示遮罩层
							        });
								}
							}
						});
					}
				},{
					handler : function(button,dialog) {
						dialog.close();
					}
				});
				return false;
		}
		//修改,return false表示取消默认修改行为
		function beforeDataEditName(treeId, treeNode) {
			dailog=jDialog.dialog({
		       	 title: '修改数据字典',
		       	 width: 300,
		       	 height:160,                    
		       	 modal: true, // 非模态，即不显示遮罩层
		       	 top:130,
		       	 left:50,                    
		       	 content:html,
		       	 buttons:[{
		                       type : 'normal',    // normal 或者 highlight
		                       text : '确定',      // 按钮的显示文本
		                       handler : function(button,dialog){ // 按钮点击事件
		                       		var paramsName = $("#paramsName").val();
			                       	var paramsType = $("#paramsType").val();
			                       	var id = treeNode.id;
			                    	if(paramsName == ""||paramsType == ""){
			                    		alert("不能添加空值！");
			                    		return;
			                    	};
		                       		$.ajax({
										type:'POST',
										url:"${pageContext.request.contextPath}/sysParams.do?updateParamsType",
										data:"name="+paramsName+"&paramsType="+paramsType+"&id="+id,
										dataType:'json',
										success:function(r){
											if(r.success){
												dialog.close();
											    jDialog.message('数据添加成功',{
														autoClose : 1000,    // 3s后自动关闭
														padding : '30px',    // 设置内部padding
														modal: false         // 非模态，即不显示遮罩层
												});
												window.location.reload();
											}else{
												dialog.close();
												jDialog.message('数据添加失败',{
														autoClose : 1000,    // 3s后自动关闭
														padding : '30px',    // 设置内部padding
														modal: false         // 非模态，即不显示遮罩层
												});
											}
										}
									});
		                       }
		                     },
		                     {
		                     type : 'normal',    // normal 或者 highlight
		                     text : '关闭',      // 按钮的显示文本
		                     handler : function(button,dialog){ // 按钮点击事件
		                    	 dialog.close();
		                     }
		                 }]
		       });
		   	$("#paramsName").val(treeNode.name);
		   	$("#paramsType").val(treeNode.type);   
		   return false;
	 }
		
		//每次点击节点后发请求获取对应数据
		function onDataTreeClick(e, treeId, treeNode) {
			$.ajax({
				type:'POST',
				url:"${pageContext.request.contextPath}/sysParams.do?getParamsList",
				data:"id="+treeNode.id,
				dataType:'json',
				success:function(r){
					if(r.success){
					    var listObj = r.obj;
					    dataListGrid.bootstrapTable('load',listObj);
					}
				}
			});
			selectDataNode = treeNode;
		}
		//添加数据发请求重新获取数据
		function afterAdd(id) {
			$.ajax({
				type:'POST',
				url:"${pageContext.request.contextPath}/sysParams.do?getParamsList",
				data:"id="+id,
				dataType:'json',
				success:function(r){
					if(r.success){
					    var listObj = r.obj;
					    dataListGrid.bootstrapTable('load',listObj);
					}
				}
			});
		}	
	
		$(document).ready(function() {
		    initParamsList();
		});
		
		/*加载组织机构列表*/
        function initParamsList(){
			dataListGrid = $('#dataListTable');
			var param={};
			$.ajax({
				type:'POST',
				url:"${pageContext.request.contextPath}/sysParams.do?getParamsType",
				dataType:'json',
				success:function(r){
					if(r.success){
					    zDataNodes = r.obj.data;
					    var ztree = $.fn.zTree.init($("#treeDictionary"), dataSetting, zDataNodes);
					    var dataNodes = ztree.getNodes();
						if(dataNodes.length > 0){
							 selectDataNode = dataNodes[0].children[0]; //获取数据字典根下的第一个节点
							 ztree.selectNode(selectDataNode);//选择第一条
						     ztree.setting.callback.onClick(null, ztree.setting.treeId, selectDataNode);//调用事件
						}
					}
				},
				error:function(){
					
				}
			});
        }
        
        //跳转到增加数据页面
        function add(){
			if(selectDataNode == null){
				return;
			}
			if(selectDataNode.id == 0){
				return;
			}
			objDialog = jDialog.iframe("${pageContext.request.contextPath}/sysParams.do?dataAdd&paramsType="+selectDataNode.type+"&pid="+selectDataNode.id,{	                
			    title: '新增数据',
		        width: 500,
		        height:310,
		        modal: true, // 非模态，即不显示遮罩层
		        iframeId:"jDialogFrame"///对话框iframe的ID
		    })
		}
		
		//关闭对话框
		function closeDialog(){
            objDialog.close();
        }
        
        
        function addData(frmName,formName,AjaxUrl) {
		    var objForm = $("#"+frmName).contents().find("#"+formName);
		    var arry = objForm.serializeArray();
		    var options = {
                url: AjaxUrl,
                type: "POST",
                data: arry,
                success: function (data) {
                   	try {
                   	    var d = $.parseJSON(data);
						if (d.success) {
							objDialog.close();
							var dialog = jDialog.message('数据添加成功',{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
							//重新获取新数据
							afterAdd(selectDataNode.id);
						}
					} catch (e) {
							var dialog = jDialog.message('数据添加失败',{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
					} 
                }
            };
            $.ajax(options);           
		}
		
		function editData(frmName,formName,AjaxUrl) {
		     var objForm = $("#"+frmName).contents().find("#"+formName);
			 var options = {
                url: AjaxUrl,
                type: 'post',
                dataType: 'json',
                data: objForm.serialize(),
                success: function (data) {
                   	try {
                   	    var d = data;
						if (d.success) {
							objDialog.close();
			 				var dialog = jDialog.message('数据修改成功',{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							}); 
							//重新获取新数据
							afterAdd(selectDataNode.id);
						}
					} catch (e) {
							var dialog = jDialog.message('数据修改失败',{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
					} 
                }
            };
            $.ajax(options); 
		}
		
		
		function modify()
		{
		    var idName = "paramsId";
		    var selRows = dataListGrid.bootstrapTable('getAllSelections');
		    if(selRows.length == 0) {
		 		jDialog.alert('请选择需要编辑的数据！', {});
		 		
			}
			if (selRows.length != 1 && selRows.length != 0) {
				var names = [];
				for ( var i = 0; i < selRows.length; i++) {
					names.push(selRows[i][idName]);
				}
				jDialog.alert('只能择一个数据进行编辑！', {});
			} else if (selRows.length == 1) {
				//弹出编辑详情框
				objDialog = jDialog.iframe("${pageContext.request.contextPath}/sysParams.do?updateView&id="+selRows[0][idName],{	                
				    title: '修改数据',
			        width: 500,
			        height:310,
			        modal: true, // 非模态，即不显示遮罩层
			        iframeId:"jDialogFrame"///对话框iframe的ID
			    });
							
			}
		}
		
		function del(){
		    var idName="paramsId";
			var ids = [];
			var selRows = dataListGrid.bootstrapTable('getAllSelections');
		    if(selRows.length > 0) {
		    	var dialog = jDialog.confirm("您要删除当前所选项目？",{
					handler : function(button,dialog) {
						dialog.close();
						for ( var i = 0; i < selRows.length; i++) {
							ids.push(selRows[i][idName]);
						}
						$.ajax({
							url : "${pageContext.request.contextPath}/sysParams.do?delete",
							data : {ids : ids.join(',')},
							cache : false,
							dataType : "json",
							success : function(data) {
								if(data.success){
									dataListGrid.bootstrapTable("uncheckAll");
									var dialog = jDialog.message('删除成功！',{
										autoClose : 1000,    // 3s后自动关闭
										padding : '30px',    // 设置内部padding
										modal: false         // 非模态，即不显示遮罩层
							        });
							        afterAdd(selectDataNode.id);
								}else{
									var dialog = jDialog.message('删除失败！',{
										autoClose : 1000,    // 3s后自动关闭
										padding : '30px',    // 设置内部padding
										modal: false         // 非模态，即不显示遮罩层
							        });
								}
							}
						});
					}
				},{
					handler : function(button,dialog) {
						dialog.close();
					}
				});
			}else {
				jDialog.alert('请选择要删除的记录！', {});
			}
		}
	
	</script>
</head>
<body style="overflow:hidden;">

		<div class="top_001">
        <img src="${pageContext.request.contextPath}/content/images/map/index/home.png" class="icon_img_01" />
        <span style="height: 35px; width: 350px;">当前位置：后台管理&nbsp;>&nbsp;<label>数据字典</label></span>
    	</div>
    	
		<div style=" width:100%; height:1px; border-bottom:1px solid #cdd2d2; margin-bottom:5px;"></div>
			<div class="form-group" style="margin:0 10px;">
				<div class="col-xs-2" style="border:1px solid #e4e4e4; border-right:none;padding-left:0;padding-right:0; height:90%;margin-bottom:20px; ">
					<h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">数据树</h3>
					<div class="OrgnSide">
						<div id="menuContent" class="menuContent" style="float:left;" >
					        <ul id="treeDictionary" class="ztree">
					        </ul>
					    </div>
					</div>
				</div>
				<div class="col-xs-10" style="border:1px solid #e4e4e4; padding-left:0;padding-right:0; height:90%;margin-bottom:20px; ">
					     <h3 style=" height:35px;line-height:35px; width:100%; text-align:center; border-bottom:1px solid #e4e4e4; margin-top:0;background-color: #f2f2f2">数据列表</h3>
					     <div id="toolbar" style="padding:0px 0px 5px 5px;">
						    <button id="add" class="btn btn-danger" onclick="add()">
					            <i class="glyphicon glyphicon-plus"></i> 新增
					        </button>
					        <button id="modify" class="btn btn-danger"  onclick="modify()">
					            <i class="glyphicon glyphicon-edit"></i> 修改
					        </button>
					        <button id="modify" class="btn btn-danger"  onclick="del()">
					            <i class="glyphicon glyphicon-edit"></i> 删除
					        </button>
					    </div>
		                 <table id="dataListTable"
			               data-toggle="table"
			               data-height="400"
			               data-toolbar="peopleToolbar"
			               data-toolbarAlign="left"
			               >
	            		<thead>
		           			 <tr>
		           			 	 <th data-field="select" data-visible="true" data-checkbox="true">ID</th>
		               			 <th data-field="paramsId" data-visible="false">ID</th>
		               			 <th data-field="paramsName" data-align="center">参数名称</th>
		               			 <th data-field="paramsType" data-align="center">参数类型</th>
		               			 <th data-field="paramsIndex" data-align="center">排序</th>
		               			 <th data-field="paramsValue" data-align="center">参数值</th>
		               			 <th data-field="paramsBz" data-align="center">备注</th>
		            		 </tr>
	           			 </thead>
	        			</table>
				</div>
			</div>

</body>
</html>
