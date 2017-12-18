package net.framework.base.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.framework.httpModel.PageResults;
import net.framework.utils.ReflectionUtils;
import net.framework.utils.RequestUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bxsurvey.sys.user.model.SysUser;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 * 
 * @author donic
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
	@Autowired  
	protected HttpServletRequest request; //这里可以获取到request
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	private static final int DEFAULT_BUFF_LENGTH = 1024;
	// 导出生成的临时文件
	public static final String EXPORT_TEMP_FILE = "export_temp_file";

	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 * 
	 */
	// @InitBinder
	// public void initBinder(ServletRequestDataBinder binder) {
	// SimpleDateFormat dateFormat = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// // dateFormat.setLenient(false);
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
	// true));
	// // binder.registerCustomEditor(String.class, new
	// StringTrimmerEditor(false));
	// }
	//
	static {
		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destory() {
		try {
			if (jsonGenerator != null) {
				jsonGenerator.flush();
				jsonGenerator = null;
			}
			if (!jsonGenerator.isClosed()) {
				jsonGenerator.close();
				objectMapper = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param fileLength
	 *            导出文件长度
	 * @param filename
	 *            导出文件名 例如：123.doc
	 * @param ins
	 *            输入流
	 * @param response
	 */
	public void export(Long fileLength, String filename, InputStream ins,
			HttpServletResponse response) {
		OutputStream out = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.reset();
			response.setContentType("application/octet-stream");
			// response.setContentType("application/" + format +
			// ";charset=utf-8");
			response.addHeader("Content-Length", "" + fileLength);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(filename.getBytes(), "ISO-8859-1"));
			// getResponse().setHeader("Content-Disposition",
			// "attachment; filename=" + toUtf8String(filename));
			byte[] buf = new byte[DEFAULT_BUFF_LENGTH];
			int len = 0;
			out = response.getOutputStream();
			while ((len = ins.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(ins);
		}
	}

	/**
	 * 直接返回字符串
	 * 
	 * @param content
	 *            发送的字符串
	 * @param response
	 */
	public void render(String content, String type, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out = response.getWriter();
			out.write(content);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.contains(",")) {
			logger.info("servlet process from client host contain proxy :" + ip);
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}

	/**
	 * 封装datagrid json
	 * 
	 * @param rows
	 * @return
	 */

	public JSONObject datagrid(JSONArray rows) {
		JSONObject josnObject = new JSONObject();
		josnObject.put("total", rows.size());
		josnObject.put("rows", rows);
		return josnObject;
	}

	/**
	 * 将List转换为json字符串并实例化为JSON对象
	 * 
	 * @param page
	 */
	public JSONObject ajaxGrid(PageResults page) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (page == null) {
			dataMap.put("pageSize", 10);
			dataMap.put("page", 1);// 当前页
			dataMap.put("total", 0);// 总计录
			dataMap.put("rows", null);
		} else {
			dataMap.put("pageSize", page.getPageSize());
			dataMap.put("page", page.getPageNo());// 当前页
			dataMap.put("total", page.getTotalCount());// 总计录
			dataMap.put("rows", page.getResult());
		}

		JSONObject json = null;
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(dataMap);
			jsonString = jsonString.replaceAll("null", "\"\"");
			json = JSONObject.fromObject(jsonString);
			
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

	/**
	 * 
	 * Title: renderJson Description: 把list集合转换成json结构 Created On: 2014-12-1
	 * 下午2:01:23
	 * 
	 * @author ldw
	 * @param list
	 * @return
	 */
	public JSONObject renderJson(List list) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		JSONObject json = null;
		try {
			dataMap.put("data", list);
			String jsonString = objectMapper.writeValueAsString(dataMap);
			jsonString = jsonString.replaceAll("null", "\"\"");

			json = JSONObject.fromObject(jsonString);

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

	/**
	 * 
	 * Title: renderJsonForMap Description: 把map数据结构转换成json结构 Created On:
	 * 2014-12-1 下午2:01:02
	 * 
	 * @author ldw
	 * @param map
	 * @return
	 */
	public JSONObject renderJsonForMap(Map map) {
		JSONObject json = null;
		try {
			String jsonString = objectMapper.writeValueAsString(map);
			jsonString = jsonString.replaceAll("null", "\"\"");
			json = JSONObject.fromObject(jsonString);
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

	/**
	 * 
	 * Title: createMap Description: 将查询条件加入到Map中 Created On: 2014-10-16
	 * 上午9:01:19
	 * 
	 * @author ldw
	 * @param array
	 * @param request
	 * @param params
	 * @return
	 */
	public Map<String, Object> createMap(String[] array,
			HttpServletRequest request, Map<String, Object> params) {
		for (int i = 0; i < array.length; i++) {
			String param = request.getParameter(array[i]);
			if (StringUtils.isNotBlank(param)) {
				params.put(array[i], new String("%" + param + "%"));
			}
		}
		return params;
	}

	// 验证List转换为json字符串是否正确
	public static void main(String[] args) throws Exception {
		/*
		 * User user = new User(); user.setAddress("123");
		 * user.setBirthday("564654"); user.setBonus("41646");
		 * 
		 * List<User> list = new ArrayList<User>(); list.add(user); Map<String,
		 * Object> lists = new HashMap<String, Object>(); lists.put("row",
		 * list); lists.put("siz", 10); String s =
		 * objectMapper.writeValueAsString(lists);
		 * 
		 * JSONObject json = JSONObject.fromObject(s);
		 * System.out.println(json.toString());
		 */
		SysUser user = new SysUser();
		user.setBirthday(new Date());
		user.setBz("11test");
		// System.out.println(objectMapper.writeValueAsString(user));
		System.out.println(bean2JSONObject(user));
	}
	
	/**
	 * 
	 * Title: bean2JSONObject
	 * Description: 将某个object转换为json
	 * Created On: 2014-12-17 上午10:04:37
	 * @author ldw 
	 * @param bean
	 * @return
	 */
	public static JSONObject bean2JSONObject(Object bean) {
		String jsonStr = "{}";
		try {
			jsonStr = objectMapper.writeValueAsString(bean);
			jsonStr = jsonStr.replaceAll("null", "\"\"");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonStr);
	}

	/**
	 * 
	 * Title: getObject 
	 * Description: 获取form表单的数据并赋在对象当中，解决前台时间字符串在后台转换为Date问题
	 * Created On: 2014-12-17上午9:55:25
	 * @author ldw
	 * @param claz
	 * @param name
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static <T> T getObject(Class<T> claz, String name, HttpServletRequest request) throws Exception {
		Object object = claz.newInstance();
		Set<String> requests = new HashSet<String>();
		Enumeration<String> rp = request.getParameterNames();
		
		while (rp.hasMoreElements()) {
			
			requests.add(rp.nextElement());
		}
		for (String param : requests) {
			if (param.contains(".")){
				Object value = get(param, request);
				String[] filter = param.split("\\.");
				Field field = claz.getDeclaredField(filter[0]);
				Object subObject =getSunObject(field.getType(),filter,0, value);
				field.setAccessible(true);
				field.set(object,subObject);
			}
			else {
				try {
					Object value = get(param, request);
					if (value != null && !value.equals("")) {
						if (isExistField(param, claz)) {
							Field field = claz.getDeclaredField(param);
							field.setAccessible(true);
							field.set(object, ReflectionUtils.convertValue(value, field.getType()));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (T) object;
	}

	public static Object get(String param, HttpServletRequest request) {
		Object object = request.getAttribute(param);
		if (object == null) {
			object = request.getParameter(param);
		}
		if (object == null) {
			object = request.getSession(true).getAttribute(param);
		}
		return object;
	}
	
	public static Object getSunObject(Class claz,String[] filter,int index,Object value) throws Exception{
		String pFieldName = filter[index];
		String subFieldName = filter[index+1];
		Object object = claz.newInstance();
		Field subField = claz.getDeclaredField(subFieldName);
		subField.setAccessible(true);
		if(index < filter.length-2){
			Object subObject =getSunObject(subField.getType(),filter,index++, value);
			subField.set(object, subObject);
		}else{
			if (value != null && !value.equals(""))
				subField.set(object, ReflectionUtils.convertValue(value, subField.getType()));
		}
		return object;
	}

	// 判断bean中是否有param名
	public static boolean isExistField(String param, Class claz) {
		for (Field field : claz.getDeclaredFields()) {
			if (field.getName().equals(param)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 组装springmvc跳转路径参数
	 * @param url	目标路径
	 * @param suffix 后缀
	 * @return
	 */
	public  String redirect(String url,String suffix){
		if(!"/".equals(url.substring(0,1))){
			url="/"+url;
		}
		suffix=suffix==null||suffix==""?"":"."+suffix;
		return "redirect:"+url+suffix;
	}
	
	/**
	 * 
	 * Title: unifyInterface
	 * Description: 获取post方法传过来的json字符串
	 * Created On: 2015-4-2 上午9:26:17
	 * @author ldw 
	 * @param request
	 * @return
	 */
	public JSONObject unifyInterface(HttpServletRequest request) {
		final int FILE_SIZE = 4*1024;
		byte[] attr = new byte[FILE_SIZE];
		ServletInputStream sis = null;
		ByteArrayOutputStream baos = null;
		String result = "";
		try {
			sis = request.getInputStream();
			if(sis != null) {
				baos = new ByteArrayOutputStream();
				int len = 0;
				while((len = sis.read(attr)) != 0) {
					baos.write(attr, 0, len);
				}
				result = baos.toString("UTF-8");
			} else {
				throw new RuntimeException("");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("json格式不正确");
		} finally{
			try {
				if(baos != null) {
					baos.close();
					baos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 
	 * @Title: getCurrentUser 
	 * @Description: 获取登录用户
	 * @param @return 
	 * @return SysUser 
	 * @throws
	 */
	public  SysUser getLoginUser(){
		return RequestUtil.getCurrentUser(request);
	}
	
	public String getReqJson(HttpServletRequest request) throws IOException {
		final int BUFFER_SIZE = 4 * 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		ServletInputStream sis = request.getInputStream();
		ByteArrayOutputStream baos = null;
		try {
			if (sis != null) {
				baos = new ByteArrayOutputStream();
				int bLen = 0;
				while ((bLen = sis.read(buffer)) > 0) {
					baos.write(buffer, 0, bLen);
				}
				String jsonStr = baos.toString("UTF-8");
				baos.close();
				sis.close();
				return jsonStr;
			} else {
				throw new RuntimeException("请求报文输入流为空,报文体错误.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("读取报文输入流错误" + ex.getMessage()
					+ ",报文体错误.");
		} finally {
			if (sis != null) {
				sis.close();
				sis = null;
			}
			if (baos != null) {
				baos.close();
				sis = null;
			}
		}
	}
}
