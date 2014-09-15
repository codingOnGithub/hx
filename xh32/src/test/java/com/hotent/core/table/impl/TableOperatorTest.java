package com.hotent.core.table.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.core.table.ITableOperator;
import com.hotent.platform.model.form.BpmFormTableIndex;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test-raise.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TableOperatorTest {
	
	@Autowired
	ITableOperator tableOperator;
	
//	@Test
	public void testCreateIndex() {		
		BpmFormTableIndex index=new BpmFormTableIndex();
		index.setIndexTable("test");
		index.setIndexName("idx_test_name");
		List<String> cols=new ArrayList<String>();
		cols.add("id");
		cols.add("name");
		index.setIndexFields(cols);
		try {
			tableOperator.createIndex(index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Test
	public void testGetIndex() {
		System.out.println("get index");
		BpmFormTableIndex index = tableOperator.getIndex("test", "idx_test_name");
		System.out.println(index);
	}
	

	@Test
	public void testGetIndexesByTable() {
		System.out.println("get index by table");
		List<BpmFormTableIndex> indexes=tableOperator.getIndexesByTable("test");
		System.out.println(indexes);
		
	}
	

//	@Test
	public void testRebuildIndex() {
		tableOperator.rebuildIndex("person", null);
		
		
	}
	
//	@Test
	public void test() {
		
		List<BpmFormTableIndex> indexes=tableOperator.getIndexesByTable("test");
		System.out.println(indexes);
		
	}
	
	
	
//	@Test
	public void testDropIndex() {
		tableOperator.dropIndex("person", "index1");
	}
	
//	@Test
	public void testGetIndexesByFuzzyMatching() {
		List<BpmFormTableIndex> indexes=tableOperator.getIndexesByFuzzyMatching(null, "idx", true);
		System.out.println(indexes);
		for(BpmFormTableIndex index:indexes){
			System.out.println(index.getIndexDdl());
		}
	}
	
//	@Test
	public void testGetKeyColumns(){
		Map<String,List<String>> map = null;
		try {
			map = tableOperator.getPKColumns(getTableNames());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);
	}
	
	
	private List<String> getTableNames(){
		List<String> list=new ArrayList<String>();
		list.add("SHIP_ORDER");
		list.add("W_BOOK");
		list.add("W_QINGJIA");
		list.add("W_C");
		list.add("W_PERSON");
		list.add("W_SALES_RECORD");
		list.add("W_DIC");
		list.add("W_MAINTABLE");
		list.add("W_SUBTABLE");
		list.add("SYS_DB_ID");
		list.add("W_SHOPPING");
		list.add("W_CHUCHAI");
		list.add("BPM_FORM_RUN");
		list.add("BPM_TABLE_TEMPLATE");
		list.add("W_HUIYI");
		list.add("W_HUIYIPERSON");
		list.add("W_NEWTABLE");
		list.add("W_BUSINESS_TRIP");
		list.add("W_APPACTIVE");
		list.add("W_PLACE_TRIP");
		list.add("ACTIVEMQ_ACKS");
		list.add("ACTIVEMQ_LOCK");
		list.add("ACTIVEMQ_MSGS");
		list.add("BPM_NODE_BTN");
		list.add("W_JIABAN");
		list.add("BPM_PRO_STATUS");
		list.add("SYS_USER_UNDER");
		list.add("W_CC");
		list.add("SALES_DETAIL");
		list.add("ACT_RU_IDENTITYLINK");
		list.add("STUDENTRESUME");
		list.add("WORK");
		list.add("SALES_ORDER");
		list.add("W_TUZI");
		list.add("BPM_AGENT");
		list.add("BPM_APPROVAL_ITEM");
		list.add("BPM_DEFINITION");
		list.add("BPM_DEF_RIGHTS");
		list.add("BPM_DEF_VARS");
		list.add("BPM_EXE_STACK");
		list.add("BPM_FORM_DEF");
		list.add("BPM_FORM_FIELD");
		list.add("BPM_FORM_RIGHTS");
		list.add("BPM_FORM_RULE");
		list.add("BPM_FORM_TABLE");
		list.add("BPM_FORM_TEMPLATE");
		list.add("BPM_NODE_MESSAGE");
		list.add("BPM_NODE_RULE");
		list.add("BPM_NODE_SCRIPT");
		list.add("BPM_NODE_SET");
		list.add("BPM_NODE_SIGN");
		list.add("BPM_NODE_USER");
		list.add("BPM_NODE_USER_UPLOW");
		list.add("BPM_PRO_RUN");
		list.add("BPM_TASK_COMMENT");
		list.add("BPM_TASK_DUE");
		list.add("BPM_TASK_FORK");
		list.add("BPM_TASK_OPINION");
		list.add("BPM_TASK_REMINDERSTATE");
		list.add("BPM_TKSIGN_DATA");
		list.add("OUT_MAIL");
		list.add("W_MYSUPPLIER");
		list.add("OUT_MAIL_LINKMAN");
		list.add("W_MYSUPPLY");
		list.add("OUT_MAIL_USER_SETING");
		list.add("QRTZ_BLOB_TRIGGERS");
		list.add("QRTZ_CALENDARS");
		list.add("QRTZ_CRON_TRIGGERS");
		list.add("QRTZ_FIRED_TRIGGERS");
		list.add("QRTZ_JOB_DETAILS");
		list.add("QRTZ_LOCKS");
		list.add("QRTZ_PAUSED_TRIGGER_GRPS");
		list.add("QRTZ_SCHEDULER_STATE");
		list.add("QRTZ_SIMPLE_TRIGGERS");
		list.add("QRTZ_SIMPROP_TRIGGERS");
		list.add("QRTZ_TRIGGERS");
		list.add("SYS_ACCEPT_IP");
		list.add("SYS_AUDIT");
		list.add("SYS_CALENDAR");
		list.add("SYS_CALENDAR_ASSIGN");
		list.add("SYS_CALENDAR_SETTING");
		list.add("SYS_DATASOURCE");
		list.add("SYS_DEMENSION");
		list.add("SYS_DEP_POS");
		list.add("SYS_DESKTOP_COLUMN");
		list.add("SYS_DESKTOP_LAYOUT");
		list.add("SYS_DESKTOP_LAYOUTCOL");
		list.add("SYS_DESKTOP_MYCOLUMN");
		list.add("SYS_DIC");
		list.add("SYS_FILE");
		list.add("SYS_GL_TYPE");
		list.add("SYS_IDENTITY");
		list.add("SYS_JOBLOG");
		list.add("SYS_MESSAGE");
		list.add("SYS_MSG_READ");
		list.add("SYS_MSG_RECEIVER");
		list.add("SYS_MSG_REPLY");
		list.add("SYS_MSG_SEND");
		list.add("SYS_ORG");
		list.add("SYS_ORG_PARAM");
		list.add("SYS_OVERTIME");
		list.add("SYS_PARAM");
		list.add("SYS_POSITION");
		list.add("SYS_POS_SUB");
		list.add("SYS_PROFILE");
		list.add("SYS_REPORT_TEMPLATE");
		list.add("SYS_RES");
		list.add("SYS_RESURL");
		list.add("SYS_ROLE");
		list.add("SYS_ROLE_POS");
		list.add("SYS_ROLE_RES");
		list.add("SYS_SCRIPT");
		list.add("SYS_SUBSYSTEM");
		list.add("SYS_TEMPLATE");
		list.add("ACT_RU_TASK");
		list.add("SYS_TYPE_KEY");
		list.add("SYS_USER");
		list.add("SYS_USER_AGENT");
		list.add("SYS_USER_ORG");
		list.add("SYS_USER_PARAM");
		list.add("SYS_USER_POS");
		list.add("SYS_USER_ROLE");
		list.add("SYS_VACATION");
		list.add("SYS_WORKTIME");
		list.add("SYS_WORKTIME_SETTING");
		list.add("W_BUS_TRIP");
		list.add("TEACHER");
		list.add("W_BUS_TRIP_DETAIL");
		list.add("BPM_RUN_LOG");
		list.add("W_FUJIAN");
		list.add("W_HUIBAOCAILIAO");
		list.add("W_JIANLI");
		list.add("W_BANJI");
		list.add("W_STUDENT");
		list.add("SYS_OFFICE_TEMPLATE");
		list.add("BPM_TABLE_TEMPRIGHTS");
		list.add("W_CONTRACT");
		list.add("W_SCHOOL");
		list.add("SYS_ROLE_SYS");
		list.add("W_POSITIONAPP");
		list.add("W_REWARDSLIST");
		list.add("W_RESUME");
		list.add("W_WORK");
		list.add("W_DINNERACTIVE");
		list.add("W_KTVACTIVE");
		list.add("W_PERACTIVE");
		list.add("W_USERTABLE");
		list.add("W_JOBTABLE");
		list.add("W_MYRESUME");
		list.add("W_MYAPPLY");
		list.add("W_MYSHOPPING");
		list.add("W_OA");
		list.add("W_QINGJIAAPPLY");
		list.add("W_MYORDER");
		list.add("W_MYTRIP");
		list.add("W_TRIPPERSON");
		list.add("W_MYSCHOOL");
		list.add("W_MYSHOPPINGA");
		list.add("W_MYORDERLIST");
		list.add("ACT_RU_EXECUTION");
		list.add("W_FORMEXAMPLE");
		list.add("W_APPLYLEAVE");
		list.add("W_PRODUCT");
		list.add("ACT_RE_PROCDEF");
		list.add("W_VACTION");
		list.add("W_USERAPPLY");
		list.add("W_USERORGS");
		list.add("BPM_FORM_DIALOG");
		list.add("TEST01");
		list.add("W_AGE");
		list.add("W_MODEL");
		list.add("W_DIANNAO_SHENQING");
		list.add("ACT_RU_JOB");
		list.add("W_MYJIANLI");
		list.add("ACT_GE_PROPERTY");
		list.add("ACT_GE_BYTEARRAY");
		list.add("ACT_RE_DEPLOYMENT");
		list.add("ACT_RU_VARIABLE");
		list.add("ACT_HI_PROCINST");
		list.add("ACT_HI_ACTINST");
		list.add("ACT_HI_TASKINST");
		list.add("ACT_HI_DETAIL");
		list.add("ACT_HI_COMMENT");
		list.add("ACT_HI_ATTACHMENT");
		list.add("TEST");
		list.add("SHIP_DETAIL");
		list.add("W_EMPLOYEE");
		list.add("W_DEALERS");
		list.add("W_OPTION");
		list.add("TESTADD");
		list.add("SYS_SEAL");
		list.add("W_VALID");
		return list;
	}
}
