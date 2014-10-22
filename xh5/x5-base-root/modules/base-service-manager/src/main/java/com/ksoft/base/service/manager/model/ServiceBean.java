package com.ksoft.base.service.manager.model;

public interface ServiceBean {
	
	public String getServicetype();
	
	public void setServicetype(String servicetype) ;
	
	public String getCategory() ;
	
	public void setCategory(String category);
	
	public String getServicename() ;
	
	public void setServicename(String servicename) ;
	
	public String getDescription();
	
	public void setDescription(String description) ;
	
	public long getDisabled() ;
	
	public void setDisabled(long disabled) ;
	
	public String getSn();
	
	public void setSn(String sn);
	
	public String getCreateby() ;
	
	public void setCreateby(String createby) ;
	
	public String getUrl();
	
	public void setUrl(String url) ;
	
	public ServiceGroupBean getServiceGroupBean() ;
	
	public void setServiceGroupBean(ServiceGroupBean serviceGroupBean) ;

}
