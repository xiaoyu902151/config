package com.bxsurvey.support.degknowlage.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bxsurvey.sys.params.model.SysParams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ComdExpert entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "danger_knowlage", schema = "dbo")
public class DangerKnowlage implements java.io.Serializable {
	private Integer id;//主键ID
	private String chName;//中文名
	private String enName;//英文名
	private String hwbh;//危险货物编号
	private String unbh;//UN编号
	private String fzs;//分子式
	private String fzl;//分子量
	private String cash;//CAS号
	private String wgyxz;//外观与性状
	private String rd;//熔点（℃）
	private String xdmd;//相对密度(水=1)
	private String fd;//沸点（℃）
	private String bhzqy;//饱和蒸气压（kPa）
	private String rjx;//溶解性
	private String qrtj;//侵入途径
	private String dx;//毒性
	private String jkwh;//健康危害
	private String jjff;//急救方法
	private String rsx;//燃烧性
	private String rsfjw;//燃烧分解物
	private String sd;//闪点(℃)
	private String yrwd;//引燃温度(℃)
	private String bzsx;//爆炸上限（v%）
	private String bzxx;//爆炸下限（v%）
	private String wxtx;//危险特性
	private String cytj;//储运条件
	private String xlcl;//泄漏处理
	private String gtfh;//个体防护
	private String czclff;//操作处理方法
	private String yszysx;//运输注意事项
	private String cczysx;//储存注意事项
	private String xlfh;//泄漏防护
	private String hzfh;//火灾防护
	private String mhcs;//灭火措施
	private String jjcs;//急救措施
	//private SysParams sysParams;//燃烧性

	// Constructors

	/** default constructor */
	public DangerKnowlage() {
	}

	/** minimal constructor */
	public DangerKnowlage(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public DangerKnowlage(Integer id, String chName, String enName,
			String hwbh, String unbh, String fzs, String fzl, String cash,
			String wgyxz, String rd, String xdmd, String fd, String bhzqy,
			String rjx, String qrtj, String dx, String jkwh, String jjff,
			String rsx, String rsfjw, String sd, String yrwd, String bzsx,
			String bzxx, String wxtx, String cytj, String xlcl, String gtfh,
			String czclff, String yszysx, String cczysx, String xlfh,
			String hzfh, String mhcs, String jjcs) {
		this.id = id;
		this.chName = chName;
		this.enName = enName;
		this.hwbh = hwbh;
		this.unbh = unbh;
		this.fzs = fzs;
		this.fzl = fzl;
		this.cash = cash;
		this.wgyxz = wgyxz;
		this.rd = rd;
		this.xdmd = xdmd;
		this.fd = fd;
		this.bhzqy = bhzqy;
		this.rjx = rjx;
		this.qrtj = qrtj;
		this.dx = dx;
		this.jkwh = jkwh;
		this.jjff = jjff;
		this.rsx = rsx;
		this.rsfjw = rsfjw;
		this.sd = sd;
		this.yrwd = yrwd;
		this.bzsx = bzsx;
		this.bzxx = bzxx;
		this.wxtx = wxtx;
		this.cytj = cytj;
		this.xlcl = xlcl;
		this.gtfh = gtfh;
		this.czclff = czclff;
		this.yszysx = yszysx;
		this.cczysx = cczysx;
		this.xlfh = xlfh;
		this.hzfh = hzfh;
		this.mhcs = mhcs;
		this.jjcs = jjcs;
		//this.sysParams = sysParams;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "ch_name")
	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	@Column(name = "en_name")
	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name = "hwbh")
	public String getHwbh() {
		return this.hwbh;
	}

	public void setHwbh(String hwbh) {
		this.hwbh = hwbh;
	}
	
	@Column(name = "unbh")
	public String getUnbh() {
		return this.unbh;
	}

	public void setUnbh(String unbh) {
		this.unbh = unbh;
	}
	
	@Column(name = "fzs")
	public String getFzs() {
		return this.fzs;
	}

	public void setFzs(String fzs) {
		this.fzs = fzs;
	}
	
	@Column(name = "fzl")
	public String getFzl() {
		return this.fzl;
	}

	public void setFzl(String fzl) {
		this.fzl = fzl;
	}

	@Column(name = "cash")
	public String getCash() {
		return this.cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	@Column(name = "wgyxz")
	public String getWgyxz() {
		return this.wgyxz;
	}

	public void setWgyxz(String wgyxz) {
		this.wgyxz = wgyxz;
	}

	@Column(name = "rd")
	public String getRd() {
		return this.rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	@Column(name = "xdmd")
	public String getXdmd() {
		return this.xdmd;
	}

	public void setXdmd(String xdmd) {
		this.xdmd = xdmd;
	}

	@Column(name = "fd")
	public String getFd() {
		return this.fd;
	}

	public void setFd(String fd) {
		this.fd = fd;
	}

	@Column(name = "bhzqy")
	public String getBhzqy() {
		return this.bhzqy;
	}

	public void setBhzqy(String bhzqy) {
		this.bhzqy = bhzqy;
	}
	@Column(name = "rjx")
	public String getRjx() {
		return this.rjx;
	}

	public void setRjx(String rjx) {
		this.rjx = rjx;
	}

	@Column(name = "qrtj")
	public String getQrtj() {
		return this.qrtj;
	}

	public void setQrtj(String qrtj) {
		this.qrtj = qrtj;
	}

	@Column(name = "dx")
	public String getDx() {
		return this.dx;
	}

	public void setDx(String dx) {
		this.dx = dx;
	}
	
	@Column(name = "jkwh")
	public String getJkwh() {
		return this.jkwh;
	}

	public void setJkwh(String jkwh) {
		this.jkwh = jkwh;
	}

	@Column(name = "jjff")
	public String getJjff() {
		return this.jjff;
	}

	public void setJjff(String jjff) {
		this.jjff = jjff;
	}
	
	@JsonSerialize(using =net.framework.utils.json.DictSerializer.class)
	@Column(name = "rsx")
	public String getRsx() {
		return this.rsx;
	}

	public void setRsx(String rsx) {
		this.rsx = rsx;
	}

	@Column(name = "rsfjw")
	public String getRsfjw() {
		return this.rsfjw;
	}

	public void setRsfjw(String rsfjw) {
		this.rsfjw = rsfjw;
	}

	@Column(name = "sd")
	public String getSd() {
		return this.sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	@Column(name = "yrwd")
	public String getYrwd() {
		return this.yrwd;
	}

	
	public void setYrwd(String yrwd) {
		this.yrwd = yrwd;
	}

	@Column(name = "bzsx")
	public String getBzsx() {
		return this.bzsx;
	}

	public void setBzsx(String bzsx) {
		this.bzsx = bzsx;
	}

	@Column(name = "bzxx")
	public String getBzxx() {
		return this.bzxx;
	}

	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}

	@Column(name = "wxtx")
	public String getWxtx() {
		return this.wxtx;
	}

	public void setWxtx(String wxtx) {
		this.wxtx = wxtx;
	}

	@Column(name = "cytj")
	public String getCytj() {
		return this.cytj;
	}

	public void setCytj(String cytj) {
		this.cytj = cytj;
	}
	
	@Column(name = "xlcl")
	public String getXlcl() {
		return this.xlcl;
	}

	public void setXlcl(String xlcl) {
		this.xlcl = xlcl;
	}

	@Column(name = "gtfh")
	public String getGtfh() {
		return this.gtfh;
	}

	public void setGtfh(String gtfh) {
		this.gtfh = gtfh;
	}

	@Column(name = "czclff")
	public String getCzclff() {
		return this.czclff;
	}

	public void setCzclff(String czclff) {
		this.czclff = czclff;
	}

	@Column(name = "yszysx")
	public String getYszysx() {
		return this.yszysx;
	}

	public void setYszysx(String yszysx) {
		this.yszysx = yszysx;
	}

	@Column(name = "cczysx")
	public String getCczysx() {
		return this.cczysx;
	}

	public void setCczysx(String cczysx) {
		this.cczysx = cczysx;
	}

	@Column(name = "xlfh")
	public String getXlfh() {
		return this.xlfh;
	}

	public void setXlfh(String xlfh) {
		this.xlfh = xlfh;
	}

	@Column(name = "hzfh")
	public String getHzfh() {
		return this.hzfh;
	}

	public void setHzfh(String hzfh) {
		this.hzfh = hzfh;
	}

	@Column(name = "mhcs")
	public String getMhcs() {
		return this.mhcs;
	}

	public void setMhcs(String mhcs) {
		this.mhcs = mhcs;
	}

	@Column(name = "jjcs")
	public String getJjcs() {
		return this.jjcs;
	}

	public void setJjcs(String jjcs) {
		this.jjcs = jjcs;
	}
	
}
