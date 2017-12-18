package com.bxsurvey.danger.DangerSupplies.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "danger_supplies_detail", schema = "dbo")
public class DangerSuppliesDetail implements java.io.Serializable {
	   private Integer id;//主键标识
	   private String name;//名称
	   private String detailType;//物资类型(关联到应急物资知识库)
	   private String size;//型号
       private String route;//用途
       private Integer  num;//数量
       private Boolean validity;//可用性，0-不可用，1-可用
	   private String writer;//添加人
	   private Date writeTime;//添加时间
	   private DangerSupplies dangerSupplies;

	   public DangerSuppliesDetail() {
		super();
	}

	public DangerSuppliesDetail(Integer id, String name, String detailType,
			String size, String route, Integer num,
			Boolean validity, String writer, Date writeTime, DangerSupplies dangerSupplies) {
		super();
		this.id = id;
		this.name = name;
		this.detailType = detailType;
		this.size = size;
		this.route = route;
		this.num = num;
		this.validity = validity;
		this.writer = writer;
		this.writeTime = writeTime;
		this.dangerSupplies = dangerSupplies;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "detail_type")
	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	@Column(name = "size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "route")
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Column(name = "num")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "writer")
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@JsonSerialize(using =com.fasterxml.jackson.databind.ser.std.DateSerializer.class) 
	@Column(name = "write_time", length = 23)
	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}


	@JsonIgnoreProperties(value={"number","chief","chiefPhone","dutyPhone","fax","remark","validity","sysDepart","x","y","writer","writeTime","vaccineMan","vaccineTime","specialty","areaCode","zipCode","leaderShip","leaderPhone","vaccine","estaDate","type","addr","qualification"})//在解析成json时，忽略的子属性
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supplies_id")
	public DangerSupplies getDangerSupplies() {
		return dangerSupplies;
	}

	public void setDangerSupplies(DangerSupplies dangerSupplies) {
		this.dangerSupplies = dangerSupplies;
	}
}
