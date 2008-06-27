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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;

public abstract class AbstractHibernatePropertiesFactoryBean extends PropertiesFactoryBean {

	private boolean enversEnabled = false;

	@Override
	protected Object createInstance() throws IOException {
		createDefaultProperties();
		setLocalOverride(true);

		return super.createInstance();
	}

	private void createDefaultProperties() throws IOException {
		Properties defaultProperties = getDefaultProperties();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(defaultProperties.size() * 30);

		defaultProperties.store(baos, null);

		ByteArrayResource resource = new ByteArrayResource(baos.toByteArray()) {

			// Spring always call this method. Since the default implementations always throws the exception,
			// it's necessary to override and ignore it. We won't use the filename anyway.
			@Override
			public String getFilename() throws IllegalStateException {
				return "";
			}

		};

		setLocation(resource);
	}

	protected Properties getDefaultProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", getDialect());
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheProvider");
		properties.put("hibernate.bytecode.use_reflection_optimizer", "true");

		if (enversEnabled) {
			final String enversListener =  "org.jboss.envers.event.VersionsEventListener";

			properties.put("hibernate.ejb.event.post-insert", enversListener);
			properties.put("hibernate.ejb.event.post-update", enversListener);
			properties.put("hibernate.ejb.event.post-delete", enversListener);
		}

		return properties;
	}

	public void setEnversEnabled(final boolean enable) {
		this.enversEnabled = enable;
	}

	protected abstract String getDialect();

}
