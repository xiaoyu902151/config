package cn.tarena.ht.pojo;

public class User extends BaseEntity{
	private Dept dept;      //一对一关联查询
	private UserInfo userInfo;  //关联用户扩展信息
	
	private String userId;  //用户id
	private String username; //用户名
	private String password;  //密码
	private Integer state;    //状态  0停用 1启用
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	
}
