package com.hotent.bo.persistence.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:xbo_def entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:何一帆
 * 创建时间:2014-01-21 10:38:10
 */
public class BODef extends AbstractModel<String>{
	public final static class BODEF_STATUS{
		/**
		 * 未激活
		 */
		public final static String INACTIVE = "inactive";
		/**
		 * 激活
		 */
		public final static String ACTIVED = "actived";
		/**
		 * 禁用
		 */
		public final static String FORBIDDEN = "forbidden";
	}
	
	
	public final static class BODEF_IS_MAIN{
		/**
		 * 主版本
		 */
		public final static char MAIN_YES = 'Y';
		/**
		 * 非从版本
		 */
		public final static char MAIN_NO = 'N';


	}
	
	public final static class BODEF_IS_MASTER{
		/**
		 * 主对象
		 */
		public final static char MASTER_YES = 'Y';
		/**
		 * 从对象
		 */
		public final static char MASTER_NO = 'N';
	}
	
	protected String  id; /*对象定义ID*/
	protected String  packageId; /*包ID*/
	protected String  code; /*对象编码*/
	protected String  name; /*对象名称*/
	protected String  desc; /*对象描述*/
	protected String  packagePath; /*包路径*/
	protected Long  version;			/*版本号*/
	protected char  isMain; /*是否主版本*/
	protected String  status; /*状态。inactive=未激活；actived=激活；forbidden=禁用*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected char  isMaster; /*是否主对象*/
	protected String dataType = "json"; /*实例化时保存数据的格式*/
	protected List<BOAttribute> bOAttributeList=new ArrayList<BOAttribute>(); /*xbo_attr列表*/
	protected List<BORule> bORuleList=new ArrayList<BORule>();   //业务对象属性共用的规则  /*xbo_rule列表*/ 
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 对象定义ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setPackageId(String packageId) 
	{
		this.packageId = packageId;
	}
	/**
	 * 返回 包ID
	 * @return
	 */
	public String getPackageId() 
	{
		return this.packageId;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	/**
	 * 返回 对象编码
	 * @return
	 */
	public String getCode() 
	{
		return this.code;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 对象名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 对象描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setPackage(String packagePath) 
	{
		this.packagePath = packagePath;
	}
	/**
	 * 返回 包路径
	 * @return
	 */
	public String getPackage() 
	{
		return this.packagePath;
	}
	public void setVersion(Long version) 
	{
		this.version = version;
	}
	/**
	 * 返回 版本号
	 * @return
	 */
	public Long getVersion() 
	{
		return this.version;
	}
	public void setIsMain(char isMain) 
	{
		this.isMain = isMain;
	}
	/**
	 * 返回 是否主版本
	 * @return
	 */
	public char getIsMain() 
	{
		return this.isMain;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态。inactive=未激活；actived=激活；forbidden=禁用
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
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
	 * 返回是否是主对象
	 * @return
	 */
	public char getIsMaster()
	{
		return isMaster;
	}
	
	public void setIsMaster(char isMaster)
	{
		this.isMaster = isMaster;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return this.createTime;
	}
	
	/**
	 * 返回 xbo_attr列表
	 * @return
	 */
	public List<BOAttribute> getBOAttributeList() 
	{
		return this.bOAttributeList;
	}	
	public void setBOAttributeList(List<BOAttribute> bOAttributeList) 
	{
		this.bOAttributeList = bOAttributeList;
	}
	
	public List<BORule> getBORuleList()
	{
		return bORuleList;
	}
	public void setBORuleList(List<BORule> bORuleList)
	{
		this.bORuleList = bORuleList;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("packageId", this.packageId) 
		.append("code", this.code) 
		.append("name", this.name) 
		.append("desc", this.desc) 
		.append("package", this.packagePath) 
		.append("version", this.version) 
		.append("isMain", this.isMain) 
		.append("status", this.status) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.toString();
	}
}