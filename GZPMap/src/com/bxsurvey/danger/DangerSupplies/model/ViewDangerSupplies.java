package com.bxsurvey.danger.DangerSupplies.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "v_danger_supplies")
public class ViewDangerSupplies implements java.io.Serializable{
	private Integer suppliesId;//supplies_id,--物资主键标识
	private Integer detailId;//detail_id,--物资详情主键
    private String suppliesTypeName;//supplies_type_name,--物资类型名称
    private String name;//name,--物资名称
	private String suppliesType;//supplies_type,--物资类型
	private String size;//size,--物资型号
	private String route;//route,----物资用途
    private String num;//,--物资数量
	private Boolean validity;//--可用性
	private Integer  vaccine;//--审核状态
	private String  writer;//--添加人
	private Date  writeTime;//write_time;--添加时间
	private Integer departId;// depart_id;//
	private String  x;//--坐标X
	private String  y;//--坐标Y
	private String vaccineMan;//  vaccine_man,--审核人
	private Date vaccineTime;// vaccine_time--审核时间
	
	private String suppliesDetailType;//物资类型
	private String suppliesDetailTypeName;//物资类型名称
	private Integer orderNum;//排序
	
	public ViewDangerSupplies() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViewDangerSupplies(Integer suppliesId, Integer detailId,
			String suppliesTypeName, String name, String suppliesType,
			String size, String route, String num, Boolean validity,
			Integer vaccine, String writer, Date writeTime, Integer departId,
			String x, String y, String vaccineMan, Date vaccineTime) {
		super();
		this.suppliesId = suppliesId;
		this.detailId = detailId;
		this.suppliesTypeName = suppliesTypeName;
		this.name = name;
		this.suppliesType = suppliesType;
		this.size = size;
		this.route = route;
		this.num = num;
		this.validity = validity;
		this.vaccine = vaccine;
		this.writer = writer;
		this.writeTime = writeTime;
		this.departId = departId;
		this.x = x;
		this.y = y;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;
	}
	
	@Column(name ="supplies_id")
	public Integer getSuppliesId() {
		return suppliesId;
	}
	public void setSuppliesId(Integer suppliesId) {
		this.suppliesId = suppliesId;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name ="detail_id")
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	@Column(name ="supplies_type_name")
	public String getSuppliesTypeName() {
		return suppliesTypeName;
	}
	public void setSuppliesTypeName(String suppliesTypeName) {
		this.suppliesTypeName = suppliesTypeName;
	}
	@Column(name ="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="supplies_type")
	public String getSuppliesType() {
		return suppliesType;
	}
	public void setSuppliesType(String suppliesType) {
		this.suppliesType = suppliesType;
	}
	@Column(name ="size")
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Column(name ="route")
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	@Column(name ="num")
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Column(name ="validity")
	public Boolean getValidity() {
		return validity;
	}
	public void setValidity(Boolean validity) {
		this.validity = validity;
	}
	@Column(name ="vaccine")
	public Integer getVaccine() {
		return vaccine;
	}
	public void setVaccine(Integer vaccine) {
		this.vaccine = vaccine;
	}
	@Column(name ="writer")
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name ="write_time")
	public Date getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}
	@Column(name ="depart_id")
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	@Column(name ="x")
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	@Column(name ="y")
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	@Column(name ="vaccine_man")
	public String getVaccineMan() {
		return vaccineMan;
	}
	public void setVaccineMan(String vaccineMan) {
		this.vaccineMan = vaccineMan;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name ="vaccine_time")
	public Date getVaccineTime() {
		return vaccineTime;
	}
	public void setVaccineTime(Date vaccineTime) {
		this.vaccineTime = vaccineTime;
	}
	
	@Column(name ="supplies_detail_type")
	public String getSuppliesDetailType() {
		return suppliesDetailType;
	}
	public void setSuppliesDetailType(String suppliesDetailType) {
		this.suppliesDetailType = suppliesDetailType;
	}
	
	@Column(name ="supplies_detail_type_name")
	public String getSuppliesDetailTypeName() {
		return suppliesDetailTypeName;
	}
	public void setSuppliesDetailTypeName(String suppliesDetailTypeName) {
		this.suppliesDetailTypeName = suppliesDetailTypeName;
	}
	
	@Column(name ="order_num")
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	
}
