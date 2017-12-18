package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//页面发送的请求  url:home.action
	//转向欢迎页面
	@RequestMapping("/home")
	public String home(){
		
		//必须经过视图解析器
		return "/home/fmain";
	}
	
	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title(){
		return "/home/title";
	}
	
	//转向home的左侧页面
	/*@RequestMapping("/homeLeft")
	public String homeLeft(){
		return "/home/left";
	}
	
	//转向home的操作页面
	@RequestMapping("/homeMain")
	public String homeMain(){
		return "/home/main";
	}*/
	
	/*@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/sysadmin/main";
	}*/
	
	//通用的left和main
	@RequestMapping("/{Module}/Left")
	public String sysadminLeft(@PathVariable String Module){
		
		return "/"+Module+"/left";
	}
	
	@RequestMapping("/{Module}/Main")
	public String sysadminMain(@PathVariable String Module){
		return "/"+Module+"/main";
	}
	
	
}
