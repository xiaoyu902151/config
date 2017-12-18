package com.bxsurvey.sys.user.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user", schema = "dbo")
public class SysUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String loginName;
	private String password;
	private String realName;
	private String address;
	private String tel;
	private String moblie;
	private String email;
	private Date birthday;
	private String oicq;
	private Date regDate;
	private String bz;
	private Boolean validity;
	private Integer departId;
	private SysUser sysUser;//创建人
	private Integer userType;
    private Set<SysLog> sysLog = new HashSet<SysLog>();
    private Set<SysUser> sysUsers = new HashSet<SysUser>();
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	

	


	public SysUser(Integer userId, String loginName, String password,
			String realName, String address, String tel, String moblie,
			String email, Date birthday, String oicq, Date regDate, String bz,
			Boolean validity, Integer departId,SysUser sysUser,Integer userType,
			Set<SysLog> sysLog,Set<SysUser> sysUsers) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.password = password;
		this.realName = realName;
		this.address = address;
		this.tel = tel;
		this.moblie = moblie;
		this.email = email;
		this.birthday = birthday;
		this.oicq = oicq;
		this.regDate = regDate;
		this.bz = bz;
		this.validity = validity;
		this.departId = departId;
		this.sysUser=sysUser;
		this.sysLog=sysLog;
		this.sysUsers=sysUsers;
	}



	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "login_name", length = 20)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "real_name", length = 20)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "tel", length = 15)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "moblie", length = 20)
	public String getMoblie() {
		return this.moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "birthday", length = 23)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "oicq", length = 15)
	public String getOicq() {
		return this.oicq;
	}

	public void setOicq(String oicq) {
		this.oicq = oicq;
	}

	@Column(name = "reg_date", length = 23)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "bz")
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "depart_id")
	public Integer getDepartId() {
		return this.departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_Id")
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "user_type")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<SysLog> getSysLog() {
		return sysLog;
	}

	public void setSysLog(Set<SysLog> sysLog) {
		this.sysLog = sysLog;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	

}