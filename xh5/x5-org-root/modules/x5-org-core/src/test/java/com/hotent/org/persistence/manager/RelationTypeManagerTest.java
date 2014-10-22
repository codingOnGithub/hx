package com.hotent.org.persistence.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.RelationType.ConstType;
import com.hotent.org.api.model.RelationType.IsBidirection;
import com.hotent.org.api.model.RelationType.Status;
import com.hotent.org.api.model.RelationType.Type;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.query.RelationTypeQuery;
import com.hotent.org.persistence.query.DimensionRelationQuery;
import com.hotent.org.persistence.query.GroupQuery;

@Repository
public class RelationTypeManagerTest extends OrgBaseTest {
	@Resource
	RelationTypeManager manager;

	@Resource
	DimensionManagerTest dimensionManagerTest;

	@Resource
	UserManagerTest userManagerTest;

	@Resource
	GroupManagerTest groupManagerTest;

	@Resource(name = "groupManager")
	GroupManager<String, DefaultGroup> groupManager;
	@Resource
	UserManager userManager;

	@Test
	@Rollback(true)
	public void testQueryByUserRelation() {
		DefaultUser user1 = userManagerTest.createUsers(1).get(0);
		DefaultUser user2 = userManagerTest.createUsers(1).get(0);

		DefaultRelationType relationType = createEntities(Type.user,ConstType.MANY2MANY,1).get(0);

		DefaultUserRelation relation = new DefaultUserRelation();
		relation.setUserId(user1.getId());
		relation.setRelUserId(user2.getId());
		relation.setRelTypeId(relationType.getId());
		relation.setId(idGenerator.getUId() + "");
		relation.setStatus(com.hotent.org.api.model.UserRelation.Status.actived);

		userManager.createUserRelation(relation);


		DefaultRelationType relationType2 = manager.queryByUserRelation(relation.getId());
		assertNotNull(relationType2);
	}

	@Test
	@Rollback(true)
	public void testQueryByGroupRelation() {
		DefaultGroup group1 = groupManagerTest.newDefaultGroup();
		DefaultGroup group2 = groupManagerTest.newDefaultGroup();
		
		DefaultRelationType relationType = createEntities(Type.group,ConstType.MANY2ONE,1).get(0);
		
		group1.setDimId(relationType.getCurrentDimId());
		groupManager.create(group1);
		
		group2.setDimId(relationType.getRelDimId());
		groupManager.create(group2);

		DefaultGroupRelation relation = new DefaultGroupRelation();
		relation.setGroupId(group1.getId());
		relation.setRelGroupId(group2.getGroupId());
		relation.setRelTypeId(relationType.getId());
		relation.setId(idGenerator.getUId() + "");
		relation.setCurrentDimId(group1.getDimId());
		relation.setRelDimId(group2.getDimId());
		relation.setStatus(GroupRelation.Status.actived);

		groupManager.createGroupRelation(relation);


		DefaultRelationType relationType2 = manager.queryByGroupRelation(relation.getId());
		assertNotNull(relationType2);
	}

	@Test
	@Rollback(true)
	public void testQueryByUserGroupRelation() {
		DefaultUser user = userManagerTest.createUsers(1).get(0);
		DefaultGroup group = groupManagerTest.newDefaultGroup();
		DefaultRelationType relationType = createEntities(Type.usergroup,ConstType.MANY2ONE,1).get(0);
		group.setDimId(relationType.getRelDimId());
		groupManager.create(group);

		DefaultUserGroupRelation relation = new DefaultUserGroupRelation();
		relation.setUserId(user.getId());
		relation.setGroupId(group.getGroupId());
		relation.setRelTypeId(relationType.getId());
		relation.setId(idGenerator.getUId() + "");
		relation.setStatus(UserGroupRelation.Status.actived);
		relation.setDimId(group.getDimId());

		groupManager.createUserGroupRelation(relation);


		DefaultRelationType relationType2 = manager.queryByUserGroupRelation(relation.getId());
		assertNotNull(relationType2);
	}

	@Test
	@Rollback(true)
	public void testQueryByCriteriaFindQuery() {
		List<DefaultRelationType> relationTypes = createEntities(21);
		List<String> ids = new ArrayList<String>();
		for (DefaultRelationType t : relationTypes) {
			ids.add(t.getId());
		}

		RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
		query.createCriteria().andIdIn(ids);
		List<DefaultRelationType> types = manager.queryByCriteria(query);
		assertEquals(relationTypes.size(), types.size());
	}

	@Test
	@Rollback(true)
	public void testQueryByCriteriaFindQueryPage() {
		List<DefaultRelationType> relationTypes = createEntities(21);
		RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
		query.createCriteria().andIdNotBetween("1", "1");
		List<DefaultRelationType> types = manager.queryByCriteria(query,new DefaultPage());
		assertEquals(20, types.size());
	}

	@Test
	@Rollback(true)
	public void testQueryCountByCriteria() {
		List<DefaultRelationType> relationTypes = createEntities(21);
		RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
		query.createCriteria().andIdNotBetween("1", "1");
		long cnt = manager.queryCountByCriteria(query);
		assertTrue(cnt>=21);
	}

	@Test
	@Rollback(true)
	public void testUpdateByUpdateQuery() {
		List<DefaultRelationType> relationTypes = createEntities(10);
		for (DefaultRelationType t : relationTypes) {
			RelationTypeQuery.UpdateQuery uQuery = new RelationTypeQuery.UpdateQuery();
			uQuery.createCriteria().andIdEqualTo(t.getId());
			uQuery.getUpdateClause().setConstType(ConstType.ONE2ONE);

			manager.updateByUpdateQuery(uQuery);

			DefaultRelationType r = manager.get(t.getId());

			assertEquals(ConstType.ONE2ONE, r.getConstType());
		}

	}

	@Test
	@Rollback(true)
	public void testDeleteByDeleteQuery() {
		List<DefaultRelationType> relationTypes = createEntities(10);
		for (DefaultRelationType t : relationTypes) {
			{
				DefaultRelationType r = manager.get(t.getId());
				assertNotNull(r);
			}

			RelationTypeQuery.DeleteQuery dQuery = new RelationTypeQuery.DeleteQuery();
			dQuery.createCriteria().andIdEqualTo(t.getId());
			manager.deleteByDeleteQuery(dQuery);

			{
				DefaultRelationType r = manager.get(t.getId());
				assertNull(r);
			}
		}

	}

	@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultRelationType> relationTypes = createEntities(3);
		for (DefaultRelationType type : relationTypes) {
			DefaultRelationType r = manager.get(type.getId());
			assertNotNull(r);
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {

	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultRelationType newu = (DefaultRelationType) createEntities(1).get(0);
		DefaultRelationType newu2 = createEntities(1).get(0);

		newu2.setId(newu.getId());
		manager.update(newu2);

		DefaultRelationType u = (DefaultRelationType) manager.get(newu.getId());
		{
			newu2.setUpdateBy(null);
			newu2.setUpdateTime(null);

			u.setUpdateBy(null);
			u.setUpdateTime(null);
		}

		String newu2_str = ToStringBuilder.reflectionToString(newu2, ToStringStyle.SHORT_PREFIX_STYLE);
		String u_str = ToStringBuilder.reflectionToString(u, ToStringStyle.SHORT_PREFIX_STYLE);
		System.out.println(newu2_str);
		System.out.println(u_str);
		assertEquals(newu2_str, u_str);
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultRelationType> news = createEntities(10);
		for (DefaultRelationType n : news) {
			// ////validate created users;
			{
				RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultRelationType> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.remove(n.getId());
			// ////validate deleted users;
			{
				RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
				query.createCriteria().andIdEqualTo(n.getId()).andStatusEqualTo(RelationType.Status.deleted);
				List<DefaultRelationType> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultRelationType> news = createEntities(1);
		{
			DefaultRelationType u = (DefaultRelationType) manager.get(news.get(0).getId());
			assertNotNull(u);
		}

		{
			manager.remove(news.get(0).getId());
		}

		{
			DefaultRelationType u = (DefaultRelationType) manager.get(news.get(0).getId());
			assertEquals(Status.deleted, u.getStatus());
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultRelationType> news = createEntities(10);

		// ////validate created users;
		for (DefaultRelationType n : news) {
			{
				RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultRelationType> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultRelationType entity : news) {
			userIds.add(entity.getId());
		}
		manager.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultRelationType n : news) {
			{
				RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
				query.createCriteria().andIdEqualTo(n.getId()).andStatusEqualTo(RelationType.Status.actived);
				List<DefaultRelationType> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

			{
				RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
				query.createCriteria().andIdEqualTo(n.getId()).andStatusEqualTo(RelationType.Status.deleted);
				List<DefaultRelationType> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
			List<DefaultRelationType> users1 = manager.queryByCriteria(query);

			List<DefaultRelationType> news2 = createEntities(10);
			RelationTypeQuery.FindQuery query2 = new RelationTypeQuery.FindQuery();
			List<DefaultRelationType> users2 = manager.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultRelationType> users1 = (PageList<DefaultRelationType>) manager.query(queryFilter);

			List<DefaultRelationType> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultRelationType> users2 = (PageList<DefaultRelationType>) manager.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount() + 10, users2.getPageResult().getTotalCount());

		}
	}

	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = manager.getAll().size();
		createEntities(5);
		long cnt2 = manager.getAll().size();
		assertEquals(cnt2, cnt1 + 5);
	}

	@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createEntities(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultRelationType> users = manager.getAllByPage(page);
		assertEquals(10, users.size());
	}

	DefaultRelationType newEntity() {
		List<DefaultDimension> dimensions = dimensionManagerTest.createEntities(2);

		DefaultRelationType entity = new DefaultRelationType();
		String id = "" + idGenerator.getUId();
		String key = id;
		Type type = Type.group;
		IsBidirection isBidirectonal = IsBidirection.Y;

		String currentDimId = dimensions.get(0).getId();
		String currentName = dimensions.get(0).getName();
		String relDimId = dimensions.get(1).getId();
		String relName = dimensions.get(1).getName();
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		Status status = Status.actived;
		ConstType constType = ConstType.MANY2MANY;

		entity.setId(id);
		entity.setCurrentDimId(currentDimId);
		entity.setCurrentName(currentName);
		entity.setRelDimId(relDimId);
		entity.setRelName(relName);
		entity.setIsBidirectonal(isBidirectonal);
		entity.setKey(key);
		entity.setType(type);
		entity.setMemo(desc);
		entity.setConstType(constType);
		entity.setName(name);
		entity.setStatus(status);

		return entity;
	}
	
	DefaultRelationType newEntity(Type type,ConstType constType) {
		List<DefaultDimension> dimensions = dimensionManagerTest.createEntities(2);

		DefaultRelationType entity = new DefaultRelationType();
		String id = "" + idGenerator.getUId();
		String key = id;
		IsBidirection isBidirectonal = IsBidirection.Y;

		String currentDimId = dimensions.get(0).getId();
		String currentName = dimensions.get(0).getName();
		String relDimId = dimensions.get(1).getId();
		String relName = dimensions.get(1).getName();
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		Status status = Status.actived;

		entity.setId(id);
		entity.setCurrentDimId(currentDimId);
		entity.setCurrentName(currentName);
		entity.setRelDimId(relDimId);
		entity.setRelName(relName);
		entity.setIsBidirectonal(isBidirectonal);
		entity.setKey(key);
		entity.setType(type);
		entity.setMemo(desc);
		entity.setConstType(constType);
		entity.setName(name);
		entity.setStatus(status);

		return entity;
	}

	List<DefaultRelationType> createEntities(Type type,ConstType constType,int cnt) {
		List<DefaultRelationType> groups = new ArrayList<DefaultRelationType>();
		for (int i = 0; i < cnt; i++) {
			DefaultRelationType group = newEntity(type, constType);
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}
	List<DefaultRelationType> createEntities(int cnt) {
		List<DefaultRelationType> groups = new ArrayList<DefaultRelationType>();
		for (int i = 0; i < cnt; i++) {
			DefaultRelationType group = newEntity();
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}
}
