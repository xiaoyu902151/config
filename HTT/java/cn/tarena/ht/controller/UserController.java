package cn.tarena.ht.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserInfoService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoleService RoleService;
	
	
	//查询用户列表信息
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<User> userList = userService.findAll();
		
		model.addAttribute("userList", userList);
		
		
		return "/sysadmin/user/jUserList";
	}
	
	
	/**@RequestParam
	 * value="userId" 页面提交的名称
	 * defaultValue   如果页面没有传递参数,则使用默认值
	 * required=true  页面提交必须传递该参数,否则报错.
	 * 
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteUsers
			(@RequestParam(value="userId",required=true) String[] userIds){
		
		userService.deleteUsers(userIds);

		//页面跳转到用户列表页面 使用重定向  
		return "redirect:/sysadmin/user/list";
	}
	
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 1;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}
	
	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="userId",required=true)String[] userIds){
		int state = 0;
		userService.updateState(userIds,state);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	//转向用户新增页面
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		//1.实现页面跳转
		//2.准备数据
		List<Dept> deptList = deptService.findParentList();
		
		List<UserInfo> managerList = userInfoService.findManagerList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		
		
		return "/sysadmin/user/jUserCreate";
	}
	
	@RequestMapping("/save")
	public String saveUser(User user){
		
		userService.saveUser(user);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	//用户的修改     必须传递userId否则会报错
	@RequestMapping("/toupdate")
	public String toUpdateUser(@RequestParam(required=true)String userId,Model model){
		//根据userId查询用户的全部信息,用于数据回显
		User user = userService.findUserById(userId);
		
		
		//部门列表信息
		List<Dept> deptList = deptService.findParentList();
		
		//查询上级领导列表信息
		List<UserInfo> managerList = userInfoService.findManagerList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		model.addAttribute("user", user);
		
		return "/sysadmin/user/jUserUpdate";
	}
	
	@RequestMapping("/update")
	public String updateUser(User user){
		
		userService.updateUser(user);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	/**
	 * 为用户转向角色分配页面
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/toRole")
	public String toRole(String userId,Model model) throws JsonProcessingException{
		
		//1.根据userId查询roleList
		List<String> uRoleIdList = RoleService.findRoleListByUserId(userId); 
		
		//2.准备角色信息list
		List<Role> roleList = RoleService.findAll();
		
		
		//3.做回显判断
		for (Role role : roleList) {
			//每次遍历的是全部的role对象
			if(uRoleIdList.contains(role.getRoleId())){
				role.setChecked(true);
			}
		}
		
		
		//自动转化为JSON串
		ObjectMapper objectMapper = new ObjectMapper();
		
		//通过调用对象的get方法获取属性名称和属性值,才能转化为JSON串
		String zTreeJSON = objectMapper.writeValueAsString(roleList);
		
		//System.out.println(zTreeJSON);
		
		model.addAttribute("zTreeJSON", zTreeJSON);
		
		//通过隐藏域的形式传递userId
		model.addAttribute("userId", userId);
		return "/sysadmin/user/jUserRole";
	}
	
	
	//保存用户和角色信息   roleIds=1,2,3,4,5
	@RequestMapping("saveUserRole")
	public String saveUserRole(String userId,String[] roleIds){
		
		userService.saveUserRole(userId,roleIds);
		
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	
	
}
