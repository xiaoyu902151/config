package com.bxsurvey.sys.params.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysParams entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_params", schema = "dbo")
public class SysParams implements java.io.Serializable {

	// Fields

	private Integer paramsId;
	private String paramsName;
	private String paramsType;
	private String paramsValue;
	private String paramsBz;
	private Boolean paramsValidity;
	private Integer paramsIndex;

	// Constructors

	/** default constructor */
	public SysParams() {
	}

	/** full constructor */
	public SysParams(String paramsName, String paramsType, String paramsValue,
			String paramsBz, Boolean paramsValidity,Integer paramsIndex) {
		this.paramsName = paramsName;
		this.paramsType = paramsType;
		this.paramsValue = paramsValue;
		this.paramsBz = paramsBz;
		this.paramsValidity = paramsValidity;
		this.paramsIndex = paramsIndex;
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
	

}