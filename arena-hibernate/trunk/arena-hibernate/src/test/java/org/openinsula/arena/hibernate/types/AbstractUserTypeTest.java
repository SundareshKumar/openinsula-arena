package org.openinsula.arena.hibernate.types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/META-INF/hibernate-context-test.xml" })
public abstract class AbstractUserTypeTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	protected SessionFactory sessionFactory;

}
