package com.ksoft.base.service.manager.model;


public class AbstractServiceBean  implements ServiceBean{
	
	private String servicetype;
	private String category;
	private String servicename;
	private String description;
	private long disabled;
	private String sn;
	private String createby;
	
    private ServiceGroupBean serviceGroupBean;
	
	private String url;
	
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getDisabled() {
		return disabled;
	}
	public void setDisabled(long disabled) {
		this.disabled = disabled;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ServiceGroupBean getServiceGroupBean() {
		return serviceGroupBean;
	}
	public void setServiceGroupBean(ServiceGroupBean serviceGroupBean) {
		this.serviceGroupBean = serviceGroupBean;
	}
	
}
