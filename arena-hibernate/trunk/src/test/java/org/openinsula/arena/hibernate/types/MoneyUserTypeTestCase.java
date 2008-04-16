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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.hibernate.Session;
import org.junit.Test;
import org.openinsula.arena.lang.numbers.Money;
import org.openinsula.arena.lang.util.OtherLocales;

public class MoneyUserTypeTestCase extends AbstractUserTypeTestCase {

	@Test
	public void testInsert() {
		Session session = sessionFactory.getCurrentSession();

		MoneySampleEntity simpleEntity = new MoneySampleEntity();

		simpleEntity.setSalary(new Money(150));
		simpleEntity.setSalaryBrazil(new Money(100).from(OtherLocales.BRAZIL));
		simpleEntity.setSalaryUs(new Money(200).from(Locale.US));

		session.save(simpleEntity);
		session.flush();
		session.clear();

		int id = simpleEntity.getId();
		assertTrue(id > 0);

		MoneySampleEntity persistentSimpleEntity = (MoneySampleEntity) session.get(MoneySampleEntity.class, id);
		assertNotNull(simpleEntity);
		assertEquals(simpleEntity.getSalary(), persistentSimpleEntity.getSalary());
		assertEquals(simpleEntity.getSalaryBrazil(), persistentSimpleEntity.getSalaryBrazil());
		assertEquals(simpleEntity.getSalaryUs(), persistentSimpleEntity.getSalaryUs());
	}

}
