package com.bxsurvey.sys.user.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysUserRoleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysUserRoleId implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public SysUserRoleId() {
	}

	/** full constructor */
	public SysUserRoleId(Integer userId, Integer roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysUserRoleId))
			return false;
		SysUserRoleId castOther = (SysUserRoleId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "SysUserRoleId [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
	

}