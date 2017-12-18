<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/content/css/site.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.request.contextPath}/content/css/map/map-index.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.request.contextPath}/content/css/map/map-plot.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.request.contextPath}/content/css/map/map-monitor.css"
		rel="stylesheet" type="text/css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/css/screen.css"
		media="screen">
		<link
			href="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/css/jquery.treetable.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/css/jquery.treetable.theme.default.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/content/css/ems/ems-index.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/content/css/ems/ems-route.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/content/css/ems/ems-resources.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/content/css/danger/danger-index.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/js/plugins/video/css/video-js.min.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/js/plugins/validate/css/formValidation.css" />
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/content/css/default/default.icon.css" />
		<script
			src="${pageContext.request.contextPath}/js/plugins/video/js/video.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/map/interfacesmap/drawExtension/tween.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/pages/ems/emsCenter.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/moment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
		<script>
	    var dojoConfig = {
		    paths: { Extension: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/Extension",
		             ExtensionDraw: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/plotDraw"
		     }
		};
    </script>
		<!-- 引入 echarts.js -->
		<script
			src="${pageContext.request.contextPath}/js/plugins/echarts/echarts.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.publicJS.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.print.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.plot.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.location.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.splitScreen.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.map2dTool.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.mapControl.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.layer.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/map/map.main.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/pages/ems/ems_dirll.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/pages/ems/ems_route.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/plugins/jquery-treetbale/jquery.treetable.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/pages/ems/pub/pub.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/plugins/validate/js/formValidation.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/plugins/validate/js/framework/bootstrap.js"></script>
		<div class="main-content">
			<section>
			<div class="allleft"
				style="width:330px;border-right: 1px solid #e1e1e1;" id="sidebar">
				<div class="container-fluid"
					style="padding-left: 0px;padding-right: 0px ;">
					<div class="le_qhbtn">
						<!-- <img src="/GZPMap/content/images/ems/task-icon.png"
						style="height: 16px;width: 16px;margin-bottom: 3px;" /> <span
						class="bborder">某公司甲醛气体泄漏处置预案</span>
					 -->
						<span style="float:left;padding-top:2px"> <i
							class="icon-project-inquire"
							style="color:#1ba1e2;margin-right:2px;font-size:15px"></i>当前预案：</span>
						<!--<button class="btn btn-default btn-xs"
						style="float:right;margin-right:10px;width:45px;height:21px; font-size: 12px;">切换</button> -->
						<select id="plan_select" class="form-control"
							style="height:24px;padding:0px;width:230px"><option
								value="0">请选择处置预案</option>
						</select>
					</div>
					<div class="bttom">
						<div class="sdlf" style="display: none">
							<ul>
								<!--  <li class="HVE">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_01.png" />
                                    <p>情报核实</p>
                                </a>
                            </li>-->
								<!-- <li class="HVET curt" style="margin-top:0px;">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_02.png" />
                                    <p>情报分析</p>
                                </a>
                            </li> -->
								<!-- 
                            <li class="HVET">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_03.png" />
                                    <p>报告建议</p>
                                </a>
                            </li>
                            <li class="HVET">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_04.png" />
                                    <p>事件处置</p>
                                </a>
                            </li>
                            <li class="btng5">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_05.png" />
                                    <p>总结归纳</p>
                                </a>
                            </li>-->
							</ul>
						</div>
						<div class="sdrt" style="margin-left: 32px;">
							<div class="btn btn-info cursp"
								style="background:#1DB1EA;width:265px;text-align:left">
								<span class="icon-land-use-analysis"
									style="font-size:16px;top:3px;"></span> <span
									style="font-size:15px;margin-left:5px;">情报分析</span>
							</div>
							<ul class="nav nav-sidebar nav-padding">
								<li><a href="#"><span class="icon-location"
										style="color:#1ba1e2;font-size:20px;margin-right: 4px;"></span><span>设置事故点</span>
										<span class="clearPoint" title="重新设置"><i
											class="glyphicon glyphicon-repeat" style="color:transparent"></i>
									</span> <!-- <button class="btn btn-default btn-xs" style="float: right">
											<i class="glyphicon glyphicon-repeat"></i>重置
										</button> --> </a></li>
								<li><a href="#"><span class="icon-Annotation"
										style="color:#1ba1e2;font-size:20px;margin-right: 4px;"></span><span>智能调度</span>
								</a></li>
								<!-- <li><a href="#"> <img
									src="/GZPMap/content/images/ems/ssjk.png"> <span>实时监控</span>
							</a></li> -->
								<li><a href="#"> <img
										src="/GZPMap/content/images/ems/road-rescue.png"
										style="height:20px;width:20px"> <span>救援路径</span>
								</a></li>
								<li><a href="#"><i class="glyphicon glyphicon-random"
										style="color:#1ba1e2;font-size:17px;margin-right: 13px"></i><span>疏散方案图</span>
								</a></li>
								<li><a href="#"><i
										class="glyphicon glyphicon-phone-alt"
										style="color:#1ba1e2;font-size:17px;margin-right: 13px"></i><span>专家咨询</span>
								</a>
								</li>
								<li><a href="#"><i
										class="glyphicon glyphicon-list-alt"
										style="color:#1ba1e2;font-size:17px;margin-right: 13px"></i><span>总结归纳</span>
								</a>
								</li>
							</ul>
						</div>
					</div>
				</div>

			</div>
			<div class="allright" id="content">
				<div class="allrttop" id="tool_container"></div>
				<div id="centerPanel"></div>
				<div id="mapSpread"></div>
			</section>
		</div>