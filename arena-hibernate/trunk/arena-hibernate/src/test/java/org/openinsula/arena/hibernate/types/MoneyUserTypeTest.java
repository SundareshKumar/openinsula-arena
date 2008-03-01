package org.openinsula.arena.hibernate.types;

import static org.junit.Assert.*;

import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.openinsula.arena.lang.numbers.Money;
import org.openinsula.arena.lang.util.OtherLocales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/META-INF/hibernate-context-test.xml" })
public class MoneyUserTypeTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void testCRUD() {
		Session session = sessionFactory.getCurrentSession();

		SimpleEntity simpleEntity = new SimpleEntity();

		simpleEntity.setSalary(new Money(150));
		simpleEntity.setSalaryBrazil(new Money(100).from(OtherLocales.BRAZIL));
		simpleEntity.setSalaryUs(new Money(200).from(Locale.US));

		session.save(simpleEntity);

		int id = simpleEntity.getId();
		assertTrue(id > 0);

		session.clear();

		SimpleEntity persistentSimpleEntity = (SimpleEntity) session.get(SimpleEntity.class, id);
		assertNotNull(simpleEntity);

		assertEquals(simpleEntity.getSalary(), persistentSimpleEntity.getSalary());
		assertEquals(simpleEntity.getSalaryBrazil(), persistentSimpleEntity.getSalaryBrazil());
		assertEquals(simpleEntity.getSalaryUs(), persistentSimpleEntity.getSalaryUs());
	}

}
