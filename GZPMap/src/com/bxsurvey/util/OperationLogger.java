package com.bxsurvey.util;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.framework.base.dao.BaseDao;
import net.framework.base.dao.IBaseDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bxsurvey.sys.user.model.SysLog;
import com.bxsurvey.sys.user.model.SysUser;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
@Aspect
public class OperationLogger {
	private static Logger log = Logger.getLogger(OperationLogger.class);
	@Resource
	private IBaseDao baseDao;
	@Autowired(required=false)
	private HttpServletRequest request;
	SysLog sysLog=new SysLog();
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;
	static {
		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//环绕通知     记录登录、删除、新增和更新操作，在service实现类设置自定义注解，service的方法第一个参数要放要操作的实体类或者要删除的ids
	@Around(value = "execution(* com.bxsurvey.login.service.impl.LoginService.findUserByName(..))&&@annotation(operateMsg) " +
			" or execution(* com.bxsurvey.sys.user.service..*Service*.save*(..))&&@annotation(operateMsg)" +
			" or execution(* com.bxsurvey.sys.user.service..*Service*.delete*(..))&&@annotation(operateMsg) " +
			" or execution(* com.bxsurvey.sys.user.service..*Service*.update*(..))&&@annotation(operateMsg) ")
	public Object   operateAfter(ProceedingJoinPoint  point,OperateAnno operateMsg){
		Object returnValue=null;
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
		if(point.getSignature().getName().indexOf("delete")!=-1){//判断是删除操作
		  String idsStr=(String) point.getArgs()[0];
		  String[] ids=idsStr.split(",");
		  for (int i=0;i<ids.length;i++) {
			  SysUser u= (SysUser) baseDao.get(operateMsg.operateClass(), Integer.parseInt(ids[i]));
			  dataMap.put("实体"+i+operateMsg.operateClass().getName(), u);
			}
		}
		returnValue = point.proceed();//执行service的方法
		
		
		JSONObject json = null;
		String jsonString = null;
		HttpSession session = request.getSession();
		SysUser user =null;
        if(point.getSignature().getName().indexOf("delete")!=-1){//判断是删除操作
        	user = (SysUser) session.getAttribute("userBO");//获取用户对象
		}else if("findUserByName".equalsIgnoreCase(point.getSignature().getName())){
			user = (SysUser) returnValue;
			dataMap.put(operateMsg.operateClass().getName(), user);
		}else{
			user = (SysUser) session.getAttribute("userBO");//获取用户对象
			dataMap.put(operateMsg.operateClass().getName(), point.getArgs()[0]);
		}
		jsonString = objectMapper.writeValueAsString(dataMap);
		jsonString = jsonString.replaceAll("null", "\"\"");
		json = JSONObject.fromObject(jsonString);
		
		sysLog.setOperateTime(new Date());
		sysLog.setOperatorIp(request.getRemoteAddr());
		sysLog.setUser(user);
		sysLog.setOperateName(operateMsg.operateName());
		sysLog.setDescription(json.toString());
		baseDao.save(sysLog);
		log.info("执行的方法："+point.getSignature().getName());
		
		} catch (Throwable e) {
			e.printStackTrace();
			try {
				returnValue = point.proceed();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			return returnValue;
			
		}
		return returnValue;
	}
}
