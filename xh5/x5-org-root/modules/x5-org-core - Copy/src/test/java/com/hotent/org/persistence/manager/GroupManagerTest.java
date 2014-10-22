package com.hotent.org.persistence.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.User;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.UserQuery;

@Repository
public class GroupManagerTest extends OrgBaseTest{
	
	@Resource
	GroupManager manager;
	
	 DefaultGroup newDefaultGroup(){
		
		List<DefaultGroup> groups = manager.getAll();
		
		DefaultGroup group=new DefaultGroup();
		String id = ""+idGenerator.getUId();
		String name =""+ new Double(1000000*Math.random()).intValue();
		String desc = name;
		String dimKey = id;
		String status = Group.STATUS_ENABLED;
		boolean isDeleted = false;
		String parentId = "0";
		String path = id+".";
		long sn =new Double(1000000*Math.random()).longValue();
		String type = ""+ new Double(10*Math.random()).intValue();
		
		
		if(groups.size()==0){
			
			group.setId(id);
			group.setDepth(1L);
			group.setDesc(desc);
			group.setDimKey(dimKey);
			group.setGroupId(id);
			group.setDeleted(isDeleted);
			group.setKey(id);
			group.setName(name);
			group.setParentId("0");
			group.setParentId(parentId);
			group.setPath(path);
			group.setSn(sn);
			group.setStatus(status);
			group.setType(type);
			group.setCreateTime(new Date());
			return group;
		}else{
			
			DefaultGroup parent = groups.get(new Double((groups.size()-1)*Math.random()).intValue());
			parentId = parent.getGroupId();
			path = parent.getPath() + id+".";
			
			group.setId(id);
			group.setDepth(1L);
			group.setDesc(desc);
			group.setDimKey(dimKey);
			group.setGroupId(id);
			group.setDeleted(isDeleted);
			group.setKey(id);
			group.setName(name);
			group.setParentId("0");
			group.setParentId(parentId);
			group.setPath(path);
			group.setSn(sn);
			group.setStatus(status);
			group.setType(type);
			group.setCreateTime(new Date());
			return group;
		}
		
		
	}
	
	List<DefaultGroup> createGroups(int cnt){
		List<DefaultGroup> groups = new ArrayList<DefaultGroup>();
		for(int i=0;i<cnt;i++){
			DefaultGroup group= newDefaultGroup();
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}
	
	
	
	

	@Test
	@Rollback(true)
	public void testGetByKey() {
		List<DefaultGroup> newEntities = this.createGroups(5);
		for(DefaultGroup entity:newEntities){
			DefaultGroup ent = manager.getByKey(entity.getKey());
			assertNotNull(ent);
		}
		
	}

	@Test
	@Rollback(true)
	public void testDeleteByGroupKey() {
		List<DefaultGroup> news = createGroups(10);
		for(DefaultGroup n:news){
			//////validate created users;
			{
				GroupQuery query = new GroupQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			//////delete created users;
			manager.deleteByGroupKey(n.getKey());
			//////validate deleted users;
			{
				GroupQuery query = new GroupQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}
			
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByDimId() {
		List<DefaultGroup> newEntities = this.createGroups(5);
		for(DefaultGroup entity:newEntities){
			List ent = manager.queryByDimId(entity.getDimKey());
			assertNotNull(ent.size()>0);
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByUserIdStringBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testQueryByUserIdStringBooleanPage() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testQueryByGroupRelation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testQueryByUserRelation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testQueryGroupByAttributeValue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testCreate(){
		List<DefaultGroup> entities = new ArrayList<DefaultGroup>();
		for(int i=0;i<100;i++){
			DefaultGroup entity= newDefaultGroup();
			entities.add(entity);
			manager.create(entity);
		}
		
		for(DefaultGroup entity:entities){
			GroupQuery query = new GroupQuery();
			query.createCriteria().andGroupIdEqualTo(entity.getGroupId());
			List<DefaultGroup> us = manager.queryByCriteria(query,new DefaultPage());
			assertEquals(1, us.size());
		}
		
	}


	
	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			GroupQuery query = new GroupQuery();
			query.createCriteria().andGroupIdEqualTo("1");
			manager.queryByCriteria(query);
		}
		
		{
			GroupQuery query = new GroupQuery();
//			query.createUserGroupCriteria().andGroupIdEqualTo("2");
			manager.queryByCriteria(query);
		}
		
		{
			GroupQuery query = new GroupQuery();
			query.createCriteria().andGroupIdEqualTo("3");
//			query.createUserGroupCriteria().andGroupIdEqualTo("3");
			manager.queryByCriteria(query);
		}
		
		{
			GroupQuery query = new GroupQuery();
			query.createCriteria().andGroupIdEqualTo("1.1");
//			query.or().andFullnameEqualTo("1.2");
			manager.queryByCriteria(query);
		}
		
		{
			GroupQuery query = new GroupQuery();
//			query.createUserGroupCriteria().andGroupIdEqualTo("2.1");
//			query.orUserGroupCriteria().andGroupIdEqualTo("2.2");
			manager.queryByCriteria(query);
		}
		
		{
			GroupQuery query = new GroupQuery();
//			query.createCriteria().andAccountEqualTo("3.1");
			
//			query.createUserGroupCriteria().andGroupIdEqualTo("3.1");
			
//			query.orUserGroupCriteria().andGroupIdEqualTo("3.2");
			manager.queryByCriteria(query);
		}
	}


	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultGroup newu = (DefaultGroup) createGroups(1).get(0);
		DefaultGroup newu2 = newDefaultGroup();
		
		
		newu2.setGroupId(newu.getGroupId());
		manager.update(newu2);
		
		DefaultGroup u = (DefaultGroup) manager.get(newu.getGroupId());
		
		String newu2_str = ToStringBuilder.reflectionToString(newu2);
		newu2_str = newu2_str.substring(newu2_str.indexOf("["));
		String u_str = ToStringBuilder.reflectionToString(u);
		u_str = u_str.substring(u_str.indexOf("["));
		assertTrue(newu2_str.equals(u_str));
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultGroup> news = createGroups(10);
		for(DefaultGroup n:news){
			//////validate created users;
			{
				GroupQuery query = new GroupQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			//////delete created users;
			manager.remove(n.getGroupId());
			//////validate deleted users;
			{
				GroupQuery query = new GroupQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}
			
		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultGroup> news = createGroups(1);
		{
			DefaultGroup u = (DefaultGroup) manager.get(news.get(0).getGroupId());
			assertNotNull(u);
		}
		
		{
			manager.remove(news.get(0).getGroupId());
		}
		
		{
			DefaultGroup u = (DefaultGroup) manager.get(news.get(0).getGroupId());
			assertNull(u);
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultGroup> news = createGroups(10);
		
		//////validate created users;
		for(DefaultGroup n:news){
			{
				GroupQuery query = new GroupQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
	
			
		}
		
		//////delete created users;
		List<String> userIds = new ArrayList<String>();
		for(DefaultGroup entity:news){
			userIds.add(entity.getGroupId());
		}
		manager.removeByIds(userIds.toArray(new String[0]));
		
		//////validate deleted users;
		for(DefaultGroup n:news){
			{
				GroupQuery query = new GroupQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}
			
		}
		
	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			GroupQuery query = new GroupQuery();
			List<DefaultGroup> users1 = manager.queryByCriteria(query);
			
			
			List<DefaultGroup> news2 = createGroups(10);
			GroupQuery query2 = new GroupQuery();
			List<DefaultGroup> users2 = manager.queryByCriteria(query2);
			assertEquals(users1.size()+10, users2.size());
		}
		//no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultGroup> users1 = (PageList<DefaultGroup>) manager.query(queryFilter);
			
			List<DefaultGroup> news2 = createGroups(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultGroup> users2 = (PageList<DefaultGroup>) manager.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount()+10, users2.getPageResult().getTotalCount());
			
		}
	}

	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = manager.getAll().size();
		createGroups(5);
		long cnt2 = manager.getAll().size();
		assertEquals(cnt2,cnt1+5);
	}

	@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createGroups(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultGroup> users = manager.getAllByPage(page);
		assertEquals(10,users.size());
	}

}
