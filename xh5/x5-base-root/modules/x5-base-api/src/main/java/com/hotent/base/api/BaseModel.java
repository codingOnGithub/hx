package com.hotent.base.api;

import java.io.Serializable;
import java.util.Date;
/**
 * 实体基础接口
 * @author csx
 *
 */
public interface BaseModel extends Serializable {
	/**
	 * 返回创建人ID
	 * @return
	 */
	public String getCreateBy();
	/**
	 * 设置设置人ID
	 * @param createBy
	 */
	public void setCreateBy(String createBy);
	/**
	 * 返回创建时间
	 * @return
	 */
	public Date getCreateTime();
	/**
	 * 设置创建时间
	 * @param createtime
	 */
	public void setCreateTime(Date createtime);
	/**
	 * 返回更新时间
	 * @return
	 */
	public Date getUpdateTime();
	/**
	 * 设置更新时间
	 * @param updatetime
	 */
	public void setUpdateTime(Date updatetime);
	/**
	 * 返回更新人ID
	 * @return
	 */
	public String getUpdateBy();
	/**
	 * 设置更新人ID
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy);
	/**
	 * 获取创建者组织ID
	 * @return
	 */
	public String getCreateOrgId();
	/**
	 * 设置创建者组织ID
	 * @param createOrgId
	 * @return
	 */
	public void setCreateOrgId(String createOrgId);
	
	
}
