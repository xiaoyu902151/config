<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/home/layout/map/2dmap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.css"/> 
	<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/tableExport/tableExport.js"></script>    
    <div id="map" class="mapone"></div>
    <div id="map-two" class="maptwo"></div>
<%--    <div id="mapcontrol" class="map_control">--%>
<%--         <div class="map_controlType"></div> --%>
<%--         <div class="map_controlbom">控件</div>                                                   --%>
<%--    </div>--%>
<%--    <div id="map_controltree" class="map_controltree"></div> --%>
    <div id="maplayer" class="map_layer" title="图层">
         <div class="map_layerType"></div> 
<%--         <div class="map_layerbom">图层</div>                                                   --%>
    </div>
    <div id="map_layertree" class="map_layertree"></div> 
    <div id="map-print" class="mapprint"></div>
  </div>
