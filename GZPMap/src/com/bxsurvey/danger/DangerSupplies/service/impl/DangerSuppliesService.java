package com.bxsurvey.danger.DangerSupplies.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.framework.httpModel.PageResults;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bxsurvey.danger.DangerSupplies.dao.DangerSuppliesDao;
import com.bxsurvey.danger.DangerSupplies.dao.DangerSuppliesDetailDao;
import com.bxsurvey.danger.DangerSupplies.dao.ViewDangerSuppliesDao;
import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.danger.DangerSupplies.model.ViewDangerSupplies;
import com.bxsurvey.danger.DangerSupplies.service.DangerSuppliesServiceI;
import com.bxsurvey.danger.DangerSupplies.vo.StandVO;
import com.bxsurvey.support.plan.dao.PlanStandDao;
//import com.bxsurvey.map.dao.SysMapServiceDao;
//import com.bxsurvey.map.model.SysMapLayer;
//import com.bxsurvey.map.model.SysMapService;
//import com.bxsurvey.sys.params.dao.SysParamsDao;
//import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.module.model.SysModule;


/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.service.danger.impl.DangerSuppliesService.java 
 * Created On: 2015-5-14 下午2:32:23
 * Description: 应急物资情况业务实现类
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Service("dangerSuppliesService")
public class DangerSuppliesService implements DangerSuppliesServiceI {
	@Resource
	private DangerSuppliesDao suppliesDao;
	@Resource
	private DangerSuppliesDetailDao suppliesDetailDao;
	
	@Resource
	private PlanStandDao planStandDao;
	
	private static String RP_ID = "75";//物资储备点ID
	private static String RP_NAME = "物资储备点";//物资储备点名称

	private static String paramsType = "SUPPLIES";//参数表类型
	/*
	 * 新增
	 */
	public void save(DangerSupplies supplies) {
		if(supplies != null) {
			supplies.setVaccine(0);
			supplies.setValidity(true);
		}
		suppliesDao.save(supplies);
	}
	/*
	 * 编辑
	 */
	public void update(DangerSupplies supplies) {
		suppliesDao.update(supplies);
	}
	/*
	 * 删除
	 */
	public void deleteById(String ids) {
		String[] str = ids.split(",");
		for(String id : str) {
			suppliesDao.deteleObjectById(Integer.parseInt(id));
		}
	}
	/*
	 * 列表查询
	 */
//	public PageResults findListForJson(PageResults page,SeachSupplies seachSupplies) {
//		return suppliesDao.findListForJson(page,seachSupplies);
//	}
	
	public PageResults<DangerSupplies> getListForJson(PageResults<DangerSupplies> page, DangerSupplies obj) {
		return suppliesDao.getObjListForPage(page, obj);
	}
	
	public DangerSupplies getObjectById(Integer suppliesId) {
		return suppliesDao.get(suppliesId);
	}
	/*
	 * 通过地图服务查询物资集合
	 */
	public JSONArray getSuppliesByService(JSONArray array) {
		JSONArray resp = new JSONArray();
		/*for(int i = 0; i < array.size(); i++) {
			JSONObject attributes = array.getJSONObject(i);
			JSONObject obj = attributes.getJSONObject("attributes");
			String serviceUrl = obj.getString("serviceUrl");
			String fid = obj.getString("OBJECTID");
			
			String[] split = serviceUrl.split("/");
			String index = split[split.length-1];
			SysMapService service = serviceDao.getByServiceUrl(serviceUrl);
			SysMapLayer layer = layerDao.findByServiceIdAndIndex(service.getMapServiceId(), Integer.parseInt(index));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mapServiceId", service.getMapServiceId());
			map.put("mapLayerId", layer.getMapLayerId());
			map.put("fid", fid);
			map.put("validity", true);
			map.put("vaccine", 1);
			DangerSupplies supplies = suppliesDao.getByProperty(map);
			
			
			if(supplies != null) {
				JSONObject respJson = new JSONObject();
				respJson.element("serviceUrl", serviceUrl);
				respJson.element("OBJECTID", fid);
				respJson.element("name", supplies.getName());
				respJson.element("num", supplies.getNum());
				respJson.element("route", supplies.getRoute());
				respJson.element("size", supplies.getSize());
				respJson.element("x", supplies.getX());
				respJson.element("y", supplies.getY());
				
				SysParams param = paramsDao.get(supplies.getSuppliesType());
				respJson.element("suppliesType", param.getParamsName());
				resp.add(respJson);
			}
			
		}*/
		
		return resp;
	}
	
	/**
	 * 
	 * Title: getSuppliesByXY
	 * Description: 通过xy坐标查询数据
	 * Created On: 2015-7-6 上午9:06:25
	 * @author ldw 
	 * @param req
	 * @return
	 
	public JSONArray getSuppliesByXY(JSONObject req) {
		JSONArray resp = new JSONArray();
		double distance = req.getDouble("distance");
		BigDecimal x = new BigDecimal(req.getString("x"));
		BigDecimal y = new BigDecimal(req.getString("y"));
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
		List<DangerSupplies> list = suppliesDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString());
		
		if(!list.isEmpty()) {
			for (DangerSupplies supplies : list) {
				JSONObject respJson = new JSONObject();
				respJson.element("serviceUrl", "");
				respJson.element("OBJECTID", "");
				respJson.element("name", supplies.getName());
				respJson.element("size", supplies.getSize());
				respJson.element("route", supplies.getRoute());
				respJson.element("num", supplies.getNum());
				respJson.element("x", supplies.getX());
				respJson.element("y", supplies.getY());
				//企业名称，联系电话
				respJson.element("departName",supplies.getSysDepartv().getDepartName());
				respJson.element("departTel",supplies.getSysDepartv().getDepartTel());
				
				SysParams param = paramsDao.get(supplies.getSuppliesType());
				respJson.element("suppliesType", param.getParamsName());
				resp.add(respJson);
			}
		}
		return resp;
	}*/

	public JSONObject getSuppliesByXY(double distance,BigDecimal x,BigDecimal y,String planId,String planType,String level) {
		JSONObject resp = new JSONObject();
		BigDecimal minX = x.subtract(new BigDecimal(distance));
		BigDecimal maxX = x.add(new BigDecimal(distance));
		BigDecimal minY = y.subtract(new BigDecimal(distance));
		BigDecimal maxY = y.add(new BigDecimal(distance));
				
		List<DangerSupplies> dangerList = suppliesDao.findListByXYminOrmax(minX.toString(), maxX.toString(), minY.toString(), maxY.toString(),distance);
		String spIds = "";
		String rpIds = "";
		JSONArray arr1 = new JSONArray();
		if(!dangerList.isEmpty()) {
			for (DangerSupplies supplies : dangerList) {
				JSONObject respJson = new JSONObject();
				if(supplies.getSuppliesType()== 75)
					rpIds += ","+supplies.getId();
				else
					spIds += ","+supplies.getId();
				respJson.element("id", supplies.getId()== null ? 0:supplies.getId());//主键标识
				respJson.element("name", supplies.getName()== null ? "":supplies.getName());//名称
				respJson.element("size", supplies.getSize() == null ? "":supplies.getSize());//型号
				respJson.element("route", supplies.getRoute()== null ? "":supplies.getRoute());//用途
				respJson.element("num", supplies.getNum()== null ? 0:supplies.getNum());//数量
				respJson.element("x", supplies.getX()== null ? 0:supplies.getX());//x坐标
				respJson.element("y", supplies.getY()== null ? 0:supplies.getY());//y坐标
				respJson.element("suppliesType", supplies.getSuppliesType() == null ? "":supplies.getSuppliesType());//应急物资类型
				respJson.element("suppliesTypeName", "");//物资类型中文名
				respJson.element("departId", supplies.getSysDepart().getDepartId() == null ? "":supplies.getSysDepart().getDepartId());//所属公司ID
				respJson.element("vaccine", supplies.getVaccine() == null ? "0":supplies.getVaccine());//审核状态,0-未审核，1-已审核，2-审核不通过
				respJson.element("validity", supplies.getValidity() == null ? false:supplies.getValidity());//可用性
				respJson.element("remark", supplies.getRemark() == null ? "":supplies.getRemark());//备注
				respJson.element("writer", supplies.getWriter()== null ? false:supplies.getWriter());//添加人
				respJson.element("vaccineMan", supplies.getVaccineMan()== null ? false:supplies.getVaccineMan());//审核人
				arr1.add(respJson);
			}
		}
		
		Map stand = planStandDao.getListByPlanAndType(planId,planType,level);
		JSONArray arr2 = new JSONArray();
		int pId =1;
		if(!spIds.equals("")){
			spIds = spIds.substring(1,spIds.length());
			List<Map<String,Object>> spSumList = suppliesDao.countBySuppliesType(spIds,RP_ID);
			if(!spSumList.isEmpty()) {
				for (Map<String,Object> map : spSumList) {
					JSONObject sumObject = new JSONObject();
					Object stTypeName = map.get("type_name");
					Object stTypeId = map.get("type_id");
					Object stSum = map.get("sum_num");
					Object stNeed = getStand(stTypeId.toString(),stand);;
					sumObject.element("suppliesTypeName",stTypeName);
					sumObject.element("id",pId);
					sumObject.element("num",stSum);
					sumObject.element("needNum",stNeed);
					arr2.add(sumObject);
					List<Map<String,Object>> spDetailList = suppliesDao.findBySuppliesType(spIds,stTypeId.toString(),RP_ID);
				    for(Map<String,Object> d : spDetailList){
				    	JSONObject viewJson = new JSONObject();
				    	viewJson.element("suppliesTypeName",d.get("name").toString()+d.get("size").toString());
						viewJson.element("num",d.get("num"));
						viewJson.element("pid",pId);
						viewJson.element("x",d.get("x"));
						viewJson.element("y",d.get("y"));
						viewJson.element("suppliesId",d.get("id"));
						arr2.add(viewJson);
				    }
				    pId++;
				}
			}
		}
		if(!rpIds.equals("")){
			rpIds = rpIds.substring(1,rpIds.length());
			List<Map<String,Object>> rpSumList = suppliesDetailDao.countBySuppliesDetailType(rpIds,RP_ID);
			if(!rpSumList.isEmpty()) {
				for (Map<String,Object> map : rpSumList) {
					JSONObject sumObject = new JSONObject();
					Object stTypeName = map.get("type_name");
					Object stTypeId = map.get("type_id");
					Object stSum = map.get("sum_num");
					Object stNeed = getStand(stTypeId.toString(),stand);
					sumObject.element("suppliesTypeName",stTypeName);
					sumObject.element("id",pId);
					sumObject.element("num",stSum);
					sumObject.element("needNum",stNeed);
					arr2.add(sumObject);
					List<Map<String,Object>> rpDetailList = suppliesDetailDao.findBySuppliesDetailType(rpIds,stTypeId.toString(),RP_ID);
					for(Map<String,Object> d : rpDetailList){
				    	JSONObject viewJson = new JSONObject();
				    	viewJson.element("suppliesTypeName",d.get("name"));
						viewJson.element("num",d.get("num"));
						viewJson.element("pid",pId);
						viewJson.element("x",d.get("x"));
						viewJson.element("y",d.get("y"));
						viewJson.element("suppliesId",d.get("id"));
						arr2.add(viewJson);
				    }
					pId++;
				}
			}
		}
		Iterator<Map.Entry<String,StandVO>> entries = stand.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String,StandVO> entry = entries.next();
			StandVO vo = (StandVO) entry.getValue();
			if(!vo.getHasItem()){
				JSONObject sumObject = new JSONObject();
				sumObject.element("suppliesTypeName",vo.getSuppliesTypeName());
				sumObject.element("id",pId);
				sumObject.element("num",0);
				sumObject.element("needNum",vo.getNeed());
				arr2.add(sumObject);
				pId++;
			}
		}
		resp.put("drawObject", arr1);
		resp.put("sumObject",arr2);
		return resp;
	}
	
	private String getStand(String key,Map stand){
		String res;
		Object value = stand.get(key);
		if(value == null){
			res = "0";
			return res;
		}else{
			StandVO vo = (StandVO)value;
			res = vo.getNeed();
			vo.setHasItem(true);
			stand.put(key, vo);
		}
		return res;
	}
	/*
	 *查询所有的supplies
	 */
	public List<DangerSupplies> getAllSupplies() {
		return suppliesDao.getAllSupplies();
	}
	
	/**
	 * 供flex调用，查询所有的物资数据
	 * @return
	 */
	public JSONArray getAllSuppliesByFlex(){
		JSONArray resp = new JSONArray();
		
		/*List<DangerSupplies> list = suppliesDao.getAllSupplies();
		if(!list.isEmpty()) {
			for (DangerSupplies supplies : list) {
				JSONObject respJson = new JSONObject();
				respJson.element("serviceUrl", "");
				respJson.element("OBJECTID", "");
				respJson.element("name", supplies.getName());
				respJson.element("size", supplies.getSize());
				respJson.element("route", supplies.getRoute());
				respJson.element("num", supplies.getNum());
				respJson.element("x", supplies.getX());
				respJson.element("y", supplies.getY());
				//企业名称，联系电话
				respJson.element("departName",supplies.getSysDepartv().getDepartName());
				respJson.element("departTel",supplies.getSysDepartv().getDepartTel());
				
				SysParams param = paramsDao.get(supplies.getSuppliesType());
				respJson.element("suppliesType", param.getParamsName());
				resp.add(respJson);
			}
		}*/
		return resp;
	}
	
}
