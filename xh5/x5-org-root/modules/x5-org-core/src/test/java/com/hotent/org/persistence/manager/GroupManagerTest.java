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
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.Attribute.DataType;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.api.model.Attribute.BelongType;
import com.hotent.org.api.model.RelationType.ConstType;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.RelationType.Type;
import com.hotent.org.api.model.UserGroupRelation.Status;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.GroupQuery.FindQuery;
import com.hotent.org.persistence.query.UserQuery;

@Repository
public class GroupManagerTest extends OrgBaseTest {

	@Resource(name = "groupManager")
	GroupManager<String, DefaultGroup> manager;

	@Resource
	AttributeManagerTest attributeManagerTest;

	@Resource
	UserManagerTest userManagerTest;
	@Resource
	AttributeManager attributeManager;

	@Resource
	UserManager userManager;

	@Resource
	RelationTypeManager relationTypeManager;

	@Resource
	RelationTypeManagerTest relationTypeManagerTest;
	@Resource
	DimensionManagerTest dimensionManagerTest;

	
	public DefaultUserGroupRelation newDefaultUserGroupRelation() {
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.usergroup, ConstType.MANY2MANY, 1).get(0);

		DefaultUser user = userManagerTest.createUsers(1).get(0);
		DefaultGroup group = newDefaultGroup();
		group.setDimId(relationType.getRelDimId());
		manager.create(group);
		
		DefaultUserGroupRelation relation = new DefaultUserGroupRelation();
		String id = "" + idGenerator.getUId();

		relation.setId(id);
		relation.setRelTypeId(relationType.getId());
		relation.setStatus(UserGroupRelation.Status.actived);
		relation.setUserId(user.getUserId());
		relation.setGroupId(group.getId());
		relation.setDimId(group.getDimId());
		return relation;
	}
	
	public List<DefaultUserGroupRelation> createDefaultUserGroupRelations(int cnt) {
		List<DefaultUserGroupRelation> relations = new ArrayList<DefaultUserGroupRelation>();
		for (int i = 0; i < cnt; i++) {
			DefaultUserGroupRelation relation = newDefaultUserGroupRelation();
			relations.add(relation);
			manager.createUserGroupRelation(relation);
		}
		return relations;
	}
	
	DefaultGroup newDefaultGroup() {

		List<DefaultGroup> groups = manager.getAll();

		DefaultDimension dimension = dimensionManagerTest.createEntities(1).get(0);

		DefaultGroup group = new DefaultGroup();
		String id = "" + idGenerator.getUId();
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		String dimId = dimension.getDimId();
		com.hotent.org.api.model.Group.Status status = Group.Status.actived;
		// boolean isDeleted = false;
		String parentId = null;
		String path = id + ".";
		long sn = new Double(1000000 * Math.random()).longValue();
		String type = "" + new Double(10 * Math.random()).intValue();

		if (groups.size() == 0) {

			group.setId(id);
			group.setDepth(1L);
			group.setDesc(desc);
			group.setDimId(dimId);
			group.setGroupId(id);
			group.setStatus(status);
			group.setKey(id);
			group.setName(name);
			group.setParentId("0");
			group.setParentId(parentId);
			group.setPath(path);
			group.setSn(sn);
			group.setType(type);
			group.setCreateTime(new Date());
			return group;
		} else {

			DefaultGroup parent = groups.get(new Double((groups.size() - 1) * Math.random()).intValue());
			parentId = parent.getGroupId();
			path = parent.getPath() + id + ".";

			group.setId(id);
			group.setDepth(1L);
			group.setDesc(desc);
			group.setDimId(dimId);
			group.setGroupId(id);
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

	List<DefaultGroup> createGroups(int cnt) {
		List<DefaultGroup> groups = new ArrayList<DefaultGroup>();
		for (int i = 0; i < cnt; i++) {
			DefaultGroup group = newDefaultGroup();
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}

	@Test
	@Rollback(true)
	public void testGetByKey() {
		List<DefaultGroup> newEntities = this.createGroups(5);
		for (DefaultGroup entity : newEntities) {
			DefaultGroup ent = manager.getByKey(entity.getKey());
			assertNotNull(ent);
		}

	}

	@Test
	@Rollback(true)
	public void testDeleteByGroupKey() {
		List<DefaultGroup> news = createGroups(10);
		for (DefaultGroup n : news) {
			// ////validate created users;
			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.deleteByGroupKey(n.getKey());
			// ////validate deleted users;
			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
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
		for (DefaultGroup entity : newEntities) {
			List ent = manager.queryByDimId(entity.getDimId());
			assertNotNull(ent.size() > 0);
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByUserIdStringStatus() {
		DefaultGroup group = newDefaultGroup();
		DefaultUser user = userManagerTest.createUsers(1).get(0);
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.usergroup,ConstType.MANY2MANY,1).get(0);

		group.setDimId(relationType.getRelDimId());
		manager.create(group);
		;

		DefaultUserGroupRelation relation = new DefaultUserGroupRelation();
		relation.setDimId(group.getDimId());
		relation.setGroupId(group.getId());
		relation.setId(idGenerator.getUId() + "");
		relation.setRelTypeId(relationType.getId());
		relation.setStatus(Status.actived);
		relation.setUserId(user.getId());
		manager.createUserGroupRelation(relation);
		{
			List<DefaultGroup> rg = manager.queryByUserId(user.getUserId(), Status.actived);
			assertEquals(1, rg.size());
		}

		{
			List<DefaultGroup> rg = manager.queryByUserId(user.getUserId(), Status.archived);
			assertEquals(0, rg.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByUserIdStringStatusPage() {
		List<DefaultGroup> groups = new ArrayList<DefaultGroup>();
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.usergroup,ConstType.MANY2MANY,1).get(0);
		for (int i = 0; i < 21; i++) {
			DefaultGroup group = newDefaultGroup();

			group.setDimId(relationType.getRelDimId());
			manager.create(group);
			groups.add(group);
		}
		DefaultUser user = userManagerTest.createUsers(1).get(0);
		DefaultUserGroupRelation relation = new DefaultUserGroupRelation();
		for (DefaultGroup group : groups) {
			relation.setDimId(group.getDimId());
			relation.setGroupId(group.getId());
			relation.setId(idGenerator.getUId() + "");
			relation.setRelTypeId(relationType.getId());
			relation.setStatus(Status.actived);
			relation.setUserId(user.getId());
			manager.createUserGroupRelation(relation);
		}
		{
			List<DefaultGroup> rg = manager.queryByUserId(user.getUserId(), Status.actived, new DefaultPage());
			assertEquals(20, rg.size());
		}

		{
			List<DefaultGroup> rg = manager.queryByUserId(user.getUserId(), Status.archived);
			assertEquals(0, rg.size());
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByGroupRelation() {
		List<DefaultGroup> groups = new ArrayList<DefaultGroup>();
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.group,ConstType.MANY2MANY,1).get(0);
		for (int i = 0; i < 3; i++) {
			DefaultGroup group = newDefaultGroup();
			group.setDimId(relationType.getRelDimId());
			manager.create(group);
			groups.add(group);
		}

		DefaultGroup group =  newDefaultGroup();
		group.setDimId(relationType.getCurrentDimId());
		manager.create(group);

		// relationTypeManagerTest.c
		for (DefaultGroup g : groups) {
			DefaultGroupRelation rel = new DefaultGroupRelation();
			rel.setGroupId(group.getGroupId());
			rel.setRelGroupId(g.getGroupId());
			rel.setStatus(GroupRelation.Status.actived);
			rel.setId(idGenerator.getUId() + "");
			rel.setRelTypeId(relationType.getId());
			rel.setCurrentDimId(relationType.getCurrentDimId());
			rel.setRelDimId(relationType.getRelDimId());
			manager.createGroupRelation(rel);
		}
		List<DefaultGroup> rgs = manager.queryByGroupRelation(group.getGroupId(), relationType.getId());
		assertEquals(3, rgs.size());
	}

	@Test
	@Rollback(true)
	public void testQueryByUserRelation() {
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.usergroup,ConstType.MANY2MANY,1).get(0);
		List<DefaultGroup> groups = new ArrayList<DefaultGroup>();
		for (int i = 0; i < 3; i++) {
			DefaultGroup group = newDefaultGroup();
			group.setDimId(relationType.getRelDimId());
			manager.create(group);
			groups.add(group);
		}

		DefaultUser user = userManagerTest.createUsers(1).get(0);

		// relationTypeManagerTest.c
		for (DefaultGroup group : groups) {
			DefaultUserGroupRelation rel = new DefaultUserGroupRelation();
			rel.setUserId(user.getUserId());
			rel.setGroupId(group.getGroupId());
			rel.setStatus(UserGroupRelation.Status.actived);
			rel.setRelId(idGenerator.getUId() + "");
			rel.setRelTypeId(relationType.getId());
			rel.setDimId(relationType.getRelDimId());
			manager.createUserGroupRelation(rel);
		}
		List<DefaultGroup> rgroups = manager.queryByUserGroupRelation(user.getUserId(), relationType.getId());
		assertEquals(3, rgroups.size());
	}

	@Test
	@Rollback(true)
	public void testQueryGroupByAttributeValue() {
		DefaultGroup group = newDefaultGroup();
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.group, ConstType.MANY2MANY, 1).get(0);

		group.setDimId(relationType.getRelDimId());
		manager.create(group);
		DefaultAttribute attribute = attributeManagerTest.createEntities(BelongType.group, DataType.TEXT, 1).get(0);
		DefaultAttributeValue attributeValue = new DefaultAttributeValue();
		attributeValue.setId(attribute.getAttrId());
		attributeValue.setAttrKey(attribute.getAttrKey());
		attributeValue.setDataType(attribute.getDataType());
		Object value = null;
		value = "23w97we9r8ew";
		attributeValue.setTextVal((String) value);
		attributeValue.setAttrId(attribute.getAttrId());
		attributeValue.setGroupId(group.getId());
		attributeValue.setValId(idGenerator.getSuid());
		attributeManager.createAttributeValue(attributeValue);
		manager.queryGroupByAttributeValue(attributeValue.getAttrKey(), value);

	}

	@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultGroup> entities = new ArrayList<DefaultGroup>();
		for (int i = 0; i < 100; i++) {
			DefaultGroup entity = newDefaultGroup();
			entities.add(entity);
			manager.create(entity);
		}

		for (DefaultGroup entity : entities) {
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			query.createCriteria().andGroupIdEqualTo(entity.getGroupId());
			List<DefaultGroup> us = manager.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			query.createCriteria().andGroupIdEqualTo("1");
			manager.queryByCriteria(query);
		}

		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			// query.createUserGroupCriteria().andGroupIdEqualTo("2");
			manager.queryByCriteria(query);
		}

		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			query.createCriteria().andGroupIdEqualTo("3");
			// query.createUserGroupCriteria().andGroupIdEqualTo("3");
			manager.queryByCriteria(query);
		}

		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			query.createCriteria().andGroupIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			manager.queryByCriteria(query);
		}

		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			// query.createUserGroupCriteria().andGroupIdEqualTo("2.1");
			// query.orUserGroupCriteria().andGroupIdEqualTo("2.2");
			manager.queryByCriteria(query);
		}

		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andGroupIdEqualTo("3.1");

			// query.orUserGroupCriteria().andGroupIdEqualTo("3.2");
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
		for (DefaultGroup n : news) {
			// ////validate created users;
			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.remove(n.getGroupId());
			// ////validate deleted users;
			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(Group.Status.deleted, users.get(0).getStatus());
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
			assertEquals(Group.Status.deleted, u.getStatus());
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultGroup> news = createGroups(10);

		// ////validate created users;
		for (DefaultGroup n : news) {
			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId());
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultGroup entity : news) {
			userIds.add(entity.getGroupId());
		}
		manager.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultGroup n : news) {
			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId()).andStatusEqualTo(Group.Status.actived);
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

			{
				GroupQuery.FindQuery query = new GroupQuery.FindQuery();
				query.createCriteria().andGroupIdEqualTo(n.getGroupId()).andStatusEqualTo(Group.Status.deleted);
				List<DefaultGroup> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			List<DefaultGroup> users1 = manager.queryByCriteria(query);

			List<DefaultGroup> news2 = createGroups(10);
			GroupQuery.FindQuery query2 = new GroupQuery.FindQuery();
			List<DefaultGroup> users2 = manager.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultGroup> users1 = (PageList<DefaultGroup>) manager.query(queryFilter);

			List<DefaultGroup> news2 = createGroups(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultGroup> users2 = (PageList<DefaultGroup>) manager.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount() + 10, users2.getPageResult().getTotalCount());

		}
	}

	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = manager.getAll().size();
		createGroups(5);
		long cnt2 = manager.getAll().size();
		assertEquals(cnt2, cnt1 + 5);
	}

	@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createGroups(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultGroup> users = manager.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
