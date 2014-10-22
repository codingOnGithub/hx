package com.hotent.org.persistence.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.Dimension;

/**
 * 对象功能:@名称：XOG_DIMENSION【维度】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:王龙升
 * 创建时间:2014-02-10 16:12:17
 */
public class DefaultDimension extends AbstractModel<String> implements Dimension{
	protected String  dimId; /*维度ID*/
	protected String  name; /*维度名称*/
	protected String  dimKey; /*维度业务主键*/
	protected char  isCombination; /*是否组合维度*/
	protected char  isSystem; /*是否系统预设维度*/
	protected Integer  sn;			/*排序号*/
	protected Status  status; 
	protected String  showType; /*tree=树型数据。flat=平铺数据*/
	protected char  gradeAuth; /*是否支持分级授权*/
	protected char  allowType; /*是否支持等级分类*/
	protected String  desc; /*描述*/
	protected List<DefaultDimensionRelation> dimensionRelationList=new ArrayList<DefaultDimensionRelation>(); /*XOG_DIM_REL列表*/
	protected DefaultRankType rankType; /*用户组等级分类*/
	@Override
	public void setId(String dimId) {
		setDimId(dimId);
	}
	@Override
	public String getId() {
		return getDimId();
	}	
	
	@Override
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setDimId(String dimId) 
	{
		this.dimId = dimId;
	}
	/**
	 * 返回 维度ID
	 * @return
	 */
	public String getDimId() 
	{
		return this.dimId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 维度名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setDimKey(String dimKey) 
	{
		this.dimKey = dimKey;
	}
	/**
	 * 返回 维度业务主键
	 * @return
	 */
	public String getDimKey() 
	{
		return this.dimKey;
	}
	public void setIsCombination(char isCombination) 
	{
		this.isCombination = isCombination;
	}
	/**
	 * 返回 是否组合维度
	 * @return
	 */
	public char getIsCombination() 
	{
		return this.isCombination;
	}
	public void setIsSystem(char isSystem) 
	{
		this.isSystem = isSystem;
	}
	/**
	 * 返回 是否系统预设维度
	 * @return
	 */
	public char getIsSystem() 
	{
		return this.isSystem;
	}
	public void setSn(Integer sn) 
	{
		this.sn = sn;
	}
	/**
	 * 返回 排序号
	 * @return
	 */
	public Integer getSn() 
	{
		return this.sn;
	}
	
	public void setShowType(String showType) 
	{
		this.showType = showType;
	}
	/**
	 * 返回 tree=树型数据。flat=平铺数据
	 * @return
	 */
	public String getShowType() 
	{
		return this.showType;
	}
	public void setGradeAuth(char gradeAuth) 
	{
		this.gradeAuth = gradeAuth;
	}
	/**
	 * 返回 是否支持分级授权
	 * @return
	 */
	public char getGradeAuth() 
	{
		return this.gradeAuth;
	}
	public void setAllowType(char allowType) 
	{
		this.allowType = allowType;
	}
	/**
	 * 返回 是否支持等级分类
	 * @return
	 */
	public char getAllowType() 
	{
		return this.allowType;
	}
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setDimensionRelationList(List<DefaultDimensionRelation> dimensionRelationList) 
	{
		this.dimensionRelationList = dimensionRelationList;
	}
	/**
	 * 返回 XOG_DIM_REL列表
	 * @return
	 */
	public List<DefaultDimensionRelation> getDimensionRelationList() 
	{
		return this.dimensionRelationList;
	}
	public void setRankType(DefaultRankType rankType) 
	{
		this.rankType = rankType;
	}
	/**
	 * 返回 用户组等级分类
	 * @return
	 */
	public DefaultRankType getRankType() 
	{
		return this.rankType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("dimId", this.dimId) 
		.append("name", this.name) 
		.append("dimKey", this.dimKey) 
		.append("isCombination", this.isCombination) 
		.append("isSystem", this.isSystem) 
		.append("sn", this.sn) 
		.append("status", this.status) 
		.append("showType", this.showType) 
		.append("gradeAuth", this.gradeAuth) 
		.append("allowType", this.allowType) 
		.append("desc", this.desc) 
		.toString();
	}
	@Override
	public String getGroupType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isDefault() {
		// TODO Auto-generated method stub
		return false;
	}
	
}