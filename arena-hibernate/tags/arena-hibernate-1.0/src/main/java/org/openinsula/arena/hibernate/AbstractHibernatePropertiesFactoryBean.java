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
package org.openinsula.arena.hibernate;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

public abstract class AbstractHibernatePropertiesFactoryBean implements FactoryBean {

	protected abstract String getDialect();

	@Override
	public Object getObject() throws Exception {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", getDialect());
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheProvider");
		properties.put("hibernate.bytecode.use_reflection_optimizer", true);

		return properties;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
