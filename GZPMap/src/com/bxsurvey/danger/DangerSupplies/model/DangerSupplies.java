package com.bxsurvey.danger.DangerSupplies.model;

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

import com.bxsurvey.sys.depart.model.SysDepart;
import com.bxsurvey.sys.params.model.SysParams;
//import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
//import com.bxsurvey.sys.depart.model.SysDepart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * DangerSupplies entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "danger_supplies", schema = "dbo")
public class DangerSupplies implements java.io.Serializable {

	// Fields
	private Integer id;//主键标识
//	private DangerZgq dangerZgq;
	private String name;//名称
	private Integer suppliesType;//物资类型(关联到应急物资知识库)
//	private Integer departId;//所属公司id
	private String x;//坐标X
	private String y;//坐标Y
	private String writer;//添加人
	private Date writeTime;//添加时间
	private String vaccineMan;//审核人
	private Date vaccineTime;//审核时间
	private Integer vaccine;//审核状态,0-未审核，1-已审核，2-审核不通过
	private String size;//型号
	private String route;//用途
	private Integer num;//数量
	private Boolean validity;//可用性，0-不可用，1-可用
    private String remark;//备注
    private String volume;//容量
	
    private SysParams sysParams;//物资类型


	private SysDepart sysDepart;
	private String area;


	// Constructors

	/** default constructor */
	public DangerSupplies() {
	}

	/** full constructor */


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	public DangerSupplies(Integer id,
			Integer suppliesType, String name, String size, String route,
			Integer num, Boolean validity, Integer vaccine, String writer,
			Date writeTime, String x, String y,
			String vaccineMan, Date vaccineTime,SysDepart sysDepart,String volume,SysParams sysParams,String area) {
		super();
		this.id = id;
		this.suppliesType = suppliesType;
		this.name = name;
		this.size = size;
		this.route = route;
		this.num = num;
		this.validity = validity;
		this.vaccine = vaccine;
		this.writer = writer;
		this.writeTime = writeTime;
		this.sysDepart = sysDepart;
		this.x = x;
		this.y = y;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
		this.volume = volume;
		this.sysParams = sysParams;
		this.area = area;
	}


	public void setId(Integer id) {
		this.id = id;
	}

//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "zgq_id")
//	public DangerZgq getDangerZgq() {
//		return this.dangerZgq;
//	}

//	public void setDangerZgq(DangerZgq dangerZgq) {
//		this.dangerZgq = dangerZgq;
//	}

	@Column(name = "supplies_type")
	public Integer getSuppliesType() {
		return this.suppliesType;
	}

	public void setSuppliesType(Integer suppliesType) {
		this.suppliesType = suppliesType;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "size", length = 20)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "route", length = 30)
	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Column(name = "num", length = 10)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "vaccine")
	public Integer getVaccine() {
		return this.vaccine;
	}

	public void setVaccine(Integer vaccine) {
		this.vaccine = vaccine;
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
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "volume")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	@JsonIgnoreProperties(value={"paramsType","paramsValue","paramsBz","paramsValidity"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supplies_type", insertable = false, updatable = false)
	public SysParams getSysParams() {
		return sysParams;
	}

	public void setSysParams(SysParams sysParams) {
		this.sysParams = sysParams;
	}

	@Column(name = "area",length = 50)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	

}