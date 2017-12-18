package com.bxsurvey.danger.DangerSupplies.vo;

public class StandVO {
    private String need;
    private Boolean hasItem;
    private String suppliesType;
    private String suppliesTypeName;
    
    
	public StandVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public StandVO(String need, Boolean hasItem, String suppliesType,
			String suppliesTypeName) {
		super();
		this.need = need;
		this.hasItem = hasItem;
		this.suppliesType = suppliesType;
		this.suppliesTypeName = suppliesTypeName;
	}




	public String getNeed() {
		return need;
	}


	public void setNeed(String need) {
		this.need = need;
	}


	public Boolean getHasItem() {
		return hasItem;
	}


	public void setHasItem(Boolean hasItem) {
		this.hasItem = hasItem;
	}


	public String getSuppliesType() {
		return suppliesType;
	}


	public void setSuppliesType(String suppliesType) {
		this.suppliesType = suppliesType;
	}


	public String getSuppliesTypeName() {
		return suppliesTypeName;
	}


	public void setSuppliesTypeName(String suppliesTypeName) {
		this.suppliesTypeName = suppliesTypeName;
	}
	
	
    
    
}
