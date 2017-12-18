package com.bxsurvey.comd.ComdExpert.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import net.framework.utils.json.DateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ComdExpert entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comd_expert", schema = "dbo")
public class ComdExpert implements java.io.Serializable {

	// Fields
	private Integer expertId;
	private String expertName;
	private String sex;
	private String nation;
	private Date birthday;
	private String poli;
	private String health;
	private String type;
	private String scopes;
	private String specialty;
	private String cardId;
	private String techTitl;
	private String post;
	private Date operTime;
	private String degrees;
	private String gradSch;
	private String unit;
	private String working;
	private String unitAddr;
	private String mailAddr;
	private String zipCode;
	private String offiPhone;
	private String mobile;
	private String homeAddr;
	private String homePhone;
	private String email;
	private String fax;
	private String resume;
	private String remark;
	private String x;
	private String y;
	private Boolean validity;
	
	private String writer;
	private Date writeTime;
	
	private String vaccineMan;
	private Date vaccineTime;
	
	private String level;
	private String field;

	@Column(name = "x")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
	
	@Column(name = "y")
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}



	// Constructors

	/** default constructor */
	public ComdExpert() {
	}



	/** full constructor */
	public ComdExpert(Integer expertId,String expertName, String sex, String nation,
			Date birthday, String poli, String health, String type,
			String scopes, String specialty, String cardId, String techTitl,
			String post, Date operTime, String degrees, String gradSch,
			String unit, String working, String unitAddr, String mailAddr,
			String zipCode, String offiPhone, String mobile, String homeAddr,
			String homePhone, String email, String fax, String resume,
			String remark, Boolean validity,
			String vaccineMan, Date vaccineTime,String writer,
			Date writeTime,String x,String y,String level,String field) {
		this.expertId = expertId;
		this.expertName = expertName;
		this.sex = sex;
		this.nation = nation;
		this.birthday = birthday;
		this.poli = poli;
		this.health = health;
		this.type = type;
		this.scopes = scopes;
		this.specialty = specialty;
		this.cardId = cardId;
		this.techTitl = techTitl;
		this.post = post;
		this.operTime = operTime;
		this.degrees = degrees;
		this.gradSch = gradSch;
		this.unit = unit;
		this.working = working;
		this.unitAddr = unitAddr;
		this.mailAddr = mailAddr;
		this.zipCode = zipCode;
		this.offiPhone = offiPhone;
		this.mobile = mobile;
		this.homeAddr = homeAddr;
		this.homePhone = homePhone;
		this.email = email;
		this.fax = fax;
		this.resume = resume;
		this.remark = remark;

		this.validity = validity;
		this.vaccineMan= vaccineMan;
		this.vaccineTime =vaccineTime; 
		this.writer = writer;
		this.writeTime = writeTime;
		this.x = x;
		this.y = y;
		this.level = level;
		this.field = field;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "expert_id", unique = true, nullable = false)
	public Integer getExpertId() {
		return this.expertId;
	}

	public void setExpertId(Integer expertId) {
		this.expertId = expertId;
	}

	@Column(name = "expert_name", length = 10)
	public String getExpertName() {
		return this.expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	@Column(name = "sex", length = 4)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "nation", length = 10)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@JsonSerialize(using =com.fasterxml.jackson.databind.ser.std.DateSerializer.class) 
	@Column(name = "birthday", length = 23)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "poli", length = 10)
	public String getPoli() {
		return this.poli;
	}

	public void setPoli(String poli) {
		this.poli = poli;
	}

	@Column(name = "health", length = 10)
	public String getHealth() {
		return this.health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "scopes", length = 20)
	public String getScopes() {
		return this.scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	@Column(name = "specialty", length = 20)
	public String getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Column(name = "cardID", length = 20)
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(name = "tech_titl", length = 20)
	public String getTechTitl() {
		return this.techTitl;
	}

	public void setTechTitl(String techTitl) {
		this.techTitl = techTitl;
	}

	@Column(name = "post", length = 50)
	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@JsonSerialize(using =com.fasterxml.jackson.databind.ser.std.DateSerializer.class) 
	@Column(name = "oper_time", length = 23)
	public Date getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(name = "degrees", length = 10)
	public String getDegrees() {
		return this.degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	@Column(name = "grad_sch", length = 20)
	public String getGradSch() {
		return this.gradSch;
	}

	public void setGradSch(String gradSch) {
		this.gradSch = gradSch;
	}

	@Column(name = "unit", length = 50)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "working", length = 10)
	public String getWorking() {
		return this.working;
	}

	public void setWorking(String working) {
		this.working = working;
	}

	@Column(name = "unit_addr", length = 50)
	public String getUnitAddr() {
		return this.unitAddr;
	}

	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}

	@Column(name = "mail_addr", length = 50)
	public String getMailAddr() {
		return this.mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

	@Column(name = "zip_code", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "offi_phone", length = 20)
	public String getOffiPhone() {
		return this.offiPhone;
	}

	public void setOffiPhone(String offiPhone) {
		this.offiPhone = offiPhone;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "home_addr", length = 50)
	public String getHomeAddr() {
		return this.homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	@Column(name = "home_phone", length = 20)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "email", length = 20)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "resume", length = 200)
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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
		return validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
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

	@JsonSerialize(using =net.framework.utils.json.DictSerializer.class)
	@Column(name = "level",length = 50)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@JsonSerialize(using =net.framework.utils.json.DictSerializer.class)
	@Column(name = "field",length = 50)
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	
}