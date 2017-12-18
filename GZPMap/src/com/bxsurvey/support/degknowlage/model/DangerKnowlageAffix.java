package com.bxsurvey.support.degknowlage.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "danger_knowlage_affix", schema = "dbo")
public class DangerKnowlageAffix implements java.io.Serializable{
	private Integer id;//主键ID
	private String name;//文件名
	private String path;//存放路径
	private String type;//附件类型
	private String fileAfter;//文件后缀
	private String writer;//添加人
	private Date uploadTime;//添加时间
	private Boolean validity;//可用性，0-不可用，1-可用
	private Integer knowlageId;//知识主表ID
	
	public DangerKnowlageAffix() {
		super();
	}
	public DangerKnowlageAffix(Integer id, String name, String path,
			String type, String fileAfter, String writer, Date uploadTime,
			Boolean validity) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.type = type;
		this.fileAfter = fileAfter;
		this.writer = writer;
		this.uploadTime = uploadTime;
		this.validity = validity;
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
	
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "file_after")
	public String getFileAfter() {
		return fileAfter;
	}
	public void setFileAfter(String fileAfter) {
		this.fileAfter = fileAfter;
	}
	
	@Column(name = "writer")
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "upload_time", length = 23)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Column(name = "validity")
	public Boolean getValidity() {
		return validity;
	}
	public void setValidity(Boolean validity) {
		this.validity = validity;
	}
	
	@Column(name = "knowlage_id")
	public Integer getKnowlageId() {
		return knowlageId;
	}
	public void setKnowlageId(Integer knowlageId) {
		this.knowlageId = knowlageId;
	}
	
	
	
	
}
