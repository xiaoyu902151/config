package com.bxsurvey.sys.role.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SysRoleModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_module", schema = "dbo")
public class SysRoleModule implements java.io.Serializable {

	// Fields

	private SysRoleModuleId id;

	// Constructors

	/** default constructor */
	public SysRoleModule() {
	}

	/** full constructor */
	public SysRoleModule(SysRoleModuleId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
	@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
	@AttributeOverride(name = "moduleId", column = @Column(name = "module_id", nullable = false, length = 32)) })
	public SysRoleModuleId getId() {
		return this.id;
	}

	public void setId(SysRoleModuleId id) {
		this.id = id;
	}
	
	

}