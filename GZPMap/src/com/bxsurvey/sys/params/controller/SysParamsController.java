/**  
 * @Title: SysParamsController.java
 * @Package com.bxsurvey.controller.sys
 * @Description: TODO
 * @author yokoboy
 * @date 2016-1-13
 */
package com.bxsurvey.sys.params.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.Tree;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.support.law.model.Law;
import com.bxsurvey.support.plan.controller.PlanController;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.params.model.SysParams;
import com.bxsurvey.sys.params.model.SysParamsTest;
import com.bxsurvey.sys.params.model.SysParamsType;
import com.bxsurvey.sys.params.service.SysParamsServiceI;


/**
 * ClassName: SysParamsController 
 * @Description: TODO
 * @author yokoboy
 * @date 2016-1-13
 */
@Controller
@RequestMapping(value="/sysParams")
public class SysParamsController extends BaseController{
	private static Logger log = Logger.getLogger(SysParamsController.class);
	
   @Resource
  private SysParamsServiceI sysParamsService;
   
   @RequestMapping(value="findListByType")
   @ResponseBody
   public List<SysParams> findListByType(String type){
	   List<SysParams> list = sysParamsService.findListByType(type);
	   return list;	   
   }
   
	@RequestMapping(params = "getDictionaryByType")
	@ResponseBody
	public JSONObject getDictionaryByType(String type,HttpServletRequest request) {
		List<SysParams> list =sysParamsService.findListByType(type);
		return renderJson(list);
	}
	
	/**
	 * 通过类型查找数据，并排序
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDictionaryByTypeOrder")
	@ResponseBody
	public JSONObject getDictionaryByTypeOrder(String type,HttpServletRequest request) {
		List<SysParams> list =sysParamsService.getDictionaryByTypeOrder(type);
		return renderJson(list);
	}
	
	/**
	 * 查询数据字典
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getParamsType")
	@ResponseBody
	public Json getParamsType(HttpServletRequest request) {
		Json j = new Json();
		try {
			List<Tree> nodes = sysParamsService.getParamsType();
			JSONObject ret = renderJson(nodes);
			j.setSuccess(true);
			j.setMsg("查询成功!");
			j.setObj(ret);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("查询失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	/**
	 * 根据节点id查询对应的数据
	 */
	@RequestMapping(params = "getParamsList")
	@ResponseBody
	public Json getParamsList(HttpServletRequest request,String id) {
		Json j = new Json();
		try {
			List<SysParamsTest> list = sysParamsService.getParamsList(id);
			j.setObj(list);
			j.setSuccess(true);
			j.setMsg("查询成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("查询失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Json save(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysParamsTest obj = getObject(SysParamsTest.class, "obj", request);
			sysParamsService.save(obj);
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
	 * 添加参数类型
	 */
	@RequestMapping(params = "saveParamsType")
	@ResponseBody
	public Json saveParamsType(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysParamsType obj = getObject(SysParamsType.class, "obj", request);
			sysParamsService.saveParamsType(obj);
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
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping(params = "dataAdd")
	@ResponseBody
	public ModelAndView dataAdd(HttpServletRequest request){
		String paramsType = request.getParameter("paramsType");
		String pid = request.getParameter("pid");
		ModelAndView md = new ModelAndView();
		md.addObject("paramsType", paramsType);
		md.addObject("pid", pid);
		md.setViewName("admin/dictionary/dataAdd");
		return md;
	}
	
	/**
	 * 跳转修改页面
	 * @return
	 */
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(HttpServletRequest request,String id){
		SysParamsTest sysParamsTest = sysParamsService.getById(id);
		ModelAndView md = new ModelAndView();
		md.addObject("sysParamsTest", sysParamsTest);
		md.setViewName("admin/dictionary/dataUpdate");
		return md;
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(params="update")
	@ResponseBody
	public Json update(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysParamsTest obj = getObject(SysParamsTest.class, "obj", request);
			sysParamsService.update(obj);
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
	 * 编辑参数类型
	 */
	@RequestMapping(params="updateParamsType")
	@ResponseBody
	public Json updateParamsType(HttpServletRequest request) {
		Json j = new Json();
		try {
			SysParamsType obj = getObject(SysParamsType.class, "obj", request);
			sysParamsService.updateParamsType(obj);
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
	 */
	@RequestMapping(params="delete")
	@ResponseBody
	public Json delete(String ids) {
		Json j = new Json();
		try {
			sysParamsService.deleteById(ids);
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
	 * 删除参数类型
	 */
	@RequestMapping(params="deleteParamsType")
	@ResponseBody
	public Json deleteParamsType(String id) {
		Json j = new Json();
		try {
			sysParamsService.deleteParamsType(id);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			log.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	
}
