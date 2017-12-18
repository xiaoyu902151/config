package com.bxsurvey.sys.role.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysRoleModuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysRoleModuleId implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String moduleId;

	// Constructors

	/** default constructor */
	public SysRoleModuleId() {
	}

	/** full constructor */
	public SysRoleModuleId(Integer roleId, String moduleId) {
		this.roleId = roleId;
		this.moduleId = moduleId;
	}

	// Property accessors

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "module_id", nullable = false, length = 32)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRoleModuleId))
			return false;
		SysRoleModuleId castOther = (SysRoleModuleId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this
						.getModuleId() != null
						&& castOther.getModuleId() != null && this
						.getModuleId().equals(castOther.getModuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		return result;
	}

}