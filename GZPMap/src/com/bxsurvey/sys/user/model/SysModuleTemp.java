package com.bxsurvey.sys.user.model;

public class SysModuleTemp {
   private String name;
   private String url;
   
   public SysModuleTemp(String name,String url){
	   this.name=name;
	   this.url=url;
   }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
   
}
