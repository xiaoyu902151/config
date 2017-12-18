/**  
 * @Title: SysVideoVO.java
 * @Package com.bxsurvey.vo.sys
 * @Description: TODO
 * @author czj
 * @date 2016-1-26
 */
package com.bxsurvey.sys.video.vo;

import com.bxsurvey.sys.video.model.SysVideo;

/**
 * ClassName: SysVideoVO 
 * @Description: TODO
 * @author czj
 * @date 2016-1-26
 */
public class SysVideoVO extends SysVideo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7606196483266395064L;
	
	private String departName;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
	
	public SysVideoVO getSysVideoVO(SysVideo sv){
		SysVideoVO sysVideoVO = new SysVideoVO();
		sysVideoVO.setDepartName(sv.getSysDepart().getDepartName());
		sysVideoVO.setName(sv.getName());
		sysVideoVO.setUrl(sv.getUrl());
		return sysVideoVO;	
	}
	
}
