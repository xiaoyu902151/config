package net.framework.utils.json;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.framework.httpModel.Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 * @author chenqc
 * 
 */


public class JsonUtil {
	
	/**
	 * 格式化JSON
	 * [{"name":null}] --> [{"name":""}]
	 * 避免 value出现        \"""\""
	 * */
	public static String formatJsonStr(String jsonStr) {
		return jsonStr.replace("null", "\"\"").replace("\\\"\"\"\\\"\"", "\"");
	}
	
	public static String htmlWordFilter(String jsonStr){
		return jsonStr.replace("\\/", "/");
	}
	
	public static String formatNullJson(String jsonStr) {
		return jsonStr.replace("null", "\"\"");
	}
	/**
	 * 把对象序列化成JSONObject
	 * @param bean
	 * @return 
	 * @throws  
	 */
	public static JSONObject bean2JSONObject(Object bean) {
		
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			JsonGenerator jsonGenerator= objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String jsonStr=null;
		try {
			jsonStr = objectMapper.writeValueAsString(bean);
			jsonStr = JsonUtil.formatJsonStr(jsonStr);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonStr);
	}

	
	public static JSONArray toJSONArray(List list) {
		if(list==null){
			return JSONArray.fromObject("[]");
		}
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			JsonGenerator jsonGenerator= objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		JSONArray json = null;
		try {
			String jsonString = objectMapper.writeValueAsString(list);
			jsonString = formatNullJson(jsonString);
			json = JSONArray.fromObject(jsonString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// destory();
		}

		return json;
	}
	
	

	
	public static JSONArray HTMLElement2JSONArray(List list) {
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			JsonGenerator jsonGenerator= objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		JSONArray json = null;
		try {
			String jsonString = objectMapper.writeValueAsString(list);
			jsonString = formatJsonStr(jsonString);
			jsonString = htmlWordFilter(jsonString);
			json = JSONArray.fromObject(jsonString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// destory();
		}

		return json;
	}
	
	
	// test
	public static void main(String[] args) {
		/*
		Json j = new Json();
		j.setMsg("111");
		j.setSuccess(true);
		System.out.println(JsonUtil.entity2JSONObject(j));
		*/
		/*
		BxSysUser user=new BxSysUser();
		user.setBirthday(new Date());
		System.out.println(bean2JSONObject(user));
		List<BxSysUser> l=new ArrayList<BxSysUser>();
		l.add(user);
		l.add(user);
		l.add(user);
		System.out.println(JsonUtil.toJSONArray(l));
		*/
		//String j="[{\"id\":\"zjg\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-chart\",\"checked\":false,\"children\":[{\"id\":\"mt1\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-chart\",\"checked\":false,\"children\":[{\"id\":\"bw1\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-bowei\",\"checked\":false,\"children\":\"\",\"text\":\"<a href='#' >泊位1<\/a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='flyTo(101);'><font color='red'>定位<\/font>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='showBwInfo(101)';><font color='red'>详情<\/a>\"},{\"id\":\"bw2\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-bowei\",\"checked\":false,\"children\":\"\",\"text\":\"<a href='#' >泊位2<\/a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\\"javascript:void(0);\\" onclick='flyTo(101);'><font color='red'>定位<\/font>&nbsp;&nbsp;<a href=\\"javascript:void(0);\\" onclick='showBwInfo(101)';><font color='red'>详情<\/a>\"}],\"text\":\"<a href='#'' >码头1(<span  style='color:red;'>2<\/span>) <\/a>\"},{\"id\":\"mt2\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-chart\",\"checked\":false,\"children\":[{\"id\":\"bw3\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-bowei\",\"checked\":false,\"children\":\"\",\"text\":\"<a href='#' >泊位名称3<\/a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\\"javascript:void(0);\\" onclick='flyTo(101);'><font color='red'>定位<\/font>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='showBwInfo(101)';><font color='red'>详情<\/a>\"},{\"id\":\"bw4\",\"state\":\"open\",\"attributes\":\"\",\"iconCls\":\"icon-large-bowei\",\"checked\":false,\"children\":\"\",\"text\":\"<a href='#' >泊位4<\/a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\\"javascript:void(0);\\" onclick='flyTo(101);'><font color='red'>定位<\/font>&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick='showBwInfo(101)';><font color='red'>详情<\/a>\"}],\"text\":\"<a href='#'' >码头2(<span  style='color:red;'>2<\/span>) <\/a>\"}],\"text\":\"湛江港(<span  style='color:red;'>2<\/span>)\"}]";
		
	}

	
	public static Json GenerateJson(Boolean isSuccess, String msg) {
		Json json = new Json();
		json.setMsg(msg);
		json.setSuccess(isSuccess);
		return json;
	}
	
	
	public static Json GenerateJson(Boolean isSuccess, String msg,Object obj,Integer state) {
		Json json=GenerateJson(isSuccess,msg);
		json.setObj(obj);
		json.setState(state);
		return json;
	}
	
	public  static Json GenerateSuccessJson() {
		return GenerateJson(true,"操作成功!",null,null);
	}
	
	public  static Json GenerateSuccessJson(Object obj) {
		return GenerateJson(true,"操作成功!",obj,null);
	}
	
	public  static Json GenerateSuccessJson(Object obj,Integer state) {
		return GenerateJson(true,"操作成功!",obj,state);
	}
	
	
	
	public  static Json GenerateSuccessJson(String msg,Object obj) {
		return GenerateJson(true,msg,obj,null);
	}
	
	public  static Json GenerateSuccessJson(String msg) {
		return GenerateJson(true,msg);
	}
	
	public  static Json GenerateFaidJson( String msg) {
		return GenerateJson(false,msg);
	}
	
	public  static Json GenerateFaidJson( String msg,Integer state) {
		return GenerateJson(false,msg,null,state);
	}
	
	public  static Json GenerateFaidJson(String msg,Object obj) {
		return GenerateJson(false,msg,obj,null);
	}

	public  static Json GenerateFaidJson() {
		return GenerateJson(false,"操作失败");
	}
	
	
}
