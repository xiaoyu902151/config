package com.bxsurvey.support.law.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bxsurvey.sys.params.model.SysParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "law_law", schema = "dbo")
public class Law implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lawName;
	private String descrition;
	private SysParams sysParams;
	private String fj;
	
	public Law() {
		super();
	}

	public Law(String lawName, String descrition, String fj,SysParams sysParams) {
		super();
		this.lawName = lawName;
		this.descrition = descrition;
		this.sysParams = sysParams;
		this.fj = fj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "law_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="law_name")
	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	@Column(name="descrition")
	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	@Column(name="fj")
	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	@JsonIgnoreProperties(value={"params_id","params_type","params_value","params_bz","params_validity"})
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type")
	public SysParams getSysParams() {
		return sysParams;
	}

	public void setSysParams(SysParams sysParams) {
		this.sysParams = sysParams;
	}

	
	
	
	
	
}
