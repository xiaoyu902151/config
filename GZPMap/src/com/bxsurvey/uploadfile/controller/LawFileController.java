package com.bxsurvey.uploadfile.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.framework.httpModel.Json;
import net.framework.utils.FTPUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/lawFile")
public class LawFileController {
	
	private static final Logger logger = LoggerFactory.getLogger(LawFileController.class);
    /** 通过注入获取property的值，用户名称 */
    @Value("#{setting[ipAddress]}")
    private String ipAddress;
    @Value("#{setting[loginUser]}")
    private String loginUser;
    @Value("#{setting[psd]}")
    private String psd;
    @Value("#{setting[port]}")
    private int port;
    @Value("#{setting[path]}")
    private String path;
    
	@RequestMapping(params = "uploadFile")
	@ResponseBody
	public Json uploadFile(@RequestParam MultipartFile file,String remotePath,HttpServletRequest request) 
	{
		String savePath = (remotePath == null || remotePath =="") ? path:remotePath;
		Json j = new Json();
		Map<String,String> map = new HashMap<String,String>();
		 try {
				FTPUtils ftputils = new FTPUtils();
				InputStream is = file.getInputStream();
				String tempName = new String(file.getOriginalFilename().getBytes("iso-8859-1"),"utf-8");
				int index = tempName.indexOf(".");
				String name = tempName.substring(0,index);
				String fileAfter = tempName.substring(index+1);
				Date dt=new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
				String fileName = name+format.format(dt);
				ftputils.uploadFile(ipAddress,port,loginUser,psd,savePath,fileName+"."+fileAfter,is);
				map.put("path", savePath);
				map.put("name",fileName);
				map.put("fileAfter",fileAfter);
				j.setSuccess(true);
				j.setObj(map);
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    } finally {  

		    } 
		return j;
	}
	
	@RequestMapping(params = "downloadFile")
	@ResponseBody
	public void downloadFile(HttpServletResponse response,String path) throws UnsupportedEncodingException{
		FTPUtils ftputils = new FTPUtils();
		//解决乱码
		String nPath = new String(path.getBytes("iso-8859-1"), "utf-8");
		String newPath = nPath.substring(0, nPath.lastIndexOf("/"));
		String suffix = nPath.substring(nPath.indexOf(".")+1);
		String fileName = nPath.substring(nPath.lastIndexOf("/")+1, nPath.indexOf("."));
		ftputils.downFile(ipAddress,port,loginUser,psd,newPath,fileName+"."+suffix, response);
	}

}






















