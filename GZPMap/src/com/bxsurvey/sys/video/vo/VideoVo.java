package com.bxsurvey.sys.video.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: Video 
 * @Description: 视频vo
 * @author cqc
 * @date 2015-5-14 下午4:16:56 
 *
 */
public class VideoVo implements Serializable{
	/** @Fields serialVersionUID : */ 
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String src;
	private String suffix;
	
	public VideoVo(){};
	
	public VideoVo(String title, String src, String suffix) {
		super();
		this.title = title;
		this.src = src;
		this.suffix = suffix;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
}
