package com.bxsurvey.sys.role.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bxsurvey.sys.user.model.SysUser;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role", schema = "dbo")
public class SysRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private Integer paramsId;
	private String roleValue;
	private String roleBz;
	private Boolean roleValidity;
	private Integer rolePid;
	private SysUser sysUser;//创建人

	// Constructors

	/** default constructor */
	public SysRole() {
	}
	
	

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", paramsId=" + paramsId + ", roleValue=" + roleValue
				+ ", roleBz=" + roleBz + ", roleValidity=" + roleValidity
				+ ", rolePid=" + rolePid + "]";
	}



	/** full constructor */
	public SysRole(String roleName, Integer paramsId, String roleValue,
			String roleBz, Boolean roleValidity, Integer rolePid,SysUser sysUser) {
		this.roleName = roleName;
		this.paramsId = paramsId;
		this.roleValue = roleValue;
		this.roleBz = roleBz;
		this.roleValidity = roleValidity;
		this.rolePid = rolePid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "params_id")
	public Integer getParamsId() {
		return this.paramsId;
	}

	public void setParamsId(Integer paramsId) {
		this.paramsId = paramsId;
	}

	@Column(name = "role_value", length = 10)
	public String getRoleValue() {
		return this.roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	@Column(name = "role_bz")
	public String getRoleBz() {
		return this.roleBz;
	}

	public void setRoleBz(String roleBz) {
		this.roleBz = roleBz;
	}

	@Column(name = "role_validity")
	public Boolean getRoleValidity() {
		return this.roleValidity;
	}

	public void setRoleValidity(Boolean roleValidity) {
		this.roleValidity = roleValidity;
	}

	@Column(name = "role_pid")
	public Integer getRolePid() {
		return this.rolePid;
	}

	public void setRolePid(Integer rolePid) {
		this.rolePid = rolePid;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_Id")
	public SysUser getSysUser() {
		return sysUser;
	}



	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
  
}