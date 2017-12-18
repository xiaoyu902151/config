package com.bxsurvey.support.plan.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan_organ_people", schema = "dbo")
public class PlanOrganPeople {
	
	private Integer id;//主键标识
	private String name;//人员名称
	private String sex;//人员名称
	private String phone;//联系电话
	private String bz;//备注
	private String duty;//职责
	private PlanOrgan planOrgan;//人员名称
	
	
	public PlanOrganPeople() {
		super();
		// TODO Auto-generated constructor stub
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
	
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "bz")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "duty")
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organ_id")
	public PlanOrgan getPlanOrgan() {
		return planOrgan;
	}
	public void setPlanOrgan(PlanOrgan planOrgan) {
		this.planOrgan = planOrgan;
	}
	
	
}
