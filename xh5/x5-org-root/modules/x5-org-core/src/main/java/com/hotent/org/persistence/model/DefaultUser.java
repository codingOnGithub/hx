package com.hotent.org.persistence.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.User;

/**
 * 对象功能:@名称：XOG_USER【系统用户】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-10 16:12:17
 */
public class DefaultUser extends AbstractModel<String> implements User{
	protected String  userId; /*用户ID*/
	protected String  account; /*用户账号*/
	protected String  fullname; /*姓名*/
	protected Status  status; /*状态。0 不可以登录系统；1 可以登录系统；2 锁定*/
	protected String  password; /*密码*/
	protected String  sex; /*male*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected String  createSaasId; /*公共 - 创建者所属SAAS ID*/
	protected String  updateBy; /*更新人ID*/
	protected java.util.Date  updateTime; /*更新时间*/
	protected String  from; /*来源*/
	protected String  mobile; /*手机*/
	protected String  email; /*邮件*/
	protected String  address; /*地址*/
	protected String  qq; /*QQ号*/
	protected String  phone; /*照片*/
	protected List<DefaultUserRelation> userRelationList=new ArrayList<DefaultUserRelation>(); /*@名称：XOG_USER_REL【用户关系表】列表*/
	@Override
	public void setId(String userId) {
		setUserId(userId);
	}
	@Override
	public String getId() {
		return getUserId();
	}	
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public String getUserId() 
	{
		return this.userId;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}
	/**
	 * 返回 用户账号
	 * @return
	 */
	public String getAccount() 
	{
		return this.account;
	}
	public void setFullname(String fullname) 
	{
		this.fullname = fullname;
	}
	/**
	 * 返回 姓名
	 * @return
	 */
	public String getFullname() 
	{
		return this.fullname;
	}
	public void setStatus(Status status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态。0 不可以登录系统；1 可以登录系统；2 锁定
	 * @return
	 */
	public Status getStatus() 
	{
		return this.status;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	/**
	 * 返回 male
	 * @return
	 */
	public String getSex() 
	{
		return this.sex;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}
	/**
	 * 返回 创建人ID
	 * @return
	 */
	public String getCreateBy() 
	{
		return this.createBy;
	}
	public void setCreateTime(java.util.Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return this.createTime;
	}
	public void setCreateSaasId(String createSaasId) 
	{
		this.createSaasId = createSaasId;
	}
	/**
	 * 返回 公共 - 创建者所属SAAS ID
	 * @return
	 */
	public String getCreateSaasId() 
	{
		return this.createSaasId;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}
	/**
	 * 返回 更新人ID
	 * @return
	 */
	public String getUpdateBy() 
	{
		return this.updateBy;
	}
	public void setUpdateTime(java.util.Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	/**
	 * 返回 更新时间
	 * @return
	 */
	public java.util.Date getUpdateTime() 
	{
		return this.updateTime;
	}
	public void setFrom(String from) 
	{
		this.from = from;
	}
	/**
	 * 返回 来源
	 * @return
	 */
	public String getFrom() 
	{
		return this.from;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}
	/**
	 * 返回 手机
	 * @return
	 */
	public String getMobile() 
	{
		return this.mobile;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	/**
	 * 返回 邮件
	 * @return
	 */
	public String getEmail() 
	{
		return this.email;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	/**
	 * 返回 地址
	 * @return
	 */
	public String getAddress() 
	{
		return this.address;
	}
	public void setQq(String qq) 
	{
		this.qq = qq;
	}
	/**
	 * 返回 QQ号
	 * @return
	 */
	public String getQq() 
	{
		return this.qq;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	/**
	 * 返回 照片
	 * @return
	 */
	public String getPhone() 
	{
		return this.phone;
	}
	public void setUserRelationList(List<DefaultUserRelation> userRelationList) 
	{
		this.userRelationList = userRelationList;
	}
	/**
	 * 返回 @名称：XOG_USER_REL【用户关系表】列表
	 * @return
	 */
	public List<DefaultUserRelation> getUserRelationList() 
	{
		return this.userRelationList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("userId", this.userId) 
		.append("account", this.account) 
		.append("fullname", this.fullname) 
		.append("status", this.status) 
		.append("pwd", this.password) 
		.append("sex", this.sex) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createSaasId", this.createSaasId) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("from", this.from) 
		.append("mobile", this.mobile) 
		.append("email", this.email) 
		.append("address", this.address) 
		.append("qq", this.qq) 
		.append("phone", this.phone) 
		.toString();
	}
	@Override
	public String getIdentityType() {
		return null;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
}