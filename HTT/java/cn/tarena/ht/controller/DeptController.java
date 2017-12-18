package cn.tarena.ht.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Dept> deptList = deptService.findAll();
		model.addAttribute("deptList", deptList);
		
		//返回部门列表页面
		return "/sysadmin/dept/jDeptList";
		
	}
	
	
	//部门删除   
	@RequestMapping("/delete")
	public String toDelete(String[] deptId){
		System.out.println(Arrays.toString(deptId));
		
		deptService.deleteDepts(deptId);
		
		//重定向到部门列表页面  重新发起部门列表请求
		return "redirect:/sysadmin/dept/list";
		
	}
	
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(String[] deptId){
		int state = 1; //1表示启用
		
		deptService.updateState(deptId,state);
		
		return "redirect:/sysadmin/dept/list";
	}
	
	//状态的停用
	@RequestMapping("/stop")
	public String toStop(String[] deptId){
		int state = 0; //1表示启用
		
		deptService.updateState(deptId,state);
		
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//跳转到部门新增页面
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		//准备上级领导数据
		List<Dept> parentList = deptService.findParentList();
		model.addAttribute("parentList", parentList);
		
		return "/sysadmin/dept/jDeptCreate";
	}
	
	
	//部门保存
	@RequestMapping("/save")
	public String toSave(Dept dept){
		
		deptService.saveDept(dept);
		
		//重定向到部门列表页面
		return "redirect:/sysadmin/dept/list";
		
	}
	
	//部门的查看
	@RequestMapping("/toview")
	public String toView(String deptId,Model model){
		
		Dept dept =deptService.findDeptById(deptId);
		model.addAttribute("dept", dept);
		
		return "/sysadmin/dept/jDeptView";//跳转到部门查看页面
	}
	
	//部门的修改页面  springmvc会自动的拼接"/"
	@RequestMapping("/toupdate")
	public String toUpdate(String deptId,Model model){
		
		//准备修改修改的数据
		Dept dept = deptService.findDeptById(deptId);
		
		//准备上级部门列表信息 排除自己之外的数据
		List<Dept> parentList = deptService.findParentRemove(deptId);
		
		model.addAttribute("dept", dept);
		model.addAttribute("parentList", parentList);
		
		return "/sysadmin/dept/jDeptUpdate";
	}
	
	
	@RequestMapping("/update")
	public String updateDept(Dept dept){
		
		deptService.updateDept(dept);
		
		return "redirect:/sysadmin/dept/list";
	}
	
}
