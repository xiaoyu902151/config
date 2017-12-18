package com.bxsurvey.uploadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class ReadImg {
	/**
	 * @filepath 指定的路径目录
	 * 读取指定文件夹下的所有文件
	 */
	public List<String> readfile(String userId,String geoId) throws FileNotFoundException, IOException {
		String tomcatBinPath = System.getProperty("user.dir");
		int index = tomcatBinPath.indexOf("bin");
		String tomcatHomePath = tomcatBinPath.substring(0,index); 
		File file = new File(tomcatHomePath+"/webapps/GISMap/upload/"+userId+"/"+geoId);
        List<String> res = new ArrayList<String>();
        
		//如果文件夹不存在则创建    
        if  (!file .exists()  && !file.isDirectory())      
            return res;
        else   
        {  
        	String[] imgList = null;
    		File[] tempList = file.listFiles();
    		for (int i = 0; i < tempList.length; i++) {
    			if (tempList[i].isFile()) {
    				res.add(tempList[i].getName());
    			}
    		}
        }
		return res;
	}
}
