package com.bxsurvey.danger.DangerZg.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.comd.ComdExpert.controller.ComdExpertController;
import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.danger.DangerZg.model.DangerZg;
import com.bxsurvey.danger.DangerZg.service.DangerZgServiceI;

@Controller
@RequestMapping("/danger/zg")
public class DangerZgController extends BaseController{
	private static Logger log = Logger.getLogger(DangerZgController.class);
	@Autowired
	private DangerZgServiceI dangerZgService;
	
	/**
	 * 根据id查询
	 * @param dangerZgId
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "searchDangerZgById")
	@ResponseBody
	public JSONObject searchDangerZgById(String dangerZgId,HttpServletRequest request){
		
		DangerZg dg = null;
		try {
			dg = dangerZgService.getObjectById(new Integer(dangerZgId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean2JSONObject(dg);
	}
	
	/**
	 * 查询所有
	 * 
	 */
	@RequestMapping(params="getAllZg")
	@ResponseBody
	public JSONObject getAllZg(HttpServletResponse response){
		List<DangerZg> list = dangerZgService.getAllZg();
		return renderJson(list);
	
	  }
	
	/**
	 * 编辑
	 * 
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params = "updateByObjectId")
	@ResponseBody
	public Json updateByObjectId(HttpServletRequest request,String objectId,String x,String y,String plotGeo) {
		Json j = new Json();
		try {
			dangerZgService.updateByObjectId(objectId, x, y,plotGeo);
			j.setSuccess(true);
			j.setMsg("修改成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	
	/**
	 * 
	 * Title: getZGByXY
	 * Description:通过xy来查询周边物资
	 * 接收格式：{"serviceUrl": "", "distance": "2000","objId": "","x": "12363613.311","y": "2447855.513"}
	 * Created On: 2015-7-3 上午10:53:47
	 * @author ldw 
	 * @return
	 */
	@RequestMapping(params = "getZGByXY", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject getZGByXY(HttpServletRequest request,String distance,String x,String y) {
		List<DangerZg> list = new ArrayList<DangerZg>();
		try {
			double dis = Double.valueOf(distance);			
			BigDecimal bx = new BigDecimal(x);
			BigDecimal by = new BigDecimal(y);
			list = dangerZgService.getZGByXY(dis,bx,by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}

}
