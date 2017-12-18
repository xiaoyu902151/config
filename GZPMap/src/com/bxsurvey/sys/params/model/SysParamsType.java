package com.bxsurvey.sys.params.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_params_type", schema = "dbo")
public class SysParamsType implements Serializable{
	private Integer id;
	private String name;
	private String paramsType;
	
	public SysParamsType() {
		super();
	}
	public SysParamsType(Integer id, String name, String paramsType) {
		super();
		this.id = id;
		this.name = name;
		this.paramsType = paramsType;
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
	
	@Column(name="name",length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="params_type",length=30)
	public String getParamsType() {
		return paramsType;
	}
	
	public void setParamsType(String paramsType) {
		this.paramsType = paramsType;
	}
	
	
	
	

}
