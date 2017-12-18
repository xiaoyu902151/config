package com.bxsurvey.sys.depart.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.danger.DangerSupplies.model.DangerSupplies;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.depart.service.SysDepartServiceI;
import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.user.model.SysUser;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONObject;
/**
 * 
 ***********************************************
 * Copyright (c) 2015 Hengte Technology Co.,Ltd. 
 * All Rights Reserved. 
 * FileName：com.bxsurvey.controller.sys.SysDepartController.java 
 * Created On: 2015-5-5 上午10:25:26
 * Description: 组织机构表mvc控制器
 * @author ldw 
 * @version 1.0
 ***********************************************
 */
@Controller
@RequestMapping("/admin/depart")
public class SysDepartController extends BaseController {
	private static Logger log = Logger.getLogger(SysDepartController.class);
	@Autowired
	private SysDepartServiceI sysDepartService;
	/*
	 * 添加
	 */
	@RequestMapping(params="save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysDepart obj = getObject(SysDepart.class, "obj", request);
			sysDepartService.save(obj);
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
	 * @param bxSysOgran
	 * @return
	 */
	@RequestMapping(params="update")
	@ResponseBody
	public Json update(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysDepart obj = getObject(SysDepart.class, "obj", request);
			sysDepartService.update(obj);
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
	 * @param ids
	 * @return
	 */
	@RequestMapping(params="delete")
	@ResponseBody
	public Json delete(String ids) {
		Json j = new Json();
		try {
			sysDepartService.deleteById(ids);
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
	 * @return
	 */
	@RequestMapping(params="searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, 
			HttpServletRequest request) {
		//page=2&rows=10
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "expertId":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<SysDepart> pageResults = new PageResults<SysDepart>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);

		try {
			SysDepart obj = getObject(SysDepart.class, "obj", request);
			pageResults = sysDepartService.findListForJson(pageResults, obj);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	
	/**
	 * 根据id查询
	 * @param departId
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "searchDepartById")
	@ResponseBody
	public JSONObject searchDepartById(String departId,HttpServletRequest request){
		SysDepart sd = null;
		try {
			sd = sysDepartService.getObjectById(new Integer(departId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean2JSONObject(sd);
	}
	
	/*
	 * 查找所有的公司
	 */
	@RequestMapping(value="findAllDepart")
	@ResponseBody
	public  List findAllDepart(){
		List<SysDepart> list = sysDepartService.findAll();
		return list;
	}
	
	@RequestMapping(params = "getAll")
	@ResponseBody
	public JSONObject getAll() {
		List<SysDepart> list =sysDepartService.findAll();
		return renderJson(list);
	}
	
//	//根据港口找到下面的公司
//	@RequestMapping(params = "getDepartsByPort")
//	@ResponseBody
//	public  JSONObject getDepartsByPort(String portId){
//		JSONObject jsonObject =null;
//		List<DangerMt> list = dangerMtService.findMtByPortId(portId);
//		if(list.size()>0){
//			jsonObject = sysDepartService.getDepartsByPort(list);
//		}		
//		return jsonObject;
//	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("admin/depart/addView");
		return md;
	}
	/**
	 * 返回需要修改页面
	 * @param departId
	 * @return
	 */
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String departId){
		ModelAndView md = new ModelAndView();
		SysDepart sysDepart = sysDepartService.getObjectById(Integer.parseInt(departId));
		md.addObject("sysDepart",sysDepart);
		md.setViewName("admin/depart/updateView");
		return md;
	}
	
	@RequestMapping(params = "getDangerAccountTree")
	@ResponseBody
	public Json getDangerAccountTree(HttpServletRequest request) {
		Json j = new Json();
		try {
			String contextPath = request.getContextPath();
			List<Tree> nodes = sysDepartService.getDangerAccountTree(contextPath);
			//JSONObject ret = renderJson(nodes);
			j.setSuccess(true);
			j.setMsg("加载成功!");
			j.setObj(nodes);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("加载失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
}
