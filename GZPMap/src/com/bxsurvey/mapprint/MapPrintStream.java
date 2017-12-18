package com.bxsurvey.mapprint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoImgStream
 */
public class MapPrintStream extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MapPrintStream() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getParameter("resultUrl");
		URL localURL = new URL(path);
		URLConnection connection = localURL.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        InputStream inputStream = null;
        //新增加部分
        String ext ="";
        String[] urlSplit = path.split("/");
        String fileName = urlSplit[urlSplit.length - 1];
//        if(fileName.split(".").length>0){
//            ext = fileName.split(".")[1];
//        }
        ext = fileName.substring(fileName.length()-3);
        Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        //DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HHmmss");//设置显示格式
        String nowTime="";
        nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
        String fn = nowTime+ "." + ext;
		//response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8") + ";");
		response.addHeader("Content-Disposition", "attachment;filename=" + fn + ";");
		response.setContentType("application/octet-stream");
       
       OutputStream writer = response.getOutputStream();
        
        try {
        	if (httpURLConnection.getResponseCode() >= 300) {
        		return;
        	}
            inputStream = httpURLConnection.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            do {
            	len = inputStream.read(bytes, 0, 1024);
            	if(len > 0){
            		writer.write(bytes, 0, len);
            	} else {
            		break;
            	}
            }while(true);
            
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }       
		writer.flush();
		writer.close();				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
