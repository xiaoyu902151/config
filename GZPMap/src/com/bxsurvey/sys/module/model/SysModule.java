package com.bxsurvey.sys.module.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_module", schema = "dbo")
public class SysModule implements java.io.Serializable {

	// Fields

	private String moduleId;
	private String moduleName;
	private Integer moduleType;
	private Integer moduleIndex;
	private String moduleValue;
	private String moduleIcon;
	private String modulePath;
	private String modulePid;
	private String moduleBz;
	private Boolean moduleValidity;
	private String moduleJs;

	// Constructors

	/** default constructor */
	public SysModule() {
	}

	/** full constructor */
	public SysModule(String moduleName, Integer moduleType,
			Integer moduleIndex, String moduleValue, String moduleIcon,
			String modulePath, String modulePid, String moduleBz,
			Boolean moduleValidity, String moduleJs) {
		this.moduleName = moduleName;
		this.moduleType = moduleType;
		this.moduleIndex = moduleIndex;
		this.moduleValue = moduleValue;
		this.moduleIcon = moduleIcon;
		this.modulePath = modulePath;
		this.modulePid = modulePid;
		this.moduleBz = moduleBz;
		this.moduleValidity = moduleValidity;
		this.moduleJs = moduleJs;
	}

	// Property accessors
	@Id
	@Column(name = "module_id", unique = true, nullable = false, length = 32)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "module_name", length = 30)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "module_type")
	public Integer getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}

	@Column(name = "module_index")
	public Integer getModuleIndex() {
		return this.moduleIndex;
	}

	public void setModuleIndex(Integer moduleIndex) {
		this.moduleIndex = moduleIndex;
	}

	@Column(name = "module_value", length = 10)
	public String getModuleValue() {
		return this.moduleValue;
	}

	public void setModuleValue(String moduleValue) {
		this.moduleValue = moduleValue;
	}

	@Column(name = "module_icon", length = 150)
	public String getModuleIcon() {
		return this.moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}

	@Column(name = "module_path", length = 150)
	public String getModulePath() {
		return this.modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	@Column(name = "module_pid", length = 32)
	public String getModulePid() {
		return this.modulePid;
	}

	public void setModulePid(String modulePid) {
		this.modulePid = modulePid;
	}

	@Column(name = "module_bz")
	public String getModuleBz() {
		return this.moduleBz;
	}

	public void setModuleBz(String moduleBz) {
		this.moduleBz = moduleBz;
	}

	@Column(name = "module_validity")
	public Boolean getModuleValidity() {
		return this.moduleValidity;
	}

	public void setModuleValidity(Boolean moduleValidity) {
		this.moduleValidity = moduleValidity;
	}

	@Column(name = "module_js", length = 20)
	public String getModuleJs() {
		return this.moduleJs;
	}

	public void setModuleJs(String moduleJs) {
		this.moduleJs = moduleJs;
	}

}