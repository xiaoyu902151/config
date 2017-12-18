package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
@RequestMapping("/sysadmin/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		
		return "/sysadmin/role/jRoleList";
	}
	
	@RequestMapping("/tocreate")
	public String toCreate(){
		
		//跳转到角色新增页面
		return "/sysadmin/role/jRoleCreate";
	}
	
	@RequestMapping("/save")
	public String saveRole(Role role){
		
		roleService.saveRole(role);
		
		//返回角色列表页面
		return "redirect:/sysadmin/role/list";
		
	}
	
	
	//为角色分配模块信息
	@RequestMapping("/roleModule")
	public String roleModule(String roleId,Model model) throws JsonProcessingException{
		
		//1.根据roleId 查询全部的模块信息   角色所管理的模块信息
		List<String> rModuleList = roleService.findRoleModuleList(roleId);
		
		
		//2.查询全部模块列表信息
		List<Module>  moduleList = moduleService.findAll();
		
		
		//3.判断moduleId是否一致
		for (Module module : moduleList) {
			
			if(rModuleList.contains(module.getModuleId())){
				module.setChecked(true);
			}
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		//zTree的json串有要求  id name pId checked
		String zTreeJSON = objectMapper.writeValueAsString(moduleList);
		System.out.println(zTreeJSON);
		
		model.addAttribute("zTreeJSON", zTreeJSON);
		model.addAttribute("roleId", roleId);
		return "/sysadmin/role/jRoleModule";
	}
	
	
	
	//角色和模块的关联保存
	@RequestMapping("/saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		
		
		roleService.saveRoleModule(roleId,moduleIds);
		
		return "redirect:/sysadmin/role/list";
	}
	
	
	
	
	
	
	
	
	
}
