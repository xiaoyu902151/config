package com.bxsurvey.support.law.controller;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.danger.DangerZg.model.DangerZg;
import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.bxsurvey.support.law.model.Law;
import com.bxsurvey.support.law.service.LawServiceI;
import com.bxsurvey.sys.depart.model.SysDepart;

/**
 * 
 *
 * Description:法律mvc控制器
 * @author wzr
 * @date 2016-8-24下午4:48:57
 *
 */
@Controller
@RequestMapping("/support/law")
public class LawController extends BaseController{
	private static Logger log = Logger.getLogger(LawController.class);
	
	@Autowired
	private LawServiceI lawService;
	
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
		String orderBy = sortName == null  ? "lawName":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<Law> pageResults = new PageResults<Law>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);

		try {
			String type = request.getParameter("type");
			Law obj = getObject(Law.class, "obj", request);
			if(type ==""||type ==null){
				pageResults = lawService.findListForJson(pageResults, obj);
			}else{
				pageResults = lawService.findListByType(pageResults, obj,type);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ajaxGrid(pageResults);
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping(params = "addLaw")
	@ResponseBody
	public ModelAndView addLaw(){
		ModelAndView md = new ModelAndView();
		md.setViewName("support/law/addLaw");
		return md;
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request,String id) {
		Json j = new Json();
		try {
			Law obj = getObject(Law.class, "obj", request);
			String lawName = obj.getLawName();
			//保存之前，查询名称是否已经存在
			Law law = lawService.findByName(lawName,id);
			if(law == null){
				lawService.save(obj);
				j.setSuccess(true);
				j.setMsg("添加成功!");
			}else{
				j.setSuccess(false);
				j.setMsg("法律名称已存在!");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
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
			lawService.deleteById(ids);
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
	 * 跳转修改页面
	 * 
	 */
	@RequestMapping(params="updateView")
	@ResponseBody
	public ModelAndView updateView(String id){
		ModelAndView md = new ModelAndView();
		Law law = lawService.findById(id);
		md.addObject("law",law);
		md.setViewName("support/law/updateView");
		return md;
	}
	
	/**
	 * 编辑
	 * 
	 */
	@RequestMapping(params = "update")
	@ResponseBody
	public Json update(HttpServletRequest request,String id) {
		Json j = new Json();
		try {
			Law obj = getObject(Law.class, "obj", request);
			String lawName = obj.getLawName();
			//保存之前，查询名称是否已经存在
			Law law = lawService.findByName(lawName,id);
			if(law == null){
				lawService.update(obj);
				j.setSuccess(true);
				j.setMsg("修改成功!");
			}else{
				j.setSuccess(false);
				j.setMsg("法律名称已存在!");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	

}
