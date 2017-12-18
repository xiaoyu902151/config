package com.bxsurvey.comd.ComdExpert.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.comd.ComdExpert.service.ComdExpertServiceI;
import com.bxsurvey.danger.DangerSupplies.model.DangerSuppliesDetail;
import com.bxsurvey.sys.params.model.SysParams;





/** 
 * @ClassName: ComdScheduController 
 * @Description: 控制器
 * @author cqc
 * @date 2015-7-20 下午2:14:52 
 *  
 */
@Controller
@RequestMapping("/comd/expert")
public class ComdExpertController extends BaseController {
	private static Logger log = Logger.getLogger(ComdExpertController.class);
	
	@Autowired
	private ComdExpertServiceI comdExpertService;//调度
	
	/**
	 * 
	 * Title: getSuppliesByXY
	 * Description:通过xy来查询周边物资
	 * 接收格式：{"serviceUrl": "", "distance": "2000","objId": "","x": "12363613.311","y": "2447855.513"}
	 * Created On: 2015-7-3 上午10:53:47
	 * @author ldw 
	 * @return
	 */
	@RequestMapping(params = "getExpertsByXY", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject getExpertsByXY(HttpServletRequest request,String distance,String x,String y) {
		List<ComdExpert> list = new ArrayList<ComdExpert>();
		try {
			double dis = Double.valueOf(distance);			
			BigDecimal bx = new BigDecimal(x);
			BigDecimal by = new BigDecimal(y);
			list = comdExpertService.getExpertsByXY(dis,bx,by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}
/*	@RequestMapping(params = "getExpertList", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Datagrid getExpertList()
	{
		return service.getExpertList();
	}*/
	
	/*
	 * 添加
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			ComdExpert obj = getObject(ComdExpert.class, "obj", request);
			obj.setWriteTime(new Date());
			comdExpertService.save(obj);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

	/**
	 * 编辑
	 * 
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params = "update")
	@ResponseBody
	public Json update(HttpServletRequest request) {
		Json j = new Json();
		try {
			ComdExpert obj = getObject(ComdExpert.class, "obj", request);
//			String zgqId = request.getParameter("zgqId");
//			if (StringUtils.isNotBlank(zgqId)) {
//				DangerZgq zgq = dangerZgqService.getObjectById(Integer
//						.parseInt(zgqId));
//				obj.setDangerZgq(zgq);
//			}
//			String departId = request.getParameter("departId");
//			if (StringUtils.isNotBlank(departId)) {
//				SysDepart sysDepart = sysDepartService.getObjectById(Integer
//						.parseInt(departId));
//				obj.setSysDepartv(sysDepart);
//			}
			
			comdExpertService.update(obj);
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
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public Json delete(String ids) {
		Json j = new Json();
		try {
			comdExpertService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

	/**
	 * 列表查询
	 * 
	 * @return
	 */
	@RequestMapping(params = "searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, 
			HttpServletRequest request) {
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "expertId":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<ComdExpert> pageResults = new PageResults<ComdExpert>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			ComdExpert obj = getObject(ComdExpert.class, "obj", request);
			obj.setValidity(true);
			pageResults = comdExpertService.getListForJson(pageResults,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajaxGrid(pageResults);
	}	
	
	/**
	 * 列表查询，不分页
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "searchGridNoPage")
	@ResponseBody
	public JSONObject searchGridNoPage(HttpServletRequest request) {
		List<ComdExpert>  list= null;
		try {
			ComdExpert obj = getObject(ComdExpert.class, "obj", request);
			list= comdExpertService.getListNoPage(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderJson(list);
	}
	
}
