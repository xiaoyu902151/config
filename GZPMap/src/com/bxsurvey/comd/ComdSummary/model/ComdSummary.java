package com.bxsurvey.comd.ComdSummary.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bxsurvey.comd.ComdEvent.model.ComdEvent;

/**
 * ComdSummary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comd_summary", schema = "dbo")
public class ComdSummary implements java.io.Serializable {

	// Fields

	private Integer summaryId;
	private ComdEvent comdEvent;
	private String theme;
	private String eventDetail;
	private String dispProcess;
	private String summary;
	private String preparer;
	private String mobile;
	private Date time;
	private String chief;
	private String fileCode;

	// Constructors

	/** default constructor */
	public ComdSummary() {
	}

	/** full constructor */
	public ComdSummary(ComdEvent comdEvent, String theme, String eventDetail,
			String dispProcess, String summary, String preparer, String mobile,
			Date time, String chief,String fileCode) {
		this.comdEvent = comdEvent;
		this.theme = theme;
		this.eventDetail = eventDetail;
		this.dispProcess = dispProcess;
		this.summary = summary;
		this.preparer = preparer;
		this.mobile = mobile;
		this.time = time;
		this.chief = chief;
		this.fileCode = fileCode;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "summary_id", unique = true, nullable = false)
	public Integer getSummaryId() {
		return this.summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	public ComdEvent getComdEvent() {
		return this.comdEvent;
	}

	public void setComdEvent(ComdEvent comdEvent) {
		this.comdEvent = comdEvent;
	}

	@Column(name = "theme", length = 20)
	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Column(name = "event_detail", length = 200)
	public String getEventDetail() {
		return this.eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}

	@Column(name = "disp_process", length = 200)
	public String getDispProcess() {
		return this.dispProcess;
	}

	public void setDispProcess(String dispProcess) {
		this.dispProcess = dispProcess;
	}

	@Column(name = "summary", length = 200)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "preparer", length = 10)
	public String getPreparer() {
		return this.preparer;
	}

	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "time", length = 23)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "chief", length = 10)
	public String getChief() {
		return this.chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	@Column(name = "file_code", length = 30)
	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	
}