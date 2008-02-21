package org.openinsula.arena.echo2.component.model.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.LRUMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.vulcano.orm.dao.hibernate.HibernateDao;
import org.openinsula.vulcano.orm.dao.query.DaoQuery;
import org.openinsula.vulcano.orm.dao.query.builder.DaoQueryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


@SuppressWarnings("unchecked")
public class HibernateTableModelTemplate<T> {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private int fetchSize = 50;
	
	private Map<Integer, T> cachedValueMap = new LRUMap(fetchSize * 3);
	
	private int rowCount = -1;
	
	private PlatformTransactionManager transactionManager;

	private InitializeCallback<T> initializeCallback = new InitializeCallback<T>() {
		public void initialize(T obj) {
		}
	};

	private HibernateDao dao;
	
	private DaoQuery daoQuery;
	
	protected TransactionTemplate createTransactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		transactionTemplate.setReadOnly(true);
		
		return transactionTemplate;
	}
	
	public int getRowCount() {
		if (daoQuery == null) {
			rowCount = 0;
		} else if (rowCount == -1) {
			if (logger.isDebugEnabled()) {
				logger.debug("Executing count(*) to get the result size.");
			}
			
			TransactionTemplate transactionTemplate = createTransactionTemplate();

			rowCount = ((Number)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					DaoQuery countDaoQuery = new DaoQueryBuilder().select().count()
					.from(daoQuery.getQueryClass())
					.where(daoQuery.getSpec()).getDaoQuery();
					
					return dao.findUnique(countDaoQuery);
				}
			})).intValue();
		}
		
		return rowCount;
	}
	
	protected void fetchValues(int index) {
		final int offset = calculateOffset(index);
		
		TransactionTemplate transactionTemplate = createTransactionTemplate();
		transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				
				daoQuery.setLimit(fetchSize);
				daoQuery.setFirstResult(offset);
				
				List<? extends T> fetchedValues = 
					dao.find(daoQuery);

				for (int i = 0; i < fetchedValues.size(); i++) {
					T value = fetchedValues.get(i);
					cachedValueMap.put(i + offset, value);
					initializeCallback.initialize(value);
				}
				return null;
			}
		});
	}

	protected int calculateOffset(int index) {
		int offset = index - fetchSize/2;
		if (offset < 0) {
			offset = 0;
		}
		
		return offset;
	}

	public T getRow(int rowIndex) {
		T row = null;
		if (rowIndex < cachedValueMap.size()) {
			row = (T) cachedValueMap.get(rowIndex);
		}

		if (row == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Row " + rowIndex + " not found.");
			}
			fetchValues(rowIndex);
			row = (T) cachedValueMap.get(rowIndex); 
		}
		
		return row;
	}
	
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
		((LRUMap)cachedValueMap).setMaximumSize(fetchSize * 3);
	}
	
	public void setDaoQuery(DaoQuery daoQuery) {
		this.daoQuery = daoQuery;
		this.rowCount = -1;
		cachedValueMap.clear();
		
		Runtime.getRuntime().gc();
	}

	public void setTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.transactionManager = platformTransactionManager;
	}

	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}

	public void setInitializeCallback(InitializeCallback<T> initializeCallback) {
		this.initializeCallback = initializeCallback;
	}

}
