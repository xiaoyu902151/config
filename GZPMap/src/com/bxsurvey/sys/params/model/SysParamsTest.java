package com.bxsurvey.sys.params.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_params_test", schema = "dbo")
public class SysParamsTest implements java.io.Serializable{
	
	private Integer paramsId;
	private String paramsName;
	private String paramsType;
	private String paramsValue;
	private String paramsBz;
	private Boolean paramsValidity;
	private Integer paramsIndex;
	private Integer pid;

	// Constructors
	public SysParamsTest() {
		super();
	}
	

	public SysParamsTest(Integer paramsId, String paramsName,
			String paramsType, String paramsValue, String paramsBz,
			Boolean paramsValidity, Integer paramsIndex, Integer pid) {
		super();
		this.paramsId = paramsId;
		this.paramsName = paramsName;
		this.paramsType = paramsType;
		this.paramsValue = paramsValue;
		this.paramsBz = paramsBz;
		this.paramsValidity = paramsValidity;
		this.paramsIndex = paramsIndex;
		this.pid = pid;
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "params_id", unique = true, nullable = false)
	public Integer getParamsId() {
		return this.paramsId;
	}

	public void setParamsId(Integer paramsId) {
		this.paramsId = paramsId;
	}

	@Column(name = "params_name", length = 20)
	public String getParamsName() {
		return this.paramsName;
	}

	public void setParamsName(String paramsName) {
		this.paramsName = paramsName;
	}

	@Column(name = "params_type", length = 32)
	public String getParamsType() {
		return this.paramsType;
	}

	public void setParamsType(String paramsType) {
		this.paramsType = paramsType;
	}

	@Column(name = "params_value", length = 32)
	public String getParamsValue() {
		return this.paramsValue;
	}

	public void setParamsValue(String paramsValue) {
		this.paramsValue = paramsValue;
	}

	@Column(name = "params_bz")
	public String getParamsBz() {
		return this.paramsBz;
	}

	public void setParamsBz(String paramsBz) {
		this.paramsBz = paramsBz;
	}

	@Column(name = "params_validity")
	public Boolean getParamsValidity() {
		return this.paramsValidity;
	}

	public void setParamsValidity(Boolean paramsValidity) {
		this.paramsValidity = paramsValidity;
	}

	@Column(name = "params_index", length = 10)
	public Integer getParamsIndex() {
		return paramsIndex;
	}

	public void setParamsIndex(Integer paramsIndex) {
		this.paramsIndex = paramsIndex;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	
}
