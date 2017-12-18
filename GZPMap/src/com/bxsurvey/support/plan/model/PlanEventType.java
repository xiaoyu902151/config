package com.bxsurvey.support.plan.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan_event_type", schema = "dbo")
public class PlanEventType implements java.io.Serializable{

	private Integer id;//主键标识
	private String eventName;//事故类型名称
	private Integer pid;//父ID
	private Integer order;//顺序
	
	public PlanEventType() {
		super();
	}
	
	public PlanEventType(Integer id, String eventName, Integer pid,
			Integer order) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.pid = pid;
		this.order = order;
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
	
	@Column(name = "event_name")
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Column(name = "pid")
	public Integer getPid() {
		return pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	@Column(name = "order")
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
}
