package net.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtils {
	private static FTPClient ftpClient = new FTPClient();  
	
	 /** 
     * Description: 向FTP服务器上传文件 
     *  
     * @Version1.0 
     * @param url 
     *            FTP服务器hostname 
     * @param port 
     *            FTP服务器端口 
     * @param username 
     *            FTP登录账号 
     * @param password 
     *            FTP登录密码 
     * @param path 
     *            FTP服务器保存目录,如果是根目录则为“/” 
     * @param filename 
     *            上传到FTP服务器上的文件名 
     * @param input 
     *            本地文件输入流 
     * @return 成功返回true，否则返回false 
     */  
    public static boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input)  
    {  
        boolean result = false;  
        try  
        {  
            int reply;  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftpClient.connect(url);  
            // 登录  
            ftpClient.login(username, password);  
            ftpClient.setControlEncoding("UTF-8");  
            // 检验是否连接成功  
            reply = ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply))  
            {   
                ftpClient.disconnect();  
                return result;  
            }  
            // 转移工作目录至指定目录下  
            boolean change = ftpClient.changeWorkingDirectory(path);  
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);  
            if (change)  
            {  
                //result = ftpClient.storeFile(new String(filename.getBytes("UTF-8"), "iso-8859-1"), input); 
            	result = ftpClient.storeFile(filename, input);
            }  
            input.close();  
            ftpClient.logout();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            if (ftpClient.isConnected())  
            {  
                try  
                {  
                    ftpClient.disconnect();  
                } catch (IOException ioe)  
                {  
                }  
            }  
        }  
        return result;  
    }
  
    /** 
     *  
     * Description: 从FTP服务器下载文件 
     *  
     * @param url 
     *            FTP服务器hostname 
     * @param port 
     *            FTP服务器端口 
     * @param username 
     *            FTP登录账号 
     * @param password 
     *            FTP登录密码 
     * @param remotePath 
     *            FTP服务器上的相对路径 
     * @param fileName 
     *            要下载的文件名 
     * @param localPath 
     *            下载后保存到本地的路径 
     * @return 
     */  
    public static boolean downFile(String url, int port, String username, String password, String remotePath, String fileName,HttpServletResponse response)  
    {  
        boolean result = false;  
        try  
        {  
            int reply;  
            ftpClient.setControlEncoding("UTF-8");  
            /* 
             * 为了上传和下载中文文件，有些地方建议使用以下两句代替 
             * new String(remotePath.getBytes(encoding),"iso-8859-1")转码。 
             * 经过测试，通不过。 
             */  
            ftpClient.connect(url, port);  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftpClient.login(username, password);// 登录  
            // 设置文件传输类型为二进制  
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            // 获取ftp登录应答代码  
            reply = ftpClient.getReplyCode();  
            // 验证是否登陆成功  
            if (!FTPReply.isPositiveCompletion(reply))  
            {  
                ftpClient.disconnect();  
                System.err.println("FTP server refused connection.");  
                return result;  
            }  
            // 转移到FTP服务器目录至指定的目录下   
            ftpClient.changeWorkingDirectory(remotePath);
            // 获取文件列表  
            FTPFile[] fs = ftpClient.listFiles();  
  
	        for(int i=0;i<fs.length;i++){  
	            if(fs[i].getName().equals(fileName)){  
	            	String saveAsFileName =fs[i].getName();
	    			response.setHeader("Content-Disposition", "attachment;fileName="+saveAsFileName);
	    			OutputStream os = response.getOutputStream();
	                ftpClient.retrieveFile(fs[i].getName(), os);
	                os.flush();
	                os.close();
	                break;
	            }
	        }
            ftpClient.logout();  
            result = true;  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            if (ftpClient.isConnected())  
            {  
                try  
                {  
                    ftpClient.disconnect();  
                } catch (IOException ioe)  
                {  
                }  
            }  
        }  
        return result;  
    }  
     
}
