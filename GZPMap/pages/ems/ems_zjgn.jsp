<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
    <link href="${pageContext.request.contextPath}/content/css/site.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/map/map-index.css" rel="stylesheet" type="text/css" /> 
    <link href="${pageContext.request.contextPath}/content/css/map/map-plot.css" rel="stylesheet" type="text/css" /> 
    <link href="${pageContext.request.contextPath}/content/css/map/map-monitor.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/content/css/ems/ems-resources.css" rel="stylesheet" type="text/css" />  
    
    <link href="${pageContext.request.contextPath}/content/css/ems/ems-bootstrap.css" rel="stylesheet" type="text/css" /> 
    <link href="${pageContext.request.contextPath}/content/css/ems/ems-index.css" rel="stylesheet" type="text/css" />

    <script src="${pageContext.request.contextPath}/js/map/interfacesmap/drawExtension/tween.js"></script>  
    <script>
	    var dojoConfig = {
		    paths: { Extension: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/Extension",
		             ExtensionDraw: location.pathname.replace(/\/[^/]+$/, "") + "/js/map/interfacesmap/drawExtension/plotDraw"
		     }
		};
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/js/plugins/echarts/echarts.min.js"></script>	    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.publicJS.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.print.js"></script>  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.plot.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.location.js"></script>            
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.splitScreen.js"></script>            
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.map2dTool.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.mapControl.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.layer.js"></script>       
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map/map.main.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource_material.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource_team.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/ems_resource_expert.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/onlinemonitor/monitor.js"></script>      
    
    

     <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script> 
                    
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/ems/emsCenter.js"></script>        -->

    <div class="main-content">
      <section>
        <div class="allleft" id="sidebar" style="height:auto;">
           	<div class="container-fluid" style="padding-right: 0px ;">
                <div class="le_qhbtn">
                    <img src="/GZPMap/content/images/ems/task-icon.png" style="margin-right:12px; margin-bottom: 15px;" />
                    <span class="bborder">XX公司甲醛气体泄漏处置预案</span>
                    <a href="#" class="btn btn-info bmbtt"  >切换</a>
                </div> 
                 <div class="bttom">
                    <div class="sdlf">
                        <ul >
                            <li class="HVE">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_01.png" />
                                    <p>情报核实</p>
                                </a>
                            </li>
                            <li class="HVET">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_02.png" />
                                    <p>情报分析</p>
                                </a>
                            </li>
                            <li class="HVET ">
                                <a href="#">
                                    <img class="Img_bg" src="/GZPMap/content/images/ems/icon-m_03.png" />
                                    <p>报告建议</p>
                                </a>
                            </li>
                            <li class="HVET curt">
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
                            </li>
                        </ul>
                    </div>
                     <div class="sdrt">
	                    	<div class="btn btn-info cursp" style="background:#87d2ef;">
	                            <span class="glyphicon glyphicon-time glmag" style="font-size:16px;"></span>
	                            <span style=" margin-left: 5px; padding-right:163px;">总结归纳</span>
							</div>
	                        <ul class="nav nav-sidebar nav-padding" style=" margin-left:0px;">
							<li class="active">
								<a href="#">
									<img src="/GZPMap/content/images/ems/alcx.png" >
									<span>案例查询</span>
								</a>
							</li>
							<li>
								<a href="#">
									<img src="/GZPMap/content/images/ems/dwzx.png" >
									<span>队伍咨询</span>
								</a>
							</li>
							<li>
								<a href="#">
									<img src="/GZPMap/content/images/ems/zjcx.png">
									<span>专家查询</span>
								</a>
							</li>
							<li>
								<a href="#">
									<img src="/GZPMap/content/images/ems/sssyt.png" width="20">
									<span>疏导示意图</span>
								</a>
							</li>
							<li>
								<a href="bgjy.html">
									<img src="/GZPMap/content/images/ems/zjgn.png">
									<span>总结归纳</span>
								</a>
							</li>
						</ul>
                    </div>
                </div>
           	</div>
 			
        </div>
        <div class="allright" id="content">
          <div class="allrttop" id="tool_container"></div>
          <div id="centerPanel">
        </div>
        <div id="mapSpread"></div>
      </section>
     </div>