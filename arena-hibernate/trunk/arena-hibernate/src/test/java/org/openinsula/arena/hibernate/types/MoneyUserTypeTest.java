package org.openinsula.arena.hibernate.types;

import static org.junit.Assert.*;

import java.util.Locale;

import org.hibernate.Session;
import org.junit.Test;
import org.openinsula.arena.lang.numbers.Money;
import org.openinsula.arena.lang.util.OtherLocales;

public class MoneyUserTypeTest extends AbstractUserTypeTest {
	
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
	
	@Test
	public void testUpdate() {
		
	}

}
