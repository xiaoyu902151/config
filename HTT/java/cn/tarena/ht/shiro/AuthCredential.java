package cn.tarena.ht.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import cn.tarena.ht.tool.MD5HashPassword;

public class AuthCredential extends  SimpleCredentialsMatcher{
	
	//进行密码的加密操作
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		//1.将token进行转化
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		
		//2.获取明文密码
		String password = String.valueOf(loginToken.getPassword()); 
		
		//3.获取盐值
		String username = loginToken.getUsername();
		
		//4.将密码进行加密处理
		password = MD5HashPassword.getMd5Hash(password, username);
		
		//5.将密文存入到令牌中
		loginToken.setPassword(password.toCharArray());
		
		return super.doCredentialsMatch(loginToken, info);
	}
	

}
