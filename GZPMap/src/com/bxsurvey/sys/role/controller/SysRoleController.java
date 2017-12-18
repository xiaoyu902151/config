package com.bxsurvey.sys.role.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.framework.httpModel.Json;
import net.framework.httpModel.PageResults;
import net.framework.httpModel.Tree;
import net.framework.utils.ExceptionUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.sys.role.model.SysRole;
import com.bxsurvey.sys.role.service.SysRoleServiceI;
import com.bxsurvey.sys.user.controller.SysUserController;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.sys.user.model.SysUserRole;
import com.bxsurvey.sys.user.service.SysUserServiceI;

/**
 * 角色view控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/role")
public class SysRoleController extends BaseController {
	private static Logger logger = Logger.getLogger(SysUserController.class);
	@Autowired
	private SysRoleServiceI sysRoleService;
	
	@Autowired
	private SysUserServiceI sysUserService;
	
	@RequestMapping(params="save")
	@ResponseBody
	public Json save(HttpServletRequest request, String rs) {
		Json j = new Json();
		
		try {
			SysRole sysRole = getObject(SysRole.class, "sysRole", request);
			SysUser sysUser = (SysUser)request.getSession().getAttribute("userBO");
			
			//根据用户的id获取角色
			//Integer roleId = sysRoleService.getRoleByUserId(sysUser.getUserId()).getRoleId();
			//根据角色角色的id给添加的角色赋值给Pid
			//sysRole.setRolePid(roleId);
			sysRole.setSysUser(sysUser);
			sysRoleService.save(sysRole, rs);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("添加失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
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
	public Json update(HttpServletRequest request, String rs) {
		Json j = new Json();
		try {
			SysRole sysRole = getObject(SysRole.class, "sysRole", request);
            SysUser sysUser = (SysUser)request.getSession().getAttribute("userBO");
			sysRole.setSysUser(sysUser);
			
			//System.out.println("sysRoleController sysRole:" + sysRole);

			sysRoleService.update(sysRole, rs);
			j.setSuccess(true);
			j.setMsg("修改成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("修改失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
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
	public Json deleteBxSysRole(String ids) {
		Json j = new Json();
		try {
			sysRoleService.deleteById(ids);
			j.setSuccess(true);
			j.setMsg("删除成功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping(params="searchGrid")
	@ResponseBody
	public JSONObject searchGrid(String pageNumber, String rowNumber,String sortName,String sortOrder, HttpServletRequest request) {
		int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
		int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
		String orderBy = sortName == null  ? "regDate":sortName;
		String order = sortOrder == null  ? PageResults.ASC:sortOrder;

		PageResults<SysRole> pageResults = new PageResults<SysRole>();
		pageResults.setPageNo(pageNo);
		pageResults.setPageSize(pageSize);
		pageResults.setOrderBy(orderBy);
		pageResults.setOrder(order);

		try {
			SysRole sysRole = getObject(SysRole.class, "sysRole", request);
			//sysRole.setRoleId(((SysRole) request.getSession().getAttribute("sysRole")).getRoleId());
			sysRole.setSysUser((SysUser) request.getSession().getAttribute("userBO"));
			pageResults = sysRoleService.getListForJson(pageResults, sysRole);
		} catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject ajaxGrid = ajaxGrid(pageResults);
		System.out.println("ajaxGrid : " + ajaxGrid);
		return ajaxGrid;
	}
	
	/**
	 * 
	 * Title: getRoleResourceList
	 * Description: 加载资源列表
	 * Created On: 2014-12-1 下午4:03:38
	 * @author ldw 
	 * @return
	 */
	@RequestMapping(params="getRoleResourceList")
	@ResponseBody
	public Json getRoleResourceList(String roleid) {
		Json j = new Json();
		try {
			List<Tree> nodes = sysRoleService.findRoleResourceTree(roleid);
			JSONObject ret = renderJson(nodes);
			j.setObj(ret);
			j.setSuccess(true);
			j.setMsg("加载成功!");
			logger.info("加载角色ID为的权限资源列表：" + j.toString());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("加载失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	
	
	
	@RequestMapping(params="getAllTree")
	@ResponseBody
	public Json getAllTree() {
		
		Json j = new Json();
		try {
			List<Tree> nodes = sysRoleService.findAllTree();
			JSONObject ret = renderJson(nodes);
			j.setObj(ret);
			j.setSuccess(true);
			j.setMsg("加载成功!");
			logger.info("加载所有资源列表：" + j.toString());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("加载失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	@RequestMapping(params="getOwerTreeByRoleId")
	@ResponseBody
	public Json getOwerTreeByRoleId() {
		
		//SysUser sysUser = (SysUser)request.getSession().getAttribute("userBO");
		
		//List<SysUserRole> userRole = sysUserService.getRoleIdByUserId(sysUser.getUserId());
		List<SysUserRole> userRole = sysUserService.getRoleIdByUserId(43);
		Integer roleId = userRole.get(0).getId().getRoleId();
		
		
		//根据用户查询用户角色表
		
		//查询出角色id（一个或多个）
		
		//根据角色加载数资源（重复的要排除）
		
		Json j = new Json();
		try {
			List<Tree> nodes = sysRoleService.findOwerTreeByRoleId(roleId);
			JSONObject ret = renderJson(nodes);
			j.setObj(ret);
			j.setSuccess(true);
			j.setMsg("加载成功!");
			logger.info("加载所有资源列表：" + j.toString());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("加载失败!");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}
	
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String roleId){
		ModelAndView md = new ModelAndView();
		SysRole sysRole = sysRoleService.findById(roleId);
		md.addObject("sysRole",sysRole);
		md.setViewName("admin/role/updateView");
		return md;
	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("admin/role/addView");
		return md;
	}
}

