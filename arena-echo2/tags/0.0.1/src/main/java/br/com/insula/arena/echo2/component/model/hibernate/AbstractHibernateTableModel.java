package br.com.insula.arena.echo2.component.model.hibernate;

import nextapp.echo2.app.table.AbstractTableModel;

import org.openinsula.vulcano.orm.dao.hibernate.HibernateDao;
import org.openinsula.vulcano.orm.dao.query.DaoQuery;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public abstract class AbstractHibernateTableModel<T> extends AbstractTableModel implements HibernateTableModel<T>, InitializingBean {
	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	private PlatformTransactionManager transactionManager;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private int currentPage = 0;
	
	private HibernateDao dao;

	private HibernateTableModelTemplate<T> template;
	
	public void afterPropertiesSet() throws Exception {
		template = new HibernateTableModelTemplate<T>();
		template.setDao(dao);
		template.setTransactionManager(transactionManager);
		template.setFetchSize(pageSize);
		template.setInitializeCallback(new InitializeCallback<T>() {
			public void initialize(T obj) {
				for (int i = 0; i < getColumnCount(); i++) {
					getColumnValue(obj, i);
				}
			}
		});
	}
	
	public int getRowCount() {
		int rows = template.getRowCount();
		if (rows == 0) { 
			return 0;
		}
		int mod = rows % pageSize;
		if (rows/pageSize == currentPage && mod > 0) {
			return mod;
		} else {
			return pageSize;
		}
	}
	
	public Object getValueAt(int columnIndex, int rowIndex) {
		T obj = template.getRow(rowIndex + (pageSize * currentPage));
		
		if (obj == null) {
			fireTableDataChanged();
		}
		
		return getColumnValue(obj, columnIndex);
	}
	
	public abstract Object getColumnValue(T obj, int columnIndex);
	
	public T getValue(int index) {
		return template.getRow(index);
	}
	
	public void setDaoQuery(DaoQuery query) {
		template.setDaoQuery(query);
		currentPage = 0;
		fireTableDataChanged();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		int rows = template.getRowCount();
		return rows/pageSize + (rows % pageSize == 0 ? 0 : 1);
	}

	public void setCurrentPage(int index) {
		currentPage = index;
		fireTableDataChanged();
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize < 1) {
			throw new IllegalArgumentException("Valor mínimo permitido de 1!");
		}
		
		this.pageSize = pageSize;
	}
	
	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
