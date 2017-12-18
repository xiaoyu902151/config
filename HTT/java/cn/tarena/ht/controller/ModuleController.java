package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;

@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		
		return "/sysadmin/module/jModuleList";
		
	}
	
	//模块信息的删除
	@RequestMapping("/delete")
	public String toDelete(@RequestParam( value="moduleId",required=true) String[] moduleIds){
		
		
		moduleService.deleteModules(moduleIds);
		
		//返回模块列表页面
		return "redirect:/sysadmin/module/list";
	}
	
	
	//状态停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam("moduleId")String[] moduleIds){
		
		int state = 0;
		
		moduleService.updateState(moduleIds,state);
		//页面跳转到列表页面
		return "redirect:/sysadmin/module/list";
	}
	
	
	//状态启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("moduleId")String[] moduleIds){
		
		int state = 1;
		
		moduleService.updateState(moduleIds,state);
		//页面跳转到列表页面
		return "redirect:/sysadmin/module/list";
	}
	
	
	//模块的新增
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		
		//准备上级模块信息
		List<Module> moduleList = moduleService.findAll();
		
		model.addAttribute("moduleList", moduleList);
		
		return "/sysadmin/module/jModuleCreate";
	}
	
	
	//模块新增
	@RequestMapping("/save")
	public String save(Module module){
		
		moduleService.saveModule(module);
		
		return "redirect:/sysadmin/module/list";
	}
	
	
}
