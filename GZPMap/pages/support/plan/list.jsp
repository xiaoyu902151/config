<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%@taglib prefix="relation" uri="http://www.bxsurvey.com/relation" %>

<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.css"/>
	<link rel='stylesheet' href="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog/jDialog.css" />
	      
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/tableExport/tableExport.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/jqueryDialog/jDialog.js"></script> 
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
 	
 	<style type="text/css">
		.rowColor
		{
			background-color:#f5f5f5;
		}
		.rowColor{background-color:#f5f5f5;}
		/*.fixed-table-container{padding-bottom:0 !important;}*/
		.fixed-table-body{ padding-bottom:0 !important;margin-bottom:0 !important;}
		.columns .btn-default{ height:34px !important;}
	</style>
 
</head>
<body style="overflow:hidden;">
	 <div class="top_001">
        <img src="${pageContext.request.contextPath}/content/images/map/index/home.png" class="icon_img_01" />
        <span style="height: 35px; width: 350px;">当前位置：应急预案&nbsp;>&nbsp;<label>预案库</label></span>
    </div>
	<div style=" width:100%; height:1px; border-bottom:1px solid #cdd2d2; margin-bottom:5px;"></div>
    <div id="queryPanel">
      <div class="form form-inline" >
    	<div class="form-group" style="margin:10px 10px;">
	        <label class="control-label" >预案名称： </label>
	        <input class="form-control" id="planName" type="text" placeholder="预案名称" >
       	</div>
       	<div class="form-group" style="margin:10px 10px;">
	        <label class="control-label" >所属公司： </label>
	        <relation:select defaultValue="" value="" saveField="departId" disField="departName" tableName="SysDepart"  name="departId" id="departId"  cssClass="form-control"/>
       	</div>
       	<div class="form-group" style="margin:10px 10px;">
	        <label class="control-label" >行业类别： </label>
	        <dict:select defaultValue="" value="" name="industry" id="industry" dictName="INDUSTRY" cssClass="form-control"/>
       	</div>
       	<div class="form-group" style="margin:10px 10px;">
	        <label class="control-label" >关键字： </label>
	        <input class="form-control" id="keyword" type="text" placeholder="关键字" >
       	</div>
        <button type="button" id="query" style="margin-right:10px;" class="btn btn-info btn-sm" onclick="query()"><i class="glyphicon glyphicon-search"></i>查询</button>
        <button type="button" id="reset" class="btn btn-default btn-sm" onclick="clearFun()"><i class="glyphicon glyphicon-repeat"></i>重置</button>
        </div>
    </div>
	<div id="toolbar" style="padding:0px 0px 5px 5px;">
	    <button id="add" class="btn btn-danger" onclick="add()">
            <i class="glyphicon glyphicon-plus"></i> 新增
        </button>
		<button id="modify" class="btn btn-danger"  onclick="modify()">
            <i class="glyphicon glyphicon-edit"></i> 修改
        </button>
        <button id="remove" class="btn btn-danger" onclick="del()">
            <i class="glyphicon glyphicon-remove"></i> 删除
        </button>
        <button id="browse" class="btn btn-danger" onclick="browse()">
            <i class="glyphicon glyphicon-list-alt"></i> 查看
        </button>
    </div>
    <table id="table"></table>
<script>
	var datagrid;
	var objDialog;
    $(function () {
    		//数据列表
            //根据窗口调整表格高度
            $(window).resize(function() {
                $('#table').bootstrapTable('resetView', {
                    height: tableHeight()
                })
            })
            datagrid = $('#table');
			datagrid = datagrid.bootstrapTable({
                url: "${pageContext.request.contextPath}/support/plan.do?searchGrid",//数据源
                dataField: "rows",//服务端返回数据键值 就是说记录放的键值是rows，分页时使用总记录数的键值为total
                height: tableHeight(),//高度调整
                search: false,//是否搜索
                pagination: true,//是否分页
                pageSize: 10,//单页记录数
                pageNumber:1, //当前第几页
                pageList: [5, 10, 20, 50],//分页步进值
                sidePagination: "server",//服务端分页
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",//请求数据内容格式 默认是 application/json 自己根据格式自行服务端处理
                dataType: "json",//期待返回数据类型
                method: "post",//请求方式
                searchAlign: "right",//查询框对齐方式
                queryParamsType: "undefined",//查询参数组织方式
                sortName:"planNum",
                sortOrder:"asc",
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
                //设置为limit可以获取limit, offset, search, sort, order 
                queryParams: function getParams(params) {
	                var param = {    
	                  pageNumber: params.pageNumber,    
	                  rowNumber: params.pageSize,
	                  sortName:params.sortName, 
	                  sortOrder:params.sortOrder,
	                  planName:$("#planName").val(),
	                  industry:$("#industry").val(),
	                  departId:$("#departId").val(),
	                  keyword:$("#keyword").val()  
	                };
                    return param;
                },
                searchOnEnterKey: false,//回车搜索
                showRefresh: true,//刷新按钮
                showColumns: true,//列选择按钮
				showExport: true,    //是否显示导出
				exportDataType: "basic",//basic', 'all', 'selected'.
                buttonsAlign: "right",//按钮对齐方式
                toolbar: "#toolbar",//指定工具栏
                toolbarAlign: "left",//工具栏对齐方式

                columns: [
                    {
                        title: "全选",
                        field: "select",
                        checkbox: true,
                        width: 30,//宽度
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "预案ID",//标题
                        field: "id",//键名
                        sortable: true,//是否可排序
                        width: 30,//宽度
                        order: "desc",//默认排序方式
                        visible:false
                    },
                    {
                        field: "planNum",
                        title: "预案编号",
                        sortable: true,
                         align: "center",//水平
                        valign: "middle",//垂直
                        width: 30,//宽度
                        titleTooltip: "预案编号",
						visible:false
                    },
                    {
                        field: "planName",
                        title: "预案名称",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 120//宽度
                    },
                    {
                        field: "sysDepart.departName",
                        title: "所属公司",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 200//宽度
                    },
                     {
                        field: "sysParams.paramsName",
                        title: "预案类型",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 50//宽度
                    },
                    {
                        field: "keyword",
                        title: "关键字",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 100//宽度
                    },
                     {
                        field: "docReleaseNumber",
                        title: "发布版本号",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 30//宽度
                    },
                    {
                        field: "expiryDate",
                        title: "有效期",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 70,//宽度
                        formatter: function(value,row,index){
					      return new Date(value).Format("yyyy-MM-dd") ;
	        	        }
                    },
                    {
                        field: "scope",
                        title: "适用范围",
                        align: "center",//水平
                        valign: "middle",//垂直
                        sortable: true,
                        width: 150//宽度
                    }
		        ],
                onClickRow: function(row, $element) {
                    //$element是当前tr的jquery对象
                    $element.toggleClass("rowColor");
                },  //单击row事件
                locale: "zh-CN"//中文支持,
           });

    });

        function tableHeight() {
            return $(window).height() - 200;
        }
        /**
         * 列的格式化函数 在数据从服务端返回装载前进行处理
         * @param  {[type]} value [description]
         * @param  {[type]} row   [description]
         * @param  {[type]} index [description]
         * @return {[type]}       [description]
         */
       function infoFormatter(value, row, index)
        {
            return "id:" + row.id + " name:" + row.name + " age:" + row.age;
        }
        
        function add()
		{
		        objDialog = jDialog.iframe("${pageContext.request.contextPath}/support/plan.do?addView",{	                
		        		title: '新增预案',
		                width: 800,
		                height:700,
		                modal: true, // 非模态，即不显示遮罩层
		                iframeId:"jDialogFrame"///对话框iframe的ID
	                })
		}
		
		 

		function del()
		{
		    var idName="id";
			var ids = [];
			var selRows = datagrid.bootstrapTable('getAllSelections');
		    if(selRows.length > 0) {
		    	var dialog = jDialog.confirm("您要删除当前所选项目？",{
					handler : function(button,dialog) {
						dialog.close();
						for ( var i = 0; i < selRows.length; i++) {
							ids.push(selRows[i][idName]);
						}
						$.ajax({
							url : "${pageContext.request.contextPath}/support/plan.do?delete",
							data : {ids : ids.join(',')},
							cache : false,
							dataType : "json",
							success : function(data) {
								if(data.success){
									datagrid.bootstrapTable("refresh");
									datagrid.bootstrapTable("uncheckAll");
									var dialog = jDialog.message('删除成功！',{
										autoClose : 1000,    // 3s后自动关闭
										padding : '30px',    // 设置内部padding
										modal: false         // 非模态，即不显示遮罩层
							        });
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
        function query()
		{
			datagrid.bootstrapTable("refresh");
		}   
		function clearFun()
		{
				$('#queryPanel input').val('');
			//	$('#queryPanel select > option:first').attr("selected", true);
				$('#queryPanel select').val('');
		} 
		function modify()
		{
		    var idName = "id";
		    var selRows = datagrid.bootstrapTable('getAllSelections');
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
				objDialog =  jDialog.iframe("${pageContext.request.contextPath}/support/plan.do?updateView&id="+selRows[0][idName],{
					title : '修改预案',
					width : 800,
					height : 700,
					iframeId : "jDialogFrame"///对话框iframe的ID
				});
							
			}
		}
		
		function browse()
		{
		    var idName = "id";
		    var selRows = datagrid.bootstrapTable('getAllSelections');
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
				objDialog =  jDialog.iframe("${pageContext.request.contextPath}/support/plan.do?browseView&id="+selRows[0][idName]+"&type=1",{
					title : '查看预案',
					width : 800,
					height : 700,
					iframeId : "jDialogFrame"///对话框iframe的ID
				});
							
			}
		}
		function appendToObj(arry,parm){
			for(var i=0;i<arry.length;i++){
				parm[""+arry[i]['name']+""] = arry[i]['value']
			}
			return parm;
		}
		function editData(frmName,formName,AjaxUrl,fileRows,delRows,peopleNodes,delPeoples,standNodes,plotNodes,delStand,delPlot) {
		     var objForm = $("#"+frmName).contents().find("#"+formName);
			 var arry = objForm.serializeArray();
		     var parm = {afs:fileRows,
		           	   	 dfs:delRows,
                		 peopleNodes:peopleNodes,
                		 delPeoples:delPeoples,
                		 standNodes:standNodes,
                		 plotNodes:plotNodes,
                		 delStand:delStand,
                		 delPlot:delPlot};
		     parm = appendToObj(arry,parm);
			 var options = {
                url: AjaxUrl,
                type: 'post',
                dataType: 'json',
                data: parm,
                success: function (data) {
                   	try {
                   	    var d = data;
						if (d.state == 0) {
							objDialog.close();
							var dialog = jDialog.message(d.msg,{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
							datagrid.bootstrapTable("refresh");
						}else{
							var dialog = jDialog.message(d.msg,{
								autoClose : 1000,    // 3s后自动关闭
								padding : '30px',    // 设置内部padding
								modal: false         // 非模态，即不显示遮罩层
							});
						}
					} catch (e) {
							var dialog = jDialog.message(d.msg,{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
					} 
                }
            };
            $.ajax(options); 
		}
		
		
		function addData(frmName,formName,AjaxUrl,fileRows,peopleNodes,standNodes,plotNodes) {
		    var objForm = $("#"+frmName).contents().find("#"+formName);
		    var arry = objForm.serializeArray();
		     var parm = {afs:fileRows,
                		peopleNodes:peopleNodes,
                		standNodes:standNodes,
                		plotNodes:plotNodes};
		     parm = appendToObj(arry,parm);
		    var options = {
                url: AjaxUrl,
                type: 'post',
                dataType: 'json',
                data: parm,
                success: function (data) {
                   	try {
                   	    var d = data;
						if (d.state == 0) {
							objDialog.close();
							var dialog = jDialog.message(d.msg,{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
							datagrid.bootstrapTable("refresh");
						}else{
							var dialog = jDialog.message(d.msg,{
								autoClose : 1000,    // 3s后自动关闭
								padding : '30px',    // 设置内部padding
								modal: false         // 非模态，即不显示遮罩层
							});
						}
					} catch (e) {
							var dialog = jDialog.message(d.msg,{
									autoClose : 1000,    // 3s后自动关闭
									padding : '30px',    // 设置内部padding
									modal: false         // 非模态，即不显示遮罩层
							});
					} 
                }
            };
            $.ajax(options);           
		}
		

        function closeDialog(){
            objDialog.close();
        }
		// 时间格式化
		Date.prototype.Format =function(format)
		{
		    var o = {
			    "M+" : this.getMonth()+1, // month
			    "d+" : this.getDate(),    // day
			    "h+" : this.getHours(),   // hour
			    "m+" : this.getMinutes(), // minute
			    "s+" : this.getSeconds(), // second
			    "q+" : Math.floor((this.getMonth()+3)/3),  // quarter
			    "S" : this.getMilliseconds() // millisecond
		    };
		    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		    (this.getFullYear()+"").substr(4- RegExp.$1.length));
		    for(var k in o)if(new RegExp("("+ k +")").test(format))
		    format = format.replace(RegExp.$1,
		    RegExp.$1.length==1? o[k] :
		    ("00"+ o[k]).substr((""+ o[k]).length));
		    return format;
		};
		 
</script>
</body>
</html>
