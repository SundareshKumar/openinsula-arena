package org.openinsula.arena.hibernate;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration
public class HibernatePropertiesFactoryBeanTestCase extends AbstractJUnit4SpringContextTests {

	@Autowired
	private MysqlHibernatePropertiesFactoryBean mysql;

	@Autowired
	private DerbyHibernatePropertiesFactoryBean derby;

	@Autowired
	private HSQLHibernatePropertiesFactoryBean hsql;

	@Autowired
	private OracleHibernatePropertiesFactoryBean oracle;

	@Autowired
	private DB2HibernatePropertiesFactoryBean db2;

	private Properties getProps(final AbstractHibernatePropertiesFactoryBean factoryBean) {
		try {
			return (Properties) factoryBean.getObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void testProperty(final Properties props, final String propertyName, final Object expected) {
		assertEquals(expected, props.get(propertyName));
	}

	private void testDefaults(final Properties props) {
		testProperty(props, "hibernate.show_sql", "true");
		testProperty(props, "hibernate.format_sql", "true");
		testProperty(props, "hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheProvider");
		testProperty(props, "hibernate.bytecode.use_reflection_optimizer", "true");
	}

	private void testDialect(final Properties props, final String simpleName) {
		testProperty(props, "hibernate.dialect", "org.hibernate.dialect." + simpleName);
	}

	@Test
	public void testMysqlProperties() {
		Properties props = getProps(mysql);
		testDialect(props, "MySQL5InnoDBDialect");
		testDefaults(props);
	}

	@Test
	public void testDerbyProperties() throws IOException {
		Properties props = getProps(derby);
		testDialect(props, "DerbyDialect");

		testProperty(props, "hibernate.format_sql", "false");
		testProperty(props, "hibernate.max_outer_join", "3");

		testProperty(props, "hibernate.show_sql", "true");
		testProperty(props, "hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheProvider");
		testProperty(props, "hibernate.bytecode.use_reflection_optimizer", "true");
	}

	@Test
	public void testHSQLProperties() {
		Properties props = getProps(hsql);
		testDialect(props, "HSQLDialect");
		testDefaults(props);
	}

	@Test
	public void testOracleProperties() {
		Properties props = getProps(oracle);
		testDialect(props, "Oracle10gDialect");
		testDefaults(props);
	}

	@Test
	public void testDB2Properties() {
		Properties props = getProps(db2);
		testDialect(props, "DB2Dialect");
		testDefaults(props);
	}

}
