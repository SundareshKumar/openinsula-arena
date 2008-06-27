package org.openinsula.arena.hibernate.types;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class AnnotatedEnumUserTypeTest extends AbstractUserTypeTestCase {

	@Test
	@Rollback(false)
	public void testInsert() {
		Session session = sessionFactory.getCurrentSession();

		AnnotatedEnumSampleEntity entity = new AnnotatedEnumSampleEntity(UFStandardEnumType.PR, UFStringEnumType.PR, UFIntEnumType.PR);
		session.save(entity);

		int id = entity.getId();
		assertTrue(id > 0);

		session.clear();

		AnnotatedEnumSampleEntity persistentEntity = (AnnotatedEnumSampleEntity) session.get(AnnotatedEnumSampleEntity.class, id);
		assertNotNull(persistentEntity);

		assertEquals(entity.ufStandard, persistentEntity.ufStandard);
		assertEquals(entity.ufString, persistentEntity.ufString);
		assertEquals(entity.ufInt, persistentEntity.ufInt);
	}

	@Test
	@Rollback(false)
	public void testUpdate() {
		Session session = sessionFactory.getCurrentSession();

		AnnotatedEnumSampleEntity entity = new AnnotatedEnumSampleEntity(UFStandardEnumType.PR, UFStringEnumType.PR, UFIntEnumType.PR);
		session.saveOrUpdate(entity);
		session.flush();
		session.clear();

		session.refresh(entity);
		entity.ufStandard = UFStandardEnumType.SC;
		entity.ufString = UFStringEnumType.SC;
		entity.ufInt = UFIntEnumType.SC;
		session.saveOrUpdate(entity);
		session.flush();
		session.clear();

		AnnotatedEnumSampleEntity persistentEntity = (AnnotatedEnumSampleEntity) session.get(AnnotatedEnumSampleEntity.class, entity.getId());
		assertNotNull(persistentEntity);

		assertEquals(entity.ufStandard, persistentEntity.ufStandard);
		assertEquals(entity.ufString, persistentEntity.ufString);
		assertEquals(entity.ufInt, persistentEntity.ufInt);
	}

}
