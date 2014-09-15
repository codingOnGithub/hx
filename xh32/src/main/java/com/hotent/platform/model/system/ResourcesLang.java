package com.hotent.platform.model.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:系统语言资源表 Model对象 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:zxh 
 * 创建时间:2014-01-08 16:39:56
 * </pre>
 */
@XmlRootElement(name = "resourcesLang")
@XmlAccessorType(XmlAccessType.NONE)
public class ResourcesLang extends BaseModel {
	/** */
	private static final long serialVersionUID = 2613466593612054039L;
	// 主键
	@XmlAttribute
	protected Long id;
	// 资源ID
	@XmlAttribute
	protected Long resId;
	// 语言类型
	@XmlAttribute
	protected String lanType;
	// 对应语言名称
	@XmlAttribute
	protected String lanMsg;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	/**
	 * 返回 资源ID
	 * 
	 * @return
	 */
	public Long getResId() {
		return this.resId;
	}

	public void setLanType(String lanType) {
		this.lanType = lanType;
	}

	/**
	 * 返回 语言类型
	 * 
	 * @return
	 */
	public String getLanType() {
		return this.lanType;
	}

	public void setLanMsg(String lanMsg) {
		this.lanMsg = lanMsg;
	}

	/**
	 * 返回 对应语言名称
	 * 
	 * @return
	 */
	public String getLanMsg() {
		return this.lanMsg;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ResourcesLang)) {
			return false;
		}
		ResourcesLang rhs = (ResourcesLang) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.resId, rhs.resId)
				.append(this.lanType, rhs.lanType)
				.append(this.lanMsg, rhs.lanMsg).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.resId).append(this.lanType).append(this.lanMsg)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("resId", this.resId).append("lanType", this.lanType)
				.append("lanMsg", this.lanMsg).toString();
	}

}