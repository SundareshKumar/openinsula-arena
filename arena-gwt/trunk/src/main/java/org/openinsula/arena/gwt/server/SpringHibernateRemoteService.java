package org.openinsula.arena.gwt.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Extension of {@link HibernateRemoteService} that exposes Spring {@link WebApplicationContext}
 * to its subclasses and automatically configures {@link HibernateBeanManager} (using Spring).
 *
 * @author Eduardo Rebola
 */
public abstract class SpringHibernateRemoteService extends HibernateRemoteService {

	/**
	 * Default bean name for {@link HibernateBeanManager} instance ('hibernateBeanManager').
	 */
	public static final String HIBERNATE_BEAN_MANAGER = "hibernateBeanManager";

	private String hibernateBeanManagerBeanName = HIBERNATE_BEAN_MANAGER;

	protected WebApplicationContext applicationContext;

	public SpringHibernateRemoteService() {
		super();
	}

	/**
	 * Bean name for {@link HibernateBeanManager}. Default is 'hibernateBeanManager'
	 * @param hibernateBeanManagerBeanName
	 */
	public void setHibernateBeanManagerBeanName(final String hibernateBeanManagerBeanName) {
		this.hibernateBeanManagerBeanName = hibernateBeanManagerBeanName;
	}

	@Override
	public void init(final ServletConfig config) throws ServletException {
		super.init(config);

		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());

		setBeanManager((HibernateBeanManager) applicationContext.getBean(hibernateBeanManagerBeanName));
	}

}
