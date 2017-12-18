package com.bxsurvey.comd.ComdEvent.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
* 
* @ClassName: ComdEvent 
* @Description: 数据库事件表实体类 
* @author lcy
* @date 2016-8-3 下午2:47:52 
*
 */
@Entity
@Table(name = "comd_event", schema = "dbo")
public class ComdEvent  implements java.io.Serializable{
	private Integer id;//主键标识
	private String eventName;//事件名称
	private String eventType;//事件类别
	private String eventLevel;//事件等级
	private Date ocrTime;//发生时间
	private String status;//处理状态
	private Date processTime;//处理时间
	private String ocrArea;//发生区域
	private String x;//经度
	private String y;//纬度
	private String detail;//事件详情
	private String userName;//处理人
	private String leader;//负责人
	private String leaderPhone;//负责人电话
	private Integer departId;//所属公司ID
	private Integer dangerId;//危险源ID
	private Integer deviceId;//设备ID.
	private Integer planId;//事件ID
	private String source;//事件来源

	/**
	 * 
	* Title:  
	* Description:默认构造函数
	 */
	public ComdEvent() {
		super();
	}

	public ComdEvent(Integer id,  String eventName,
			String eventType, String eventLevel, Date ocrTime,
			String status, Date processTime, String ocrArea, String x,
			String y, String detail, String userName, String leader,
			String leaderPhone, Integer departId, Integer dangerId,
			Integer deviceId, String source) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventLevel = eventLevel;
		this.ocrTime = ocrTime;
		this.status = status;
		this.processTime = processTime;
		this.ocrArea = ocrArea;
		this.x = x;
		this.y = y;
		this.detail = detail;
		this.userName = userName;
		this.leader = leader;
		this.leaderPhone = leaderPhone;
		this.departId = departId;
		this.dangerId = dangerId;
		this.deviceId = deviceId;
		this.source = source;
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
	
	@Column(name = "event_type")
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Column(name = "event_level")
	public String getEventLevel() {
		return eventLevel;
	}
	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}
	
	@JsonSerialize(using =com.fasterxml.jackson.databind.ser.std.DateSerializer.class) 
	@Column(name = "ocr_time", length = 23)
	public Date getOcrTime() {
		return ocrTime;
	}
	
	public void setOcrTime(Date ocrTime) {
		this.ocrTime = ocrTime;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonSerialize(using =com.fasterxml.jackson.databind.ser.std.DateSerializer.class) 
	@Column(name = "process_time", length = 23)
	public Date getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	
	@Column(name = "ocr_area")
	public String getOcrArea() {
		return ocrArea;
	}
	public void setOcrArea(String ocrArea) {
		this.ocrArea = ocrArea;
	}
	
	@Column(name = "x")
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	@Column(name = "y")
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	@Column(name = "detail")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "leader")
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Column(name = "leader_phone")
	public String getLeaderPhone() {
		return leaderPhone;
	}
	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}
	
	@Column(name = "depart_id")
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	
	@Column(name = "danger_id")
	public Integer getDangerId() {
		return dangerId;
	}
	public void setDangerId(Integer dangerId) {
		this.dangerId = dangerId;
	}
	
	@Column(name = "device_id")
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(name = "source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Column(name = "plan_id")
	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	
	
}
