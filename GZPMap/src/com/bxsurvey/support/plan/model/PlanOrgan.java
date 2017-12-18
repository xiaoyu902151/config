package com.bxsurvey.support.plan.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plan_organ", schema = "dbo")
public class PlanOrgan implements java.io.Serializable{
	private Integer id;//主键标识
	private String name;//机构名称
	private String duty;//机构职责
	private String bz;//备注
	private Integer pid;//主键标识
//	private Integer planId;
	private PlanPlan planPlan;//知识主表ID
	private Set<PlanOrganPeople> planOrganPeoples = new HashSet<PlanOrganPeople>();
	
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
	
	@Column(name = "duty")
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@Column(name = "bz")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Column(name = "pid")
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
//	@Column(name = "plan_id")
//	public Integer getPlanId() {
//		return planId;
//	}
//	public void setPlanId(Integer planId) {
//		this.planId = planId;
//	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id")
	public PlanPlan getPlanPlan() {
		return planPlan;
	}
	public void setPlanPlan(PlanPlan planPlan) {
		this.planPlan = planPlan;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "planOrgan")
	public Set<PlanOrganPeople> getPlanOrganPeoples() {
		return planOrganPeoples;
	}
	
	public void setPlanOrganPeoples(Set<PlanOrganPeople> planOrganPeoples) {
		this.planOrganPeoples = planOrganPeoples;
	}
	
	
	
	
}
