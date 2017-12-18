package com.bxsurvey.sys.user.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_role", schema = "dbo")
public class SysUserRole implements java.io.Serializable {

	// Fields

	private SysUserRoleId id;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** full constructor */
	public SysUserRole(SysUserRoleId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)) })
	public SysUserRoleId getId() {
		return this.id;
	}

	public void setId(SysUserRoleId id) {
		this.id = id;
	}
//private Integer user_id;
//private Integer role_id;

//@Id
//@GeneratedValue(strategy = IDENTITY)
//@Column(name = "user_id",unique = false,nullable = true,length = 10)  
//public Integer getUser_id() {
//	return user_id;
//}
//
//public void setUser_id(Integer user_id) {
//	this.user_id = user_id;
//}
//@Column(name = "role_id",unique = false,nullable = true,length = 10)  
//public Integer getRole_id() {
//	return role_id;
//}
//public void setRole_id(Integer role_id) {
//	this.role_id = role_id;
//}
	
	

}