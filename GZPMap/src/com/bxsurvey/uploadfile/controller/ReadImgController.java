package com.bxsurvey.uploadfile.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.framework.httpModel.Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxsurvey.sys.user.controller.SysUserController;
import com.bxsurvey.sys.user.model.SysUser;
import com.bxsurvey.uploadfile.ReadImg;


@Controller
@RequestMapping("/readimg")
public class ReadImgController {
	private static final Logger logger = LoggerFactory.getLogger(ReadImgController.class);

	@Autowired
	private ReadImg readImg;
	
	@RequestMapping(params = "getFileListByUser")
	@ResponseBody
	public Json getFileListByUser(HttpServletRequest request,String userId,String geoId) 
	{
		Json j = new Json();
		HttpSession session = request.getSession();
		try{
			List<String> files = readImg.readfile(userId,geoId);
			j.setSuccess(true);
			j.setObj(files);
		}catch(Exception e){
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
