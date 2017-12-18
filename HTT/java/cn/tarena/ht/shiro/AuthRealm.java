package cn.tarena.ht.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.org.apache.xerces.internal.xs.StringList;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;

public class AuthRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	//权限控制
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		/*List<String> pList = new ArrayList<String>();
		pList.add("货运管理");
		pList.add("系统管理");*/
		
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		//模块信息的名称
		List<String> pList = userService.findPListByUserId(user.getUserId());
		
		
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(pList);
		return info;
	}
	
	//认证模块
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户和密码
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		String username = loginToken.getUsername();
		
		//2.根据用户名查询真实的用户信息
		User user = userService.findUserByUserName(username);
		
		
		/**
		 * 3.创建info对象 回传数据  
		 *  1.principal 真实的用户对象
		 *  2.credentials 真实的密码
		 *  3.realmName   当前realm的名称
		 */
		
		AuthenticationInfo info = 
		new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		
		return info;
	}
	
	
	
	
}
