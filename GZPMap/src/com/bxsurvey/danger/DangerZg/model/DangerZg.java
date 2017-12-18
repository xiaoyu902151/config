package com.bxsurvey.danger.DangerZg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
import com.bxsurvey.support.degknowlage.model.DangerKnowlage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "danger_zg", schema = "dbo")
public class DangerZg implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer zgId;
	private DangerZgq dangerZgq;
	private String code;
	private String name;
	private String shape;
	private String zgForm;
	private String installForm;
	private String material;
	private String diameter;
	private String volume;
	private String matter;
	private String daySum;
	private String sjyl;
	private String sjgzyl;
	private String sjwd;
	private String sjgzwd;
	private String grade;
	private String sjsynx;
	private Date tcsj;
	private String writer;
	private Date writeTime;
	private Boolean validity;
	private Integer vaccine;
	private Integer departId;
	private String files;
	private String images;
	private String vaccineMan;
	private Date vaccineTime;
	private Integer typeId;
	private Integer height;
	private String plotGeo;
	private DangerKnowlage dangerKnowlage;
	private String x;
	private String y;
	private String fId;
	
	/** default constructor */
	public DangerZg() {
	}

	public DangerZg(DangerZgq dangerZgq, String code, String name,
			String shape, String zgForm, String installForm, String material,
			String diameter, String volume, String matter, String daySum,
			String sjyl, String sjgzyl, String sjwd, String sjgzwd,
			String grade, String sjsynx, Date tcsj, String writer,
			Date writeTime, Boolean validity, Integer vaccine,
			Integer departId, String files, String images, String vaccineMan,
			Date vaccineTime, Integer typeId, Integer height, String plotGeo,DangerKnowlage dangerKnowlage,
			String x,String y,String fId) {
		this.dangerZgq = dangerZgq;
		this.code = code;
		this.name = name;
		this.shape = shape;
		this.zgForm = zgForm;
		this.installForm = installForm;
		this.material = material;
		this.diameter = diameter;
		this.volume = volume;
		this.matter = matter;
		this.daySum = daySum;
		this.sjyl = sjyl;
		this.sjgzyl = sjgzyl;
		this.sjwd = sjwd;
		this.sjgzwd = sjgzwd;
		this.grade = grade;
		this.sjsynx = sjsynx;
		this.tcsj = tcsj;
		this.writer = writer;
		this.writeTime = writeTime;
		this.validity = validity;
		this.vaccine = vaccine;
		this.departId = departId;
		this.files = files;
		this.images = images;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
		this.typeId = typeId;
		this.height = height;
		this.plotGeo = plotGeo;
		this.dangerKnowlage = dangerKnowlage;
		this.x = x;
		this.y = y;
		this.fId = fId;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "zg_id", unique = true, nullable = false)
	public Integer getZgId() {
		return zgId;
	}

	public void setZgId(Integer zgId) {
		this.zgId = zgId;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zgq_id")
	public DangerZgq getDangerZgq() {
		return dangerZgq;
	}

	public void setDangerZgq(DangerZgq dangerZgq) {
		this.dangerZgq = dangerZgq;
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

	@Column(name = "shape", length = 32)
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	@Column(name = "zg_form", length = 32)
	public String getZgForm() {
		return zgForm;
	}

	public void setZgForm(String zgForm) {
		this.zgForm = zgForm;
	}

	@Column(name = "install_form", length = 32)
	public String getInstallForm() {
		return installForm;
	}

	public void setInstallForm(String installForm) {
		this.installForm = installForm;
	}

	@Column(name = "material", length = 32)
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "diameter", length = 32)
	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	@Column(name = "volume", length = 32)
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "matter", length = 32)
	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	@Column(name = "day_sum", length = 32)
	public String getDaySum() {
		return daySum;
	}

	public void setDaySum(String daySum) {
		this.daySum = daySum;
	}

	@Column(name = "sjyl", length = 32)
	public String getSjyl() {
		return sjyl;
	}

	public void setSjyl(String sjyl) {
		this.sjyl = sjyl;
	}

	@Column(name = "sjgzyl", length = 32)
	public String getSjgzyl() {
		return sjgzyl;
	}

	public void setSjgzyl(String sjgzyl) {
		this.sjgzyl = sjgzyl;
	}

	@Column(name = "sjwd", length = 32)
	public String getSjwd() {
		return sjwd;
	}

	public void setSjwd(String sjwd) {
		this.sjwd = sjwd;
	}

	@Column(name = "sjgzwd", length = 32)
	public String getSjgzwd() {
		return sjgzwd;
	}

	public void setSjgzwd(String sjgzwd) {
		this.sjgzwd = sjgzwd;
	}

	@Column(name = "grade", length = 32)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "sjsynx", length = 32)
	public String getSjsynx() {
		return sjsynx;
	}

	public void setSjsynx(String sjsynx) {
		this.sjsynx = sjsynx;
	}

	@Column(name = "tcsj", length = 23)
	public Date getTcsj() {
		return tcsj;
	}

	public void setTcsj(Date tcsj) {
		this.tcsj = tcsj;
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

	@Column(name = "depart_id")
	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	@Column(name = "files", length = 100)
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
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

	@Column(name = "type_id")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name="height")
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Column(name = "plot_geo")
	public String getPlotGeo() {
		return plotGeo;
	}

	public void setPlotGeo(String plotGeo) {
		this.plotGeo = plotGeo;
	}

	@JsonIgnoreProperties(value={"enName","hwbh","unbh","fzs","fzl","cash","wgyxz","rd","xdmd","fd","bhzqy","rjx","qrtj","dx","jkwh","jjff","rsx","rsfjw","sd","yrwd","bzsx","bzxx","wxtx","cytj","xlcl","gtfh","czclff","yszysx","cczysx","xlfh","hzfh","mhcs","jjcs"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "knowlage_id")
	public DangerKnowlage getDangerKnowlage() {
		return dangerKnowlage;
	}

	public void setDangerKnowlage(DangerKnowlage dangerKnowlage) {
		this.dangerKnowlage = dangerKnowlage;
	}

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

	@Column(name = "f_id")
	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}



}
