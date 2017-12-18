package com.bxsurvey.rmt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bxsurvey.danger.DangerDevice.model.DangerDevi;

/**
 * RtmDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rtm_dictionary", schema = "dbo")
public class RtmDictionary implements java.io.Serializable {

	// Fields

	private Integer dictionaryId;
	private DangerDevi dangerDevi;
	private Boolean validity;
	private String bz;

	// Constructors

	/** default constructor */
	public RtmDictionary() {
	}

	/** full constructor */
	public RtmDictionary(DangerDevi dangerDevi, Boolean validity, String bz) {
		this.dangerDevi = dangerDevi;
		this.validity = validity;
		this.bz = bz;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dictionary_id", unique = true, nullable = false)
	public Integer getDictionaryId() {
		return this.dictionaryId;
	}

	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	public DangerDevi getDangerDevi() {
		return this.dangerDevi;
	}

	public void setDangerDevi(DangerDevi dangerDevi) {
		this.dangerDevi = dangerDevi;
	}

	@Column(name = "validity")
	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	@Column(name = "bz")
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}