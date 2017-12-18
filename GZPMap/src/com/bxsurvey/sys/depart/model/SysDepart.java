package com.bxsurvey.sys.depart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * SysDepart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_depart", schema = "dbo")
public class SysDepart implements java.io.Serializable {

	// Fields
	private Integer departId;
	private Integer departPid;
	private String departName;
	private String departValue;
	private String departContact;
	private String departTel;
	private String departAddress;
	private String departEmail;
	private Integer paramsId;
	private String departBz;
	private Boolean departValidity;
	private Boolean departType;
	private String departFax;
	private String departZipcode;
	private String departLicence;
	private String departForm;
	private String departRedistration;
	private String departRepresentative;
	private String departSupervisor;
	private String departWorkerNum;
	private String departTechnicalNum;
	private String departSafetyNum;
	private String departRegisteredCapital;
	private String departBusinessAddress;
	private String departBusinessProperty;
	private String departStorageAddress;
	private String departStorageProperty;
	private String departBuilding;
	private String departStoragePower;
	private String departSystemName;
	private String departArea;
	private String files;
	private String images;
	private Integer vaccine;
	private String vaccineMan;
	private Date vaccineTime;
	private String plotGeo;

	// Constructors

	/** default constructor */
	public SysDepart() {
	}

	/** full constructor */
	public SysDepart(Integer departPid, String departName, String departValue,
			String departContact, String departTel, String departAddress,
			String departEmail, Integer paramsId, String departBz,
			Boolean departValidity, Boolean departType, String departFax,
			String departZipcode, String departLicence, String departForm,
			String departRedistration, String departRepresentative,
			String departSupervisor, String departWorkerNum,
			String departTechnicalNum, String departSafetyNum,
			String departRegisteredCapital, String departBusinessAddress,
			String departBusinessProperty, String departStorageAddress,
			String departStorageProperty, String departBuilding,
			String departStoragePower, String departSystemName,
			String departArea, String files, String images,
			Integer vaccine, String vaccineMan, Date vaccineTime) {
		this.departPid = departPid;
		this.departName = departName;
		this.departValue = departValue;
		this.departContact = departContact;
		this.departTel = departTel;
		this.departAddress = departAddress;
		this.departEmail = departEmail;
		this.paramsId = paramsId;
		this.departBz = departBz;
		this.departValidity = departValidity;
		this.departType = departType;
		this.departFax = departFax;
		this.departZipcode = departZipcode;
		this.departLicence = departLicence;
		this.departForm = departForm;
		this.departRedistration = departRedistration;
		this.departRepresentative = departRepresentative;
		this.departSupervisor = departSupervisor;
		this.departWorkerNum = departWorkerNum;
		this.departTechnicalNum = departTechnicalNum;
		this.departSafetyNum = departSafetyNum;
		this.departRegisteredCapital = departRegisteredCapital;
		this.departBusinessAddress = departBusinessAddress;
		this.departBusinessProperty = departBusinessProperty;
		this.departStorageAddress = departStorageAddress;
		this.departStorageProperty = departStorageProperty;
		this.departBuilding = departBuilding;
		this.departStoragePower = departStoragePower;
		this.departSystemName = departSystemName;
		this.departArea = departArea;
		this.files = files;
		this.images = images;
		this.vaccine = vaccine;
		this.vaccineMan = vaccineMan;
		this.vaccineTime = vaccineTime;

	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "depart_id", unique = true, nullable = false)
	public Integer getDepartId() {
		return this.departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	@Column(name = "depart_pid")
	public Integer getDepartPid() {
		return this.departPid;
	}

	public void setDepartPid(Integer departPid) {
		this.departPid = departPid;
	}

	@Column(name = "depart_name", length = 50)
	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	@Column(name = "depart_value", length = 10)
	public String getDepartValue() {
		return this.departValue;
	}

	public void setDepartValue(String departValue) {
		this.departValue = departValue;
	}

	@Column(name = "depart_contact", length = 20)
	public String getDepartContact() {
		return this.departContact;
	}

	public void setDepartContact(String departContact) {
		this.departContact = departContact;
	}

	@Column(name = "depart_tel", length = 20)
	public String getDepartTel() {
		return this.departTel;
	}

	public void setDepartTel(String departTel) {
		this.departTel = departTel;
	}

	@Column(name = "depart_address", length = 50)
	public String getDepartAddress() {
		return this.departAddress;
	}

	public void setDepartAddress(String departAddress) {
		this.departAddress = departAddress;
	}

	@Column(name = "depart_email", length = 100)
	public String getDepartEmail() {
		return this.departEmail;
	}

	public void setDepartEmail(String departEmail) {
		this.departEmail = departEmail;
	}

	@Column(name = "params_id")
	public Integer getParamsId() {
		return this.paramsId;
	}

	public void setParamsId(Integer paramsId) {
		this.paramsId = paramsId;
	}

	@Column(name = "depart_bz")
	public String getDepartBz() {
		return this.departBz;
	}

	public void setDepartBz(String departBz) {
		this.departBz = departBz;
	}

	@Column(name = "depart_validity")
	public Boolean getDepartValidity() {
		return this.departValidity;
	}

	public void setDepartValidity(Boolean departValidity) {
		this.departValidity = departValidity;
	}

	@Column(name = "depart_type")
	public Boolean getDepartType() {
		return this.departType;
	}

	public void setDepartType(Boolean departType) {
		this.departType = departType;
	}

	@Column(name = "depart_fax", length = 20)
	public String getDepartFax() {
		return this.departFax;
	}

	public void setDepartFax(String departFax) {
		this.departFax = departFax;
	}

	@Column(name = "depart_zipcode", length = 10)
	public String getDepartZipcode() {
		return this.departZipcode;
	}

	public void setDepartZipcode(String departZipcode) {
		this.departZipcode = departZipcode;
	}

	@Column(name = "depart_licence", length = 50)
	public String getDepartLicence() {
		return this.departLicence;
	}

	public void setDepartLicence(String departLicence) {
		this.departLicence = departLicence;
	}

	@Column(name = "depart_form", length = 50)
	public String getDepartForm() {
		return this.departForm;
	}

	public void setDepartForm(String departForm) {
		this.departForm = departForm;
	}

	@Column(name = "depart_redistration", length = 50)
	public String getDepartRedistration() {
		return this.departRedistration;
	}

	public void setDepartRedistration(String departRedistration) {
		this.departRedistration = departRedistration;
	}

	@Column(name = "depart_representative", length = 20)
	public String getDepartRepresentative() {
		return this.departRepresentative;
	}

	public void setDepartRepresentative(String departRepresentative) {
		this.departRepresentative = departRepresentative;
	}

	@Column(name = "depart_supervisor", length = 20)
	public String getDepartSupervisor() {
		return this.departSupervisor;
	}

	public void setDepartSupervisor(String departSupervisor) {
		this.departSupervisor = departSupervisor;
	}

	@Column(name = "depart_worker_num", length = 5)
	public String getDepartWorkerNum() {
		return this.departWorkerNum;
	}

	public void setDepartWorkerNum(String departWorkerNum) {
		this.departWorkerNum = departWorkerNum;
	}

	@Column(name = "depart_technical_num", length = 5)
	public String getDepartTechnicalNum() {
		return this.departTechnicalNum;
	}

	public void setDepartTechnicalNum(String departTechnicalNum) {
		this.departTechnicalNum = departTechnicalNum;
	}

	@Column(name = "depart_safety_num", length = 5)
	public String getDepartSafetyNum() {
		return this.departSafetyNum;
	}

	public void setDepartSafetyNum(String departSafetyNum) {
		this.departSafetyNum = departSafetyNum;
	}

	@Column(name = "depart_registered_capital", length = 10)
	public String getDepartRegisteredCapital() {
		return this.departRegisteredCapital;
	}

	public void setDepartRegisteredCapital(String departRegisteredCapital) {
		this.departRegisteredCapital = departRegisteredCapital;
	}

	@Column(name = "depart_business_address")
	public String getDepartBusinessAddress() {
		return this.departBusinessAddress;
	}

	public void setDepartBusinessAddress(String departBusinessAddress) {
		this.departBusinessAddress = departBusinessAddress;
	}

	@Column(name = "depart_business_property", length = 20)
	public String getDepartBusinessProperty() {
		return this.departBusinessProperty;
	}

	public void setDepartBusinessProperty(String departBusinessProperty) {
		this.departBusinessProperty = departBusinessProperty;
	}

	@Column(name = "depart_storage_address")
	public String getDepartStorageAddress() {
		return this.departStorageAddress;
	}

	public void setDepartStorageAddress(String departStorageAddress) {
		this.departStorageAddress = departStorageAddress;
	}

	@Column(name = "depart_storage_property", length = 20)
	public String getDepartStorageProperty() {
		return this.departStorageProperty;
	}

	public void setDepartStorageProperty(String departStorageProperty) {
		this.departStorageProperty = departStorageProperty;
	}

	@Column(name = "depart_building", length = 20)
	public String getDepartBuilding() {
		return this.departBuilding;
	}

	public void setDepartBuilding(String departBuilding) {
		this.departBuilding = departBuilding;
	}

	@Column(name = "depart_storage_power", length = 50)
	public String getDepartStoragePower() {
		return this.departStoragePower;
	}

	public void setDepartStoragePower(String departStoragePower) {
		this.departStoragePower = departStoragePower;
	}

	@Column(name = "depart_system_name")
	public String getDepartSystemName() {
		return this.departSystemName;
	}

	public void setDepartSystemName(String departSystemName) {
		this.departSystemName = departSystemName;
	}

	@Column(name = "depart_area", length = 20)
	public String getDepartArea() {
		return this.departArea;
	}

	public void setDepartArea(String departArea) {
		this.departArea = departArea;
	}

	@Column(name = "files", length = 100)
	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Column(name = "images", length = 100)
	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}


	@Column(name = "vaccine")
	public Integer getVaccine() {
		return this.vaccine;
	}

	public void setVaccine(Integer vaccine) {
		this.vaccine = vaccine;
	}

	@Column(name = "vaccine_man", length = 20)
	public String getVaccineMan() {
		return this.vaccineMan;
	}

	public void setVaccineMan(String vaccineMan) {
		this.vaccineMan = vaccineMan;
	}

	@Column(name = "vaccine_time", length = 23)
	public Date getVaccineTime() {
		return this.vaccineTime;
	}

	public void setVaccineTime(Date vaccineTime) {
		this.vaccineTime = vaccineTime;
	}
	
	@Column(name = "plot_geo")
	public String getPlotGeo() {
		return plotGeo;
	}

	public void setPlotGeo(String plotGeo) {
		this.plotGeo = plotGeo;
	}
	
	
}