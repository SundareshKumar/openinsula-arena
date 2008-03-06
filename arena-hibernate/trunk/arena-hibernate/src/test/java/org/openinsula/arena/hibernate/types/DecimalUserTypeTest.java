package org.openinsula.arena.hibernate.types;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.exception.DataException;
import org.junit.Test;
import org.openinsula.arena.lang.numbers.Decimal;

public class DecimalUserTypeTest extends AbstractUserTypeTest {

	@Test
	public void testInsert() {
		Session session = sessionFactory.getCurrentSession();

		DecimalSampleEntity entity = new DecimalSampleEntity();
		entity.decimal1_3 = new Decimal(1.123);
		entity.decimal2_2 = new Decimal(12.34);
		entity.decimal4_0 = new Decimal(1234);

		session.save(entity);

		int id = entity.getId();
		assertTrue(id > 0);

		session.clear();

		DecimalSampleEntity persistentEntity = (DecimalSampleEntity) session.get(DecimalSampleEntity.class, id);
		assertNotNull(persistentEntity);

		assertEquals(entity.decimal1_3, persistentEntity.decimal1_3);
		assertEquals(entity.decimal2_2, persistentEntity.decimal2_2);
		assertEquals(entity.decimal4_0, persistentEntity.decimal4_0);
	}

	@Test
	public void testInsertRounding() {
		Session session = sessionFactory.getCurrentSession();

		DecimalSampleEntity entity = new DecimalSampleEntity();
		entity.decimal1_3 = new Decimal(1.1231);
		entity.decimal2_2 = new Decimal(12.346);

		session.save(entity);

		int id = entity.getId();
		assertTrue(id > 0);

		session.clear();

		DecimalSampleEntity persistentEntity = (DecimalSampleEntity) session.get(DecimalSampleEntity.class, id);
		assertNotNull(persistentEntity);

		assertEquals(new Decimal(1.123), persistentEntity.decimal1_3);
		assertEquals(new Decimal(12.35), persistentEntity.decimal2_2);
		assertNull(persistentEntity.decimal4_0);
	}

	@Test
	public void testInsertOverflow() {
		Session session = sessionFactory.getCurrentSession();

		DecimalSampleEntity entity = new DecimalSampleEntity();
		entity.decimal1_3 = new Decimal(9.9999);

		try {
			session.save(entity);
			fail("Overflow expected!");

		}
		catch (DataException exc) {
			exc.printStackTrace();

		}
		catch (RuntimeException otherException) {
			fail("Overflow expected!");
		}

	}

	@Test
	public void testUpdate() {
		Session session = sessionFactory.getCurrentSession();
		
		DecimalSampleEntity entity = new DecimalSampleEntity();
		entity.decimal1_3 = new Decimal(1.123);
		session.saveOrUpdate(entity);
		session.flush();
		session.clear();
		
		session.refresh(entity);
		entity.decimal1_3.add(.877);
		session.saveOrUpdate(entity);
		session.flush();
		session.clear();
		
		DecimalSampleEntity persistentEntity = (DecimalSampleEntity) session.get(DecimalSampleEntity.class, entity.getId());
		assertEquals(entity.decimal1_3, persistentEntity.decimal1_3);
	}

}
