package com.bxsurvey.danger.DangerTeam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bxsurvey.sys.depart.model.SysDepart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DangerTeam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "danger_team", schema = "dbo")
public class DangerTeam implements java.io.Serializable {

	// Fields

	private Integer id;//主键标识
	private String teamName;//队伍名称
	private Integer number;//队伍人数
	private String chief;//负责人
	private String chiefPhone;//负责人联系电话
	private String dutyPhone;//值班电话
	private String fax;//传真
	private String remark;//备注
	private Boolean validity;//可用性，0-不可用，1-可用
	//private Integer departId;
	private SysDepart sysDepart;//所属公司
	private String x;//坐标X
	private String y;//坐标Y
	private String writer;//添加人
	private Date writeTime;//添加时间
	private String vaccineMan;//审核人
	private Date vaccineTime;//审核人

	private String specialty;//专长描述
	private String areaCode;//行政区域代码
	private String zipCode;//邮政编码

	private String leaderShip;//分管领导
	private String leaderPhone;//分管领导联系电话
	private Integer vaccine;//审核状态,0-未审核，1-已审核，2-审核不通过
	private Date estaDate;//成立日期
	
	
	private String type;//队伍类型
	private String addr;//地址
	private String qualification;//队伍资质
	
	private String area;//所在区域
	private String field;//擅长领域

	// Constructors

	/** default constructor */
	public DangerTeam() {
	}
	public DangerTeam(String teamName, Integer number, String chief,
			String chiefPhone, String dutyPhone, String fax, String remark,
			Boolean validity,SysDepart sysDepart, String x, String y,String writer,Date writeTime,String vaccineMan,Date vaccineTime,
			String specialty,String areaCode,String zipCode,String leaderShip,String leaderPhone,Integer vaccine,Date estaDate,String type,
			String addr,String qualification,String area,String field) {
		this.teamName = teamName;
		this.number = number;
		this.chief = chief;
		this.chiefPhone = chiefPhone;
		this.dutyPhone = dutyPhone;
		this.fax = fax;
		this.remark = remark;
		this.validity = validity;
		this.sysDepart = sysDepart;
		this.x = x;
		this.y = y;
		this.writer = writer;
		this.writeTime = writeTime;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
		
		
		this.specialty = specialty;//专长描述
		this.areaCode = areaCode;//行政区域代码
		this.zipCode = zipCode;//邮政编码
		
		this.leaderShip = leaderShip;//分管领导
		this.leaderPhone = leaderPhone;//分管领导联系电话
		this.vaccine = vaccine;//可用性，0-不可用，1-可用
		this.estaDate =estaDate;//成立日期
		
		this.type = type; //队伍类型
		this.addr = addr; //地址
		this.qualification = qualification;//队伍资质
		
		this.area = area;
		this.field = field;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "team_name", length = 20)
	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "chief", length = 10)
	public String getChief() {
		return this.chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	@Column(name = "chief_phone", length = 20)
	public String getChiefPhone() {
		return this.chiefPhone;
	}

	public void setChiefPhone(String chiefPhone) {
		this.chiefPhone = chiefPhone;
	}

	@Column(name = "duty_phone", length = 20)
	public String getDutyPhone() {
		return this.dutyPhone;
	}

	public void setDutyPhone(String dutyPhone) {
		this.dutyPhone = dutyPhone;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@JsonIgnoreProperties(value={"departPid","departValue","departContact","departTel","departAddress","departEmail","paramsId","departBz","departValidity","departType","departFax","departZipcode","departLicence","departForm","departRedistration","departRepresentative","departSupervisor","departWorkerNum","departTechnicalNum","departSafetyNum","departRegisteredCapital","departBusinessAddress","departBusinessProperty","departStorageAddress","departStorageProperty","departBuilding","departStoragePower","departSystemName","departArea","files","images","vaccine","vaccineMan","vaccineTime"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id")
	public SysDepart getSysDepart() {
		return sysDepart;
	}
	
	public void setSysDepart(SysDepart sysDepart) {
		this.sysDepart = sysDepart;
	}
	
	@Column(name = "x", length = 20)
	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Column(name = "y", length = 20)
	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
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
	
	
	
	@Column(name = "specialty")
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@Column(name = "area_code")
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Column(name = "zip_code")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	@Column(name = "leader_ship")
	public String getLeaderShip() {
		return leaderShip;
	}
	public void setLeaderShip(String leaderShip) {
		this.leaderShip = leaderShip;
	}
	
	@Column(name = "leader_phone")
	public String getLeaderPhone() {
		return leaderPhone;
	}
	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}
	
	@Column(name = "vaccine")
	public Integer getVaccine() {
		return vaccine;
	}
	public void setVaccine(Integer vaccine) {
		this.vaccine = vaccine;
	}

	@JsonSerialize(using =com.fasterxml.jackson.databind.ser.std.DateSerializer.class) 
	@Column(name = "esta_date", length = 23)
	public Date getEstaDate() {
		return estaDate;
	}
	public void setEstaDate(Date estaDate) {
		this.estaDate = estaDate;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "addr")
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Column(name = "qualification")
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	@Column(name = "area",length = 50)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name = "field",length = 50)
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
}