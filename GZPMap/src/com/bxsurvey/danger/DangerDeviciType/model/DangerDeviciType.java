package com.bxsurvey.danger.DangerDeviciType.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bxsurvey.danger.DangerDevice.model.DangerDevi;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * DangerDeviciType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "danger_devici_type", schema = "dbo")
public class DangerDeviciType implements java.io.Serializable {

	// Fields

	private Integer deviceTypeId;
	private String type;
	private String remark;
	private String valUnit;
	private Boolean validity;
	private String paramsName;
	private Set<DangerDevi> dangerDevis = new HashSet<DangerDevi>(0);

	// Constructors

	/** default constructor */
	public DangerDeviciType() {
	}

	/** full constructor */
	public DangerDeviciType(String type, String remark, String valUnit,
			Boolean validity, String paramsName , Set<DangerDevi> dangerDevis) {
		this.type = type;
		this.remark = remark;
		this.valUnit = valUnit;
		this.validity = validity;
		this.dangerDevis = dangerDevis;
		this.paramsName = paramsName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "device_type_id", unique = true, nullable = false)
	public Integer getDeviceTypeId() {
		return this.deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	@Column(name = "type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "remark", length = 1024)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "val_unit", length = 32)
	public String getValUnit() {
		return this.valUnit;
	}

	public void setValUnit(String valUnit) {
		this.valUnit = valUnit;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name ="params_name")
	public String getParamsName() {
		return paramsName;
	}

	public void setParamsName(String paramsName) {
		this.paramsName = paramsName;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dangerDeviciType")
	public Set<DangerDevi> getDangerDevis() {
		return this.dangerDevis;
	}

	public void setDangerDevis(Set<DangerDevi> dangerDevis) {
		this.dangerDevis = dangerDevis;
	}

}