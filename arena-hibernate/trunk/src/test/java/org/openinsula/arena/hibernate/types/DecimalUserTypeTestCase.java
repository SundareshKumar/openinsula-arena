/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena Hibernate.
 *
 *  Arena Hibernate is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena Hibernate is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena Hibernate.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.hibernate.types;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.exception.DataException;
import org.junit.Test;
import org.openinsula.arena.lang.numbers.Decimal;

public class DecimalUserTypeTestCase extends AbstractUserTypeTestCase {

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

		DecimalSampleEntity persistentEntity = (DecimalSampleEntity) session.get(DecimalSampleEntity.class, entity
				.getId());
		assertEquals(entity.decimal1_3, persistentEntity.decimal1_3);
	}

}
