package com.bxsurvey.support.plan.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.params.model.SysParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "plan_plan", schema = "dbo")
public class PlanPlan implements java.io.Serializable  {
	private Integer id;//主键标识
	private String planNum;//预案编号（Plan-001）
	private String versionNumber;//版本号
	private String planName;//预案名称
	private Date docReleaseDate;//发布时间
	private String docReleaseNumber;//发布版本号
	private String keyword;//关键词
	private Integer departId;//所属公司
	private String writer;//创建人
	private Date writeTime;//创建时间
	private String vaccineStatus;//审核状态
	private String vaccineMan;//审核人
	private Date vaccineTime;//审核时间
	private String planType;//预案类型
	private Integer planTemplateId;//预案模板
	private String planLevel;//预案级别（企业级、政府级）
	private Date expiryDate;//有效期
	private Date filingTime;//备案时间
	private String scope;//适用范围
	private String range;//发放范围
	private String remark;//预案描述
	private String industry;//行业类别
	private String eventType;//关联事故类型
	private SysDepart sysDepart;
	private SysParams sysParams;//燃烧性
	
	private Set<PlanOrgan> planOrgans = new HashSet<PlanOrgan>();
	private Set<PlanStand> planStands = new HashSet<PlanStand>();
	private Set<PlanPlot> planPlots = new HashSet<PlanPlot>();
	private Set<PlanAffix> planAffixs = new HashSet<PlanAffix>();
	
	/** default constructor */
	public PlanPlan() {
	}

	/** full constructor */
	public PlanPlan(Integer id, String planNum,
			String versionNumber, String planName, Timestamp docReleaseDate,
			String docReleaseNumber,  String keyword,
			Integer departId, String writer, Timestamp writeTime,
			String vaccineStatus, String vaccineMan, Timestamp vaccineTime,
			String planType, Integer planTemplateId, String planLevel,
			Timestamp expiryDate, Timestamp filingTime, String scope,
			String range,String remark,String industry,String eventType,SysDepart sysDepart,SysParams sysParams) {
		this.id = id;
		this.planNum = planNum;
		this.versionNumber = versionNumber;
		this.planName = planName;
		this.docReleaseDate = docReleaseDate;
		this.docReleaseNumber = docReleaseNumber;
		this.keyword = keyword;
		this.departId = departId;
		this.writer = writer;
		this.writeTime = writeTime;
		this.vaccineStatus = vaccineStatus;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
		this.planType = planType;
		this.planTemplateId = planTemplateId;
		this.planLevel = planLevel;
		this.expiryDate = expiryDate;
		this.filingTime = filingTime;
		this.scope = scope;
		this.range = range;
		this.remark = remark;
		this.industry = industry;
		this.eventType = eventType;
		this.sysDepart = sysDepart;
		this.sysParams = sysParams;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "plan_num")
	public String getPlanNum() {
		return planNum;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	@Column(name = "version_number")
	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Column(name = "plan_name")
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	@Column(name = "doc_release_date")
	public Date getDocReleaseDate() {
		return docReleaseDate;
	}

	public void setDocReleaseDate(Date docReleaseDate) {
		this.docReleaseDate = docReleaseDate;
	}

	@Column(name = "doc_release_number")
	public String getDocReleaseNumber() {
		return docReleaseNumber;
	}

	public void setDocReleaseNumber(String docReleaseNumber) {
		this.docReleaseNumber = docReleaseNumber;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "depart_id")
	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	@Column(name = "writer")
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Column(name = "write_time")
	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	@Column(name = "vaccine_status")
	public String getVaccineStatus() {
		return vaccineStatus;
	}

	public void setVaccineStatus(String vaccineStatus) {
		this.vaccineStatus = vaccineStatus;
	}

	@Column(name = "vaccine_man")
	public String getVaccineMan() {
		return vaccineMan;
	}

	public void setVaccineMan(String vaccineMan) {
		this.vaccineMan = vaccineMan;
	}

	@Column(name = "vaccine_time")
	public Date getVaccineTime() {
		return vaccineTime;
	}

	public void setVaccineTime(Date vaccineTime) {
		this.vaccineTime = vaccineTime;
	}

	@Column(name = "plan_type")
	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Column(name = "plan_template_id")
	public Integer getPlanTemplateId() {
		return planTemplateId;
	}

	public void setPlanTemplateId(Integer planTemplateId) {
		this.planTemplateId = planTemplateId;
	}

	@Column(name = "plan_level")
	public String getPlanLevel() {
		return planLevel;
	}

	public void setPlanLevel(String planLevel) {
		this.planLevel = planLevel;
	}

	@Column(name = "expiry_date")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "filing_time")
	public Date getFilingTime() {
		return filingTime;
	}

	public void setFilingTime(Date filingTime) {
		this.filingTime = filingTime;
	}

	@Column(name = "scope")
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Column(name = "range")
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "industry")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Column(name = "event_type")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
	@JsonIgnoreProperties(value={"departPid","departValue","departContact","departTel","departAddress","departEmail","paramsId","departBz","departValidity","departType","departFax","departZipcode","departLicence","departForm","departRedistration","departRepresentative","departSupervisor","departWorkerNum","departTechnicalNum","departSafetyNum","departRegisteredCapital","departBusinessAddress","departBusinessProperty","departStorageAddress","departStorageProperty","departBuilding","departStoragePower","departSystemName","departArea","files","images","vaccine","vaccineMan","vaccineTime"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id", insertable = false, updatable = false)
	public SysDepart getSysDepart() {
		return sysDepart;
	}

	public void setSysDepart(SysDepart sysDepart) {
		this.sysDepart = sysDepart;
	}

	@JsonIgnoreProperties(value={"paramsType","paramsValue","paramsBz","paramsValidity"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_type", insertable = false, updatable = false)
	public SysParams getSysParams() {
		return sysParams;
	}

	public void setSysParams(SysParams sysParams) {
		this.sysParams = sysParams;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "planPlan")
	public Set<PlanOrgan> getPlanOrgans() {
		return planOrgans;
	}

	public void setPlanOrgans(Set<PlanOrgan> planOrgans) {
		this.planOrgans = planOrgans;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "planPlan")
	public Set<PlanStand> getPlanStands() {
		return planStands;
	}

	public void setPlanStands(Set<PlanStand> planStands) {
		this.planStands = planStands;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "planPlan")
	public Set<PlanPlot> getPlanPlots() {
		return planPlots;
	}

	public void setPlanPlots(Set<PlanPlot> planPlots) {
		this.planPlots = planPlots;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "planPlan")
	public Set<PlanAffix> getPlanAffixs() {
		return planAffixs;
	}

	public void setPlanAffixs(Set<PlanAffix> planAffixs) {
		this.planAffixs = planAffixs;
	}
	
	
	
	
}
