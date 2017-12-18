package com.bxsurvey.uploadfile;
//import com.poweree.util.DateUtils;
//import net.framework.utils.DateUtils;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: http://www.mbaike.net
 * Date: 13-10-31
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.                                   a
 */
public class UpLoadImg extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map map = new HashMap();
        request.setCharacterEncoding("utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取用户ID
		String userId = request.getParameter("userId");
		//获取地块唯一编号
		String fid = request.getParameter("fid");
        //String path = request.getRealPath("/upload"); 
        //String path = request.getSession().getServletContext().getRealPath("/upload"+"/"+userId+"/"+fid);
        String path = request.getSession().getServletContext().getRealPath("/upload"+"/"+userId);
        File file=new File(path); 
        //如果文件夹不存在则创建    
        if  (!file.exists()  && !file.isDirectory())      
        {       
        	System.out.println("//不存在");  
        	file.mkdir();    
        } else   
        {  
        	System.out.println("//目录存在");  
        }
        path = request.getSession().getServletContext().getRealPath("/upload"+"/"+userId+"/"+fid);
        file=new File(path); 
        //如果文件夹不存在则创建    
        if  (!file.exists()  && !file.isDirectory())      
        {       
        	System.out.println("//不存在");  
        	file.mkdir();    
        } else   
        {  
        	System.out.println("//目录存在");  
        }
        //String folderName = getFolderName(path);
        //pathimg = path.replace("/", "\\");  
        //return path + "\\"; 
        
        //factory.setRepository(new File(path));
        factory.setRepository(file);
        factory.setSizeThreshold(1024*1024) ;
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //可以上传多个文件
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
            for(FileItem item : list){
                if(!item.isFormField()){
                    String name = item.getName() ;
                    String fileSuffix  = name.substring(name.lastIndexOf(".")+1,name.length());
                    String oldName = name.replaceAll("." + fileSuffix,"");
                    //String fileName = DateUtils.getNowTime(DateUtils.DATE_All_KEY_STR);
                    String fileName = userId+"_"+fid;
                    String newName = oldName + "." + fileSuffix;
                    //String newName = name;
                    OutputStream out = new FileOutputStream(new File(path,newName));
                    InputStream in = item.getInputStream() ;
                    int length = 0 ;
                    byte [] buf = new byte[1024] ;
                    while( (length = in.read(buf) ) != -1){
                        out.write(buf, 0, length);
                    }
                    in.close();
                    out.close();
                    /**将上传处理后的数据返回**/
                    map.put("fileSuffix",fileSuffix);
                    map.put("fileName",oldName);
                    map.put("filePath",fileName);
                    break;
                }
            }
        }catch (Exception e) {
            System.out.println("出错了：" + e.getMessage());
        }
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = JSONObject.fromObject(map);
        String msg =  jsonObject.toString();
        out.print(msg);
        out.close();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}