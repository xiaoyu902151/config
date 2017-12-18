package com.bxsurvey.danger.DangerDevice.model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.bxsurvey.danger.DangerDeviciType.model.DangerDeviciType;
import com.bxsurvey.rmt.model.RtmDictionary;
import com.bxsurvey.rmt.model.RtmWarning;
import com.bxsurvey.sys.depart.model.SysDepart;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * DangerDevi entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "danger_devi", schema = "dbo", uniqueConstraints = @UniqueConstraint(columnNames = "identity_label"))
public class DangerDevi implements java.io.Serializable {

	// Fields

	private Integer deviceId;
	private DangerDeviciType dangerDeviciType;
	//private Integer departId;
	private SysDepart sysDepart;
	private String dataProviderUr;
	private Integer mapServiceId;
	private Integer mapLayerId;
	private String fid;
	private Integer forignId;
	private Integer paramsId;
	private String name;
	private Double warnLevel1Upper;
	private Double warnLevel1Lower;
	private Double warnLevel2Upper;
	private Double warnLevel2Lower;
	private Double warnLevel3Upper;
	private Double warnLevel3Lower;
	private String writer;
	private Date writeTime;
	private Integer vaccine;
	private Boolean validity;
	private String x;
	private String y;
	private Date useTime;
	private Date checkTime;
	private String sjsynx;
	private String model;
	private String identityLabel;
	private String vaccineMan;
	private Date vaccineTime;
	private Set<RtmWarning> rtmWarnings = new HashSet<RtmWarning>(0);
	private Set<RtmDictionary> rtmDictionaries = new HashSet<RtmDictionary>(0);
    private String rtdbNo;
	// Constructors

	/** default constructor */
	public DangerDevi() {
	}


	/** full constructor */
	public DangerDevi(Integer deviceId, DangerDeviciType dangerDeviciType,
			SysDepart sysDepart, String dataProviderUr, Integer mapServiceId,
			Integer mapLayerId, String fid, Integer forignId, Integer paramsId,
			String name, Double warnLevel1Upper, Double warnLevel1Lower,
			Double warnLevel2Upper, Double warnLevel2Lower,
			Double warnLevel3Upper, Double warnLevel3Lower, String writer,
			Date writeTime, Integer vaccine, Boolean validity, String x,
			String y, Date useTime, Date checkTime, String sjsynx,
			String model, String identityLabel, String vaccineMan,
			Date vaccineTime, String rtdbNo, Set<RtmWarning> rtmWarnings,
			Set<RtmDictionary> rtmDictionaries) {
		super();
		this.deviceId = deviceId;
		this.dangerDeviciType = dangerDeviciType;
		this.sysDepart = sysDepart;
		this.dataProviderUr = dataProviderUr;
		this.mapServiceId = mapServiceId;
		this.mapLayerId = mapLayerId;
		this.fid = fid;
		this.forignId = forignId;
		this.paramsId = paramsId;
		this.name = name;
		this.warnLevel1Upper = warnLevel1Upper;
		this.warnLevel1Lower = warnLevel1Lower;
		this.warnLevel2Upper = warnLevel2Upper;
		this.warnLevel2Lower = warnLevel2Lower;
		this.warnLevel3Upper = warnLevel3Upper;
		this.warnLevel3Lower = warnLevel3Lower;
		this.writer = writer;
		this.writeTime = writeTime;
		this.vaccine = vaccine;
		this.validity = validity;
		this.x = x;
		this.y = y;
		this.useTime = useTime;
		this.checkTime = checkTime;
		this.sjsynx = sjsynx;
		this.model = model;
		this.identityLabel = identityLabel;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
		this.rtdbNo = rtdbNo;
		this.rtmWarnings = rtmWarnings;
		this.rtmDictionaries = rtmDictionaries;
	}

	

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "device_id", unique = true, nullable = false)
	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "device_type_id")
	public DangerDeviciType getDangerDeviciType() {
		return this.dangerDeviciType;
	}

	public void setDangerDeviciType(DangerDeviciType dangerDeviciType) {
		this.dangerDeviciType = dangerDeviciType;
	}

//	@Column(name = "depart_id")
//	public Integer getDepartId() {
//		return this.departId;
//	}
//
//	public void setDepartId(Integer departId) {
//		this.departId = departId;
//	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id", nullable = false)
	public SysDepart getSysDepart() {
		return sysDepart;
	}


	public void setSysDepart(SysDepart sysDepart) {
		this.sysDepart = sysDepart;
	}


	@Column(name = "data_provider_ur", length = 1024)
	public String getDataProviderUr() {
		return this.dataProviderUr;
	}

	public void setDataProviderUr(String dataProviderUr) {
		this.dataProviderUr = dataProviderUr;
	}

	@Column(name = "map_service_id")
	public Integer getMapServiceId() {
		return this.mapServiceId;
	}

	public void setMapServiceId(Integer mapServiceId) {
		this.mapServiceId = mapServiceId;
	}

	@Column(name = "map_layer_id")
	public Integer getMapLayerId() {
		return this.mapLayerId;
	}

	public void setMapLayerId(Integer mapLayerId) {
		this.mapLayerId = mapLayerId;
	}

	@Column(name = "f_id", length = 10)
	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	@Column(name = "forign_id")
	public Integer getForignId() {
		return this.forignId;
	}

	public void setForignId(Integer forignId) {
		this.forignId = forignId;
	}

	@Column(name = "params_id")
	public Integer getParamsId() {
		return this.paramsId;
	}

	public void setParamsId(Integer paramsId) {
		this.paramsId = paramsId;
	}

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "warn_level1_upper", precision = 53, scale = 0)
	public Double getWarnLevel1Upper() {
		return this.warnLevel1Upper;
	}

	public void setWarnLevel1Upper(Double warnLevel1Upper) {
		this.warnLevel1Upper = warnLevel1Upper;
	}

	@Column(name = "warn_level1_lower", precision = 53, scale = 0)
	public Double getWarnLevel1Lower() {
		return this.warnLevel1Lower;
	}

	public void setWarnLevel1Lower(Double warnLevel1Lower) {
		this.warnLevel1Lower = warnLevel1Lower;
	}

	@Column(name = "warn_level2_upper", precision = 53, scale = 0)
	public Double getWarnLevel2Upper() {
		return this.warnLevel2Upper;
	}

	public void setWarnLevel2Upper(Double warnLevel2Upper) {
		this.warnLevel2Upper = warnLevel2Upper;
	}

	@Column(name = "warn_level2_lower", precision = 53, scale = 0)
	public Double getWarnLevel2Lower() {
		return this.warnLevel2Lower;
	}

	public void setWarnLevel2Lower(Double warnLevel2Lower) {
		this.warnLevel2Lower = warnLevel2Lower;
	}

	@Column(name = "warn_level3_upper", precision = 53, scale = 0)
	public Double getWarnLevel3Upper() {
		return this.warnLevel3Upper;
	}

	public void setWarnLevel3Upper(Double warnLevel3Upper) {
		this.warnLevel3Upper = warnLevel3Upper;
	}

	@Column(name = "warn_level3_lower", precision = 53, scale = 0)
	public Double getWarnLevel3Lower() {
		return this.warnLevel3Lower;
	}

	public void setWarnLevel3Lower(Double warnLevel3Lower) {
		this.warnLevel3Lower = warnLevel3Lower;
	}

	@Column(name = "writer", length = 20)
	public String getWriter() {
		return this.writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "write_time", length = 23)
	public Date getWriteTime() {
		return this.writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	@Column(name = "vaccine")
	public Integer getVaccine() {
		return this.vaccine;
	}

	public void setVaccine(Integer vaccine) {
		this.vaccine = vaccine;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "X", length = 20)
	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Column(name = "Y", length = 20)
	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "use_time", length = 23)
	public Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "check_time", length = 23)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "sjsynx", length = 32)
	public String getSjsynx() {
		return this.sjsynx;
	}

	public void setSjsynx(String sjsynx) {
		this.sjsynx = sjsynx;
	}

	@Column(name = "model")
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "identity_label", unique = true)
	public String getIdentityLabel() {
		return this.identityLabel;
	}

	public void setIdentityLabel(String identityLabel) {
		this.identityLabel = identityLabel;
	}

	@Column(name = "vaccine_man", length = 20)
	public String getVaccineMan() {
		return this.vaccineMan;
	}

	public void setVaccineMan(String vaccineMan) {
		this.vaccineMan = vaccineMan;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vaccine_time", length = 23)
	public Date getVaccineTime() {
		return this.vaccineTime;
	}

	public void setVaccineTime(Date vaccineTime) {
		this.vaccineTime = vaccineTime;
	}

	@Column(name="rtdb_no",length=24)
	public String getRtdbNo() {
		return rtdbNo;
	}

	public void setRtdbNo(String rtdbNo) {
		this.rtdbNo = rtdbNo;
	}


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dangerDevi")
	public Set<RtmWarning> getRtmWarnings() {
		return this.rtmWarnings;
	}

	public void setRtmWarnings(Set<RtmWarning> rtmWarnings) {
		this.rtmWarnings = rtmWarnings;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dangerDevi")
	public Set<RtmDictionary> getRtmDictionaries() {
		return this.rtmDictionaries;
	}

	public void setRtmDictionaries(Set<RtmDictionary> rtmDictionaries) {
		this.rtmDictionaries = rtmDictionaries;
	}


}