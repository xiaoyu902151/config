package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestFulController {
	
	/**
	 * restFul结构参数传递是通过"/"分割
	 * @return
	 * url:restFul/tom/18/男/亦庄   restful结构只传递有效数据
	 * get请求
	 * url:restFul?name=tom&age=18&sex=男&addr=亦庄
	 */
	
	
	@RequestMapping("/restFul/{name}/{age}")
	public String restFul(@PathVariable String name,@PathVariable int age){
		
		System.out.println(name+":"+age);
		
		return null;
	}
}
