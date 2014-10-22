package com.hotent.examples.school.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 *
 * @author csx
 */
public class Student extends AbstractModel<String> {
    //StuId
    private String stuId;
    //姓名
    private String name;
    //生日
    private Date birthday;
    //姓别
    private Short sex;
    //简短介绍
    private String desc;

    public Student() {
    }

    public String getId() {
        return this.getStuId();
    }

    public void setId(String id) {
        this.setStuId(id);
    }


    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    @Override
    public String toString() {
    	return new ToStringBuilder(this).append("stuId", this.stuId)
				.append("name", this.name)
				.append("birthday", this.birthday)
				.append("sex", this.sex).append("desc", this.desc)
				.toString();
    }
    
    
}
