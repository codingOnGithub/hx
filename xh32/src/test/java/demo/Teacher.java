package demo;

import java.util.Date;

public class Teacher implements java.io.Serializable {  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
  
    private  String name ="haibing.xiao";  
      
    private int age=27  ;  
  
    private Boolean sex =true ;  
      
    private Date birthday =new Date();  
      
    private Date workDate =new Date();  
    public Date getWorkDate() {  
        return workDate;  
    }  
  
    public void setWorkDate(Date workDate) {  
        this.workDate = workDate;  
    }  
  
       
       
  
       
   
  
    public Date getBirthday() {  
        return birthday;  
    }  
  
    public void setBirthday(Date birthday) {  
        this.birthday = birthday;  
    }  
  
    public static long getSerialversionuid() {  
        return serialVersionUID;  
    }  
  
    public Boolean getSex() {  
        return sex;  
    }  
  
    public void setSex(Boolean sex) {  
        this.sex = sex;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
      
       
  
       
}  
