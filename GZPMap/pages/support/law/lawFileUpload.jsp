<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />

<script
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function uploadFile() {
		parent.uploadFile("jDialogFrame","organForm","${pageContext.request.contextPath}/lawFile.do?uploadFile");
	}
</script>
</head>
<body>
	<div id="organDialog">
		<div class="container" style="margin-top:10px;">
			<form id="organForm" method="post" class="form-horizontal" enctype="multipart/form-data">
				<div class="form-group">
					<label class="col-xs-2 control-label" style="line-height:34px;">文件：</label>
					<div class="col-xs-10">
						<input type="file" class="col-xs-9 form-control" name="file" id="file" />
					</div>
					<input type="text" style="display:none" class="form-control" name="remotePath" id="remotePath" value="/GZPMap/law" />
				</div>
			</form>
			<div class="modal-footer Ptfixed" style="position:fixed; bottom:10px;right:15px;width:100%;">
				<div>
					<button onclick="uploadFile()" class=" btn btn-default" name="addTJ"
						value="close">
						<i class="glyphicon glyphicon-plus"></i>上传文件
					</button>
					<button  onclick="parent.closeDialog()" class=" btn btn-default" name="close" value="close"><i class="glyphicon glyphicon-off"></i>关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>