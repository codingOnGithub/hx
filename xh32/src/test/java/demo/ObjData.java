package demo;

import java.util.ArrayList;
import java.util.List;

public class ObjData {
	
	private String className="";
	private List<FieldData> fieldDataList=new ArrayList<FieldData>();
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<FieldData> getFieldDataList() {
		return fieldDataList;
	}
	public void setFieldDataList(List<FieldData> fieldDataList) {
		this.fieldDataList = fieldDataList;
	}
	
}
