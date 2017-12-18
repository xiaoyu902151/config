package com.bxsurvey.rmt.model;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bxsurvey.danger.DangerDevice.model.DangerDevi;
import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * RtmWarning entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rtm_warning", schema = "dbo")
public class RtmWarning implements java.io.Serializable {

	// Fields

	private Integer warningId;
	private DangerDevi dangerDevi;
	private Float val;
	private Integer warningLevel;
	private Date warnTime;
	private Date syncTime;
	private Integer state;
	private String type_name;
	private String departName;
	private String zgName;
	private DangerZgq dangerZgq;
	private Integer isWarning;
	// Constructors



	/** default constructor */
	public RtmWarning() {
	}

	/** minimal constructor */
	public RtmWarning(DangerDevi dangerDevi) {
		this.dangerDevi = dangerDevi;
	}

	/** full constructor */
	public RtmWarning(Integer warningId, DangerDevi dangerDevi, Float val,
			Integer warningLevel, Date warnTime, Date syncTime,
			Integer state, String type_name, String departName, String zgName, String isWarning,DangerZgq dangerZgq) {
		super();
		this.warningId = warningId;
		this.dangerDevi = dangerDevi;
		this.val = val;
		this.warningLevel = warningLevel;
		this.warnTime = warnTime;
		this.syncTime = syncTime;
		this.state = state;
		this.type_name = type_name;
		this.departName = departName;
		this.zgName = zgName;
		this.dangerZgq =dangerZgq;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "warning_id", unique = true, nullable = false)
	public Integer getWarningId() {
		return this.warningId;
	}

	public void setWarningId(Integer warningId) {
		this.warningId = warningId;
	}

	@JsonIgnoreProperties(value={"dataProviderUr","mapServiceId","mapLayerId","fid","forignId","paramsId","warnLevel1Upper","warnLevel1Lower","warnLevel2Upper","warnLevel2Lower","warnLevel3Upper","warnLevel3Lower","writer","writeTime","vaccine","validity","useTime","checkTime","sjsynx","model","identityLabel","vaccineMan","vaccineTime","rtdbNo"})//在解析成json时，忽略的子属性
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name = "device_id", nullable = false)
	public DangerDevi getDangerDevi() {
		return this.dangerDevi;
	}

	public void setDangerDevi(DangerDevi dangerDevi) {
		this.dangerDevi = dangerDevi;
	}

	@Column(name = "val", precision = 53, scale = 0)
	public Float getVal() {
		return this.val;
	}

	public void setVal(Float val) {
		this.val = val;
	}

	@Column(name = "warning_level")
	public Integer getWarningLevel() {
		return this.warningLevel;
	}

	public void setWarningLevel(Integer warningLevel) {
		this.warningLevel = warningLevel;
	}

	@Column(name = "warn_time", length = 23)
	public Date getWarnTime() {
		return this.warnTime;
	}

	public void setWarnTime(Date warnTime) {
		this.warnTime = warnTime;
	}

	@Column(name = "sync_time", length = 23)
	public Date getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "type_name",length=50)
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Column(name = "depart_name")
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Column(name = "zg_name")
	public String getZgName() {
		return zgName;
	}

	public void setZgName(String zgName) {
		this.zgName = zgName;
	}

	@Column(name = "is_warning")
	public Integer getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(Integer isWarning) {
		this.isWarning = isWarning;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zgq_id")
	@JsonIgnore
	public DangerZgq getDangerZgq() {
		return dangerZgq;
	}

	public void setDangerZgq(DangerZgq dangerZgq) {
		this.dangerZgq = dangerZgq;
	}

}