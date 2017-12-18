package com.bxsurvey.sys.video.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bxsurvey.sys.depart.model.SysDepart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SysVideo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_video", schema = "dbo")
public class SysVideo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private int portId;
	private String name;
	private String bz;
	private Boolean validity;
	private String y;
	private String x;
	//private Integer departId;
	
	private SysDepart sysDepart;
	private Integer type;

	// Constructors

	/** default constructor */
	public SysVideo() {
	}

	/** full constructor */
	public SysVideo(Integer id, String url, int portId, String name,
			String bz, Boolean validity, String y,  String x, SysDepart sysDepart,Integer type) {
		super();
		this.id = id;
		this.url = url;
		this.portId = portId;
		this.name = name;
		this.bz = bz;
		this.validity = validity;
		this.y = y;
		this.x = x;
		this.sysDepart = sysDepart;
		//this.departId = departId;
		this.type = type;
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

	@Column(name = "url", length = 120)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "port_id", length = 10)
	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}
	

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "bz")
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "Y", length = 20)
	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Column(name = "X", length = 20)
	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@JsonIgnoreProperties(value={"departPid","departValue","departContact","departTel","departAddress","departEmail","paramsId","departBz","departValidity","departType","departFax","departZipcode","departLicence","departForm","departRedistration","departRepresentative","departSupervisor","departWorkerNum","departTechnicalNum","departSafetyNum","departRegisteredCapital","departBusinessAddress","departBusinessProperty","departStorageAddress","departStorageProperty","departBuilding","departStoragePower","departSystemName","departArea","files","images","vaccine","vaccineMan","vaccineTime"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "depart_id")
	public SysDepart getSysDepart() {
		return sysDepart;
	}

	public void setSysDepart(SysDepart sysDepart) {
		this.sysDepart = sysDepart;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

//	@Column(name = "depart_id")
//	public Integer getDepartId() {
//		return this.departId;
//	}
//
//	public void setDepartId(Integer departId) {
//		this.departId = departId;
//	}

}