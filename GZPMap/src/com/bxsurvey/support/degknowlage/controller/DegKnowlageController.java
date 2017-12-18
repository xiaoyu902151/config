package com.bxsurvey.support.degknowlage.controller;

import java.util.Date;
import java.util.List;

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

import com.bxsurvey.comd.ComdExpert.controller.ComdExpertController;
import com.bxsurvey.comd.ComdExpert.model.ComdExpert;
import com.bxsurvey.comd.ComdExpert.service.ComdExpertServiceI;
import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.bxsurvey.support.degknowlage.service.DangerKnowlageServiceI;
import com.bxsurvey.support.law.model.Law;

@Controller
@RequestMapping("/support/degKnowlage")
public class DegKnowlageController  extends BaseController  {
	private static Logger log = Logger.getLogger(DegKnowlageController.class);
	@Autowired
	private DangerKnowlageServiceI dangerKnowlageService;//调度
	
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String id){
		ModelAndView md = new ModelAndView();
		DangerKnowlage dangerKnowlage = dangerKnowlageService.findById(id);
		md.addObject("dangerKnowlage",dangerKnowlage);
		md.setViewName("support/degknowlage/updateView");
		
		return md;
	}
	
	@RequestMapping(params = "browseView")
	@ResponseBody
	public ModelAndView browseView(String id){
		ModelAndView md = new ModelAndView();
		DangerKnowlage dangerKnowlage = dangerKnowlageService.findById(id);
		md.addObject("dangerKnowlage",dangerKnowlage);
		md.setViewName("support/degknowlage/browseView");
		return md;
	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("support/degknowlage/addView");
		return md;
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
		String orderBy = sortName == null  ? "id":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<DangerKnowlage> pageResults = new PageResults<DangerKnowlage>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);
		try {
			DangerKnowlage obj = getObject(DangerKnowlage.class, "obj", request);
			pageResults = dangerKnowlageService.getListForJson(pageResults,obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ajaxGrid(pageResults);
	}
	
	
	/*
	 * 添加
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request, String afs,String id) {
		Json j = new Json();
		try {
			DangerKnowlage obj = getObject(DangerKnowlage.class, "obj", request);
			String chName = obj.getChName();
			String enName = obj.getEnName();
			String hwbh = obj.getHwbh();
			DangerKnowlage dangerKnowlage1 = dangerKnowlageService.findByChName(chName,id);
			//中文名不存在
			if(dangerKnowlage1 == null){
				DangerKnowlage dangerKnowlage2 = dangerKnowlageService.findEnName(enName,id);
				//英文名不存在
				if(dangerKnowlage2 == null){
					DangerKnowlage dangerKnowlage3 = dangerKnowlageService.findByHwbh(hwbh,id);
					//货物编号不存在
					if(dangerKnowlage3 == null){
						dangerKnowlageService.save(obj,afs);
						//0代表保存成功
						j.setState(0);
						j.setMsg("添加成功!");
					}else{
						//3代表货物编号已存在
						j.setState(3);
						j.setMsg("货物编号已存在!");
					}
				}else{
					//2代表英文名已存在
					j.setState(2);
					j.setMsg("英文名已存在!");
				}
			}else{
				//1代表中文名已存在
				j.setState(1);
				j.setMsg("中文名已存在!");
			}
			
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
	public Json update(HttpServletRequest request, String afs, String dfs,String id) {
		Json j = new Json();
		try {
			DangerKnowlage obj = getObject(DangerKnowlage.class, "obj", request);
			String chName = obj.getChName();
			String enName = obj.getEnName();
			String hwbh = obj.getHwbh();
			DangerKnowlage dangerKnowlage1 = dangerKnowlageService.findByChName(chName,id);
			//中文名不存在
			if(dangerKnowlage1 == null){
				DangerKnowlage dangerKnowlage2 = dangerKnowlageService.findEnName(enName,id);
				//英文名不存在
				if(dangerKnowlage2 == null){
					DangerKnowlage dangerKnowlage3 = dangerKnowlageService.findByHwbh(hwbh,id);
					//货物编号不存在
					if(dangerKnowlage3 == null){
						dangerKnowlageService.update(obj,afs,dfs);
						//0代表保存成功
						j.setState(0);
						j.setMsg("修改成功!");
					}else{
						//3代表货物编号已存在
						j.setState(3);
						j.setMsg("货物编号已存在!");
					}
				}else{
					//2代表英文名已存在
					j.setState(2);
					j.setMsg("英文名已存在!");
				}
			}else{
				//1代表中文名已存在
				j.setState(1);
				j.setMsg("中文名已存在!");
			}
			
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
			dangerKnowlageService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	

	@RequestMapping(params = "getAffixByKnowlageId")
	@ResponseBody
	public Json getAffixByKnowlageId(HttpServletRequest request,String knowlageId) {
		Json j = new Json();
		try {
			List list =dangerKnowlageService.findAffixByKnowlageId(knowlageId);
			j.setSuccess(true);
			j.setMsg("删除成功!");
			j.setObj(list);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

}
