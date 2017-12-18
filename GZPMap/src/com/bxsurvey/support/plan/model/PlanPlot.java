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
@Table(name = "plan_plot", schema = "dbo")
public class PlanPlot {
	private Integer id;//主键标识
	private String planType;//事故类型
	private String plotName;//方案名称
	private PlanPlan planPlan;//预案ID
	private String plotGeo;//方案内容
	private String bz;//备注
	
	public PlanPlot() {
		super();
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
	
	@Column(name = "plan_type")
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	@Column(name = "plot_name")
	public String getPlotName() {
		return plotName;
	}
	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}
	
	@Column(name = "plot_geo")
	public String getPlotGeo() {
		return plotGeo;
	}
	public void setPlotGeo(String plotGeo) {
		this.plotGeo = plotGeo;
	}
	
	@Column(name = "bz")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id")
	public PlanPlan getPlanPlan() {
		return planPlan;
	}

	public void setPlanPlan(PlanPlan planPlan) {
		this.planPlan = planPlan;
	}
	
	
}
