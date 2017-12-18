package cn.tarena.ht.tool;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5HashPassword {
	
	public static String getMd5Hash(String password,String username){
		
		//哈希次数三次固定
		return new Md5Hash(password, username, 3).toString();
	}
	
	public static void main(String[] args) {
		String password = "123456";
		/**
		 * source 明文
		 * salt   盐
		 * hashIterations
		 */
		Md5Hash md5Hash = new Md5Hash("admin", "admin", 3);
		System.out.println(md5Hash);
	}
}
