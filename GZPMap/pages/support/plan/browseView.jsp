<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
<%@taglib prefix="relation" uri="http://www.bxsurvey.com/relation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/jquerymCustomScrollbar/jquery.mCustomScrollbar.css"/>
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
	<script src="${pageContext.request.contextPath}/js/plugins/jquerymCustomScrollbar/jquery.mousewheel.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/jquerymCustomScrollbar/jquery.mCustomScrollbar.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.min.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/js/plugins/ztree/zTreeStyle/img/zTreeStyle.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		var fileListGrid;
        var fileRows = [];
        var delRows =[];
        var fileUploadDialog;
        var selectPlotNode;
        var selectStandNode;
        var selectPeopleNode;
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
        var peopleListGrid;
        var type;
        var peopleSetting = {
		    view: {
		        dblClickExpand: false,
		        selectedMulti: false, //是否允许多选
		        txtSelectedEnable: false //是否允许选中节点的文字
		    },
		    data: {
		        simpleData: {
		            enable: true,
		            pIdKey : 'pid'
		        }
		    },
		    callback: {
		        onClick: onPeopleTreeClick
		    },
		    view: {
		        expandSpeed: "",
		        selectedMulti: false
		    },
		    edit: {
		    	enable: false
		    }
		};
		var standListGrid;
		var standSetting = {
		    view: {
		        dblClickExpand: false,
		        selectedMulti: false, //是否允许多选
		        txtSelectedEnable: false //是否允许选中节点的文字
		    },
		    data: {
		        simpleData: {
		            enable: true,
		            pIdKey : 'pid'
		        }
		    },
		    callback: {
		        onClick: onStandTreeClick
		    },
		    view: {
		        expandSpeed: "",
		        selectedMulti: false
		    },
		    edit: {
		    	enable: false
		    }
		};
		var plotListGrid;
		var plotSetting = {
		    view: {
		        dblClickExpand: false,
		        selectedMulti: false, //是否允许多选
		        txtSelectedEnable: false //是否允许选中节点的文字
		    },
		    data: {
		        simpleData: {
		            enable: true,
		            pIdKey : 'pid'
		        }
		    },
		    callback: {
		        onClick: onPlotTreeClick
		    },
		    view: {
		        expandSpeed: "",
		        selectedMulti: false
		    },
		    edit: {
		    	enable: false
		    }
		};		
		$(document).ready(function() {
		    var planId = "${plan.id}";
		    type = "${type}";
			initFileList(planId);
			initOrganList(planId);
			initStandList(planId);
			initPlotList(planId);
			initEventType();			
  			//设置滚动样式
			$("#knowlage_content").mCustomScrollbar({
				theme: "minimal-dark"
			});

		});

    
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
		function genterOperate(value,row,index){
		     var html = "<button type='button' onclick='downLoad("+index+")' class=' btn ' name='reset'><i class='glyphicon glyphicon glyphicon-download-alt'></i>下载</button>";
		     return  html;
		};
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
							 //ztree.selectNode(selectPeopleNode);//选择点 
						}
					}
				},
				error:function(){
					
				}
			});
        }
		function onPeopleTreeClick(e, treeId, treeNode) { 
			selectPeopleNode = treeNode;
		  	peopleListGrid.bootstrapTable('load', treeNode.data);
		}
		function genterPeopleOperate(value,row,index){
		    var html = "<button type='button'  class='btn btn-default' name='modifyPeople'><i class='glyphicon glyphicon glyphicon glyphicon-edit'></i>电话</button>";
		    return  html;
		};
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
						     ztree.setting.callback.onClick(null, ztree.setting.treeId, selectStandNode);//调用事件
						     //ztree.selectNode(selectStandNode);//选择点 
						}
					}
				},
				error:function(){
					
				}
			});
        }
		function onStandTreeClick(e, treeId, treeNode) { 
			selectStandNode = treeNode;
		  	standListGrid.bootstrapTable('load', treeNode.data);
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
						if(type == "2")
					    	zPlotNodes = r.obj;
					    else
					     	zPlotNodes = initGraphic(r.obj);
					    var ztree = $.fn.zTree.init($("#treePlot"), plotSetting, zPlotNodes);
						if(zPlotNodes.length > 1){
						     var plotNodes = ztree.getNodes();
							 selectPlotNode = plotNodes[0].children[0];
						     ztree.setting.callback.onClick(null, ztree.setting.treeId, selectPlotNode);//调用事件
						     //ztree.selectNode(selectPlotNode);//选择点 
						}					
					}
				},
				error:function(){
					
				}
			});
        }
        
        function onPlotTreeClick(e, treeId, treeNode) { 
			selectPlotNode = treeNode;
		  	plotListGrid.bootstrapTable('load', treeNode.data);
		}
		
		function genterPlotOperate(value,row,index){
		    var html = "<button type='button' onclick='showGraphicsWin("+index+")' class='btn btn-default' name='modifyPeople'><i class='glyphicon glyphicon glyphicon glyphicon-edit'></i>查看</button>";
		    return  html;
		};
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
		function downLoad(index){
		    var row = fileRows[index];
            window.open("${pageContext.request.contextPath}/file.do?downloadFile&path="+row.path+"&fileName="+encodeURI(encodeURI(row.name))+"&fileAfter="+row.fileAfter);  
		};
		
		function showGraphicsWin(index){
			var row = selectPlotNode.data[index]
			if(type == "2"){
				parent.home.center.drawPlot(row.plotGeo);
			}
			else {
				parent.parent.support.plot_plan.plotMap(row.geoIndex);
			}
		}
		
		
		
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
			eventObjDis.text(display);
		}
		
		function genterPeopleSex(value,row,index){
			var sex = "男";
			if(value ==1 )
				sex = "男";
			else
				sex = "女";
			return sex;
		}
	</script>
	<style>
	 .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
	  }
	h3{text-align:center;color:#333; font-size:16px; font-weight:bold;background:#EEEEEE; line-height:55px; margin-bottom:0px; padding-bottom:0px;}
	caption{text-align:center;color:#333; font-size:16px; font-weight:bold;background:#EEEEEE; line-height:35px;}
	table tr .percent{width:18%;}
	table tr td{line-height:30px !important;}
	</style>
</head>
<body style="overflow: hidden;">
<div id="knowlage_content" style="height:99.5%;margin-top:2px;">
	<div style="width:90%;margin:0 auto;">
    <table class="table table-bordered">
    	<caption>基本信息 </caption>
    	<tbody>
	      		<tr>
		      		<td class="percent" align="center"><label>预案类型</label></td>
					<td ><span><dict:label dictName="" itemCode="${plan.planType}"/></span></td>
					<td class="percent" align="center"><label>预案名称</label></td>
					<td ><span>${plan.planName}</span></td>
	      		</tr>
	      		<tr>
		      		<td class="percent" align="center"><label>预案编号</label></td>
					<td ><span>${plan.planNum}</span></td>
					<td class="percent" align="center"><label>预案版本号</label></td>
					<td ><span>${plan.versionNumber}</span></td>
	      		</tr>
	      		<tr>
		      		<td class="percent" align="center"><label>发布版本号</label></td>
					<td ><span>${plan.docReleaseNumber}</span></td>
					<td class="percent" align="center"><label>预案级别</label></td>
					<td ><span><dict:label dictName="" itemCode="${plan.planLevel}"/></span></td>
	      		</tr>
	      		<tr>
		      		<td class="percent" align="center"><label>行业类别</label></td>
					<td ><span><dict:label dictName="" itemCode="${plan.industry}"/></span></td>
					<td class="percent" align="center"><label>所属企业</label></td>
					<td ><span><relation:label  value="${plan.departId}" saveField="departId" disField="departName" tableName="SysDepart"  /></span></td>
	      		</tr>
	      		<tr>
		      		<td class="percent" align="center"><label>有效期</label></td>
					<td ><span><fmt:formatDate value='${plan.expiryDate}' pattern='yyyy-MM-dd' /></span></td>
					<td class="percent" align="center"><label>备案时间</label></td>
					<td ><span><fmt:formatDate value='${plan.filingTime}' pattern='yyyy-MM-dd' /></span></td>
	      		</tr>
	      		<tr>
		      		<td class="percent" align="center"><label>发布时间</label></td>
					<td ><span><fmt:formatDate value='${plan.docReleaseDate}' pattern='yyyy-MM-dd' /></span></td>
					<td class="percent" align="center"><label>关键字</label></td>
					<td ><span>${plan.keyword}</span></td>
	      		</tr>
	      		<tr>
	      			<td class="percent" align="center"><label>关联事件类型 </label></td>
					<td colspan="3"><span><label id="eventTypeDisplay"></label></span></td>
      			</tr>
	      		<tr>
	      			<td class="percent" align="center"><label>适用范围 </label></td>
					<td colspan="3"><span>${plan.scope}</span></td>
      			</tr>
      			<tr>
	      			<td class="percent" align="center"><label>发放范围</label></td>
					<td colspan="3"><span>${plan.range}</span></td>
      			</tr>	
      			<tr>
	      			<td class="percent" align="center"><label>预案描述</label></td>
					<td colspan="3"><span>${plan.remark}</span></td>
      			</tr>      
       </tbody>
    </table>
 	<table class="table table-bordered">
	        <caption>组织机构 </caption>
	        <tbody>
	         	<tr>
		         	<td width="30%"><h3 style=" height:35px;line-height:35px;width:100%; text-align:center;margin-top:0; background:none;">机构树</h3></td>
		         	<td><h3 style=" height:35px;line-height:35px; width:100%; text-align:center; margin-top:0; background:none;">人员列表</h3></td>
	         	</tr>
	         	<tr>
	         		<td width="30%" valign="top"><ul id="treePeople" class="ztree"></ul></td>
	         		<td valign="top">
	         			<table id="peopleListTable"
			               data-toggle="table"
			               data-height="400"
			               data-width="509"
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
			               			 <th data-field="operation" data-width="30"  data-formatter="genterPeopleOperate">操作</th>
			            		</tr>
		           			 </thead>
	        			</table>
				      </td>
	         	</tr>
	        </tbody>
        </table>
 	<table class="table table-bordered">
	        <caption>分级响应要求</caption>
	        <tbody>
		        <tr>
			       <td width="30%"><h3 style=" height:35px;line-height:35px;width:100%; text-align:center;margin-top:0; background:none;">事件类型</h3></td>
			       <td><h3 style=" height:35px;line-height:35px; width:100%; text-align:center; margin-top:0; background:none;">响应要求</h3></td>
		        </tr>
		        <tr>
		           <td width="30%" valign="top"> <ul id="treeStand" class="ztree"></ul></td>
		           <td valign="top"> 
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
			               			 <th data-field="oneLevelStand">I级需求</th>
			               			 <th data-field="twoLevelStand">II级需求</th>
			               			 <th data-field="threeLevelStand">II级需求</th>
			            		</tr>
		           			 </thead>
	        			</table>
	        		</td>
		        </tr>
	   		</tbody>
	</table>
 	<table class="table table-bordered">
	        <caption>疏散方案图</caption>
	        <tbody>
	        <tr>
		       <td width="30%"><h3 style=" height:35px;line-height:35px;width:100%; text-align:center;margin-top:0; background:none;">方案列表</h3></td>
		       <td><h3 style=" height:35px;line-height:35px; width:100%; text-align:center; margin-top:0; background:none;">方案详情</h3></td>
	        </tr>
	        <tr>
	           <td width="30%" valign="top"> <ul id="treePlot" class="ztree"></ul></td>
	           <td valign="top">  
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
       			</td>
	        </tr>
	   </tbody>
	</table>
       <table id="fileListTable"  data-toggle="table" data-height="105" style="padding-top:0px;margin-top:0px; border-bottom:none;">
        	<h3>附件</h3>
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
</body>
</html>
