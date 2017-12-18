<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="dict" uri="http://www.bxsurvey.com/dict" %>
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
	<script type="text/javascript">
		var fileListGrid;
		var fileRows = [];
		$(document).ready(function() {
		    var knowlageId = "${dangerKnowlage.id}";
		    initFileList(knowlageId);
		    
  			//设置滚动样式
			$("#knowlage_content").mCustomScrollbar({
				theme: "minimal-dark"
			});
		});

    
    	/*加载附件列表*/
		function initFileList(knowlageId) {
			fileListGrid = $('#fileListTable');
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
		
		function genterOperate(value,row,index){
		     var html = "<button type='button' onclick='downLoad("+index+")' class=' btn ' name='reset'><i class='glyphicon glyphicon glyphicon-download-alt'></i>下载</button>";
		     return  html;
		};
       
		function downLoad(index){
		    var row = fileRows[index];
            window.open("${pageContext.request.contextPath}/file.do?downloadFile&path="+row.path+"&fileName="+encodeURI(encodeURI(row.name))+"&fileAfter="+row.fileAfter);  
		};
	</script>
	<style>
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
      			<td class="percent" align="center" ><label>中文名 </label></td>
				<td><span>${dangerKnowlage.chName}</span></td>
				<td class="percent" align="center" ><label>危险货物编号 </label></td>
				<td><span>${dangerKnowlage.hwbh}</span></td>
      		</tr>
      		 <tr>
      			<td class="percent" align="center"><label>英文名<span>*</span> </label></td>
				<td ><span>${dangerKnowlage.enName}</span></td>
				<td class="percent" align="center"><label>UN编号<span>*</span> </label></td>
				<td ><span>${dangerKnowlage.unbh}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>分子式 </label></td>
				<td ><span>${dangerKnowlage.fzs}</span></td>
				<td class="percent" align="center"><label>分子量</label></td>
				<td ><span>${dangerKnowlage.fzl}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>CAS号<span>*</span></label></td>
				<td ><span>${dangerKnowlage.fzs}</span></td>
				<td class="percent" align="center"  ><label>外观与性状</label></td>
				<td ><span>${dangerKnowlage.wgyxz}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>熔点（℃）</label></td>
				<td ><span>${dangerKnowlage.rd}</span></td>
				<td class="percent" align="center"  ><label>相对密度</label></td>
				<td ><span>${dangerKnowlage.xdmd}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>沸点（℃）</label></td>
				<td ><span>${dangerKnowlage.fd}</span></td>
				<td class="percent" align="center"  ><label>饱和蒸气压</label></td>
				<td ><span>${dangerKnowlage.bhzqy}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>溶解性</label></td>
				<td ><span>${dangerKnowlage.rjx}</span></td>
				<td class="percent" align="center"  ><label>侵入途径</label></td>
				<td ><span>${dangerKnowlage.qrtj}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>毒性 </label></td>
				<td colspan="3"><span>${dangerKnowlage.dx}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>健康危害 </label></td>
				<td colspan="3"><span>${dangerKnowlage.jkwh}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>急救方法 </label></td>
				<td colspan="3"><span>${dangerKnowlage.jjff}</span></td>
      		</tr>
       </tbody>
    </table>
      <table class="table table-bordered">
    	<caption>危险性 </caption>
    	<tbody>
    		<tr>
    		    <td class="percent" align="center"><label>燃烧性</label></td>
				<td ><span><dict:label dictName="" itemCode="${dangerKnowlage.rsx}"/></span></td>
				<td class="percent" align="center"><label>燃烧分解物</label></td>
				<td ><span>${dangerKnowlage.rsfjw}</span></td>
      		</tr>
      		<tr>
    		    <td class="percent" align="center"><label>闪点(℃)</label></td>
				<td ><span>${dangerKnowlage.sd}</span></td>
				<td class="percent" align="center"  ><label>爆炸上限(v%)</label></td>
				<td ><span>${dangerKnowlage.bzsx}</span></td>
      		</tr>
      		<tr>
    		    <td class="percent" align="center"><label>引燃温度(℃)</label></td>
				<td ><span>${dangerKnowlage.yrwd}</span></td>
				<td class="percent" align="center"  ><label>爆炸下限(v%)</label></td>
				<td ><span>${dangerKnowlage.bzxx}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>危险特性 </label></td>
				<td colspan="3"><span>${dangerKnowlage.wxtx}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>储运条件 </label></td>
				<td colspan="3"><span>${dangerKnowlage.cytj}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"><label>泄漏处理 </label></td>
				<td colspan="3"><span>${dangerKnowlage.xlcl}</span></td>
      		</tr>
      </tbody>
    </table>
    <table class="table table-bordered">
	   	<caption>保护措施 </caption>
	   	<tbody>
      		<tr>
      			<td class="percent" align="center"   ><label>个体防护 </label></td>
				<td><span>${dangerKnowlage.gtfh}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"   ><label>操作处理方法 </label></td>
				<td><span>${dangerKnowlage.czclff}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"   ><label>运输注意事项 </label></td>
				<td><span>${dangerKnowlage.yszysx}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"   ><label>储存注意事项 </label></td>
				<td><span>${dangerKnowlage.cczysx}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"   ><label>泄漏防护</label></td>
				<td ><span>${dangerKnowlage.xlfh}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"   ><label>火灾防护</label></td>
				<td><span>${dangerKnowlage.hzfh}</span></td>
      		</tr>
          </tbody>
       </table>
      <table class="table table-bordered">
	   	<caption>处置 </caption>
	   	<tbody>
    		<tr>
      			<td class="percent" align="center"   ><label>灭火措施</label></td>
				<td><span>${dangerKnowlage.mhcs}</span></td>
      		</tr>
      		<tr>
      			<td class="percent" align="center"   ><label>急救措施</label></td>
				<td><span>${dangerKnowlage.jjcs}</span></td>
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
