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
@Table(name = "plan_stand", schema = "dbo")
public class PlanStand {

	private Integer id;//主键标识
	private String planType;//事故类型
	private String suppliesType;//物资名称
	private Integer  oneLevelStand;//一级要求
	private Integer  twoLevelStand;//二级要求
	private Integer  threeLevelStand;//三级要求
//	private Integer  planId;
	private PlanPlan planPlan;//预案ID

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "plan_type")
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	@Column(name = "supplies_type")
	public String getSuppliesType() {
		return suppliesType;
	}
	public void setSuppliesType(String suppliesType) {
		this.suppliesType = suppliesType;
	}
	
	@Column(name = "one_level_stand")
	public Integer getOneLevelStand() {
		return oneLevelStand;
	}
	public void setOneLevelStand(Integer oneLevelStand) {
		this.oneLevelStand = oneLevelStand;
	}
	
	@Column(name = "two_level_stand")
	public Integer getTwoLevelStand() {
		return twoLevelStand;
	}
	public void setTwoLevelStand(Integer twoLevelStand) {
		this.twoLevelStand = twoLevelStand;
	}
	
	@Column(name = "three_level_stand")
	public Integer getThreeLevelStand() {
		return threeLevelStand;
	}
	public void setThreeLevelStand(Integer threeLevelStand) {
		this.threeLevelStand = threeLevelStand;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id")
	public PlanPlan getPlanPlan() {
		return planPlan;
	}
	public void setPlanPlan(PlanPlan planPlan) {
		this.planPlan = planPlan;
	}
	
/*	@Column(name = "plan_id")
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}*/
	
	
	
	

}
