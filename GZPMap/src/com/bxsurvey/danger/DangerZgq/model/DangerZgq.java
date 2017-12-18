package com.bxsurvey.danger.DangerZgq.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="danger_zgq",schema="dbo")
public class DangerZgq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer zgqId;
	private String code;
	private String name;
	private String schjgnq;
	private String circle;
	private Boolean isProtection;
	private String proCircle;
	private Integer num;
	private String line;
	private String writer;
	private Date writeTime;
	private Boolean validity;
	private Integer vaccine;
	private String files;
	private Integer departId;
	private String images;
	private String vaccineMan;
	private Date vaccineTime;
	private String plotGeo;
	
	// Constructors
	/** default constructor */
	public DangerZgq() {
	}

	public DangerZgq(String code, String name, String schjgnq, String circle,
			Boolean isProtection, String proCircle, Integer num, String line,
			String writer, Date writeTime, Boolean validity, Integer vaccine,
			String files, Integer departId, String images, String vaccineMan,
			Date vaccineTime, String plotGeo) {
		this.code = code;
		this.name = name;
		this.schjgnq = schjgnq;
		this.circle = circle;
		this.isProtection = isProtection;
		this.proCircle = proCircle;
		this.num = num;
		this.line = line;
		this.writer = writer;
		this.writeTime = writeTime;
		this.validity = validity;
		this.vaccine = vaccine;
		this.files = files;
		this.departId = departId;
		this.images = images;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
		this.plotGeo = plotGeo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "zgq_id",unique = true, nullable = false)
	public Integer getZgqId() {
		return zgqId;
	}


	public void setZgqId(Integer zgqId) {
		this.zgqId = zgqId;
	}

	@Column(name = "code", length = 32)
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "schjgnq", length = 20)
	public String getSchjgnq() {
		return schjgnq;
	}


	public void setSchjgnq(String schjgnq) {
		this.schjgnq = schjgnq;
	}

	@Column(name = "circle", length = 32)
	public String getCircle() {
		return circle;
	}


	public void setCircle(String circle) {
		this.circle = circle;
	}

	@Column(name = "is_protection")
	public Boolean getIsProtection() {
		return isProtection;
	}


	public void setIsProtection(Boolean isProtection) {
		this.isProtection = isProtection;
	}

	@Column(name = "pro_circle", length = 32)
	public String getProCircle() {
		return proCircle;
	}


	public void setProCircle(String proCircle) {
		this.proCircle = proCircle;
	}

	@Column(name = "num")
	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "line", length = 32)
	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "writer", length = 20)
	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "write_time", length = 23)
	public Date getWriteTime() {
		return writeTime;
	}


	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return validity;
	}


	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "vaccine")
	public Integer getVaccine() {
		return vaccine;
	}


	public void setVaccine(Integer vaccine) {
		this.vaccine = vaccine;
	}

	@Column(name = "files", length = 100)
	public String getFiles() {
		return files;
	}


	public void setFiles(String files) {
		this.files = files;
	}

	@Column(name = "depart_id")
	public Integer getDepartId() {
		return departId;
	}


	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	@Column(name = "images", length = 100)
	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}

	@Column(name = "vaccine_man", length = 20)
	public String getVaccineMan() {
		return vaccineMan;
	}


	public void setVaccineMan(String vaccineMan) {
		this.vaccineMan = vaccineMan;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vaccine_time", length = 23)
	public Date getVaccineTime() {
		return vaccineTime;
	}


	public void setVaccineTime(Date vaccineTime) {
		this.vaccineTime = vaccineTime;
	}

	@Column(name="plot_geo")
	public String getPlotGeo() {
		return plotGeo;
	}

	
	public void setPlotGeo(String plotGeo) {
		this.plotGeo = plotGeo;
	}
	


}
