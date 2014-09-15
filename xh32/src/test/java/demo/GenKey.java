package demo;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import com.hotent.core.encrypt.Base64;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;

public class GenKey {
	
	private static String propertiesPath="";
	private static String isFormal="1";
	private static String dbType="mysql";

	public static void setPropertiesPath(String path) {
		System.out.println("属性文件路径:" +path);
		propertiesPath = path;
	}
	
	public static void setFormal(String _isFormal) {
		isFormal = _isFormal;
	}
	
	public static void setDbType(String _dbType){
		
		dbType=_dbType;
	}
	
	
	public void execute() throws Exception
	{
		if(StringUtil.isEmpty(propertiesPath)){
			throw new Exception("请设置属性文件路径!");
		}
		String productKey="";
		if("1".equals(isFormal) ){
			FileUtil.delProperties(propertiesPath, "productKey");
			System.out.println("删除产品码成功!");
		}
		else{
			productKey=generateEvaluateKey(true);
			System.out.println("试用版key:"+productKey);
			FileUtil.saveProperties(propertiesPath, "productKey", productKey);
			System.out.println("生成产品码成功:" +Base64.getFromBase64(EncryptUtil.decrypt(productKey)) );
		}
		
		if("mysql".equalsIgnoreCase(dbType)){
			setMySql();
		}
		else if("mssql".equalsIgnoreCase(dbType)){
			setMsSql();
		}
		else if("oracle".equalsIgnoreCase(dbType)){
			setOracle();
		}
		
		
	}
	
	private void setMySql(){
		FileUtil.saveProperties(propertiesPath, "jdbc.dbType", "mysql");
		FileUtil.saveProperties(propertiesPath, "jdbc.driverClassName", "com.mysql.jdbc.Driver");
		FileUtil.saveProperties(propertiesPath, "jdbc.url", "jdbc:mysql://192.168.1.80:3306/bpmx0227?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=round");
		System.out.println("当前数据库为MYSQL");
	}
	
	private void setMsSql(){
		FileUtil.saveProperties(propertiesPath, "jdbc.dbType", "mssql");
		FileUtil.saveProperties(propertiesPath, "jdbc.driverClassName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		FileUtil.saveProperties(propertiesPath, "jdbc.url", "jdbc:sqlserver://localhost:1433;DatabaseName=bpm32");
		System.out.println("当前数据库为sqlserver");
	}

	private void setOracle(){
		FileUtil.saveProperties(propertiesPath, "jdbc.dbType", "oracle");
		FileUtil.saveProperties(propertiesPath, "jdbc.driverClassName", "oracle.jdbc.OracleDriver");
		FileUtil.saveProperties(propertiesPath, "jdbc.url", "jdbc:oracle:thin:@192.168.1.80:1521:ORCL");
		System.out.println("当前数据库为ORACLE");
	}

	
	
	private String generateEvaluateKey(boolean isEval) {
		String productKey="http://www.jee-soft.cn/";
		if(!isEval){
			Long startTime = System.currentTimeMillis();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 60);
			Long endTime = calendar.getTimeInMillis();
			productKey = "1," + startTime + "," + endTime;
		}
		try {
			productKey = EncryptUtil.encrypt(Base64.getBase64(productKey));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productKey;
	}
	
	
	
	

}
