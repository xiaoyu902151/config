package com.bxsurvey.danger.DangerTeam.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "danger_team_detail", schema = "dbo")
public class DangerTeamDetail implements java.io.Serializable  {
	private Integer id;//主键标识
	private String teamName;//队伍名称
	private Integer number;//队伍人数
	private String chief;//负责人
	private String chiefPhone;//负责人联系电话
	private String dutyPhone;//值班电话
	private String fax;//传真
	private String remark;//备注
	private String writer;//添加人
	private Date writeTime;//添加时间
	private String specialty;//专长描述
	private String areaCode;//行政区域代码
	private String zipCode;//邮政编码
	private String leaderShip;//分管领导
	private String leaderPhone;//分管领导联系电话
	private Date estaDate;//成立日期
	private String type;//队伍类型
	private String addr;//地址
	private String qualification;//队伍资质
//	private Integer teamId ;//队伍主表ID
	private DangerTeam dangerTeam;//队伍主表
	private Integer pid;//父ID
//	private Integer departId;
	
	private String area;//所在区域
	private String field;//擅长领域
	
	public DangerTeamDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DangerTeamDetail(Integer id, String teamName, Integer number,
			String chief, String chiefPhone, String dutyPhone, String fax,
			String remark, String writer, Date writeTime, String specialty,
			String areaCode, String zipCode, String leaderShip, String leaderPhone,
			Date estaDate, String type, String addr, String qualification,
			DangerTeam dangerTeam,String area,String field) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.number = number;
		this.chief = chief;
		this.chiefPhone = chiefPhone;
		this.dutyPhone = dutyPhone;
		this.fax = fax;
		this.remark = remark;
		this.writer = writer;
		this.writeTime = writeTime;
		this.specialty = specialty;
		this.areaCode = areaCode;
		this.zipCode = zipCode;
		this.leaderShip = leaderShip;
		this.leaderPhone = leaderPhone;
		this.estaDate = estaDate;
		this.type = type;
		this.addr = addr;
		this.qualification = qualification;
		this.dangerTeam = dangerTeam;
		this.area = area;
		this.field = field;
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
	
	@Column(name = "team_name")
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Column(name = "chief")
	public String getChief() {
		return chief;
	}
	public void setChief(String chief) {
		this.chief = chief;
	}
	
	@Column(name = "chief_phone")
	public String getChiefPhone() {
		return chiefPhone;
	}
	public void setChiefPhone(String chiefPhone) {
		this.chiefPhone = chiefPhone;
	}
	
	@Column(name = "duty_phone")
	public String getDutyPhone() {
		return dutyPhone;
	}
	public void setDutyPhone(String dutyPhone) {
		this.dutyPhone = dutyPhone;
	}
	
	@Column(name = "fax")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	@Temporal(TemporalType.DATE)
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
	
//	@Column(name = "team_id")
//	public Integer getTeamId() {
//		return teamId;
//	}
//	public void setTeamId(Integer teamId) {
//		this.teamId = teamId;
//	}
	
	
	
	@Column(name = "pid")
	public Integer getPid() {
		return pid;
	}
	@JsonIgnoreProperties(value={"dangerZgq","suppliesType","departId","x","y","writer","writeTime","vaccineMan","vaccineTime","vaccine","size","route","num","validity","remark"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	public DangerTeam getDangerTeam() {
		return dangerTeam;
	}
	public void setDangerTeam(DangerTeam dangerTeam) {
		this.dangerTeam = dangerTeam;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
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
